package budget.testing.testOutFlow;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;


import budget.model.Outflow;
import budget.model.Transaction;

class TestConstructor {

	@Test
	void testValidConstructor() {
		LocalDateTime currTime = LocalDateTime.now();
		Transaction outflow = new Outflow(50, currTime, "bills");
		
		assertEquals("bills", outflow.getTitle());
		assertTrue(currTime.equals(outflow.getDate()));
		assertEquals(50, outflow.getAmount());
		
	}
	
	@Test
	void testInvalidAmount() {
		
		LocalDateTime currTime = LocalDateTime.now();
		
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> new Outflow(-55, currTime, "bills"));
		assertEquals("Amount must be greater than zero", exception.getMessage());
		
		
		
	}

}
