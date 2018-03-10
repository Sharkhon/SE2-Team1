package budget.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Defines Category Class
 * 
 * @author Software Engineering Group 1
 * @version 1
 *
 */
public class Category implements Comparable<Category> {
	private StringProperty name;
	private DoubleProperty allocatedAmount;
	private DoubleProperty spentAmount;

	/**
	 * Constructor for Category
	 * 
	 * @precondition name != null AND name != empty AND allocatedAmount >= 0 AND
	 *               spentAmount >= 0
	 * @postcondition none
	 * 
	 * @param name
	 * 			name of the category
	 * @param allocatedAmount
	 * 				amount given to category 
	 * @param spentAmount
	 * 				amount spent out by category
	 */
	public Category(String name, double allocatedAmount, double spentAmount) {
		if(name == null || name.equals("")) {
			throw new IllegalArgumentException("Must provide valid name for Category");
		}
		if(allocatedAmount < 0) {
			throw new IllegalArgumentException("allocated amount must be positive amount");
		}
		if(spentAmount < 0) {
			throw new IllegalArgumentException("spent amount must be positive amount");
		}
		this.name = new SimpleStringProperty(name);
		this.allocatedAmount = new SimpleDoubleProperty(allocatedAmount);
		this.spentAmount = new SimpleDoubleProperty(spentAmount);
	}

	/**
	 * gets name of the category
	 * 
	 * @precondition none 
	 * @postcondition none
	 * 
	 * @return name of category
	 */
	public StringProperty getName() {
		return this.name;
	}

	/**
	 * gets allocated amount for category
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return allocated amount
	 */
	public DoubleProperty getAllocatedAmount() {
		return this.allocatedAmount;
	}

	/**
	 * gets spent amount for category
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return spent amount
	 */
	public DoubleProperty getSpentAmount() {
		return this.spentAmount;
	}

	public void setAllocatedAmount(double allocatedAmount) {
		this.allocatedAmount.set(allocatedAmount);
	}
	
	public void setName(String name) {
		this.name.set(name);
	}
	
	public void setSpentAmount(double amount) {
		this.spentAmount.set(amount);
	}
	
	public void addToSpentAmount(double amount) {
		double newAmount = this.spentAmount.get() + amount;
		this.spentAmount.set(newAmount);
	}
	
	@Override
	public int compareTo(Category arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public boolean validEdit(String name, double newAllocatedAmount, double totalUnallocated) {
		return !name.isEmpty() && totalUnallocated - (newAllocatedAmount - this.allocatedAmount.get()) >= 0;
	}
	
	@Override
	public String toString() {
		return this.name.get();
	}

}
