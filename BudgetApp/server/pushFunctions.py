import os

def pushFunctions(inputGiven):
    if(inputGiven[0] == "push"):
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