package budget.testing.testOverview;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import budget.model.Category;
import budget.model.OverView;

class TestAddNewOutflow {

	@Test
	void testAddOneOutflow() {
		OverView ovr = new OverView();
		LocalDateTime date = LocalDateTime.now();
		Category cat = new Category("Groceries",0,0);
		
		ovr.addNewOutflow(26, date, "Milk", cat);
		
		assertEquals(1,ovr.getTransactions().size());
		assertEquals(-26, ovr.getOverallBalance());
	}
	
	@Test
	void testAddMultipleOutflow() {
		OverView ovr = new OverView();
		LocalDateTime date = LocalDateTime.now();
		Category cat = new Category("Groceries",0,0);
		
		ovr.addNewOutflow(26, date, "Milk", cat);
		ovr.addNewOutflow(16, date, "veggies", cat);
		ovr.addNewOutflow(15, date, "protein", cat);
		ovr.addNewOutflow(36, date, "eggs", cat);
		
		assertEquals(4,ovr.getTransactions().size());
		assertEquals(-93, ovr.getOverallBalance());
	}

}
