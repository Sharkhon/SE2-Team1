package budget.model;

import java.time.LocalDateTime;

public class Outflow extends Transaction {

	public Outflow(int amount, LocalDateTime date, String title) {
		super(amount, date, title);
		
		
	}

	/**
	 * 
	 */
	public int getAmount() {
		return super.getAmount();
	}

	/**
	 * 
	 */
	public LocalDateTime getDate() {
		return super.getDate();
	}

	
	/**
	 * 
	 */
	public String getTitle() {
		return super.getTitle();
	}

}
