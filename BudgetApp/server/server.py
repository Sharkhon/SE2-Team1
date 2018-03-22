import zmq

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
            with open("testFile.txt", 'rb') as file: #"./server/testFile.txt", "rb") as file:
                file.seek(offset, os.SEEK_SET)
                data = file.read(chunksz)
                
        if(command.__contains__("write")):
            
            with open("./server/data/" + username + '/' + command[2], "w") as file:
                file.append()
                
                print('success')
                
            #data = str.encode(data)
    
            # Send resulting chunk to client
        router.send_multipart([identity, data])