package app.javafx.project;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import exceptions.ValidationException;
import gui_utils.Alerts;
import gui_utils.Constraints;
import gui_utils.DataChangeListener;
import gui_utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Department;
import model_services.DepartmentService;

public class DepartmentFormController implements Initializable {

    private Department departmentEntity;

    private DepartmentService departmentService;

    private List<DataChangeListener> dataChangeListeners = new ArrayList<>();

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private Label labelErrorName;

    @FXML
    private Button btSave;

    @FXML
    private Button btCancel;

    public void setDepartment(Department departmentEntity) {
        this.departmentEntity = departmentEntity;
    }

    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    public void subscribeDataChangeListener(DataChangeListener listener) {
        dataChangeListeners.add(listener);
    }

    @FXML
    public void onSaveButtonClicked(ActionEvent event) {
        try {
            validateFormAndSave();
            Utils.currentStage(event).close();
        } catch (ValidationException e) {
            setFormErrorMessages(e.getErrors());
        } catch (Exception e) {
            Alerts.showAlert("Error", null, "Error saving department: " + e.getMessage(), AlertType.ERROR);
        }
    }

    @FXML
    public void onCancelButtonClicked(ActionEvent event) {
        Utils.currentStage(event).close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeConstraints();
    }

    private void initializeConstraints() {
        Constraints.setTextFieldInteger(txtId);
        Constraints.setTextFieldMaxLength(txtName, 30);
    }

    public void updateFormData() {
        if (departmentEntity == null) {
            throw new IllegalStateException("Department entity was null");
        }
        txtId.setText(String.valueOf(departmentEntity.getId()));
        txtName.setText(departmentEntity.getName());
    }

    private void validateFormAndSave() {
        Department department = getFormData();
        departmentService.saveOrUpdate(department);
        notifyDataChangeListeners();
    }

    private Department getFormData() {
        Department department = new Department();

        ValidationException validationException = new ValidationException("Validation error");

        department.setId(Utils.tryParseToInt(txtId.getText()));

        String departmentName = txtName.getText();
        if (departmentName == null || departmentName.trim().isEmpty()) {
            validationException.addError("name", "Field can't be empty");
        }
        department.setName(departmentName);

        if (!validationException.getErrors().isEmpty()) {
            throw validationException;
        }

        return department;
    }

    private void notifyDataChangeListeners() {
        for (DataChangeListener listener : dataChangeListeners) {
            listener.onDataChanged();
        }
    }

    private void setFormErrorMessages(Map<String, String> errors) {
        if (errors.containsKey("name")) {
            labelErrorName.setText(errors.get("name"));
        }
    }


}
