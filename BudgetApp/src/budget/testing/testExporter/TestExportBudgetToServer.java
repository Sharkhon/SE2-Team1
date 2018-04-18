package budget.testing.testExporter;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import budget.io.Exporter;
import budget.model.Budget;

class TestExportBudgetToServer {
	
	@Test
	void testExportNullUsername() {
		Exporter exporter = new Exporter();
		assertThrows(IllegalArgumentException.class, () -> exporter.ExportBudgetToServer(null, new Budget("test1")));
	}
	
	@Test
	void testExportEmptyUserName() {
		Exporter exporter = new Exporter();
		assertThrows(IllegalArgumentException.class, () -> exporter.ExportBudgetToServer("", new Budget("test1")));
	}
	
	@Test
	void testExportNullBudget() {
		Exporter exporter = new Exporter();
		assertThrows(IllegalArgumentException.class, () -> exporter.ExportBudgetToServer("test", null));
	}
	
	@Test
	void testExportValidBudgetWithValidUsername() {
		
	}

}
