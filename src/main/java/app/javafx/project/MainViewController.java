package app.javafx.project;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

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
        System.out.println("Seller");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
