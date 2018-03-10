package budget.testing.testOverview;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import budget.model.OverView;

class TestAddNewCategory {

	@Test
	void testOneParameterMethod() {
		OverView over = new OverView();
		
		over.addNewCategory("Groceries");
		
		assertEquals(1,over.getCategories().size());
		
	}
	
	@Test
	void testTwoParameterConstructor() {
		OverView over = new OverView();
		
		over.addNewCategory("Groceries",50.00);
		
		assertEquals(1,over.getCategories().size());
		
	}

}
