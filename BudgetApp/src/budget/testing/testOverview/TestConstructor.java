package budget.testing.testOverview;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import budget.model.Budget;
import budget.model.OverView;

class TestConstructor {

	@Test
	void testValidConstructor() {
		OverView over = new OverView("rachetl");
		Budget budget =  new Budget("groceries", 200, 100);
		over.addBudget(budget);
		over.setCurrentBudget(0);
		
		assertEquals(0, over.getCategories().size());
		assertEquals(200.0, over.getOverallBalanceProperty().doubleValue());
		assertEquals(100.0, over.getUnallocatedBalanceProperty().doubleValue());

	}
	


}
