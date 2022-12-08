

package main.java.com.tickettoride;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Scale;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage mainStage;
    private Scale scale;
    private double width = 600;
    private double height = 400;

    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefWidth(width);
        anchorPane.setPrefHeight(height);

        Button start = makeButton(250, 200, 100, 25, "Start Game", anchorPane);

        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)  {


                mainStage = (Stage)anchorPane.getScene().getWindow();
                mainStage.hide();
                PlayerSelector playerSelector = new PlayerSelector();
                playerSelector.start(new Stage());




            }
        });

        Button quit = makeButton(250, 250, 100, 25, "Quit Game", anchorPane);

        quit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)  {
                Platform.exit();
                System.exit(0);
            }
        });
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

    private Button makeButton(int x, int y, int width, int height, String text, AnchorPane boardPane) {
        Button button = new Button();
        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setPrefWidth(width);
        button.setPrefHeight(height);
        button.setText(text);
        button.setTextAlignment(TextAlignment.CENTER);
        button.setWrapText(true);
        boardPane.getChildren().add(button);
        return button;
    }



    public static void main(String[] args) {
        launch();
    }
}
