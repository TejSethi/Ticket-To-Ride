

package main.java.com.tickettoride;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Main extends Application {
    public static Stage mainStage;

    public Main() {
    }

    public void start(Stage stage) throws Exception {
        final AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefWidth(600.0);
        anchorPane.setPrefHeight(400.0);
        Button start = this.makeButton(250, 200, 100, 25, "Start Game", anchorPane);
        start.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Main.mainStage = (Stage)anchorPane.getScene().getWindow();
                Main.mainStage.hide();
            }
        });
        Button quit = this.makeButton(250, 250, 100, 25, "Quit Game", anchorPane);
        quit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Platform.exit();
                System.exit(0);
            }
        });
        Scene scene = new Scene(anchorPane);
        stage.setTitle("Ticket to Ride");
        stage.setScene(scene);
        stage.show();
    }

    private Button makeButton(int x, int y, int width, int height, String text, AnchorPane boardPane) {
        Button button = new Button();
        button.setLayoutX((double)x);
        button.setLayoutY((double)y);
        button.setPrefWidth((double)width);
        button.setPrefHeight((double)height);
        button.setText(text);
        button.setTextAlignment(TextAlignment.CENTER);
        button.setWrapText(true);
        boardPane.getChildren().add(button);
        return button;
    }

    public static void main(String[] args) {
        launch(new String[0]);
    }
}
