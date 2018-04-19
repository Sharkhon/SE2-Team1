package budget.testing.testBudget;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import budget.model.Budget;
import budget.model.Category;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

class TestSetName {

	@Test
	void testNameWasChanged() {
		Budget budget = new Budget("ja", 2.5, 4.5);
		budget.setName("la");
		StringProperty result = new SimpleStringProperty("la");
		assertEquals(result.get(), budget.getName());
		
	}
	@Test
	void testNameWasNull() {
		Budget budget = new Budget("ja", 2.5, 4.5);
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> budget.setName(null));
		assertEquals("A budget's name cannot be empty or have a ',' in it.", exception.getMessage());
	}
	@Test
	void testNameWasEmpty() {
		Budget budget = new Budget("ja", 2.5, 4.5);
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> budget.setName(""));
		assertEquals("A budget's name cannot be empty or have a ',' in it.", exception.getMessage());
	}
	@Test
	void testNameContainsComma() {
		Budget budget = new Budget("ja", 2.5, 4.5);
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> budget.setName("la,d"));
		assertEquals("A budget's name cannot be empty or have a ',' in it.", exception.getMessage());
	}
	
	

}
