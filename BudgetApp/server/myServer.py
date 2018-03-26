import socket, os
from threading import Thread
from userFunctions import userFunctions
from pushFunctions import pushFunctions

def client_thread(connection, ip, port):
    inputGivenInBytes = connection.recv(4096)

    inputGiven = inputGivenInBytes.decode("utf-8").rstrip()
    inputGiven = inputGiven.split(",")
    
    print(inputGiven)

    response = ""
    if('user' in inputGiven[0]):
        response = str(userFunctions(inputGiven))
    if('push' in inputGiven[0]):
        response = str(pushFunctions(inputGiven))

    print(response)
    connection.send(response.encode("utf-8"))
    connection.close()

serverSock = socket.socket()

serverSock.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)

serverSock.bind(("127.0.0.1", 6000))

serverSock.listen(10)
print("listen")

while True:
    conn, addr = serverSock.accept()
    ip, port = str(addr[0]), str(addr[1])
    print('Connected')
    Thread(target=client_thread, args=(conn,addr, 6000)).start() 

serverSock.close()
