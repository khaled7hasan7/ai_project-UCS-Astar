package ai_project;

import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class mainAIProject extends Application {
	
	
	
	public void start(Stage stage) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(mainAIProject.class.getResource("MainWindow.fxml"));

			Scene scene = new Scene(fxmlLoader.load());

			stage.setResizable(false);
			stage.setTitle("project 1");
			stage.setScene(scene);
			stage.show();
			}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROOOOOOOOOOOR ");
		}

	}
	
	public static void main(String[] args) throws FileNotFoundException{
		
		
		launch(args);
	}

}
