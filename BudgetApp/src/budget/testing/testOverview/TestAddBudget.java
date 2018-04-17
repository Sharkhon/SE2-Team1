package budget.testing.testOverview;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import budget.model.Budget;
import budget.model.OverView;

class TestAddBudget {

	@Test
	void testAddOneBudget() {
		OverView ovr = new OverView("user");
		Budget budget = new Budget("Groceries");
		
		ovr.addBudget(budget);
		
		assertEquals(1, ovr.getBudgets().size());
		
	}
	
	@Test
	void testAddMultipleBudgets() {
		OverView ovr = new OverView("user");
		Budget budget1 = new Budget("Groceries");
		Budget budget2 = new Budget("Bills");
		
		ovr.addBudget(budget1);
		ovr.addBudget(budget2);
		
		
		assertEquals(2, ovr.getBudgets().size());
		
	}

}
