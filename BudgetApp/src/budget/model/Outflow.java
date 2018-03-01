package budget.model;

import java.time.LocalDateTime;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Outflow extends Transaction {
	
	private Category selectedCategory;

	public Outflow(int amount, LocalDateTime date, String title, Category category) {
		super(amount, date, title);
		
		this.selectedCategory = category;
	}

	public StringProperty getCategoryName() {
		return new SimpleStringProperty(this.selectedCategory.getName());
	}

}
