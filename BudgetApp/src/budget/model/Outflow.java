package budget.model;

import java.time.LocalDateTime;

public class Outflow extends Transaction {

	public Outflow(int amount, LocalDateTime date, String title) {
		super(amount, date, title);
	}

}
