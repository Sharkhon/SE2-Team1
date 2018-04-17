package budget.testing.testOverview;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import budget.model.Budget;
import budget.model.Category;
import budget.model.OverView;

class TestAddNewOutflow {

	@Test
	void testAddOneOutflow() {
		OverView ovr = new OverView("rachetl");
		Budget budget = new Budget("home",200,100);
		ovr.addBudget(budget);
		ovr.setCurrentBudget(0);
		LocalDateTime date = LocalDateTime.now();
		ovr.addNewCategory("groceries", 0, 0);
		ovr.addNewOutflow(50, date, "Milk", "groceries");
		
		assertEquals(1,ovr.getTransactions().size());
		assertEquals(150, ovr.getOverallBalanceProperty().doubleValue());
	}
	
	@Test
	void testAddMultipleOutflow() {
		OverView ovr = new OverView("rachetl");
		Budget budget = new Budget("home",200,100);
		ovr.addBudget(budget);
		ovr.setCurrentBudget(0);
		LocalDateTime date = LocalDateTime.now();
		ovr.addNewCategory("groceries", 0, 0);
		
		ovr.addNewOutflow(26, date, "Milk", "groceries");
		ovr.addNewOutflow(16, date, "veggies", "groceries");
		ovr.addNewOutflow(15, date, "protein", "groceries");
		ovr.addNewOutflow(36, date, "eggs", "groceries");
		
		assertEquals(4,ovr.getTransactions().size());
		assertEquals(107, ovr.getOverallBalanceProperty().doubleValue());
	}

}
