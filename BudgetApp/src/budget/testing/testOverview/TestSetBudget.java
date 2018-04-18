package budget.testing.testOverview;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import budget.model.Budget;
import budget.model.OverView;

class TestSetBudget {

	@Test
	void testValidSet() {
		OverView over = new OverView("rachetl");
		Budget budget =  new Budget("groceries", 200, 100);
		over.addBudget(budget);
		over.setCurrentBudget(0);
		
		assertTrue(over.getCurrentBudget().equals(budget));
	}
	
	@Test
	void testinValidSetBelowZero() {
		OverView over = new OverView("rachetl");
		Budget budget =  new Budget("groceries", 200, 100);
		over.addBudget(budget);
		
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> over.setCurrentBudget(-1));
		assertEquals("index given is out of bounds to update the current budget", exception.getMessage());
		
		
	}
	
	@Test
	void testinValidSetBiggerThanIndex() {
		OverView over = new OverView("rachetl");
		Budget budget =  new Budget("groceries", 200, 100);
		over.addBudget(budget);
		
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> over.setCurrentBudget(1));
		assertEquals("index given is out of bounds to update the current budget", exception.getMessage());
		
		
	}
	

}
