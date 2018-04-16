import os

def userFunctions(inputGiven):
    if(inputGiven[0] == "new user"):
        return newUser(inputGiven[1], inputGiven[2])
    if(inputGiven[0] == "login user"):
        return checkLogin(inputGiven[1], inputGiven[2])

    return 'Invalid User Request'

def newUser(userName, password):
    if(userExsists(userName) == False):
        with open(os.path.join(os.getcwd(), "data", "users.txt"), 'a+') as f:
            f.write(userName + "," + password + "\n")
            os.mkdir(os.path.join(os.getcwd(), "data", userName))
            return True
    return False

def checkLogin(userName, password):
    with open(os.path.join(os.getcwd(), "data", "users.txt"), 'r') as f:
        for line in f.readlines():
            splitstr = line.strip().split(",")
            if(splitstr[0] == userName and splitstr[1] == password):
                return True
    return False

def userExsists(userName):
    with open(os.path.join(os.getcwd(), "data", "users.txt"), 'r') as f:
        for line in f.readlines():
            splitstr = line.strip().split(",")
            if(splitstr[0] == userName):
                return True
    return False