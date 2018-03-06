package budget.viewmodel;

import budget.model.OverView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class NewCategoryViewController {
	 	@FXML
	    private Button CreateNewCategoryButton;

	    @FXML
	    private TextField categoryNameBox;

	    @FXML
	    private Button CancelButton;

	    @FXML
	    private TextField initalAllocatedAmount;
	    private String nameText;
	    
	    private OverView overview;

	    public NewCategoryViewController(OverView overview) {
			this.overview = overview;
		}
	    
	    @FXML
	    public void initialize() {
	    		this.initalAllocatedAmount.setText(this.nameText);
	    }
	    
	    @FXML
	    public void CreateNewCategory(ActionEvent event) {
	    		if (!this.categoryNameBox.textProperty().get().isEmpty()) {
	    			this.overview.addNewCategory(this.categoryNameBox.textProperty().get());
	    			this.CancelButton.getScene().getWindow().hide();
	    		}
	    }

	    @FXML
	    public void Cancel(ActionEvent event) {
	    		this.CancelButton.getScene().getWindow().hide();
	    }

}
