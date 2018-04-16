package budget.model;

import java.util.ArrayList;

public class Budget {
	private ArrayList<Category> categories;
	private ArrayList<Transaction> transactions;
	
	private String name;
	
	private double overallAmount;
	private double unallocatedAmount;
	
	public Budget(String name) {
		this(name, 0, 0);
	}
	
	public Budget(String name, double overallAmount, double unallocatedAmount) {
		if(name == null || name.contains(",") || name.isEmpty()) {
			throw new IllegalArgumentException("Invalid budget name given");
		}
		
		this.name = name;
		this.overallAmount = overallAmount;
		this.unallocatedAmount = unallocatedAmount;
		
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
			this.unallocatedAmount += category.getAllocatedAmount().get() - category.getSpentAmount().get();
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
		
		this.overallAmount += inflow.getAmount().get();
		this.transactions.add(inflow);
	}
	
	public void addOutflow(Transaction outflow, String categoryName) {
		if(outflow == null) {
			throw new IllegalArgumentException("Cannot add null transaction.");
		}
		
		this.overallAmount -= outflow.getAmount().get();
		this.getCategoryByName(categoryName).addToSpentAmount(outflow.getAmount().get());
		
	}
	
	public void removeTransaction(Transaction toDelete) {
		if(toDelete instanceof Outflow) {
			this.overallAmount += toDelete.getAmount().get();
			this.getCategoryByName(((Outflow) toDelete).getCategoryName().get()).addToSpentAmount(-(toDelete.getAmount().get()));;
		} else {
			this.unallocatedAmount -= toDelete.getAmount().get();
			this.overallAmount -= toDelete.getAmount().get();
		}
			
		this.transactions.remove(toDelete);
	}
	
	public void removeCategory(Category toDelete) {
		this.unallocatedAmount += toDelete.getAllocatedAmount().get() - toDelete.getSpentAmount().get();
		this.categories.remove(toDelete);
	}
	
	public double getUnallocatedAmount() {
		return this.unallocatedAmount;
	}
	
	public double getOverallAmount() {
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
