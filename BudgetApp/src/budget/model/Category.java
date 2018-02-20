package budget.model;

/**
 * Defines Category Class
 * 
 * @author Software Engineering Group 1
 * @version 1
 *
 */
public class Category {
	private String name;
	private int allocatedAmount;
	private int spentAmount;

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
	public Category(String name, int allocatedAmount, int spentAmount) {
		if(name == null || name.equals("")) {
			throw new IllegalArgumentException("Must provide valid name for Category");
		}
		if(allocatedAmount < 0) {
			throw new IllegalArgumentException("allocated amount must be positive amount");
		}
		if(spentAmount < 0) {
			throw new IllegalArgumentException("spent amount must be positive amount");
		}
		this.name = name;
		this.allocatedAmount = allocatedAmount;
		this.spentAmount = spentAmount;
	}

	/**
	 * gets name of the category
	 * 
	 * @precondition none 
	 * @postcondition none
	 * 
	 * @return name of category
	 */
	public String getName() {
		return name;
	}

	/**
	 * gets allocated amount for category
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return allocated amount
	 */
	public int getAllocatedAmount() {
		return allocatedAmount;
	}

	/**
	 * gets spent amount for category
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return spent amount
	 */
	public int getSpentAmount() {
		return spentAmount;
	}

}
