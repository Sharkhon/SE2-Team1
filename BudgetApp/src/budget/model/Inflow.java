package budget.model;

import java.time.LocalDateTime;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Inflow extends Transaction {

	public Inflow(double amount, LocalDateTime date, String title) {
		super(amount, date, title); 
	}
	
	@Override
	public StringProperty getType() {
		return new SimpleStringProperty("Inflow");
	}

	@Override
	public int compareTo(Transaction arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
