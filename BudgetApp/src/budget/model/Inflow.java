package budget.model;

import java.time.LocalDateTime;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Stores information for an inflow transaction
 * 
 * @author Software Engineering Group 1
 * @version 1
 *
 */
public class Inflow extends Transaction {

	/**
	 * Creates a new inflow transaction
	 * 
	 * @precondition amount > 0 && date != null && title != null && title != ""
	 * 
	 * @param amount the amount of money of the transaction
	 * @param date the date of the transaction
	 * @param title the title of the transaction
	 */
	public Inflow(double amount, LocalDateTime date, String title) {
		super(amount, date, title); 
	}
	
	/**
	 * Gets the type of the inflow transaction
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return String "Inflow" as type
	 */
	@Override
	public StringProperty getType() {
		return new SimpleStringProperty("Inflow");
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
