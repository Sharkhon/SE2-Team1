package budget.codebehinds;

import budget.model.Budget;
import budget.model.OverView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class NewBudgetViewModel {

	@FXML
    private Button submitButton;

    @FXML
    private Button cancelNewBudget;

    @FXML
    private TextField budgetNameBox;
    
    @FXML
    public Label errorLabel;

    private OverView overview;
    
    public NewBudgetViewModel(OverView overview) {
		this.overview = overview;
	}
    
    @FXML
    void makeNewBudget(ActionEvent event) {
    		if(this.budgetNameBox.textProperty().get().contains(",") || this.budgetNameBox.textProperty().get().contains("~") || this.budgetNameBox.textProperty().get().isEmpty()) {
    			this.errorLabel.setText("Invalid Name. Cannot have special cahracters");
    			this.errorLabel.setVisible(true);
    		} else {
    			this.overview.addBudget(new Budget(this.budgetNameBox.textProperty().get()));
    		}
    }

    @FXML
    void cancel(ActionEvent event) {
    		this.cancelNewBudget.getScene().getWindow().hide();
    }

}
