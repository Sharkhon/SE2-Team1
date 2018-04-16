package budget.model;

import java.util.ArrayList;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Budget {
	private ArrayList<Category> categories;
	private ArrayList<Transaction> transactions;
	
	private String name;
	
	private DoubleProperty overallAmount;
	private DoubleProperty unallocatedAmount;
	
	public Budget(String name) {
		this(name, 0, 0);
	}
	
	public Budget(String name, double overallAmount, double unallocatedAmount) {
		if(name == null || name.contains(",") || name.isEmpty()) {
			throw new IllegalArgumentException("Invalid budget name given");
		}
		
		this.name = name;
		this.overallAmount = new SimpleDoubleProperty(overallAmount);
		this.unallocatedAmount = new SimpleDoubleProperty(unallocatedAmount);
		
		this.categories = new ArrayList<Category>();
		this.transactions = new ArrayList<Transaction>();
	}
	
	public Budget(String name, double overallAmount, double unallocatedAmount, ArrayList<Category> categories, ArrayList<Transaction> transactions) {
		this(name, overallAmount, unallocatedAmount);
		
		this.categories = categories;
		this.transactions = transactions;
	}
	
	/**
	 * Gets the name of the budget
	 * @return The name of the budget
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Updates the name
	 * @precondition name cannot be null, empty, or contain ','
	 * @postcondition Budget's name is updated
	 * @param name The new name for the budget
	 */
	public void setName(String name) {
		if(name == null || name.isEmpty() || name.contains(",")) {
			throw new IllegalArgumentException("A budget's name cannot be empty or have a ',' in it.");
		}
		
		this.name = name;
	}
	
	/**
	 * Adds a category to the budget
	 * @precondition category cannot be null
	 * @postcondition Budget::getCategories.size() == @prev Budget::getCategories.size() + 1 
	 * @param category 
	 */
	public void addCategory(Category category) {
		if(category == null) {
			throw new IllegalArgumentException("Cannot add a null category");
		}
		
		this.categories.add(category);
	}
	
	/**
	 * Removes the category from the budget and 
	 * updates the unallocated amount for the budget
	 * @precondition None
	 * @postcondition Budget::getCategories.size() == @prev Budget::getCategories.size() - 1
	 * @param category The category to remove from the budget
	 */
	public void deleteCaegory(Category category) {
		if(this.categories.remove(category)) {
			this.unallocatedAmount.add(category.getAllocatedAmount().doubleValue() - category.getSpentAmount().doubleValue());
		}
		
	}
	
	public Category getCategoryByName(String categoryName) {
		for(Category current : this.categories) {
			if(current.getName().get().equals(categoryName)) {
				return current;
			}
		}
		return null;
	}
	
	/**
	 * Gets the categories for the budget
	 * @return
	 */
	public ArrayList<Category> getCategories() {
		return this.categories;
	}
	
	/**
	 * Updates the allocated amount for the category
	 * @precondition None
	 * @postcondition The category's allocated amount now
	 * 				equals newAmount
	 * @param category The category's name
	 * @param newAmount The new allocated amount
	 */
	public void updateCategoryAllocatedAmount(String category, double newAmount) {
		this.getCategoryByName(category).setAllocatedAmount(newAmount);
	}
	
	public ArrayList<Transaction> getTransactions() {
		return this.transactions;
	}
	
	public void addInflow(Transaction inflow) {
		if(inflow == null) {
			throw new IllegalArgumentException("Cannot add null transaction.");
		}
		
		this.overallAmount.add(inflow.getAmount().doubleValue());
		this.transactions.add(inflow);
	}
	
	public void addOutflow(Transaction outflow, String categoryName) {
		if(outflow == null) {
			throw new IllegalArgumentException("Cannot add null transaction.");
		}
		
		this.overallAmount.add(outflow.getAmount().doubleValue());
		this.getCategoryByName(categoryName).addToSpentAmount(outflow.getAmount().get());
		
	}
	
	public void removeTransaction(Transaction toDelete) {
		if(toDelete instanceof Outflow) {
			this.overallAmount.add(toDelete.getAmount().doubleValue()) ;
			this.getCategoryByName(((Outflow) toDelete).getCategoryName().get()).addToSpentAmount(-(toDelete.getAmount().get()));;
		} else {
			this.unallocatedAmount.add(toDelete.getAmount().doubleValue()) ;
			this.overallAmount.add(toDelete.getAmount().doubleValue());
		}
			
		this.transactions.remove(toDelete);
	}
	
	public void removeCategory(Category toDelete) {
		this.unallocatedAmount.add(toDelete.getAllocatedAmount().doubleValue() - toDelete.getSpentAmount().doubleValue());
		this.categories.remove(toDelete);
	}
	
	public DoubleProperty getUnallocatedAmount() {
		return this.unallocatedAmount;
	}
	
	public DoubleProperty getOverallAmount() {
		return this.overallAmount;
	}
	
	/*
	 * Methods needed:
	 * Add category
	 * Delete category
	 * Change allocated amount for category
	 * 
	 * Add inflow
	 * Add outflow
	 * Delete transaction
	 * 
	 * update name
	 * get name
	 */
}
