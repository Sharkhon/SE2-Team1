import os

def pushFunctions(inputGiven):
    if("delete" in inputGiven[0]):
        return deleteBudget(inputGiven[1], inputGiven[2])
    else:
        return pushBudget(inputGiven[1], inputGiven[2], inputGiven[3:len(inputGiven)])
    

def pushBudget(userName, budgetName, fileData):
    data = ''
    for part in fileData:
        print(part)
        data += part + ","
    if data.endswith(","):
        data = data[0:-1]
    with open(os.path.join(os.getcwd(), "data", userName, budgetName + ".txt"), 'w') as f:
        f.write(data)
    return True

def deleteBudget(username, budgetName):
    try:
        os.remove(os.path.join(os.getcwd(), "data", username, budgetName + ".txt"))
        return True
    except OSError as e:
        return False