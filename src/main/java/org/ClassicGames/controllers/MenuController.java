package org.ClassicGames.controllers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

import org.ClassicGames.games.snake.snake;

public class MenuController {

    @FXML
    public void handleButtonActionSnake(MouseEvent event) throws IOException {
        snake s = new snake();
        s.MainStartSnake(event);
    }

}
