package budget.testing.testOverview;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import budget.model.OverView;

class TestAddCategory {

	@Test
	void test() {
		OverView over = new OverView();
		
		over.addNewCategory("Groceries");
		
		assertEquals(1,over.getCategories().size());
		
	}

}
