package budget.testing.testOverview;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import budget.model.Budget;
import budget.model.OverView;

class TestRemoveTransaction {

	@Test
	void testOneTransaction() {
		OverView ovr = new OverView("rachetl");
		LocalDateTime date = LocalDateTime.now();
		Budget budget = new Budget("home",200,100);
		ovr.addBudget(budget);
		ovr.setCurrentBudget(0); 
		
		ovr.addNewInflow(10.0, date, "Milk");

		ovr.RemoveTransaction("Milk");

		assertEquals(0,ovr.getTransactions().size());
		assertEquals(210,ovr.getOverallBalanceProperty().doubleValue());
	}
	

	@Test
	void testMultipleInflow() {
		OverView ovr = new OverView("rachetl");
		LocalDateTime date = LocalDateTime.now();
		Budget budget = new Budget("home",200,100);
		ovr.addBudget(budget);
		ovr.setCurrentBudget(0);
		
		ovr.addNewInflow(20.0, date, "Milk");
		ovr.addNewInflow(15.0, date, "veggies");
		ovr.addNewInflow(5.0, date, "eggs");
		
		ovr.RemoveTransaction("Milk");
		ovr.RemoveTransaction("veggies");
		
		assertEquals(1,ovr.getTransactions().size());
		assertEquals(240,ovr.getOverallBalanceProperty().doubleValue());
	}
	
	@Test
	void testOneOutflow() {
		OverView ovr = new OverView("rachetl");
		Budget budget = new Budget("home",200,100);
		ovr.addBudget(budget);
		ovr.setCurrentBudget(0);
		LocalDateTime date = LocalDateTime.now();
		ovr.addNewCategory("groceries", 0, 0);
		ovr.addNewOutflow(50, date, "Milk", "groceries");
		ovr.RemoveTransaction("Milk");

		
		
		
		assertEquals(0,ovr.getTransactions().size());
		assertEquals(150, ovr.getOverallBalanceProperty().doubleValue());
	}
	
	@Test
	void testMultipleOutflow() {
		OverView ovr = new OverView("rachetl");
		Budget budget = new Budget("home",200,100);
		ovr.addBudget(budget);
		ovr.setCurrentBudget(0);
		LocalDateTime date = LocalDateTime.now();
		ovr.addNewCategory("groceries", 0, 0);
		
		ovr.addNewOutflow(26, date, "Milk", "groceries");
		ovr.addNewOutflow(16, date, "veggies", "groceries");
		ovr.addNewOutflow(15, date, "protein", "groceries");
		ovr.addNewOutflow(36, date, "eggs", "groceries");
		
		ovr.RemoveTransaction("Milk");
		ovr.RemoveTransaction("eggs");
		
		assertEquals(2,ovr.getTransactions().size());
		assertEquals(107, ovr.getOverallBalanceProperty().doubleValue());
	}
}
