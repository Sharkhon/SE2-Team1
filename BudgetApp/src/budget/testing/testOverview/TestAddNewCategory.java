package budget.testing.testOverview;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import budget.model.Budget;
import budget.model.OverView;

class TestAddNewCategory {

	@Test
	void testOneParameterMethod() {
		OverView over = new OverView("rachetl");
		Budget budget = new Budget("home",200,100);
		over.addBudget(budget);
		over.setCurrentBudget(0); 
		
		over.addNewCategory("Groceries",0,0);
		
		assertEquals(1,over.getCategories().size());
		
	}
	
	@Test
	void testTwoParameterConstructor() {
		OverView over = new OverView("rachetl");
		Budget budget = new Budget("home",200,100);
		over.addBudget(budget);
		over.setCurrentBudget(0); 
		
		over.addNewCategory("Groceries",50.00,89);
		
		assertEquals(1,over.getCategories().size());
		
	}

}
