package ui;

import dataStructures.queue.QueueException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Client;
import model.Store;

import java.io.IOException;

public class MainWindowController {

    private final String FOLDER = "fxml/";
    private EmergentWindowController EWC;
    private Store myStore;

    @FXML
    private BorderPane mainPane;
    //Cashier
    @FXML
    private TextField amountCashiers;
    //Shelf
    @FXML
    private TextField shelfId;
    //VideoGame
    @FXML
    private ComboBox<String> shelfIdComboBox;
    @FXML
    private TextField gameCode;
    @FXML
    private TextField gameName;
    @FXML
    private TextField gameQuantity;
    @FXML
    private TextField gamePrice;
    //Client
    @FXML
    private TextField clientId;
    @FXML
    private ListView<String> gamesList;
    @FXML
    private ListView<Client> clientsList;

    public MainWindowController(Store myStore) {
        this.myStore = myStore;
        EWC = new EmergentWindowController(myStore);
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
                showInformationAlert("Cantidad inv??lida", "La cantidad de cajeros a registrar debe ser mayor a 0.", null);
            }//End if/else
        } catch(NumberFormatException e) {
            showInformationAlert("Entrada inv??lida", "La entrada debe ser un n??mero.", null);
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
                showInformationAlert("Registro exitoso", "Se ha registrado la estanter??a correctamente", null);
                shelfId.clear();
            } else {
                showInformationAlert("Entrada inv??lida", "Ya existe una estanter??a con ese identificador. Pruebe con otro.", null);
            }//End if/else
        } else {
            showInformationAlert("Entrada inv??lida", "Debe llenar el campo de texto.", null);
        }//End if/else
    }//End registerShelf

    @FXML
    public void checkShelvesSize() throws IOException {
        if(myStore.getShelvesSize() > 0)
            showRegisterGames();
        else
            showInformationAlert("","Debe registrar por lo menos una estanter??a para poder continuar.", null);
    }//End checkShelvesSize

    public void showRegisterGames() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FOLDER + "RegisterVideoGame.fxml"));
        fxmlLoader.setController(this);
        Parent registerGame = fxmlLoader.load();
        mainPane.getChildren().clear();;
        mainPane.setCenter(registerGame);
        initializeShelvesComboBox();
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setTitle("");
        stage.setHeight(415.0);
        stage.setWidth(335.0);
        stage.setResizable(false);
    }//End showRegisterGames

    private void initializeShelvesComboBox() {
        shelfIdComboBox.getItems().clear();
        ObservableList<String> identifiers = FXCollections.observableList(myStore.getShelvesIdAsList());
        shelfIdComboBox.setItems(identifiers);
    }//End initializeShelvesComboBox

    public void registerGames() {
        String shelfId = shelfIdComboBox.getValue();
        String name = gameName.getText();
        String c = gameCode.getText();
        String q = gameQuantity.getText();
        String p = gamePrice.getText();
        if(shelfId == null || name == null || c == null || q == null || p == null)
            showInformationAlert("Entradas inv??lidas", "Debe llenar todos los campos.", null);
        else {
            try {
                int code = Integer.parseInt(c);
                int quantity = Integer.parseInt(q);
                double price = Double.parseDouble(p);
                if(quantity <= 0 || price <= 0.0) {
                    showInformationAlert("Entradas inv??lidas", "La cantidad y el precio deben ser mayores a cero", null);
                } else if (myStore.registerVideoGame(shelfId, code, name, quantity, price)) {
                    showInformationAlert("Registro exitoso", "Se ha registrado el juego exitosamente", null);
                    gameName.clear();
                    gameCode.clear();
                    gameQuantity.clear();
                    gamePrice.clear();
                    initializeShelvesComboBox();
                } else {
                    showInformationAlert("", "No se pud?? registrar el juego", null);
                }//End if/else
            } catch (NumberFormatException e) {
                showInformationAlert("Entradas inv??lidas", "El c??digo, la cantidad y el precio debe ser n??meros. Por favor revise los campos y vuelva a intentarlo.", null);
            }//End try/catch
        }//End if/else
    }//End registerGames

    @FXML
    public void checkGameSize() throws IOException {
        if(myStore.getRegisteredGamesAmount() > 0)
            showRegisterClient();
        else
            showInformationAlert("","Debe registrar por lo menos un juego para poder continuar", null);
    }//End checkGamesSize

    public void showRegisterClient() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FOLDER + "RegisterClient.fxml"));
        fxmlLoader.setController(this);
        Parent registerClient = fxmlLoader.load();
        mainPane.getChildren().clear();
        mainPane.setCenter(registerClient);
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setTitle("");
        stage.setHeight(460.0);
        stage.setWidth(380.0);
        stage.setResizable(false);
    }//End showRegisterClient

    private int[] getGamesCodesInList() {
        int[] codes = new int[gamesList.getItems().size()];
        for(int i = 0; i < codes.length; i ++) {
            String[] auxArray = gamesList.getItems().get(i).split("x");
            codes[i] = Integer.parseInt(auxArray[0]);
        }//End for
        return codes;
    }//End getGamesCodesInList

    private int[] getGamesQuantities() {
        int[] quantities = new int[gamesList.getItems().size()];
        for(int i = 0; i < quantities.length; i ++) {
            String[] auxArray = gamesList.getItems().get(i).split("x");
            quantities[i] = Integer.parseInt(auxArray[1]);
        }//End for
        return quantities;
    }//End getGamesQuantities

    public void addGamesToList() throws IOException {
        EWC.showSelectGame(getGamesCodesInList());
        if(EWC.getAdded())
            gamesList.getItems().add(EWC.getGameToAdd());
    }//End addGamesToList

    public void registerClient() throws QueueException {
        String id = clientId.getText();
        int[] games = getGamesCodesInList();
        int[] quantities = getGamesQuantities();
        if(!id.isEmpty() && games.length > 0 && quantities.length > 0) {
            if(myStore.registerClient(id)) {
                int length = games.length;
                String msg = "Se agreg?? exitosamente al cliente junto con su lista de juegos.";
                for (int i = 0; i < length; i++) {
                    if(!myStore.addVideoGameToClient(id, games[i], quantities[i]))
                        msg = "No se pudieron agregar todos los juegos";
                }//End for
                showInformationAlert("", msg, null);
                clientId.clear();
                gamesList.getItems().clear();
            } else {
                showInformationAlert("Registro incompleto", "La identificaci??n ya se encuentra en uso.", null);
            }//End if/else
        } else {
            showInformationAlert("Entradas inv??lidas", "Deben llenarse todos los campos.", null);
        }//End if/else
    }//End registerClient

    @FXML
    public void checkClientsAmount() throws IOException {
        if(myStore.getClientsAmount() > 0)
            showClients();
        else
            showInformationAlert("", "Debe registrar por lo menos un cliente para continuar", null);
    }//End checkClientsAmount

    private void showClients() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FOLDER + "ShowClients.fxml"));
        fxmlLoader.setController(this);
        Parent showClients = fxmlLoader.load();
        mainPane.getChildren().clear();
        mainPane.setCenter(showClients);
        initializeClientsList();
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setTitle("");
        stage.setHeight(430.0);
        stage.setWidth(600.0);
        stage.setResizable(false);
    }//End showClients

    private void initializeClientsList() {
        ObservableList<Client> clients = FXCollections.observableList(myStore.getClientsAsList());
        clientsList.setItems(clients);
    }//End initializeClientsList

    @FXML
    public void chooseSortingMethod(MouseEvent e) throws IOException {
        if(e.getClickCount() == 2) {
            Client selection = clientsList.getSelectionModel().getSelectedItem();
            if(selection != null)
                EWC.chooseSortingMethod(selection);
        }//End if
    }//End chooseSortMethod

    @FXML
    public void checkSortedClients() throws QueueException, IOException {
        if(myStore.allClientsSorted())
            showClientsShoppingCart();
        else
            showInformationAlert("", "No se puede continuar hasta que no se ordenen las listas de todos los clientes.", null);
    }//End checkSortedClients

    private void showClientsShoppingCart() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FOLDER + "ClientsShoppingCart.fxml"));
        fxmlLoader.setController(this);
        Parent root = fxmlLoader.load();
        mainPane.getChildren().clear();
        mainPane.setCenter(root);
        myStore.pickUpGames();
        initializeClientsList();
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setTitle("");
        stage.setHeight(400.0);
        stage.setWidth(315.0);
        stage.setResizable(false);
    }//End showClientsCheckOutQueue

    @FXML
    public void showShoppingCartInfo(MouseEvent e) throws IOException {
        if(e.getClickCount() == 2) {
            Client selection = clientsList.getSelectionModel().getSelectedItem();
            if(selection != null)
                EWC.showClientShoppingCart(selection);
        }//End if
    }//End showShoppingCartInfo

    @FXML
    public void showClientsExitOrder() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FOLDER + "ClientsExitOrder.fxml"));
        fxmlLoader.setController(this);
        Parent root = fxmlLoader.load();
        mainPane.getChildren().clear();
        mainPane.setCenter(root);
        myStore.checkOut();
        initializeClientsExitList();
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setTitle("");
        stage.setHeight(390.0);
        stage.setWidth(315.0);
        stage.setResizable(false);
    }//End showClientsExitOrder

    public void initializeClientsExitList() {
        ObservableList<Client> clients = FXCollections.observableList(myStore.getClientsExitAsList());
        clientsList.setItems(clients);
    }//End initializeClientsExitList

    @FXML
    public void showShoppingBag(MouseEvent e) throws IOException {
        if(e.getClickCount() == 2) {
            Client selection = clientsList.getSelectionModel().getSelectedItem();
            if(selection != null)
                EWC.showClientShoppingBag(selection);
        }//End if
    }//ENd showShoppingBag

    public void showInformationAlert(String title,String msg,String header){
        Alert feedBack = new Alert(Alert.AlertType.INFORMATION);
        feedBack.setTitle(title);
        feedBack.setHeaderText(header);
        feedBack.setContentText(msg);
        DialogPane dp = feedBack.getDialogPane();
        dp.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        dp.getStyleClass().add("application");
        feedBack.showAndWait();
    }//End showInformationAlert

}//End MainWindowController class
