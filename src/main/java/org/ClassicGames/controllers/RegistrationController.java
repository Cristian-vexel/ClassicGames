package org.ClassicGames.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

import org.ClassicGames.exceptions.BlankFieldException;
import org.ClassicGames.exceptions.LogInFailException;
import org.ClassicGames.exceptions.UsernameAlreadyExistsException;
import org.ClassicGames.services.UserService;

public class RegistrationController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Text registrationMessage;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    @FXML
    private ChoiceBox role;

    @FXML
    public void initialize() {
        role.getItems().addAll("Client", "Admin");
    }

    @FXML
    public void handleRegisterAction() {
        try {
            UserService.addUser(usernameField.getText(), passwordField.getText(), (String) role.getValue());
            registrationMessage.setText("Account created successfully!");
        } catch (UsernameAlreadyExistsException e) {
            registrationMessage.setText(e.getMessage());
        } catch (BlankFieldException e) {
            registrationMessage.setText(e.getMessage());
        }
    }

    @FXML
    public void handleLoginAction(MouseEvent event) throws IOException {
        try {
            UserService.logIn(usernameField.getText(), passwordField.getText(), (String) role.getValue());
            registrationMessage.setText("Successfully logged in!");
            switchToGame1Scene(event);
        } catch (LogInFailException e) {
            registrationMessage.setText(e.getMessage());
        } catch (BlankFieldException e) {
            registrationMessage.setText(e.getMessage());
        }
    }

    public void switchToGame1Scene(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getClassLoader().getResource("menu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.show();
    }
}
