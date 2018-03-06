package budget.viewmodel;

import java.time.LocalDateTime;

import budget.model.Category;
import budget.model.Inflow;
import budget.model.Outflow;
import budget.model.OverView;
import budget.model.Transaction;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import javafx.util.Callback;

public class OverviewViewModel {

	@FXML
	private Button EditButton;

	@FXML
	private Label TotalAmountLabel;

	@FXML
	private Button AddButton;

	@FXML
	private TableView<Category> categoryView;
	
	@FXML
	private TableView<Transaction> transactionView;

	@FXML
	private Label UnallocatedAmountLabel;
	
	@FXML
	private TableColumn<Category, String> catNameCol;
	
	@FXML
	private TableColumn<Category, Double> catAllocatedCol;
	
	@FXML
	private TableColumn<Category, Double> catSpentCol;
	
	@FXML
	private TableColumn<Transaction, String> transactionTitleCol;
	
	@FXML
	private TableColumn<Transaction, LocalDateTime> transactionDateCol;
	
	@FXML
	private TableColumn<Transaction, Double> transactionAmountCol;
	
	@FXML
	private TableColumn<Transaction, String> transactionTypeCol;
	
	@FXML
	private TableColumn<Transaction, String> transactionCategory;
	
	private OverView overview;
	
	public OverviewViewModel() {
		this.categoryView = new TableView<Category>();	
		this.overview = new OverView();
		this.overview.addNewCategory("name1");
		this.overview.getSpecificCategory("name1").setAllocatedAmount(10);
	}
	
	@FXML
	public void initialize() {
		this.initailizeValues();
	}
	
	private void initailizeValues() {
		this.showOverview();
		this.showTransactions();
	}
	
	private void showOverview() {
		this.catNameCol.setCellValueFactory(cellData -> cellData.getValue().getName());
		this.catNameCol.prefWidthProperty().bind(this.categoryView.widthProperty().divide(3));
		
		this.catAllocatedCol.setCellValueFactory(cellData -> cellData.getValue().getAllocatedAmount().asObject());
		this.catAllocatedCol.prefWidthProperty().bind(this.categoryView.widthProperty().divide(3));
		
		this.catSpentCol.setCellValueFactory(cellData -> cellData.getValue().getSpentAmount().asObject());
		this.catSpentCol.prefWidthProperty().bind(this.categoryView.widthProperty().divide(3));
		
		this.categoryView.setItems(this.overview.getCategories());
	}
	
	private void showTransactions() {
		int inOutWidthVal = 60;
		
		this.transactionAmountCol.prefWidthProperty().bind((this.transactionView.widthProperty().subtract(inOutWidthVal).divide(4)));
		this.transactionAmountCol.setCellValueFactory(cellData -> cellData.getValue().getAmount().asObject());
		
		this.transactionDateCol.prefWidthProperty().bind((this.transactionView.widthProperty().subtract(inOutWidthVal).divide(4)));
		this.transactionDateCol.setCellValueFactory(cellData -> cellData.getValue().getDate());
		
		this.transactionTitleCol.prefWidthProperty().bind((this.transactionView.widthProperty().subtract(inOutWidthVal).divide(4)));
		this.transactionTitleCol.setCellValueFactory(cellData -> cellData.getValue().getTitle());
		
		this.transactionCategory.prefWidthProperty().bind((this.transactionView.widthProperty().subtract(inOutWidthVal).divide(4)));
		this.transactionCategory.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Transaction, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Transaction, String> arg0) {
				if (arg0.getValue() instanceof Inflow) {
					return new SimpleStringProperty("N/A");
				}
				else 
				{
					return ((Outflow)arg0.getValue()).getCategoryName();
				}
			}
			
		});
		
		this.transactionTypeCol.setPrefWidth(inOutWidthVal);
		this.transactionTypeCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Transaction, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Transaction, String> arg0) {
				if (arg0.getValue() instanceof Inflow) {
					return new SimpleStringProperty("Inflow");
				}
				else 
				{
					return new SimpleStringProperty("Outflow");
				}
			}
			
		});
		
		this.overview.addNewInflow(100, LocalDateTime.now(), "test 1");
		this.overview.addNewOutflow(200, LocalDateTime.now(), "test 1", new Category("testing", 0, 0));
		this.transactionView.setItems(this.overview.getTransactions());
	}
	
	private boolean isOverview() {
		return this.categoryView.isVisible();
	}
	
	@FXML
	public void addItem() {
		if(this.isOverview()) {
			this.showAddCategoryView();
		}
		else 
		{
			System.out.println("other");
		}
	}
	
	private void showAddCategoryView() {
		try {
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/NewCategoryView.fxml"));
			loader.setController(new NewCategoryViewController(this.overview));
			Parent root = loader.load();
			
			Scene scene = new Scene(root, 450, 200);
			primaryStage.setScene(scene);
			primaryStage.initOwner(this.AddButton.getScene().getWindow());
			primaryStage.initModality(Modality.APPLICATION_MODAL);
			primaryStage.showAndWait();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
