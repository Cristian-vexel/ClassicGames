package org.ClassicGames.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;

import org.ClassicGames.games.snake.snake;
import org.ClassicGames.model.User;

public class gameMenuController {

    @FXML
    public static void switchToMenuScene(MouseEvent event) throws IOException {
        Stage stage;
        Scene scene;
        Parent root;

        root = FXMLLoader.load(gameMenuController.class.getClassLoader().getResource("gameMenu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.show();

        Button adminPanelButton = (Button) root.lookup("#adminPanelButton");
        if(User.getLoggedUserRole() == "Client"){
            adminPanelButton.setVisible(false);
            System.out.println("I am Client");
        }

    }

    @FXML
    public void handleButtonAdminPanel(MouseEvent event) {
        try {
            adminMenuController adminMenu = new adminMenuController();
            adminMenu.switchToAdminMenuScene(event);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    public void handleButtonActionSnake(MouseEvent event) throws IOException {
        snake s = new snake();
        s.MainStartSnake(event);
    }

    @FXML
    public void start(MouseEvent event) throws Exception {
        ViewRecordController.start(event);
    }

    @FXML
    public void handleButtonActionLogOut(MouseEvent event) throws IOException {
        RegistrationController.switchToRegistrationScene(event);
    }

    @FXML
    public void handleButtonActionExit(MouseEvent event) {
        System.exit(0);
    }
}
