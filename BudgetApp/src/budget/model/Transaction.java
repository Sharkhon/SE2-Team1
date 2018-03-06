package budget.model;

import java.time.LocalDateTime;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Transaction implements Comparable<Transaction> {
	private DoubleProperty amount;
	private ObjectProperty<LocalDateTime> date;
	private StringProperty title;
	
	public Transaction(double amount, LocalDateTime date, String title) {
		
		if (amount <= 0) {
			throw new IllegalArgumentException("Amount must be greater than zero");
		}
		
		if (date == null) {
			throw new IllegalArgumentException("Date cannot be null");
		}
		
		if (title == null || title.length() < 1) {
			throw new IllegalArgumentException("Invalid name");
		}

		this.amount = new SimpleDoubleProperty(amount);
		this.date = new SimpleObjectProperty<LocalDateTime>(date);
		this.title = new SimpleStringProperty(title);
	}
	
	public DoubleProperty getAmount() {
		return this.amount; 
	}
	
	public ObjectProperty<LocalDateTime> getDate() {
		return this.date;
	}
	
	public StringProperty getTitle() {
		return this.title;
	}
	
	abstract StringProperty getType();

}
