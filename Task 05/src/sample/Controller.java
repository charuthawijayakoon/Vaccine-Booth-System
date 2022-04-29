package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class Controller {

    @FXML
    private Button btnadd;
    @FXML
    private Button btnview;
    @FXML
    private Button btnquit;
    @FXML
    private Button btnback;
    @FXML
    private Button btncontact;
    @FXML
    private Button btnclose;

    public void setBtnadd() throws IOException {
        Stage stage = (Stage)this.btnadd.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("add_contact.fxml"));
        primaryStage.setTitle("Add Contact");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public void setBtnclose(){
        Stage stage = (Stage) btnclose.getScene().getWindow();
        stage.close();
    }

    public void setBtnview() throws IOException {

        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("view_contact.fxml"));
        primaryStage.setTitle("View Contact");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public void setBtncontact() throws IOException {
        Alert ab = new Alert(Alert.AlertType.CONFIRMATION);
        ab.setContentText("Contact added successfully");
        Optional<ButtonType> result = ab.showAndWait();
        ButtonType button = result.orElse(ButtonType.CANCEL);
        if (button == ButtonType.OK) {
            Stage stage = (Stage)this.btnback.getScene().getWindow();
            stage.close();
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            primaryStage.setTitle("Add Contact");
            primaryStage.setScene(new Scene(root, 300, 275));
            primaryStage.show();
        } else {
            System.out.println("Canceled");
               }

    }

    public void setBtnback() throws IOException {
        Stage stage = (Stage)this.btnback.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Add Contact");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public void setBtnquit(){
        Stage stage = (Stage)this.btnquit.getScene().getWindow();
        stage.close();
    }


}
