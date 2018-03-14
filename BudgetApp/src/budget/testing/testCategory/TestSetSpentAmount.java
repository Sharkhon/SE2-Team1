package budget.testing.testCategory;

import static org.junit.Assert.assertEquals;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import budget.model.Category;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

class TestSetSpentAmount {

	@Test
	void testSpentAmountWasChanged() {
		Category cat = new Category("ja", 2.5, 4.5);
		cat.setSpentAmount(5.5);
		DoubleProperty result = new SimpleDoubleProperty(5.5);
		assertEquals(result.toString(), cat.getSpentAmount().toString());
		
	}

}
