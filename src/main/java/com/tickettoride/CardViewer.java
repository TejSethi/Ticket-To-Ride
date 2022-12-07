package main.java.com.tickettoride;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Scale;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Pair;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardViewer extends Application {
    private AnchorPane anchorPane;
    private List<Pair<Button, DestinationCard>> destinationButtons;
    private Button confirm;
    private Button cancel;
    private double width = 1920;
    private double height = 1080;
    private Scale scale;
    private DestinationCard destinationCardToRemove;
    @Override
    public void start(Stage stage) {
        anchorPane = new AnchorPane();
        anchorPane.setPrefWidth(width);
        anchorPane.setPrefHeight(height);
        destinationButtons = new ArrayList<>();



        Map<Color, int[]> positions = new HashMap();
        int startX = 40;
        int startY = 150;
        int rightShift = 180;
        int downShift = 50;
        int column = 0;
        int index = 0;
        int count = 0;
        Player player = Board.board.getGame().getPlayers().get(Board.board.getGame().getTurn());
        for (TrainCard trainCard : player.getTrainCards()){
            Button button;
            if (positions.containsKey(trainCard.getColor())){
                int[] pair = positions.get(trainCard.getColor());
                index = pair[0];
                count = pair[1];
                button = buildButton(startX + rightShift * index, startY + downShift * count,
                        156, 242, trainCard.getName());
                pair[1] += 1;
            } else{
                int[] pair = {column, 0};
                button = buildButton(startX + rightShift * column, startY,
                        156, 242, trainCard.getName());
                column += 1;
                pair[1] += 1;
                positions.put(trainCard.getColor(), pair);

            }
            button.setText(null);
            String name = Game.IMAGES_PATH_LOCATION + trainCard.getName() + ".png";
            Image img =  new Image(new File(name).toURI().toString());
            ImageView view = new ImageView(img);
            view.setPreserveRatio(true);
            button.setGraphic(view);
            button.setContentDisplay(ContentDisplay.BOTTOM);
            button.setText(Board.board.getLabel(trainCard.getColor()));



        }
        count = 0;
        for(DestinationCard destinationCard: player.getDestinationCards()){
            Button button = buildButton(startX + rightShift * column + 40, startY + downShift*count,
                    156, 242, destinationCard.getStart() + " to " + destinationCard.getEnd());
            count += 1;
            button.toFront();
            Pair<Button, DestinationCard> pair = new Pair(button, destinationCard);
            destinationButtons.add(pair);
            button.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    button.toFront();
                }
            });
            button.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    for(Pair pair: destinationButtons){
                        ((Button)pair.getKey()).toFront();
                    }

                }
            });
            button.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if(Board.board.getRemoveDestinationFlag()){
                        confirm.setVisible(true);
                        cancel.setVisible(true);
                        destinationCardToRemove = destinationCard;
                    }
                }
            });
            Image img = new Image(new File(Game.IMAGES_PATH_LOCATION + "destination.png").toURI().toString());
            ImageView view = new ImageView(img);
            view.setPreserveRatio(true);
            button.setGraphic(view);
            button.setContentDisplay(ContentDisplay.BOTTOM);


        }

        confirm = new Button();
        confirm.setLayoutX(1800);
        confirm.setLayoutY(800);
        confirm.setPrefWidth(100);
        confirm.setPrefHeight(25);
        confirm.setText("Confirm");
        confirm.setTextAlignment(TextAlignment.CENTER);
        confirm.setVisible(false);

        cancel = new Button();
        cancel.setLayoutX(1800);
        cancel.setLayoutY(850);
        cancel.setPrefWidth(100);
        cancel.setPrefHeight(25);
        cancel.setText("Cancel");
        cancel.setTextAlignment(TextAlignment.CENTER);
        cancel.setVisible(false);

        confirm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Board.board.getGame().playTurn(destinationCardToRemove, false, 0, 0, false, "", "");
                if(Board.board.getGame().getRound() == 1){
                    Board.board.setState(Board.State.Default);
                    Board.board.getSkipButton().setVisible(false);
                    Board.board.getRemoveDestinationButton().setVisible(false);
                }
                Board.board.setCardStageNull();
                Board.board.updateBoard();
                stage.close();
            }
        });

        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                destinationCardToRemove = null;
                cancel.setVisible(false);
                confirm.setVisible(false);
            }
        });

        anchorPane.getChildren().addAll(confirm, cancel);

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
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent windowEvent) {
                Board.board.setCardStageNull();
            }
        });
        stage.show();

    }

    public Button buildButton(int x, int y, int width, int height, String text) {
        Button button = new Button();
        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setPrefWidth(width);
        button.setPrefHeight(height);
        button.setText(text);
        button.setTextAlignment(TextAlignment.CENTER);
        button.setWrapText(true);
        anchorPane.getChildren().add(button);

        return  button;
    }
}