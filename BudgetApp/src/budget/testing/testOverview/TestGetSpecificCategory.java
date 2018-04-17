package budget.testing.testOverview;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import budget.model.Budget;
import budget.model.OverView;

class TestGetSpecificCategory {
	private OverView overview;
	private Budget budget;

	@Before
	public void setup() {
		overview = new OverView("rachetl");
		budget = new Budget("groceries", 200, 100);
		overview.addBudget(budget);
		overview.setCurrentBudget(0);

		overview.addNewCategory("Groceries", 50, 0);
		overview.addNewCategory("Category1", 50, 0);
		overview.addNewCategory("car", 100, 0);

	}

	@Test
	void testNullNameCategory() {
		setup();

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> overview.getSpecificCategory(null));
		assertEquals("name cannot be null", exception.getMessage());
	}

	@Test
	void testEmptyNameCategory() {
		setup();

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> overview.getSpecificCategory(""));
		assertEquals("Must Provide a Name", exception.getMessage());
	}

	@Test
	void testOneValidCategory() {
		setup();

		assertEquals(overview.getCategories().get(0), overview.getSpecificCategory("Groceries"));
	}

	@Test
	void testWantedCategoryIsFirst() {
		setup();

		assertEquals(overview.getCategories().get(0), overview.getSpecificCategory("Groceries"));
	}

	@Test
	void testWantedCategoryIsLast() {
		overview = new OverView("rachetl");
		budget = new Budget("groceries", 200, 100);
		overview.addBudget(budget);
		overview.setCurrentBudget(0);

		overview.addNewCategory("Category1", 50, 0);
		overview.addNewCategory("car", 100, 0);
		overview.addNewCategory("Groceries", 50, 0);

		assertEquals(overview.getCategories().get(2), overview.getSpecificCategory("Groceries"));
	}

	@Test
	void testWantedCategoryIsMiddle() {
		overview = new OverView("rachetl");
		budget = new Budget("groceries", 200, 100);
		overview.addBudget(budget);
		overview.setCurrentBudget(0);

		overview.addNewCategory("Category1", 50, 0);
		overview.addNewCategory("Groceries", 50, 0);
		overview.addNewCategory("car", 100, 0);
		

		assertEquals(overview.getCategories().get(1), overview.getSpecificCategory("Groceries"));
	}

}
