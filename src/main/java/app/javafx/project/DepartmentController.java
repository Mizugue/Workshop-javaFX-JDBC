package app.javafx.project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model_entities.Department;
import model_services.DepartmentService;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DepartmentController implements Initializable {

    private DepartmentService service;

    @FXML
    private TableView<Department> departmentTableView;

    @FXML
    private TableColumn<Department, Integer> tableColumnId;

    @FXML
    private TableColumn<Department, String> tableColumnName;

    @FXML
    private Button btNew;

    private ObservableList<Department> obsList;

    @FXML
    public void OnBtNewAction(){
        System.out.println("oi");

    }

    public void setDepartmentService(DepartmentService service){
        this.service = service;
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeNodes();

    }

    private void initializeNodes() {
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    public void updateTableView(){
       if (service == null) {
           throw new IllegalStateException("Service = Null");
       }
        List<Department> list = service.findAll();
        obsList = FXCollections.observableArrayList(list);
        departmentTableView.setItems(obsList);


       }
    }

