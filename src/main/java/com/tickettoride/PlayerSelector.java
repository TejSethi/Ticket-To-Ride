

package main.java.com.tickettoride;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class PlayerSelector extends Application {
    private GridPane gridPane;

    public PlayerSelector() {
    }

    public void start(Stage stage) {
        final AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefWidth(600.0);
        anchorPane.setPrefHeight(400.0);
        Label label = new Label("Number of Players: ");
        label.setLayoutX(180.0);
        label.setLayoutY(100.0);
        label.setPrefWidth(150.0);
        label.setPrefHeight(25.0);
        final ComboBox comboBox = new ComboBox();
        comboBox.getItems().add(2);
        comboBox.getItems().add(3);
        comboBox.getItems().add(4);
        comboBox.getSelectionModel().selectFirst();
        comboBox.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println(comboBox.getSelectionModel().getSelectedItem());
            }
        });
        HBox hbox = new HBox(new Node[]{comboBox});
        hbox.setLayoutX(290.0);
        hbox.setLayoutY(100.0);
        Button confirm = new Button();
        confirm.setLayoutX(300.0);
        confirm.setLayoutY(300.0);
        confirm.setPrefWidth(100.0);
        confirm.setPrefHeight(25.0);
        confirm.setText("Confirm");
        confirm.setTextAlignment(TextAlignment.CENTER);
        confirm.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Stage stage = (Stage)anchorPane.getScene().getWindow();
                stage.close();
            }
        });
        Scene scene = new Scene(anchorPane);
        stage.setTitle("Ticket to Ride");
        stage.setScene(scene);
        stage.show();
    }
}

