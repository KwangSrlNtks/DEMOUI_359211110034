package sample;

import Admin.AdminController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private loginModel loginModel = new loginModel();
    @FXML
    private Label dbStatus;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button btnLogin;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (this.loginModel.isDatabaseConnection()){
            this.dbStatus.setText("Connected To DB");
        }else {
            this.dbStatus.setText("Not Connected To DB");
        }

    }//initialize

    @FXML
    public void Login(ActionEvent event){

        try {
            if (this.loginModel.isLogin(this.username.getText(),
                    this.password.getText())) {
                Stage stage = (Stage) this.btnLogin.getScene().getWindow();
                stage.close();

                adminLogin();

            } else {
                JOptionPane.showMessageDialog(null,"Your username or "+
                "password is not corrected.");
            }
        }catch (Exception localException){
            localException.printStackTrace();
        }

    }//Login

    private void adminLogin() {
       // JOptionPane.showMessageDialog(null," Welcome To System");

    try {
        Stage adminstage = new Stage();
        FXMLLoader adminLoader = new FXMLLoader();
        Pane adminRoot = (Pane) adminLoader.load(getClass().getResource("/Admin/AdminDashBoard.fxml").openStream());

        AdminController adminController = (AdminController) adminLoader.getController();
        Scene scene = new Scene(adminRoot);
        adminstage.setScene(scene);
        adminstage.setTitle("Admin Dashbord");
        adminstage.setResizable(false);
        adminstage.show();

    }catch (IOException ex){
        ex.printStackTrace();
    }
    }//adminLogin


}
