package budget.viewmodel;

import budget.model.Category;
import budget.model.OverView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EditCategoryDialogController {

	@FXML
    private TextField allocatedAmount;

    @FXML
    private TextField categoryNameBox;

    @FXML
    private Button UpdateCategoryButton;

    @FXML
    private Button CancelButton;
    
    @FXML
    private Label errorLabel;
    
    private Category currentCategory;
    private OverView overview;

    public EditCategoryDialogController(OverView overview, Category category) {
		this.currentCategory = category;
		this.overview = overview;
	}
    
    @FXML
    public void initialize() {
    		this.categoryNameBox.setText(this.currentCategory.getName().get());
    		this.allocatedAmount.setText(Double.toString(this.currentCategory.getAllocatedAmount().doubleValue()));
    }
    
    @FXML
    void UpdateCategory(ActionEvent event) {
    		try {
    			if(this.currentCategory.validEdit(this.categoryNameBox.getText(), Double.parseDouble(this.allocatedAmount.getText()), this.overview.getUnallocatedBalance())) {
        			this.overview.editCategory(this.currentCategory.getName().get(), this.categoryNameBox.getText(), Double.parseDouble(this.allocatedAmount.getText()));
        			this.CancelButton.getScene().getWindow().hide();
        		} else {
        			this.showErrorStatement();
        		}
    		} catch (Exception e) {
    			this.showErrorStatement();
    		}
    	
    		
    		
    }
    
    private void showErrorStatement() {
    		this.errorLabel.setVisible(true);
    }

    @FXML
    void Cancel(ActionEvent event) {
    		this.CancelButton.getScene().getWindow().hide();
    }
}
