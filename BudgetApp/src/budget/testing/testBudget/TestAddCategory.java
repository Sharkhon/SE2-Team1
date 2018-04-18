package budget.testing.testBudget;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import budget.model.Budget;
import budget.model.Category;
import budget.model.Transaction;

class TestAddCategory {

	@Test
	void testCatehoryIsNull() {
		ArrayList<Category> category = new ArrayList<Category>();
		category.add(new Category("Groceries", 50.0, 100.0));
		ArrayList<Transaction> transaction = new ArrayList<Transaction>();
		Budget budget = new Budget("Groceries", 500.0, 40.0, category, transaction);
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> budget.addCategory((null)));
		assertEquals("Cannot add a null category", exception.getMessage());
	}

}
