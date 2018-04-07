package budget.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.Scanner;

import budget.model.Category;
import budget.model.OverView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;

public class ImportLocalFile {

	private OverView newBudget;
	
	private File localFile;
	private Scanner scan;
	/*
	 * File will go like this:
	 * 1. username,budgetname,overallBal
	 * **Categories**
	 * 2. category,allocatedAmount,spentAmount
	 * **Transactions**
	 * transactionTitle,date,amount,in/out(as bool),category
	 */
	
	public OverView importLocalFileFromPicker() throws FileNotFoundException {
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
	
	public OverView importLocalFile(String fileName) {
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
		this.newBudget = new OverView(topLine[1]);
		
		this.newBudget.setName(topLine[1]);
		
		String tmp = this.scan.nextLine();
		System.out.println(tmp);
		this.parseCategories();
		this.parseTransactions();
	}
	
	private void parseCategories() {
		String current;
		while(!(current = this.scan.nextLine()).equals("**Transactions**")) {
			String[] catData = current.split(",");
			this.newBudget.addNewCategory(catData[0], Double.parseDouble(catData[1]), Double.parseDouble(catData[2]));
		}
	}
	
	private void parseTransactions() {
		while(this.scan.hasNextLine()) {
			String[] transactionData = this.scan.nextLine().split(",");
			this.newBudget.addTransaction(transactionData[0], 
					Double.parseDouble(transactionData[2]), 
					LocalDateTime.parse(transactionData[1]), 
					Boolean.parseBoolean(transactionData[3]), 
					transactionData[4]);
		}
	}
	
}
