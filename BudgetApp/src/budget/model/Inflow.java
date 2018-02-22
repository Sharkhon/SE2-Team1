package budget.model;

import java.time.LocalDateTime;

public class Inflow extends Transaction {

	public Inflow(int amount, LocalDateTime date, String title) {
		super(amount, date, title); 
	}


}
