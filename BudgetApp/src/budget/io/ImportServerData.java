package budget.io;

import java.util.ArrayList;

import budget.model.Budget;
import budget.server.ServerAccess;

public class ImportServerData {

	public ArrayList<Budget> pullFromServer(String username) {
		String[] fileNames = ServerAccess.pullUserData(username).split(",");
		ArrayList<String> data = new ArrayList<String>();
		
		for(String name : fileNames) {
			if(!name.isEmpty()) {
				data.add(ServerAccess.pullBudget(username, name));
			}
		}
		
		Parser budgetParser = new Parser();
		return budgetParser.parseFromString(data);
	}
}
