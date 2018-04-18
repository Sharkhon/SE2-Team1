package budget.testing.testBudget;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import budget.model.Budget;
import budget.model.Category;
import budget.model.Inflow;
import budget.model.Transaction;

class TestGetTransactionByName {

	@Test
	void testThatTransactionDoesNotExist() {
		ArrayList<Category> category = new ArrayList<Category>();
		category.add(new Category("Groceries", 50.0, 100.0));
		ArrayList<Transaction> transaction = new ArrayList<Transaction>();
		Transaction Inflow = new Inflow(50, LocalDateTime.now(), "bills");
		transaction.add(Inflow);
		
		Budget budget = new Budget("Groceries", 500.0, 40.0, category, transaction);
		assertEquals(null, budget.getTransactionByName("help"));
	}

}
