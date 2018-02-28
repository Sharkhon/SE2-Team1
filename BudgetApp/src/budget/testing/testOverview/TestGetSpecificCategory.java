package budget.testing.testOverview;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import budget.model.Category;
import budget.model.OverView;

class TestGetSpecificCategory {

	@Test
	void testNullNameCategory() {

		OverView ovr = new OverView(100);

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> ovr.getSpecificCategory(null));
		assertEquals("name cannot be null", exception.getMessage());
	}

	@Test
	void testOneValidCategory() {
		OverView ovr = new OverView(100);

		ovr.addNewCategory("groceries");

		assertEquals(ovr.getCategories().get(0), ovr.getSpecificCategory("Groceries"));
	}
	
	@Test
	void testWantedCategoryIsFirst() {
		OverView ovr = new OverView(100);

		ovr.addNewCategory("groceries");
		ovr.addNewCategory("bills");
		ovr.addNewCategory("car");
		

		assertEquals(ovr.getCategories().get(0), ovr.getSpecificCategory("Groceries"));
	}
	
	@Test
	void testWantedCategoryIsLast() {
		OverView ovr = new OverView(100);

		ovr.addNewCategory("bills");
		ovr.addNewCategory("car");
		ovr.addNewCategory("groceries");
		

		assertEquals(ovr.getCategories().get(2), ovr.getSpecificCategory("Groceries"));
	}
	
	@Test
	void testWantedCategoryIsMiddle() {
		OverView ovr = new OverView(100);
		
		ovr.addNewCategory("groceries");

		ovr.addNewCategory("bills");
		
		ovr.addNewCategory("car");
		
		

		assertEquals(ovr.getCategories().get(1), ovr.getSpecificCategory("Bills"));
	}

}
