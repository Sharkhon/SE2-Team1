package budget.testing.testBudget;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import budget.model.Budget;
import budget.model.Category;
import budget.model.Inflow;
import budget.model.Transaction;

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
	@Test
	void testValidConstructorForFiveParameterConstructor() {
		ArrayList<Category> category = new ArrayList<Category>();
		category.add(new Category("Groceries", 50.0, 100.0));
		ArrayList<Transaction> transaction = new ArrayList<Transaction>();
		Transaction Inflow = new Inflow(50, LocalDateTime.now(), "bills");
		transaction.add(Inflow);
		
		Budget budget = new Budget("Groceries", 500.0, 40.0, category, transaction);
		

		assertEquals("Groceries",budget.getName());
		assertEquals(500.0, budget.getOverallAmount().get());
		assertEquals(40.0, budget.getUnallocatedAmount().get());
		assertEquals("Groceries", budget.getCategoryByName("Groceries").toString());
		assertEquals("bills", budget.getTransactionByName("bills").getTitle().get());
		assertEquals("Groceries", budget.toString());

	}



}
