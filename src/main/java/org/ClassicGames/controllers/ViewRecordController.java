package org.ClassicGames.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.ClassicGames.model.Record;

public class ViewRecordController {

    public static void start(MouseEvent event) throws Exception {
        Parent root = FXMLLoader.load(ViewRecordController.class.getClassLoader().getResource("record.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Record Manager");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }


    @FXML
    public TableView<Record> userTable;

    @FXML
    public TableColumn<Record, String> usernameColumn;
    @FXML
    public TableColumn<Record, Integer> recordColumn;

    @FXML
    public void initialize() {
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        recordColumn.setCellValueFactory(new PropertyValueFactory<>("record"));

        userTable.setItems(users);
    }

    private ObservableList<Record> users = FXCollections.observableArrayList(
            new Record("John",15 ),
            new Record("Jane", 87),
            new Record("Jack", 65),
            new Record("Julia", 12),
            new Record("Johnny", 77),
            new Record("Michelle", 3)
    );
}

