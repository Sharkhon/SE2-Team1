package budget.testing.testOverview;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import budget.model.Budget;
import budget.model.OverView;

class TestRemoveCategory {

	@Test
	void testMultiplCategories() {
		OverView overview = new OverView("rachetl");
		Budget budget = new Budget("groceries", 200, 100);
		overview.addBudget(budget);
		overview.setCurrentBudget(0);

		overview.addNewCategory("Category1", 50, 0);
		overview.addNewCategory("car", 100, 0);
		overview.addNewCategory("Groceries", 50, 0);
		
		overview.RemoveCategory("car");

		assertEquals(2,overview.getCategories().size());
	}
	
	@Test
	void testOneCategory() {
		OverView overview = new OverView("rachetl");
		Budget budget = new Budget("groceries", 200, 100);
		overview.addBudget(budget);
		overview.setCurrentBudget(0);

		overview.addNewCategory("Category1", 50, 0);

		
		overview.RemoveCategory("Category1");

		assertEquals(0,overview.getCategories().size());
	}

}
