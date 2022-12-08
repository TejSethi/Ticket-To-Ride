package main.java.com.tickettoride;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Scale;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class PlayerSelector extends Application {
    private GridPane gridPane;
    private ComboBox comboBox;
    private ColorPicker[] colorPickers;
    private Scale scale;
    private double width = 600;
    private double height = 400;
    private Stage stage;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefWidth(width);
        anchorPane.setPrefHeight(height);

        Label label = new Label("Number of Players: ");
        label.setLayoutX(180);
        label.setLayoutY(100);
        label.setPrefWidth(150);
        label.setPrefHeight(25);

        comboBox = new ComboBox<Integer>();

        comboBox.getItems().add(2);
        comboBox.getItems().add(3);
        comboBox.getItems().add(4);
        comboBox.getSelectionModel().selectFirst();


        /*As an admin player,
        I want to be able to
        see a dropdown
        menu giving various
        options for the
        number of players
        so that I can
        customize how
        many people are
        playing.*/
        comboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                anchorPane.getChildren().remove(gridPane);
                gridPane = drawPlayerChoices((Integer)comboBox.getValue());
                anchorPane.getChildren().add(gridPane);
            }
        });

        HBox hbox = new HBox(comboBox);
        hbox.setLayoutX(290);
        hbox.setLayoutY(100);


        Button confirm = new Button();
        confirm.setLayoutX(300);
        confirm.setLayoutY(300);
        confirm.setPrefWidth(100);
        confirm.setPrefHeight(25);
        confirm.setText("Confirm");
        confirm.setTextAlignment(TextAlignment.CENTER);

        confirm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)  {


                Stage stage = (Stage)anchorPane.getScene().getWindow();
                stage.close();
                Board b = Board.getInstance();
                Color[] colors = new Color[(Integer)comboBox.getValue()];
                for(int i = 0; i < colors.length; i++){
                    colors[i] = colorPickers[i].getValue();
                }
                b.setColors(colors);
                b.start(new Stage());



            }
        });

        gridPane = drawPlayerChoices(2);
        anchorPane.getChildren().addAll(label, hbox, gridPane, confirm);





        Scene scene=new Scene(anchorPane);

        scale = new Scale();
        scene.getRoot().getTransforms().setAll(scale);

        Rectangle2D screenBounds = Screen.getPrimary().getBounds();

        stage.setWidth(screenBounds.getWidth());
        stage.setHeight(screenBounds.getHeight());
        scale.setX(stage.getWidth()/width);
        scale.setY(stage.getHeight()/height);

        stage.widthProperty().addListener((obs, oldVal, newVal) -> {
            // Do whatever you want
            scale.setX(stage.getWidth()/width);
        });

        stage.heightProperty().addListener(
                (obs, oldVal, newVal) -> {
                    // Do whatever you want
                    scale.setY(stage.getHeight()/height);
                });


        stage.setTitle("Ticket to Ride");
        stage.setScene(scene);
        stage.show();
    }

    /*
    As a player, I want
    to be able select a
    custom colour so
    that I can have a
    custom identity in
    the game for
    myself.
     */
    private GridPane drawPlayerChoices(int numPlayers){
        double localWidth;
        localWidth = width/(numPlayers + 1);
        double X = localWidth/(numPlayers);
        GridPane gridPane = new GridPane();
        gridPane.setLayoutX(X);
        gridPane.setLayoutY(200);
        gridPane.setAlignment(Pos.CENTER);
        colorPickers = new ColorPicker[numPlayers];

        for(int i = 0; i < numPlayers; i++){
            colorPickers[i] = new ColorPicker();
            Label label = new Label("Player " + Integer.toString(i + 1));
            label.setAlignment(Pos.CENTER);
            gridPane.getColumnConstraints().add(new ColumnConstraints(localWidth));
            gridPane.add(label, i, 0);

            gridPane.add(colorPickers[i], i, 1);
        }

        return gridPane;
    }
}
