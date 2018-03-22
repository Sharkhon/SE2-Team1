import os
from threading import Thread

import zmq, binascii
from encodings.base64_codec import base64_encode

CHUNK_SIZE = 250000
PIPELINE = 10

def client_thread(ctx, pipe):
    dealer = ctx.socket(zmq.DEALER)
    socket_set_hwm(dealer, PIPELINE)
    dealer.connect("tcp://127.0.0.1:6000")

    credit = PIPELINE   # Up to PIPELINE chunks in transit

    total = 0           # Total bytes received
    chunks = 0          # Total chunks received
    offset = 0          # Offset of next chunk request

    while True:
        while credit:
            # ask for next chunk
            dealer.send_multipart([
                b"fetch", #make this user,budjetName
                b"%i" % total,
                b"%i" % CHUNK_SIZE,
                b"", #username
                b"" #budgetName
            ])

            offset += CHUNK_SIZE
            credit -= 1

        try:
            chunk = dealer.recv()
            print(chunk)
        except zmq.ZMQError as e:
            if e.errno == zmq.ETERM:
                return   # shutting down, quit
            else:
                raise

        chunks += 1
        credit += 1
        size = len(chunk)
        total += size
        if size < CHUNK_SIZE:
            break   # Last chunk received; exit

    print ("%i chunks received, %i bytes" % (chunks, total))
    pipe.send(b"OK")

# The rest of the code is exactly the same as in model 2, except
# that we set the HWM on the server's ROUTER socket to PIPELINE
# to act as a sanity check.

def server_thread(ctx):
    #file = open("testFile.txt", "rb")

    router = ctx.socket(zmq.ROUTER)
    socket_set_hwm(router, PIPELINE)
    router.bind("tcp://*:6000")

    while True:
        # First frame in each message is the sender identity
        # Second frame is "fetch" command
        try:
            msg = router.recv_multipart()
        except zmq.ZMQError as e:
            if e.errno == zmq.ETERM:
                print("closing")
                return   # shutting down, quit
            else:
                raise

        identity, command, offset_str, chunksz_str, username, budgetName = msg

        #assert command == b"fetch"
        data = b""
        
        command = command.decode("UTF-8")
        if(command == "fetch"):
            offset = int(offset_str)
            chunksz = int(chunksz_str)
    
            # Read chunk of data from file
            with open("./server/testFile.txt", "rb") as file:
                file.seek(offset, os.SEEK_SET)
                data = file.read(chunksz)
                
        if(command.__contains__("write")):
            
            with open("./server/data/" + username + '/' + command[2], "w") as file:
                file.append()
                
                print('success')
                
            #data = str.encode(data)
    
            # Send resulting chunk to client
        router.send_multipart([identity, data])


def zpipe(ctx):
    """build inproc pipe for talking to threads
    mimic pipe used in czmq zthread_fork.
    Returns a pair of PAIRs connected via inproc
    """
    a = ctx.socket(zmq.PAIR)
    b = ctx.socket(zmq.PAIR)
    a.linger = b.linger = 0
    a.hwm = b.hwm = 1
    iface = "inproc://%s" % binascii.hexlify(os.urandom(8))
    a.bind(iface)
    b.connect(iface)
    return a,b

def socket_set_hwm(socket, hwm=-1):
    """libzmq 2/3/4 compatible sethwm"""
    try:
        socket.sndhwm = socket.rcvhwm = hwm
    except AttributeError:
        socket.hwm = hwm

# The main task is just the same as in the first model.

def main():

    # Start child threads
    ctx = zmq.Context()
    a,b = zpipe(ctx)

    client = Thread(target=client_thread, args=(ctx, b))
    server = Thread(target=server_thread, args=(ctx,))
    client.start()
    server.start()

    # loop until client tells us it's done
    try:
        print (a.recv())
    except KeyboardInterrupt:
        pass
    del a,b
    ctx.term()

if __name__ == '__main__':
    main()