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
	 * @precondition
	 * @postcondition
	 * 
	 * @param overallBalanace
	 */
	public OverView(int overallBalance) {
		if(overallBalance < 0) {
			throw new IllegalArgumentException("overall Balance must be initially 0 or greater");
		}

		this.categories = new ArrayList<Category>();
		this.overallBalance = overallBalance;
		this.unallocatedBalance = overallBalance;
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<Category> getCategories() {
		return categories;
	}

	/**
	 * 
	 * @return
	 */
	public int getOverallBalance() {
		return overallBalance;
	}

	/**
	 * 
	 * @return
	 */
	public int getUnallocatedBalance() {
		return unallocatedBalance;
	}

}
