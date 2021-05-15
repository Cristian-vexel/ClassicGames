package org.ClassicGames.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;

import org.ClassicGames.games.snake.SnakeConfiguration;

public class adminMenuController {

    @FXML
    private TextField speed;
    @FXML
    private TextField initialLength;
    @FXML
    private TextField greedSize;
    @FXML
    private Text saveMessage;

    @FXML
    public void switchToAdminMenuScene(MouseEvent event) throws IOException {
        Stage stage;
        Scene scene;
        Parent root;

        root = FXMLLoader.load(adminMenuController.class.getClassLoader().getResource("adminMenu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.show();

        speed = (TextField) root.lookup("#speed");
        speed.setText(String.valueOf(SnakeConfiguration.getSpeed()));

        initialLength = (TextField) root.lookup("#initialLength");
        initialLength.setText(String.valueOf(SnakeConfiguration.getInitialLength()));

        greedSize = (TextField) root.lookup("#greedSize");
        greedSize.setText(String.valueOf(SnakeConfiguration.getGreedSize()));

        saveMessage = (Text) root.lookup("#saveMessage");

    }

    @FXML
    public void handleButtonActionBack(MouseEvent event) throws IOException {
        gameMenuController.switchToMenuScene(event);
    }

    @FXML
    public void handleButtonActionSave(MouseEvent event) throws IOException {
        SnakeConfiguration.setSpeed(Float.parseFloat(speed.getText()));
        SnakeConfiguration.setGreedSize(Integer.parseInt(greedSize.getText()));
        SnakeConfiguration.setInitialLength(Integer.parseInt(initialLength.getText()));

        saveMessage.setText("Settings saved");
    }

}
