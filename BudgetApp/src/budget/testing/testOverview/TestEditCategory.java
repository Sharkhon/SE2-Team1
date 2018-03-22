package budget.testing.testOverview;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import budget.model.OverView;

class TestEditCategory {

	@Test
	void testChangingCategoryNameAndAmount() {
		OverView overview = new OverView(5.5);
		overview.addNewCategory("hey", 2.4);
		overview.editCategory("hey", "bye", 2.5);
		assertEquals("bye".toString(), overview.getSpecificCategory("bye").toString());
		assertEquals(3.0, overview.getUnallocatedBalance(), 0.0001);
		
	}

}
