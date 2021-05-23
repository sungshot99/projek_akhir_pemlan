package TextEditor;

import javafx.application.Application;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
        import javafx.scene.text.Font;
        import javafx.stage.Stage;

import java.util.List;

public class Utama extends Application {

    static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Text Editor"); //ganti nama program
        primaryStage.getIcons().add(new Image("file:Pensil.png")); //Ganti icon program
        primaryStage.setScene(new Scene(root, 600, 500));
        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(500);
        primaryStage.show();
        stage = primaryStage;
    }


    public static void main(String[] args) {
        launch(args);
    }
}

