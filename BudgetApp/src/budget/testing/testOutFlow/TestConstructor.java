package budget.testing.testOutFlow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import budget.model.Category;
import budget.model.Outflow;
import budget.model.Transaction;

class TestConstructor {

	@Test
	void testValidConstructor() {
		LocalDateTime currTime = LocalDateTime.now();
		Transaction outflow = new Outflow(50, currTime, "bills", new Category("title", 0, 0));
		
		assertEquals("bills", outflow.getTitle().get().toString());
		assertTrue(currTime.equals(outflow.getDate().getValue()));
		assertEquals(50, outflow.getAmount().intValue());
		
	}
	
	@Test
	void testInvalidAmount() {
		
		LocalDateTime currTime = LocalDateTime.now();
		
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> new Outflow(-55, currTime, "bills", new Category("title", 0, 0)));
		assertEquals("Amount must be greater than zero", exception.getMessage());
		
		
		
	}

}