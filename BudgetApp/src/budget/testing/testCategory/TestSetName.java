package budget.testing.testCategory;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import budget.model.Category;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

class TestSetName {

	@Test
	void testNameWasChanged() {
		Category cat = new Category("ja", 2.5, 4.5);
		cat.setName("la");
		StringProperty result = new SimpleStringProperty("la");
		assertEquals(result.get(), cat.getName().get());
		
	}

}
