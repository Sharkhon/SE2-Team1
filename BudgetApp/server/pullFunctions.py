import os

def pullFunctions(inputGiven):
    return pullBudget(inputGiven[1], inputGiven[2])

def pullBudget(username, budgetname):
    data = ""
    with open(os.path.join(os.getcwd(), "data", username, budgetname + ".txt")) as f:
        data = f.read()
    if(len(data) < 1):
        data = "File does not exsist"
    return data