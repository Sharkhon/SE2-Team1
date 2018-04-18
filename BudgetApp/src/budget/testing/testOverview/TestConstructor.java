package budget.testing.testOverview;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import budget.model.Budget;
import budget.model.OverView;

class TestConstructor {

	@Test
	void testOneParameterConstructor() {
		OverView over = new OverView("rachetl");
		Budget budget =  new Budget("groceries", 200, 100);
		over.addBudget(budget);
		over.setCurrentBudget(0);

		
		assertTrue(over.getCurrentBudget().equals(budget));
		assertEquals(0, over.getCategories().size());
		assertEquals(200.0, over.getOverallBalanceProperty().doubleValue());
		assertEquals(100.0, over.getUnallocatedBalanceProperty().doubleValue());

	}
	
	@Test
	void testOneParameterConstructorChangeBudgetName() {
		OverView over = new OverView("rachetl");
		Budget budget =  new Budget("groceries", 200, 100);
		over.addBudget(budget);
		over.setCurrentBudget(0);
		over.setName("more food");
		
		assertEquals("more food", over.getCurrentBudget().getName());
		assertTrue(over.getCurrentBudget().equals(budget));
		assertEquals(0, over.getCategories().size());
		assertEquals(200.0, over.getOverallBalanceProperty().doubleValue());
		assertEquals(100.0, over.getUnallocatedBalanceProperty().doubleValue());

	}
	
	@Test
	void testTwoParameterConstructor() {
		ArrayList<Budget> budgets = new ArrayList<Budget>();
		budgets.add(new Budget("Games", 500, 50));
		budgets.add(new Budget("groceries", 200, 100));
		budgets.add(new Budget("Car", 1200, 900));
		
		OverView over = new OverView("rachetl",budgets);
		

		
		
		assertTrue(over.getCurrentBudget().getName().equals("Games"));

		assertEquals(0, over.getCategories().size());
		assertEquals(3,over.getBudgets().size());
		assertEquals(500.0, over.getOverallBalanceProperty().doubleValue());
		assertEquals(50.0, over.getUnallocatedBalanceProperty().doubleValue());

	}
	


}
