/**
 * 
 */
package budget.model;

import java.time.LocalDateTime;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Defines Overview Class
 * 
 * @author Software Engineering Group 1
 * @version 1
 * 
 */
public class OverView {

	private ObservableList<Transaction> transactions;
	private ObservableList<Category> categories;
	private double overallBalance;
	private StringProperty overallBalanceLabel;
	private double unallocatedBalance;
	private StringProperty unallocatedBalanceLabel;
	
	private StringProperty name;
	

	/**
	 * Default Constructor
	 * 
	 * @precondition overallbalance >= 0
	 * @postcondition none
	 * 
	 * @param overallBalanace
	 */
	public OverView() {		
		this(0);
	}
	
	public OverView(String name) {
		this(name, 0);
	}
	
	public OverView(String name, double overallBal) {
		this(overallBal);
		if(name == null || name.isEmpty() || name.contains(",")) {
			throw new IllegalArgumentException("Invalid budget name");
		}
		
		this.name.set(name);
	}

	/**
	 * Constructor
	 * 
	 * @precondition overallbalance >= 0
	 * @postcondition none
	 * 
	 * @param overallBalanace
	 */
	public OverView(double overallBalance) {
		if (overallBalance < 0) {
			throw new IllegalArgumentException("overall Balance must be initially positive");
		}

		this.categories = FXCollections.observableArrayList();
		this.transactions = FXCollections.observableArrayList();
		this.overallBalance = overallBalance;
		this.unallocatedBalance = overallBalance;
		this.unallocatedBalanceLabel = new SimpleStringProperty(Double.toString(this.unallocatedBalance));
		this.overallBalanceLabel = new SimpleStringProperty(Double.toString(this.overallBalance));
		this.name = new SimpleStringProperty("name");
	}

	/**
	 * Gets ArrayList of of categories
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return list of categories
	 */
	public ObservableList<Category> getCategories() {
		return this.categories;
	}
	
	/**
	 * Get the list of transactions
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the list of transactions
	 */
	public ObservableList<Transaction> getTransactions() {
		return this.transactions;
	}

	/**
	 * Gets the overallBalance
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return overall balance
	 */
	public double getOverallBalance() {
		return this.overallBalance;
	}

	/**
	 * Gets the unallocated balance
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return unallocated balance
	 */
	public double getUnallocatedBalance() {
		return this.unallocatedBalance;
	}
	
	/**
	 * Gets the overallBalance property
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return overall balance
	 */
	public StringProperty getOverallBalanceProperty() {
		return this.overallBalanceLabel;
	}

	/**
	 * Gets the unallocated balance property
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return unallocated balance
	 */
	public StringProperty getUnallocatedBalanceProperty() {
		return this.unallocatedBalanceLabel;
	}

	/**
	 * Gets the category specified by name and returns it, otherwise returns null
	 * 
	 * @precondition name != null
	 * @postcondition none
	 * 
	 * @param name
	 *            name of the category
	 * @return the category if
	 */
	public Category getSpecificCategory(String name) {
		if (name == null) {
			throw new IllegalArgumentException("name cannot be null");
		}
		Category category = null;
		for (Category currCategory : this.categories) {
			if (currCategory.getName().get().equalsIgnoreCase(name)) {
				category = currCategory;
			}
		}
		return category;
	}
	
	public void editCategory(String oldName, String newName, double newAllocatedAmount) {
		Category selected = this.getSpecificCategory(oldName);
		
		double oldAllocatedVal = selected.getAllocatedAmount().get();
		
		double netAllocatedChange = newAllocatedAmount - oldAllocatedVal;
		
		this.unallocatedBalance -= netAllocatedChange;
		
		selected.setAllocatedAmount(newAllocatedAmount);
		selected.setName(newName);
		
		this.updateLabels();
	}
	
	public void updateCategoryAmounts(Category selected, double allocatedAmount, double spentAmount) {
		double oldAllocatedAmount = selected.getAllocatedAmount().get();
		selected.setAllocatedAmount(allocatedAmount);
		this.unallocatedBalance -= allocatedAmount - oldAllocatedAmount;
		
		selected.setSpentAmount(spentAmount);
		
		this.updateLabels();
	}

	/**
	 * Adds a new category by specified name
	 * 
	 * @precondition name != null AND name != ""
	 * @postcondition A new category named name is added to the 
	 * 				list of categories
	 * 
	 * @param name
	 *            name of the new category
	 */
	public void addNewCategory(String name) {
		this.addNewCategory(name, 0, 0);
	}
	
	/**
	 * Adds a new category by specified name
	 * 
	 * @precondition name != null AND name != ""
	 * @postcondition A new category named name is with an allocated amount
	 * 				of AllocatedAmount added to the 
	 * 				list of categories
	 * 
	 * @param name
	 *            name of the new category
	 */
	public void addNewCategory(String name, double AllocatedAmount) {
		this.addNewCategory(name, AllocatedAmount, 0);
	}
	
	/**
	 * Adds a new category by specified name
	 * 
	 * @precondition name != null AND name != ""
	 * @postconditio none
	 * 
	 * @param name
	 *            name of the new category
	 */
	public void addNewCategory(String name, double AllocatedAmount, double SpentAmount) {
		Category newCat = new Category(name, AllocatedAmount, SpentAmount);
		this.categories.add(newCat);
		this.unallocatedBalance -= AllocatedAmount;
		this.overallBalance -= SpentAmount;
		this.updateLabels();
	}
	
	public void addTransaction(String title, double amount, LocalDateTime date, boolean inOut, String categoryName) {
		if(inOut) {
			this.addNewInflow(amount, date, title);
		} else {
			this.addNewOutflow(amount, date, title, this.getSpecificCategory(categoryName));
		}
	}
	
	public void addNewInflow(double amount, LocalDateTime date, String title) {
		this.transactions.add(new Inflow(amount, date, title));
		this.unallocatedBalance += amount;
		this.overallBalance += amount;
		
		this.updateLabels();
	}
	
	private void updateLabels() {
		this.unallocatedBalanceLabel.set(Double.toString(this.unallocatedBalance));
		this.overallBalanceLabel.set(Double.toString(this.overallBalance));
	}
	
	public void addNewOutflow(double amount, LocalDateTime date, String title, Category category) {
		this.transactions.add(new Outflow(amount, date, title, category));
		
		category.addToSpentAmount(amount);
		this.overallBalance -= amount;
		this.updateLabels();
	}
	
	public void RemoveTransaction(Transaction toDelete) {
		if(toDelete instanceof Outflow) {
			this.overallBalance += toDelete.getAmount().get();
			this.getSpecificCategory(((Outflow) toDelete).getCategoryName().get()).addToSpentAmount(-(toDelete.getAmount().get()));;
		} else {
			this.unallocatedBalance -= toDelete.getAmount().get();
			this.overallBalance -= toDelete.getAmount().get();
		}
		
		this.transactions.remove(toDelete);
		this.updateLabels();
	}
	
	public void RemoveCategory(Category toDelete) {
		this.unallocatedBalance += toDelete.getAllocatedAmount().get() - toDelete.getSpentAmount().get();
		
		this.categories.remove(toDelete);
		this.updateLabels();
	}
	
	/**
	 * Updates the budget's name
	 * 
	 * @precondition The name cannot be null, empty, or contain commas
	 * @postcondition The budget's name is changed
	 * @param name The new name for the budget
	 */
	public void setName(String name) {
		if(name == null || name.isEmpty() || name.contains(",")) {
			throw new IllegalArgumentException("Invalid name");
		}
		this.name.set(name);
	}

}
