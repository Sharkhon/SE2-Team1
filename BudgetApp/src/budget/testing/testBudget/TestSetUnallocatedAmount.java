package budget.testing.testBudget;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import budget.model.Budget;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

class TestSetUnallocatedAmount {

	@Test
	void testToSeeIfAmountChanged() {
		Budget budget = new Budget("ja", 2.5, 4.5);
		DoubleProperty result = new SimpleDoubleProperty(8.9);
		budget.setUnallocatedAmount(result);
		assertEquals(result.get(), budget.getUnallocatedAmount().get(), 0.00);
		
		
	}

}
