package budget.codebehinds;

import java.time.LocalDateTime;

import budget.model.Category;
import budget.model.Inflow;
import budget.model.Outflow;
import budget.model.OverView;
import budget.model.Transaction;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.util.Callback;

public class NewTransactionDialogController implements ChangeListener<Toggle> {

	@FXML
    private TextField AmountTextBox;

    @FXML
    private ComboBox<Category> CategoriesComboBox;

    @FXML
    private Button CancelButton;

    @FXML
    private RadioButton OutflowRadioButton;

    @FXML
    private Button CreateTransactionButton;

    @FXML
    private TextField TitleTextbox;

    @FXML
    private RadioButton InflowRadioButton;
    
    @FXML
    private DatePicker theDatePicker;
    
    @FXML
    private RadioButton RecurDaily;
    
    @FXML
    private RadioButton RecurWeekly;
    
    @FXML
    private RadioButton RecurMonthly;
    
    @FXML
    private RadioButton RecurYearly;
    
    private OverView overview;
    
    private ToggleGroup group;
    
    public NewTransactionDialogController(OverView overview) {
    		this.overview = overview;
    }
    
    @FXML
    public void initialize() {
    		this.CategoriesComboBox.getItems().addAll(this.overview.getCategories());
    		this.CategoriesComboBox.setCellFactory(new Callback<ListView<Category>, ListCell<Category>>() {
    			public ListCell<Category> call(ListView<Category> p) {
                     
                    final ListCell<Category> cell = new ListCell<Category>(){
     
                        @Override
                        protected void updateItem(Category t, boolean bln) {
                            super.updateItem(t, bln);
                             
                            if(t != null){
                                setText(t.getName().get());
                            }else{
                                setText(null);
                            }
                        }
      
                    };
                     
                    return cell;
                }
    		});
    		this.group = new ToggleGroup();
    		this.InflowRadioButton.setToggleGroup(this.group);
    		this.OutflowRadioButton.setToggleGroup(this.group);
    		
    		this.group.selectedToggleProperty().addListener(this);
    		
    		ToggleGroup recurGroup = new ToggleGroup();
    		this.RecurDaily.setToggleGroup(recurGroup);
    		this.RecurMonthly.setToggleGroup(recurGroup);
    		this.RecurWeekly.setToggleGroup(recurGroup);
    		this.RecurYearly.setToggleGroup(recurGroup);
    }

    @FXML
    void createNewTransaction(ActionEvent event) {
    		if(this.group.getSelectedToggle() == null || this.AmountTextBox.getText().isEmpty() || this.TitleTextbox.getText().isEmpty() || this.theDatePicker.getValue() == null) {
    			return;//TODO Show that the inputs are not valid
    		}
    		
    		try {
    			Double amount = Double.parseDouble(this.AmountTextBox.getText());
        		LocalDateTime date = this.theDatePicker.getValue().atStartOfDay();
        		String title = this.TitleTextbox.getText();
        		
    			
    			if(this.group.getSelectedToggle().equals(this.InflowRadioButton)) {
        			this.overview.addNewInflow(amount, date, title);
        		} else {
        			String category = this.CategoriesComboBox.getValue().getName().get();
        			this.overview.addNewOutflow(amount, date, title, category);
        		}
    			
    			this.AmountTextBox.getScene().getWindow().hide();
    		} catch (Exception e) {
    			//TODO Show that the Iputs are null 
    		}
    }

    @FXML
    void cancelOperation(ActionEvent event) {
    		this.AmountTextBox.getScene().getWindow().hide();
    }
    
    @FXML
    void EnableCategoryComboBox(ActionEvent event) {
    		//this.CategoriesComboBox.setDisable(false);
    }

	@Override
	public void changed(ObservableValue<? extends Toggle> arg0, Toggle arg1, Toggle arg2) {
		if(this.group.getSelectedToggle() == null) {
			return;
		}
		
		if(this.group.getSelectedToggle().equals(this.OutflowRadioButton)) {
			this.CategoriesComboBox.setDisable(false);
		} else {
			this.CategoriesComboBox.setDisable(true);
		}
	}
}
