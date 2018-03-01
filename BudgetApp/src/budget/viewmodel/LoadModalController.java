package budget.viewmodel;

import java.time.LocalDateTime;

import budget.model.Outflow;
import budget.model.Transaction;
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
    public void login(ActionEvent event) {
    		if(true) {//Checks Server for User and Password
    			this.showMainView();
    		} else {
    			//Deny access
    		}
    	
    }
    
    private void showMainView() {
    		Transaction yes = new Outflow(0, LocalDateTime.parse("2007-12-03T10:15:30"), "title");
    	
		try {
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("../view/MainView.fxml"));
			Scene scene = new Scene(root, 800, 600);
			primaryStage.setScene(scene);
			primaryStage.show();
			((Stage) this.loginButton.getScene().getWindow()).hide();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }

}
