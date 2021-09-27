package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Store;

import java.io.IOException;
import java.util.Collections;

public class EmergentWindowController {

    private final String FOLDER = "fxml/";
    private Store myStore;
    private boolean added;
    private int code;
    private int quantity;
    private String gameToAdd;

    @FXML
    private ComboBox<Integer> gamesCodesComboBox;
    @FXML
    private TextField gameQuantity;
    @FXML
    private TextArea gameInfo;
    @FXML
    private Label checkLabel;
    @FXML
    private Button addBtn;

    public EmergentWindowController(Store myStore) {
        this.myStore = myStore;
        added = false;
    }//End Constructor

    public boolean getAdded() {
        return added;
    }//End getAdded

    public String getGameToAdd() {
        return gameToAdd;
    }//End getGameInfo

    @FXML
    public void showSelectGame(int[] codesInList) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FOLDER + "SelectGame.fxml"));
        fxmlLoader.setController(this);
        Parent selectGame = fxmlLoader.load();
        Scene scene = new Scene(selectGame, null);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.setTitle("");
        stage.setHeight(390.0);
        stage.setWidth(383.0);
        stage.setResizable(false);
        initializeGamesCodeComboBox(codesInList);
        added = false;
        gameQuantity.setDisable(true);
        addBtn.setDisable(true);
        stage.showAndWait();
    }//End showSelectGames

    private void initializeGamesCodeComboBox(int[] codesInList) {
        gamesCodesComboBox.getItems().clear();
        ObservableList<Integer> codes = FXCollections.observableList(myStore.getGamesCodesAsList(codesInList));
        gamesCodesComboBox.setItems(codes);
    }//End initializeGamesCodeComboBox

    @FXML
    public void setInfoText() {
        Integer code = gamesCodesComboBox.getValue();
        if(code != null) {
            gameInfo.setText(myStore.getVideoGameInfo(code));
            gameQuantity.setDisable(false);
            this.code = code;
        }//End if
    }//End setInfoText

    @FXML
    public void checkQuantity(KeyEvent keyEvent) {
        if(!gameQuantity.getText().isEmpty()) {
            try {
                int quantity = Integer.parseInt(gameQuantity.getText());
                if (quantity <= 0) {
                    checkLabel.setText("La cantidad debe ser positiva");
                    addBtn.setDisable(true);
                } else if (quantity > myStore.getVideoGameQuantity(gamesCodesComboBox.getValue())) {
                    checkLabel.setText("La cantidad debe ser menor a la disponible");
                    addBtn.setDisable(true);
                } else {
                    checkLabel.setText("");
                    addBtn.setDisable(false);
                    this.quantity = quantity;
                }//End if/else
            } catch (NumberFormatException e) {
                checkLabel.setText("La cantidad debe ser un número");
                addBtn.setDisable(true);
            }//End try/catch
        } else {
            checkLabel.setText("");
            addBtn.setDisable(true);
        }//End if/else
    }//End checkAmount

    @FXML
    public void addGameToList(ActionEvent e) {
        added = true;
        gameToAdd = code + "x" + quantity;
        closeEmergentWindow(e);
    }//End addGameToList

    private void closeEmergentWindow(ActionEvent e) {
        Node source = (Node) e.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }//End closeEmergentWindow

}//End EmergentWindowController class
