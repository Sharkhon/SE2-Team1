package budget.testing.testOverview;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import budget.model.Budget;
import budget.model.OverView;

class TestAddNewInflow {

	@Test
	void testAddOneInflow() {
		OverView ovr = new OverView("rachetl");
		LocalDateTime date = LocalDateTime.now();
		Budget budget = new Budget("home",200,100);
		ovr.addBudget(budget);
		ovr.setCurrentBudget(0); 
		
		ovr.addNewInflow(10.0, date, "Milk");
		
		assertEquals(1,ovr.getTransactions().size());
		assertEquals(210,ovr.getOverallBalanceProperty().doubleValue());
	}
	
	@Test
	void testAddMultipleInflow() {
		OverView ovr = new OverView("rachetl");
		LocalDateTime date = LocalDateTime.now();
		Budget budget = new Budget("home",200,100);
		ovr.addBudget(budget);
		ovr.setCurrentBudget(0);
		
		ovr.addNewInflow(20.0, date, "Milk");
		ovr.addNewInflow(15.0, date, "veggies");
		ovr.addNewInflow(5.0, date, "eggs");
		
		assertEquals(3,ovr.getTransactions().size());
		assertEquals(240,ovr.getOverallBalanceProperty().doubleValue());
	}

}