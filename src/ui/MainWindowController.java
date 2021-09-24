package ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Store;
import java.io.IOException;

public class MainWindowController {

    final private String FOLDER = "fxml/";
    private Store myStore;

    @FXML
    private BorderPane mainPane;
    @FXML
    private TextField amountCashiers;

    public MainWindowController(Store myStore) {
        this.myStore = myStore;
    }//End Constructor

    @FXML
    public void showRegisterCashier() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FOLDER + "RegisterCashier.fxml"));
        fxmlLoader.setController(this);
        Parent registerCashier = fxmlLoader.load();
        mainPane.getChildren().clear();
        mainPane.setCenter(registerCashier);
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setTitle("");
        stage.setHeight(180.0);
        stage.setWidth(330.0);
        stage.setResizable(false);
    }//End showRegisterCashier

    public void showInformationAlert(String title,String msg,String header){
        Alert feedBack = new Alert(Alert.AlertType.INFORMATION);
        feedBack.setTitle(title);
        feedBack.setHeaderText(header);
        feedBack.setContentText(msg);
        feedBack.showAndWait();
    }//End showInformationAlert

}//End MainWindowController class
