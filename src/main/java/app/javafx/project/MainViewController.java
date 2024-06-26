package app.javafx.project;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class MainViewController extends Application {
    private static Scene mainScene;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainViewController.class.getResource("MainView.fxml"));
        mainScene = new Scene(fxmlLoader.load());
        stage.setTitle("Javafx App");
        stage.setScene(mainScene);
        stage.show();
    }

    public static Scene getMainScene() {
        return mainScene;
    }

    public static void main(String[] args) {
        launch();
    }

}

