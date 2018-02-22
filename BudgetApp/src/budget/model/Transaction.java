package budget.model;

import java.time.LocalDateTime;

public abstract class Transaction {
	private int amount;
	private LocalDateTime date;
	private String title;
	
	public Transaction(int amount, LocalDateTime date, String title) {
		
		if (amount <= 0) {
			throw new IllegalArgumentException("Amount must be greater than zero");
		}
		
		if (date == null) {
			throw new IllegalArgumentException("Date cannot be null");
		}
		
		if (title == null || title.length() < 1) {
			throw new IllegalArgumentException("Invalid name");
		}

		this.amount = amount;
		this.date = date;
		this.title = title;
	}
	
	public int getAmount() {
		return this.amount; 
	}
	
	public LocalDateTime getDate() {
		return this.date;
	}
	
	public String getTitle() {
		return this.title;
	}

}
