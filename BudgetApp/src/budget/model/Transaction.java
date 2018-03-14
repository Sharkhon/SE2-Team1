package budget.model;

import java.time.LocalDateTime;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Stores information for a Transaction
 * 
 * @author Software Engineering Group 1
 * @version 1
 *
 */
public abstract class Transaction implements Comparable<Transaction> {
	private DoubleProperty amount;
	private ObjectProperty<LocalDateTime> date;
	private StringProperty title;
	
	/**
	 * Creates a new transaction with specified amount, date, and title
	 * 
	 * @precondition amount > 0 && date != null && title != null && title != ""
	 * 
	 * @param amount the amount of money of the transaction
	 * @param date the date of the transaction
	 * @param title the title of the transaction
	 */
	public Transaction(double amount, LocalDateTime date, String title) {
		
		if (amount <= 0) {
			throw new IllegalArgumentException("Amount must be greater than zero");
		}
		
		if (date == null) {
			throw new IllegalArgumentException("Date cannot be null");
		}
		
		if (title == null || title.equals("")) {
			throw new IllegalArgumentException("Invalid name");
		}

		this.amount = new SimpleDoubleProperty(amount);
		this.date = new SimpleObjectProperty<LocalDateTime>(date);
		this.title = new SimpleStringProperty(title);
	}
	
	/**
	 * Gets the amount of the transaction
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return amount of transaction
	 */
	public DoubleProperty getAmount() {
		return this.amount; 
	}
	
	/**
	 * Gets the date of the transaction
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return date of transaction
	 */
	public ObjectProperty<LocalDateTime> getDate() {
		return this.date;
	}
	
	/**
	 * Gets the title of the transaction
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return title of transaction
	 */
	public StringProperty getTitle() {
		return this.title;
	}
	
	/**
	 * Gets the type of the transaction
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return type of transaction
	 */
	public abstract StringProperty getType();

}
