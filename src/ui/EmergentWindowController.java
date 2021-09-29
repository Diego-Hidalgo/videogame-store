package ui;

import dataStructures.queue.QueueException;
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
import model.Client;
import model.Store;
import model.VideoGame;

import java.io.IOException;

public class EmergentWindowController {

    private final String FOLDER = "fxml/";
    private Store myStore;
    private boolean added;
    private int code;
    private int quantity;
    private String gameToAdd;
    private Client current;

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
    @FXML
    private Label idLabel;
    @FXML
    private ComboBox<String> sortOptions;
    @FXML
    private ListView<VideoGame> gamesList;
    @FXML
    private Button sortBtn;
    @FXML
    private Label timeLabel;
    @FXML
    private Label positionLabel;
    @FXML
    private Label totalLabel;

    public EmergentWindowController(Store myStore) {
        current = null;
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

    @FXML
    public void chooseSortingMethod(Client client) throws IOException {
        current = client;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FOLDER + "ChooseSortingMethod.fxml"));
        fxmlLoader.setController(this);
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, null);
        Stage form = new Stage();
        form.initModality(Modality.APPLICATION_MODAL);
        form.setScene(scene);
        initializeSortOptionsComboBox();
        initializeGamesList();
        idLabel.setText(current.getId());
        timeLabel.setText(current.getTime() + " minuto(s)");
        sortBtn.setDisable(true);
        form.setTitle("");
        form.setHeight(440.0);
        form.setWidth(351.0);
        form.setResizable(false);
        form.showAndWait();
    }//End showClientData

    @FXML
    public void checkSortOption() {
        if(sortOptions.getValue() != null)
            sortBtn.setDisable(false);
    }//End checkSortOption

    private void initializeSortOptionsComboBox() {
        sortOptions.getItems().clear();
        sortOptions.getItems().add("1. Insertion");
        sortOptions.getItems().add("2. Bubble");
    }//End initializeSortOptionsComboBox

    private void initializeGamesList() {
        try {
            ObservableList<VideoGame> list = FXCollections.observableList(myStore.getClientGamesAsList(current));
            gamesList.setItems(list);
        } catch (QueueException ignored) {}
    }//End initializeGamesList

    @FXML
    public void sortClientList(ActionEvent e) {
        int option;
        if(sortOptions.getValue().equals("1. Insertion"))
            option = 1;
        else
            option = 2;
        try {
            myStore.sortClientList(option, current.getId());
            sortBtn.setDisable(true);
            initializeGamesList();
            initializeSortOptionsComboBox();
            timeLabel.setText(current.getTime() + " minutos(s)");
        } catch (QueueException ignored) {}
    }//End sortClientList

    public void showClientShoppingCart(Client client) throws IOException  {
        current = client;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FOLDER + "ClientSCInfo.fxml"));
        fxmlLoader.setController(this);
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, null);
        Stage form = new Stage();
        form.initModality(Modality.APPLICATION_MODAL);
        form.setScene(scene);
        idLabel.setText(current.getId());
        timeLabel.setText(current.getTime() + " minutos(s)");
        positionLabel.setText(current.getPosition() + "");
        initializeCartList();
        form.setTitle("");
        form.setHeight(455.0);
        form.setWidth(351.0);
        form.setResizable(false);
        form.showAndWait();
    }//End showClientShoppingCart

    private void initializeCartList() {
        try {
            ObservableList<VideoGame> list = FXCollections.observableList(myStore.getClientShoppingCartAsList(current));
            gamesList.setItems(list);
        } catch (QueueException ignored) {}
    }//End initializeCartList

    public void showClientShoppingBag(Client client) throws IOException {
        current = client;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FOLDER + "ClientSBInfo.fxml"));
        fxmlLoader.setController(this);
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, null);
        Stage form = new Stage();
        form.initModality(Modality.APPLICATION_MODAL);
        form.setScene(scene);
        idLabel.setText(current.getId());
        timeLabel.setText(current.getTime() + " minutos(s)");
        positionLabel.setText(current.getPosition() + "");
        totalLabel.setText("$" + current.getTotal());
        initializeBagList();
        form.setTitle("");
        form.setHeight(470.0);
        form.setWidth(351.0);
        form.setResizable(false);
        form.showAndWait();
    }//End showClientShoppingBag

    private void initializeBagList() {
        try {
            ObservableList<VideoGame> list = FXCollections.observableList(myStore.getClientShoppingBagAsList(current));
            gamesList.setItems(list);
        } catch (QueueException ignored) {}
    }//End initializeBagList

    @FXML
    public void closeEmergentWindow(ActionEvent e) {
        current = null;
        Node source = (Node) e.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }//End closeEmergentWindow

}//End EmergentWindowController class
