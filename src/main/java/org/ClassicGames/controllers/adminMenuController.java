package org.ClassicGames.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;

public class adminMenuController {

    public static void switchToAdminMenuScene(MouseEvent event) throws IOException {
        Stage stage;
        Scene scene;
        Parent root;

        root = FXMLLoader.load(adminMenuController.class.getClassLoader().getResource("adminMenu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void handleButtonActionBack(MouseEvent event) throws IOException {
        gameMenuController.switchToMenuScene(event);
    }

}
