package budget.testing.testOverview;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


import budget.model.OverView;

class TestConstructor {

	@Test
	void testValidConstructor() {
		OverView over = new OverView(100);
		
		assertEquals(0, over.getCategories().size());
		assertEquals(100, over.getOverallBalance());
		assertEquals(100, over.getUnallocatedBalance());
	}
	
	@Test
	void testNegativeStartingBalance() {
		
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> new OverView(-1));
		assertEquals("overall Balance must be initially positive", exception.getMessage());

	}

}
