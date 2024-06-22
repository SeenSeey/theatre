package com.example.theatre.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HelloController {
    public Button btn_admin;
    public Button btn_client;
    public Button btn_employee;

    @FXML
    protected void handleAdminAuthorization(ActionEvent event) {
        OtherUtils.changeScene(event, "/com/example/theatre/admin.fxml", "Admin");
    }

    @FXML
    protected void handleClientAuthorization(ActionEvent event) {
        OtherUtils.changeScene(event, "/com/example/theatre/viewer.fxml", "Client");
    }

    @FXML
    protected void handleEmployeeAuthorization(ActionEvent event) {
        OtherUtils.changeScene(event, "/com/example/theatre/employee.fxml", "Employee");
    }
}