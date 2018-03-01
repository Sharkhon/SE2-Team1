package budget.viewmodel;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewController {

	private Stage primaryStage;
	
	public ViewController(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	public void ShowGUI(String path, int height, int width) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource(path));
			Scene scene = new Scene(root, width, height);
			this.primaryStage.setScene(scene);
			this.primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
