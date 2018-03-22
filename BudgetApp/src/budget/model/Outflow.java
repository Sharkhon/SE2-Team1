package budget.model;

import java.time.LocalDateTime;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Stores information for an outflow transaction
 * 
 * @author Software Engineering Group 1
 * @version 1
 *
 */
public class Outflow extends Transaction {
	
	private Category selectedCategory;

	/**
	 * Creates a new outflow transaction
	 * 
	 * @precondition amount > 0 && date != null && title != null && title != ""
	 * 
	 * @param amount the amount of money of the transaction
	 * @param date the date of the transaction
	 * @param title the title of the transaction
	 * @param category TODO
	 */
	public Outflow(double amount, LocalDateTime date, String title, Category category) {
		super(amount, date, title);
		
		this.selectedCategory = category;
	}
	
	/**
	 * Gets the type of the outflow transaction
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return String "Outflow" as type
	 */
	@Override
	public StringProperty getType() {
		return new SimpleStringProperty("Outflow");
	}

	/**
	 * Gets the name of the category
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return name of category
	 */
	public StringProperty getCategoryName() {
		return new SimpleStringProperty(this.selectedCategory.getName().get());
	}

	/**
	 * TODO
	 */
	@Override
	public int compareTo(Transaction arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}
