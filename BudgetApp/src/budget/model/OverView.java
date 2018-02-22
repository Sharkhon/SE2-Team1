/**
 * 
 */
package budget.model;

import java.util.ArrayList;

/**
 * Defines Overview Class
 * 
 * @author Software Engineering Group 1
 * @version 1
 * 
 */
public class OverView {

	private ArrayList<Category> categories;
	private int overallBalance;
	private int unallocatedBalance;

	/**
	 * Constructor
	 * 
	 * @precondition overallbalance >= 0
	 * @postcondition none
	 * 
	 * @param overallBalanace
	 */
	public OverView(int overallBalance) {
		if(overallBalance < 0) {
			throw new IllegalArgumentException("overall Balance must be initially positive");
		}

		this.categories = new ArrayList<Category>();
		this.overallBalance = overallBalance;
		this.unallocatedBalance = overallBalance;
	}

	/**
	 * Gets ArrayList of of categories
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return list of categories
	 */
	public ArrayList<Category> getCategories() {
		return categories;
	}

	/**
	 * Gets the overallBalance
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return overall balance
	 */
	public int getOverallBalance() {
		return overallBalance;
	}

	/**
	 * Gets the unallocated balance
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return unallocated balance
	 */
	public int getUnallocatedBalance() {
		return unallocatedBalance;
	}
	
	public void addCategory(String name) {
		Category newCat = new Category(name,0,0);
		this.categories.add(newCat);
	}

}
