package budget.viewmodel;

import java.time.LocalDateTime;

import budget.model.Transaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class OverviewViewModel {

	@FXML
	private Button EditButton;

	@FXML
	private Label TotalAmountLabel;

	@FXML
	private Button AddButton;

	@FXML
	private TableView<?> tableview;

	@FXML
	private Label UnallocatedAmountLabel;
	
	public OverviewViewModel() {
		this.tableview = new TableView<Transaction>();		
	}
	
	@FXML
	public void initialize() {
		this.initailizeValues();
	}
	
	private void initailizeValues() {
		System.out.println("test");
		this.showTransactions();
	}
	
	private void showOverview() {
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void showTransactions() {
		TableColumn col1 = new TableColumn("Title");
		col1.setCellValueFactory(new PropertyValueFactory<Transaction, String>("tileCol"));
		
		TableColumn col2 = new TableColumn("Date");
		col2.setCellValueFactory(new PropertyValueFactory<Transaction, LocalDateTime>("dateCol"));
		
		TableColumn col3 = new TableColumn("Amount");
		col3.setCellValueFactory(new PropertyValueFactory<Transaction, Double>("amountCol"));
		
		
		this.tableview.getColumns().addAll(col1, col2, col3);
	}

}
