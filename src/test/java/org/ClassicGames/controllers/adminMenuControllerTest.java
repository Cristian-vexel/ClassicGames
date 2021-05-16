package org.ClassicGames.controllers;

import org.ClassicGames.model.User;
import org.ClassicGames.services.FileSystemService;
import org.ClassicGames.services.UserService;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static org.testfx.assertions.api.Assertions.assertThat;

import org.ClassicGames.games.snake.*;

@ExtendWith(ApplicationExtension.class)
class adminMenuControllerTest {

    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".ClassicGames";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
    }

    @Start
    void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("gameMenu.fxml"));
        primaryStage.setScene(new Scene(root, 800, 600));
        User.setLoggedUserRole("Admin");
        primaryStage.show();
    }

    @Test
    void testAdminMenu(FxRobot robot){
        robot.clickOn("#adminPanelButton");


        double speed = 5;
        int initialLength = 10;
        int greedSize = 40;

        robot.doubleClickOn("#speed");
        robot.write(String.valueOf(speed));
        robot.doubleClickOn("#initialLength");
        robot.write(String.valueOf(initialLength));
        robot.doubleClickOn("#greedSize");
        robot.write(String.valueOf(greedSize));

        robot.clickOn("#save");

        assertThat(robot.lookup("#saveMessage").queryText()).hasText("Settings saved");

        assertThat(SnakeConfiguration.getSpeed()).isEqualTo(speed);
        assertThat(SnakeConfiguration.getInitialLength()).isEqualTo(initialLength);
        assertThat(SnakeConfiguration.getGreedSize()).isEqualTo(greedSize);

        speed = 10;
        initialLength = 3;
        greedSize = 20;

        robot.doubleClickOn("#speed");
        robot.write(String.valueOf(speed));
        robot.doubleClickOn("#initialLength");
        robot.write(String.valueOf(initialLength));
        robot.doubleClickOn("#greedSize");
        robot.write(String.valueOf(greedSize));

        robot.clickOn("#save");

        assertThat(robot.lookup("#saveMessage").queryText()).hasText("Settings saved");

        assertThat(SnakeConfiguration.getSpeed()).isEqualTo(speed);
        assertThat(SnakeConfiguration.getInitialLength()).isEqualTo(initialLength);
        assertThat(SnakeConfiguration.getGreedSize()).isEqualTo(greedSize);

        robot.clickOn("#back");
        robot.clickOn("#playSnake");

        assertThat(SnakeConfiguration.getSpeed()).isEqualTo(speed);
        assertThat(SnakeConfiguration.getInitialLength()).isEqualTo(initialLength);
        assertThat(SnakeConfiguration.getGreedSize()).isEqualTo(greedSize);
    }

}
