package org.ClassicGames.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;

import org.ClassicGames.games.snake.snake;

public class pauseMenuController {

    private static Button btnContinue;
    private static Button btnBack;

    public static void createPauseMenu(Pane root){

		btnContinue = (Button) root.lookup("#buttonContinue");
		btnContinue.setOnMouseClicked(e -> pauseMenuController.pauseButtonActionHandler_Continue(e));

        btnBack = (Button) root.lookup("#buttonBack");
		btnBack.setOnMouseClicked(e -> pauseMenuController.pauseButtonActionHandler_Back(e));
    }

    @FXML
    public static void pauseButtonActionHandler_Continue(MouseEvent e){
        snake.continueGame();
    }

    public static void pauseButtonActionHandler_Back(MouseEvent event){
        try {
            MenuController.switchToMenuScene(event);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
