package budget.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.Scanner;

import budget.model.Budget;
import budget.model.Category;
import budget.model.Inflow;
import budget.model.Outflow;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class ImportLocalFile {

	private Budget newBudget;
	
	private File localFile;
	private Scanner scan;
	/*
	 * File will go like this:
	 * 1. username,budgetname,overallBal,unallocatedBal
	 * **Categories**
	 * 2. category,allocatedAmount,spentAmount
	 * **Transactions**
	 * transactionTitle,date,amount,in/out(as bool),category
	 */
	
	public Budget importLocalFileFromPicker() throws FileNotFoundException {
		Stage primaryStage = new Stage();
		FileChooser picker = new FileChooser();
		picker.setSelectedExtensionFilter(new ExtensionFilter("txt files", ".txt"));
		File selectedFile = picker.showOpenDialog(primaryStage);
		
		if(selectedFile == null) {
			throw new FileNotFoundException("No file selected");
		}
		
		this.localFile = selectedFile;
		this.readFile();
		
		return this.newBudget;
	}
	
	public Budget importLocalFile(String fileName) {
		this.localFile = new File(fileName);
		try {
			this.readFile();
		} catch (FileNotFoundException fnfe) {
			System.err.println("File: " + fileName + " not found");
		}
		
		return this.newBudget;
	}
	
	private void readFile() throws FileNotFoundException {
		this.scan = new Scanner(this.localFile);
		
		String[] topLine = scan.nextLine().split(",");
		this.newBudget = new Budget(topLine[1]);
		
		this.parseCategories();
		this.parseTransactions();
	}
	
	private void parseCategories() {
		String current;
		while(!(current = this.scan.nextLine()).equals("**Transactions**")) {
			String[] catData = current.split(",");
			this.newBudget.addCategory(new Category(catData[0], Double.parseDouble(catData[1]), Double.parseDouble(catData[2])));
		}
	}
	
	private void parseTransactions() {
		//transactionTitle,date,amount,in/out(as bool),category
		while(this.scan.hasNextLine()) {
			String[] transactionData = this.scan.nextLine().split(",");
			
			if(Boolean.parseBoolean(transactionData[3])) {
				this.newBudget.addInflow(new Inflow(
						Double.parseDouble(transactionData[2]),
						LocalDateTime.parse(transactionData[1]),
						transactionData[0]
						));
			} else {
				this.newBudget.addOutflow(new Outflow(Double.parseDouble(transactionData[2]),
						LocalDateTime.parse(transactionData[1]), 
						transactionData[0], this.newBudget.getCategoryByName(transactionData[4])), 
						transactionData[4]);
			}
		}
	}
	
}
