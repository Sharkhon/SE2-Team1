import os

def pullFunctions(inputGiven):
    if('files' in inputGiven[0]):
        return pullAllFiles(inputGiven[1])
    elif(len(inputGiven) == 3):
        return pullBudget(inputGiven[1], inputGiven[2])
    else:
        return pullAllData(inputGiven[1])

def pullBudget(username, budgetname):
    data = ""
    with open(os.path.join(os.getcwd(), "data", username, budgetname + ".txt")) as f:
        data = f.read()
    if(len(data) < 1):
        data = "File does not exsist"
    return data

def pullAllFiles(username):
    filelist = ""
    fileList = os.listdir(os.path.join(os.getcwd(), "data", username))
    for file in filelist:
        filelist += os.path.splitext(file)
    return filelist