import os

def pullFunctions(inputGiven):
    if('files' in inputGiven[0]):
        return pullAllFiles(inputGiven[1])
    else:
        return pullBudget(inputGiven[1], inputGiven[2])

def pullBudget(username, budgetname):
    data = ""
    with open(os.path.join(os.getcwd(), "data", username, budgetname + ".txt")) as f:
        data = f.read()
    if(len(data) < 1):
        data = "File does not exsist"
    return data

def pullAllFiles(username):
    filelistStr = ""
    fileList = os.listdir(os.path.join(os.getcwd(), "data", username))
    print(fileList)
    for file in fileList:
        filelistStr += os.path.splitext(file)[0]
    print(filelistStr)
    return filelistStr