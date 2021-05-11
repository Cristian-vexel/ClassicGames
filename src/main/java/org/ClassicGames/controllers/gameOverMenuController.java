package org.ClassicGames.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;

import org.ClassicGames.games.snake.snake;

public class gameOverMenuController {

    private static Button btnRetry;
    private static Button btnBack;

    public static void createGameOverMenu(Pane root){ // de facut un fxml separat pentru gameOverMenu si de folosit controllerul cu mouse even

		btnRetry = (Button) root.lookup("#buttonRetry");
		btnRetry.setOnMouseClicked(e -> gameOverMenuController.gameOverButtonActionHandler_Retry(e));

        btnBack = (Button) root.lookup("#buttonBack");
		btnBack.setOnMouseClicked(e -> gameOverMenuController.gameOverButtonActionHandler_Back(e));
    }

    @FXML
    public static void gameOverButtonActionHandler_Retry(MouseEvent e){
        snake s = new snake();
        try {
            s.MainStartSnake(e);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    public static void gameOverButtonActionHandler_Back(MouseEvent event){
        try {
            MenuController.switchToMenuScene(event);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
