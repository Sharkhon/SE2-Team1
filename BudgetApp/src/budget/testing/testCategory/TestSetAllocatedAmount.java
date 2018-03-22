package budget.testing.testCategory;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import org.junit.jupiter.api.Test;

import budget.model.Category;

class TestSetAllocatedAmount {

	@Test
	void testAllocatedAmountWasChanged() {
		Category cat = new Category("ja", 2.5, 4.5);
		cat.setAllocatedAmount(5.5);
		DoubleProperty result = new SimpleDoubleProperty(5.5);
		assertEquals(result.toString(), cat.getAllocatedAmount().toString());
		
	}

}
