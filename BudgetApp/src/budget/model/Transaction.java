package budget.model;

import java.time.LocalDateTime;

public abstract class Transaction {
	private int amount;
	private LocalDateTime date;
	private String title;
	
	
	public abstract int getAmount();
	
	public abstract int getDate();
	
	public abstract String getTitle();

}
