package budget.testing.testOverview;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import budget.model.Category;
import budget.model.OverView;

class TestAddNewInflow {

	@Test
	void testAddOneInflow() {
		OverView ovr = new OverView();
		LocalDateTime date = LocalDateTime.now();
		
		ovr.addNewInflow(10.0, date, "Milk");
		
		assertEquals(1,ovr.getTransactions().size());
	}
	
	@Test
	void testAddMultipleOutflow() {
		OverView ovr = new OverView();
		LocalDateTime date = LocalDateTime.now();
		
		ovr.addNewInflow(20.0, date, "Milk");
		ovr.addNewInflow(15.0, date, "veggies");
		ovr.addNewInflow(5.0, date, "eggs");
		
		assertEquals(3,ovr.getTransactions().size());
	}

}