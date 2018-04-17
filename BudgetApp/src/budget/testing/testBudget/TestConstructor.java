package budget.testing.testBudget;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import budget.model.Budget;

class TestConstructor {

	@Test
	void testNameIsNull() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> new Budget(null, 25, 2));
		assertEquals("Invalid budget name given", exception.getMessage());

	}
	
	@Test
	void testNameContainsAComma() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> new Budget("ds,d", 25, 2));
		assertEquals("Invalid budget name given", exception.getMessage());

	}
	@Test
	void testNameIsEmpty() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> new Budget("", 25, 2));
		assertEquals("Invalid budget name given", exception.getMessage());

	}
	@Test
	void testValidName() {
		Budget budget = new Budget("Groceries", 500.0, 40.0);

		assertEquals("Groceries", budget.getName());

	}



}
