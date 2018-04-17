package budget.io;

import java.util.ArrayList;

import budget.model.Budget;
import budget.model.Category;
import budget.model.Outflow;
import budget.model.Transaction;
import budget.server.ServerAccess;

public class Exporter {

	/*
	 * File will go like this:
	 * 1. username,budgetname,overallBal,unallocatedBal~
	 * **Categories**~
	 * 2. category,allocatedAmount,spentAmount~
	 * **Transactions**~
	 * transactionTitle,date,amount,in/out(as bool),category~
	 */
	
	private String lineSeparator = "~\n";
	public boolean ExportBudgetToServer(String username, Budget toExport) {		
		return ServerAccess.pushBudget(username, toExport.getName(), this.PrepareExport(username, toExport));
	}
	
	private String PrepareExport(String username, Budget toExport) {
		StringBuilder sb = new StringBuilder();
		sb.append(username + "," + toExport.getName() + "," + toExport.getOverallAmount().get() + "," + toExport.getUnallocatedAmount().get() + this.lineSeparator);
		sb.append(this.prepareCategories(toExport.getCategories()));
		sb.append(prepareTransactions(toExport.getTransactions()));
		
		return sb.toString();
	}
	
	private String prepareCategories(ArrayList<Category> categories) {
		StringBuilder categoryBuilder = new StringBuilder("**Categories**" + this.lineSeparator);
		for (Category category : categories) {
			categoryBuilder.append(category.getName() + "," + category.getAllocatedAmount().get() + "," + category.getSpentAmount().get() + this.lineSeparator);
		}
		
		return categoryBuilder.toString();
	}
	
	private String prepareTransactions(ArrayList<Transaction> transactions) {
		StringBuilder transactionBuilder = new StringBuilder("**Transactions**" + this.lineSeparator);
		for (Transaction transaction : transactions) {
			if(transaction instanceof Outflow) {
				transactionBuilder.append(transaction.getTitle() + "," + transaction.getDate() + "," + transaction.getAmount().get() + ",false," + ((Outflow)transaction).getCategoryName().get() + this.lineSeparator);
			} else {
				transactionBuilder.append(transaction.getTitle() + "," + transaction.getDate() + "," + transaction.getAmount().get() + ",true,N/A" + this.lineSeparator);
			}
		}
		
		return transactionBuilder.toString();
	}
}
