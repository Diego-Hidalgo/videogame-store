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
    @FXML
    private TextField shelfId;

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
        stage.setWidth(315.0);
        stage.setResizable(false);
    }//End showRegisterCashier

    @FXML
    public void registerCashier() throws IOException {
        try {
            int amount = Integer.parseInt(amountCashiers.getText());
            if(amount > 0) {
                myStore.registerCashiers(amount);
                showRegisterShelf();
            } else {
                showInformationAlert("Cantidad inválida", "La cantidad de cajeros a registrar debe ser mayor a 0.", null);
            }//End if/else
        } catch(NumberFormatException e) {
            showInformationAlert("Entrada inválida", "La entrada debe ser un número.", null);
        }//End try/catch
    }//End registerCashier

    @FXML
    public void showRegisterShelf() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FOLDER + "RegisterShelf.fxml"));
        fxmlLoader.setController(this);
        Parent registerShelf = fxmlLoader.load();
        mainPane.getChildren().clear();
        mainPane.setCenter(registerShelf);
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setTitle("");
        stage.setHeight(230.0);
        stage.setWidth(360.0);
        stage.setResizable(false);
    }//End showRegisterShelf

    @FXML
    public void registerShelf() {
        String id = shelfId.getText();
        if(!id.isEmpty()) {
            if(myStore.registerShelf(id)) {
                showInformationAlert("Registro exitoso", "Se ha registrado la estantería correctamente", null);
            } else {
                showInformationAlert("Entrada inválida", "Ya existe una estantería con ese identificador. Pruebe con otro.", null);
            }//End if/else
        } else {
            showInformationAlert("Entrada inválida", "Debe llenar el campo de texto.", null);
        }//End if/else
    }//End registerShelf

    public void showInformationAlert(String title,String msg,String header){
        Alert feedBack = new Alert(Alert.AlertType.INFORMATION);
        feedBack.setTitle(title);
        feedBack.setHeaderText(header);
        feedBack.setContentText(msg);
        feedBack.showAndWait();
    }//End showInformationAlert

}//End MainWindowController class
