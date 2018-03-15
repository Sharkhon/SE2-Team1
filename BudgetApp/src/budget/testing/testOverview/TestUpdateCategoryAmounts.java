package budget.testing.testOverview;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import budget.model.Category;
import budget.model.OverView;

class TestUpdateCategoryAmounts {

	@Test
	void testUpdatingTheCategoryAndSeeingIfItUpdatesCorrectly() {
		OverView overview = new OverView(110.0);
		Category cat = new Category("wh", 20.0, 1.0);
		overview.updateCategoryAmounts(cat, 40.0, 7.0);
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
