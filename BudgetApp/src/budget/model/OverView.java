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
	 * Default Constructor
	 * 
	 * @precondition overallbalance >= 0
	 * @postcondition none
	 * 
	 * @param overallBalanace
	 */
	public OverView() {

		this.categories = new ArrayList<Category>();
		this.overallBalance = 0;
		this.unallocatedBalance = 0;
	}

	/**
	 * Constructor
	 * 
	 * @precondition overallbalance >= 0
	 * @postcondition none
	 * 
	 * @param overallBalanace
	 */
	public OverView(int overallBalance) {
		if (overallBalance < 0) {
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
			if (currCategory.getName().equalsIgnoreCase(name)) {
				category = currCategory;
				return category;
			}
		}
		return category;
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
	public void addNewCategory(String name) {

		Category newCat = new Category(name, 0, 0);
		this.categories.add(newCat);
	}

}
