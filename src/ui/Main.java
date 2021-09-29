package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Store;

import java.io.IOException;

public class Main extends Application {

    private MainWindowController mwc;
    private Store myStore;

    public Main() {
        myStore = new Store();
        mwc = new MainWindowController(myStore);
    }//End Constructor

    public static void main(String[] args) {
        launch(args);
    }//End main

    @Override
    public void start(Stage window) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/MainPane.fxml"));
        fxmlLoader.setController(mwc);
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, null);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        window.setScene(scene);
        window.setTitle("");
        window.show();
        mwc.showRegisterCashier();
    }//End start

}//Enc Main class
