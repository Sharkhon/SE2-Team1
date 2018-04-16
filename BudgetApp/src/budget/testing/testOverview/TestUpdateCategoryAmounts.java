package budget.testing.testOverview;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import budget.model.Category;
import budget.model.OverView;

class TestUpdateCategoryAmounts {
//TODO Corey finish this test Class
	private OverView overview;
	

	@Before
	public void setup() {
		overview = new OverView("rachetl");
		Category cat = new Category("wh", 20.0, 1.0);

	}

	@Test
	void testUpdatingTheCategoryAndSeeingIfItUpdatesCorrectly() {
		overview.

		overview.updateCategoryAmounts("", 40.0);
		assertEquals(90.0, overview.getUnallocatedBalance(), 0.00001);
	}

	@Test
	void testUpdatingTheCategoryAndShouldBePossibleToBeNegative() {
		OverView overview = new OverView(15.0);
		Category cat = new Category("wh", 20.0, 1.0);
		overview.updateCategoryAmounts(cat, 40.0, 7.0);
		assertEquals(-5.0, overview.getUnallocatedBalance(), 0.00001);
	}

}
