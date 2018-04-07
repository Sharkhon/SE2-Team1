package budget.codebehinds;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoadModalController {

    @FXML
    private Button loginButton;

    @FXML
    private TextField passwordBox;

    @FXML
    private TextField usernameBox;
    
    @FXML
    private TextField invalidMessage;
    
    @FXML
    private Button createUserButton;

    @FXML
    public void login(ActionEvent event) {
    		if(true) {//Checks Server for User and Password
    			this.showMainView();
    		} else {
    			this.invalidMessage.visibleProperty().set(true);
    		}
    	
    }
    
    @FXML
    public void create(ActionEvent event) {
    		
    }
    
    private void showMainView() {
		try {
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("../view/MainView.fxml"));
			Scene scene = new Scene(root, 700, 600);
			primaryStage.setScene(scene);
			primaryStage.show();
			((Stage) this.loginButton.getScene().getWindow()).hide();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }

}
