import sys, zmq
from threading import Thread

ctx = None
a = None
b = None

def getBudget(username, budgetName):
    pass

def writeToBudget(username, budgetName, filePath):
    pass

def checkUser(username, password):
    multipart = [b'checkUser', username.encode(), password.encode()]
    
    client = Thread(target=client_thread(ctx, pipe, multiPart))
    
    client.start()

def newUser(username, password):
    pass

def client_thread(ctx, pipe, multiPart):
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
                multiPart
                #b"fetch", #command
                #b"%i" % total,
                #b"%i" % CHUNK_SIZE,
                #b"", #username
                #b"" #budgetName/password
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

def run():
    #if(len(sys.argv) == 1):
    #    print('invalid command')
    #    return
    
    global ctx
    global a
    global b
    
    ctx = zmq.Context()
    a,b = zpipe(ctx)
    
    if(sys.argv[1] == "checkUser"):
        checkUser(sys.argv[2], sys.argv[3])
    if(sys.argv[1] == "newUser"):
        newUser(sys.argv[2], sys.argv[3])
    if(sys.argv[1] == "getBudget"):
        getBudget(sys.argv[2], sys.argv[3])
    if(sys.argv[1] == 'saveBudget'):
        writeToBudget(sys.argv[2], sys.argv[3])
        

if __name__ == '__main__':
    run()