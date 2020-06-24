package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;


public class Main extends Application {
	public static Stage sageRoot = new Stage();
    public static Scene scene;
	@Override
	public void start(Stage primaryStage) {
		try {
			
			  Parent root = FXMLLoader.load(getClass().getResource("/application/vue/Accueil.fxml"));
		        scene = new Scene(root);
		        sageRoot.setScene(scene);
		        sageRoot.setTitle("Gestion des patients");
		        sageRoot.getIcons().add(new Image("/application/images/logo.png"));
		        sageRoot.show();
		        
		       
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
