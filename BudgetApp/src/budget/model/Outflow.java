package budget.model;

import java.time.LocalDateTime;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Outflow extends Transaction {
	
	private Category selectedCategory;

	public Outflow(double amount, LocalDateTime date, String title, Category category) {
		super(amount, date, title);
		
		this.selectedCategory = category;
	}
	
	@Override
	public StringProperty getType() {
		return new SimpleStringProperty("Outflow");
	}

	public StringProperty getCategoryName() {
		return new SimpleStringProperty(this.selectedCategory.getName().get());
	}

	@Override
	public int compareTo(Transaction arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}
