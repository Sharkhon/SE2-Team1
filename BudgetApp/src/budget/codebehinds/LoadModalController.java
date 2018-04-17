package budget.codebehinds;

import budget.server.ServerAccess;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoadModalController {

    @FXML
    private Button loginButton;

    @FXML
    private TextField passwordBox;

    @FXML
    private TextField usernameBox;
    
    @FXML
    private Text invalidMessage;
    
    @FXML
    private Button createUserButton;

    @FXML
    public void initalize() {
    }
    
    @FXML
    public void login(ActionEvent event) {
    		if(ServerAccess.loginUser(this.usernameBox.getText(), this.passwordBox.getText())) {//Checks Server for User and Password
    			this.showMainView();
    		} else {
    			this.showErrorMessage("Could not find user " + this.usernameBox.getText());
    		}
    	
    }
    
    private void showErrorMessage(String message) {
    		this.invalidMessage.setText(message);
    		this.invalidMessage.visibleProperty().set(true);
    }
    
    @FXML
    public void create(ActionEvent event) {
    		if(ServerAccess.newUser(this.usernameBox.getText(), this.passwordBox.getText())) {
    			this.showErrorMessage("User created, now login");
    		} else {
    			this.showErrorMessage("Could not create user " + this.usernameBox.getText());
    		}
    }
    
    private void showMainView() {
		try {
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/MainView.fxml"));
			loader.setController(new OverviewViewModel(this.usernameBox.getText()));
			Scene scene = new Scene(loader.load(), 700, 600);
			primaryStage.setScene(scene);
			primaryStage.show();
			((Stage) this.loginButton.getScene().getWindow()).hide();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }

}
