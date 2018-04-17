package budget.codebehinds;

import java.io.FileNotFoundException;
import java.text.Format;
import java.text.NumberFormat;
import java.time.LocalDateTime;

import budget.io.ImportLocalFile;
import budget.model.Budget;
import budget.model.Category;
import budget.model.Inflow;
import budget.model.Outflow;
import budget.model.OverView;
import budget.model.Transaction;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

public class OverviewViewModel {

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
	
	@FXML
	private Button DeleteItemButton;
	
	@FXML
	private ComboBox<Budget> budgetSelector;
	
	@FXML
    private MenuItem importBudgetMenuItem;
	
	private OverView overview;
	
	private StringConverter<Number> numberConverter;
	
	public OverviewViewModel(String username) {
		this.categoryView = new TableView<Category>();
		this.overview.loadUser();
		this.overview = new OverView(username);
		this.numberConverter = new NumberStringConverter(NumberFormat.getCurrencyInstance());
	}
	
	@FXML
	public void initialize() {
		this.initailizeValues();
	}
	
	private void initailizeValues() {
		this.showOverview();
		this.showTransactions();
		
		Bindings.bindBidirectional(this.TotalAmountLabel.textProperty(), this.overview.getOverallBalanceProperty(), this.numberConverter);
		Bindings.bindBidirectional(this.UnallocatedAmountLabel.textProperty(), this.overview.getUnallocatedBalanceProperty(), this.numberConverter);
		
		this.budgetSelector.setItems(this.overview.getBudgets());
		this.budgetSelector.selectionModelProperty().get().select(this.overview.getCurrentBudget());
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
		this.transactionView.setItems(this.overview.getTransactions());
	}
	
	@FXML
	public void addItem() {
		if(this.categoryView.isVisible()) {
			this.showAddCategoryView(new NewCategoryViewController(this.overview));
		}
		
		if(this.transactionView.isVisible())
		{
			this.showNewTransactionView(new NewTransactionDialogController(this.overview));
		}
	}
	
	@FXML
	public void DeleteItem() {
		if(this.categoryView.isVisible() && this.categoryView.getSelectionModel().getSelectedItem() != null) {
			this.overview.RemoveCategory(this.categoryView.getSelectionModel().getSelectedItem());
		}
		
		if(this.transactionView.isVisible() && this.transactionView.getSelectionModel().getSelectedItem() != null) {
			this.overview.RemoveTransaction(this.transactionView.getSelectionModel().getSelectedItem());
		}
	}
	
	@FXML
    void ImportBudget(ActionEvent event) {
		ImportLocalFile importer = new ImportLocalFile();
		try {
			this.overview.addBudget(importer.importLocalFileFromPicker());
		} catch (FileNotFoundException e) {
			new Alert(AlertType.ERROR).contentTextProperty().set(e.getMessage());
		}
    }
	
	@FXML
    void ChangeBudget(ActionEvent event) {

    }
	
	private void showAddCategoryView(NewCategoryViewController controller) {
		try {
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/NewCategoryView.fxml"));
			loader.setController(controller);
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
	
	private void showNewTransactionView(NewTransactionDialogController controller) {
		try {
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/NewTransactionDialog.fxml"));
			loader.setController(controller);
			Parent root = loader.load();
			
			Scene scene = new Scene(root, 450, 275);
			primaryStage.setScene(scene);
			primaryStage.initOwner(this.AddButton.getScene().getWindow());
			primaryStage.initModality(Modality.APPLICATION_MODAL);
			primaryStage.showAndWait();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
