package org.ClassicGames.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;

import org.ClassicGames.games.snake.snake;

public class MenuController {

    public static void switchToMenuScene(MouseEvent event) throws IOException {
        Stage stage;
        Scene scene;
        Parent root;

        root = FXMLLoader.load(MenuController.class.getClassLoader().getResource("menu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void handleButtonActionSnake(MouseEvent event) throws IOException {
        snake s = new snake();
        s.MainStartSnake(event);
    }

}
