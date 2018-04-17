package budget.testing.testOverview;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import budget.model.Budget;
import budget.model.Category;
import budget.model.OverView;

class TestUpdateCategoryAmounts {
	// TODO Corey finish this test Class
	private OverView overview;
	private Budget budget;

	@Before
	public void setup() {
		
		budget = new Budget("hello", 0, 0);

		overview.addBudget(budget);
		overview.addNewCategory("Category1", 50, 0);
		// Category cat = new Category("wh", 20.0, 1.0);
		// System.out.println(overview.getCategories());

	}

	@Test
	void testUpdatingTheCategoryAndSeeingIfItUpdatesCorrectly() {
		overview = new OverView("rachetl");
		// overview.updateCategoryAmounts("Category1", 40.0);
		// assertEquals(2,overview.getCategories());
		assertEquals(90.0, overview.getUnallocatedBalanceProperty());
	}

	// @Test
	// void testUpdatingTheCategoryAndShouldBePossibleToBeNegative() {
	// OverView overview = new OverView(15.0);
	// Category cat = new Category("wh", 20.0, 1.0);
	// overview.updateCategoryAmounts(cat, 40.0, 7.0);
	// assertEquals(-5.0, overview.getUnallocatedBalance(), 0.00001);
	// }

}
