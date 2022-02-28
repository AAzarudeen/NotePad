package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {
    public static Stage stage;
    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        changeTitle("*Untitled Note");
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getClassLoader().getResourceAsStream("Resource/App.png"))));
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void changeTitle(String title){
        stage.setTitle(title);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
