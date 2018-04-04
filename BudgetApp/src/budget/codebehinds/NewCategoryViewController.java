package budget.codebehinds;

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
		if (this.categoryNameBox.getText().isEmpty()) {
			return;// TODO Show add title 
		}

		handleAddNewCategory();

		this.CancelButton.getScene().getWindow().hide();
	}

	@FXML
	public void Cancel(ActionEvent event) {
		this.CancelButton.getScene().getWindow().hide();
	}
	
	

	private void handleAddNewCategory() {
		if (this.initalAllocatedAmount.getText().isEmpty()) {
			this.overview.addNewCategory(this.categoryNameBox.getText());
		} else {
			this.overview.addNewCategory(this.categoryNameBox.getText(),
					Double.parseDouble(this.initalAllocatedAmount.getText()));
		}
	}
}
