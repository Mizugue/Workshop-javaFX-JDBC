package app.javafx.project;

import gui_utils.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    @FXML
    private MenuItem menuSeller;

    @FXML
    private MenuItem menuDepartment;

    @FXML
    private MenuItem menuAbout;

    @FXML
    public void OnMenuSellerAction(){
        System.out.println("Seller");
    }

    @FXML
    public void OnMenuDepartmentAction(){
        System.out.println("Seller");
    }

    @FXML
    public void OnMenuAboutAction(){
        loadView("About.fxml");


    }

    private void loadView(String absoluteName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
            VBox newRoot = loader.load();

            Scene mainScene = Main.getMainScene();
            VBox mainVbox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();

            Node mainMenu = mainVbox.getChildren().get(0);
            mainVbox.getChildren().clear();
            mainVbox.getChildren().add(mainMenu);
            mainVbox.getChildren().addAll(newRoot.getChildren());

        } catch (IOException e) {
            Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), Alert.AlertType.ERROR);
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
