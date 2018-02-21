package budget.testing.testCategory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import budget.model.Category;

class TestConstructor {

	@Test
	void testNullName() {

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> new Category(null, 25, 2));
		assertEquals("Must provide valid name for Category", exception.getMessage());

	}

	@Test
	void testEmptyName() {

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> new Category("", 25, 2));
		assertEquals("Must provide valid name for Category", exception.getMessage());

	}

	@Test
	void testInvalidAllocatedAmount() {

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> new Category("Groceries", -500, 2));
		assertEquals("allocated amount must be positive amount", exception.getMessage());

	}

	@Test
	void testInvalidSpentAmount() {

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> new Category("Groceries", 500, -40));
		assertEquals("spent amount must be positive amount", exception.getMessage());

	}

	@Test
	void testValidConstructor() {
		Category cat = new Category("Groceries", 500, 40);

		assertEquals("Groceries", cat.getName());

		assertEquals(500, cat.getAllocatedAmount());

		assertEquals(40, cat.getSpentAmount());

	}

}
