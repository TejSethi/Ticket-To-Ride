package main.java.com.tickettoride;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Scale;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static main.java.com.tickettoride.Game.*;

public class Board extends Application {
    public static Board board;
    private Map<String, Button> buildings;
    private List<Path> paths;
    private Color[] colors;
    private AnchorPane boardPane;
    private AnchorPane playerPane;
    private int firstCardIndex;
    private int secondCardIndex;
    private Button firstCardButton;
    private Button secondCardButton;
    private State state;
    private Map<Button, Integer> buttonMap;
    private Map<Integer, Button> buttonIntMap;
    private int buttonCount;
    private Button cancelButton;
    private Label playerTurn;
    private Label[] playerPoints;
    private Game game;
    private Button skipButton;
    private Button removeDestinationButton;
    private Button viewHandButton;
    private Stage cardStage;
    private boolean removeDestinationFlag;
    private Rectangle playerColor;
    private Button endTurnButton;
    private Color buttonColor;
    private Button firstBuildingButton;
    private Button secondBuildingButton;
    private Button destinationDeckButton;
    private Scale scale;
    private double height = 1080;
    private double  width = 1920;


    enum State{
        FirstRound,
        Default,
        FirstCardSelected,
        SecondCardSelected,
        DestinationSelected,
        FirstBuildingSelected,
        SecondBuildingSelected,
        GameOver
    }

    public Board(){
        board = this;
        buildings = new HashMap<>();
        paths = new ArrayList<>();
        state = State.FirstRound;
        buttonMap = new HashMap<>();
        buttonIntMap = new HashMap<>();
        buttonCount = 0;
        removeDestinationFlag = false;
        buttonColor =Color.LIGHTGOLDENRODYELLOW;
    }

    @Override
    public void start(Stage stage) {


        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefWidth(width);
        anchorPane.setPrefHeight(height);

        /*
        MenuBar menuBar = new MenuBar();
        menuBar.setLayoutX(29);
        menuBar.setLayoutY(30);
        AnchorPane.setLeftAnchor(menuBar, 0.0);
        AnchorPane.setRightAnchor(menuBar, 0.0);
        AnchorPane.setTopAnchor(menuBar, 0.0);

        Menu file = new Menu("File");
        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) anchorPane.getScene().getWindow();
                stage.close();
                Main.mainStage.show();
            }
        });

        file.getItems().addAll(exit);
        menuBar.getMenus().addAll(file);
*/
        /*
        SplitPane splitPane = new SplitPane();
        splitPane.setDividerPositions(0.7);
        splitPane.setOrientation(Orientation.VERTICAL);
        splitPane.setPrefHeight(1080);
        splitPane.setPrefWidth(1920);
        AnchorPane.setBottomAnchor(splitPane, 0.0);
        AnchorPane.setLeftAnchor(splitPane, 0.0);
        AnchorPane.setRightAnchor(splitPane, 0.0);
        AnchorPane.setTopAnchor(splitPane, 25.0);*/

        boardPane = new AnchorPane();
        boardPane.setStyle("-fx-background-image: url('bus.gif');");
        playerPane = new AnchorPane();

        boardPane.setPrefWidth(width);
        boardPane.setPrefHeight(742);
        playerPane.setPrefWidth(width);
        playerPane.setPrefHeight(318);
        playerPane.setLayoutY(742);
        anchorPane.getChildren().addAll(boardPane, playerPane);

        /*
        splitPane.getItems().addAll(boardPane, playerPane);
        */


        game = new Game(this);

        Button b = buildButton(1100, 20, 156, 242, "Train Deck");
        Image img = new Image(new File(IMAGES_PATH_LOCATION + "back.png").toURI().toString());
        ImageView view = new ImageView(img);
        view.setPreserveRatio(true);
        b.setGraphic(view);
        b.setContentDisplay(ContentDisplay.BOTTOM);
        destinationDeckButton = buildButton(1340, 20, 156, 242, "Destination Deck");
        img = new Image(new File(IMAGES_PATH_LOCATION + "destination.png").toURI().toString());
        view = new ImageView(img);
        view.setPreserveRatio(true);
        destinationDeckButton.setGraphic(view);
        destinationDeckButton.setContentDisplay(ContentDisplay.BOTTOM);


        viewHandButton = buildButton(25, 100, 156, 50, "View Hand");

        viewHandButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)  {

                if(cardStage == null){
                    CardViewer cardViewer = new CardViewer();
                    cardStage = new Stage();
                    cardViewer.start(cardStage);
                }
                else{
                    cardStage.requestFocus();
                }
            }


        });



        GridPane gridPane = new GridPane();
        gridPane.setLayoutX(1600);
        gridPane.setLayoutY(40);
        gridPane.setAlignment(Pos.CENTER);
        playerTurn = new Label("Current Turn: Player " + (game.getPlayers().get(game.getTurn()).getNumber()));

        playerColor = new Rectangle();
        playerColor.setArcHeight(50);
        playerColor.setArcWidth(50);
        playerColor.setFill(game.getPlayers().get(game.getTurn()).getColor());
        playerColor.setHeight(30);
        playerColor.setWidth(30);
        playerColor.setStroke(Color.BLACK);
        playerColor.setStrokeType(StrokeType.INSIDE);
        gridPane.add(playerColor, 0, 0);

        gridPane.add(playerTurn, 1, 0);
        Label points = new Label("Points");
        gridPane.add(points, 0, 1);
        playerPoints = new Label[colors.length];
        for(int i = 0; i < colors.length; i++){
            playerPoints[i] = new Label("Player" + game.getPlayers().get(i).getNumber() +  " Points: " + game.getPoints()[i]);
            gridPane.add(playerPoints[i], 1, i + 2);
            Rectangle rectangle = new Rectangle();
            rectangle.setArcHeight(50);
            rectangle.setArcWidth(50);
            rectangle.setFill(game.getPlayers().get(i).getColor());
            rectangle.setHeight(25);
            rectangle.setWidth(25);
            rectangle.setStroke(Color.BLACK);
            rectangle.setStrokeType(StrokeType.INSIDE);
            gridPane.add(rectangle, 0, i + 2);
        }

        playerPane.getChildren().add(gridPane);

        cancelButton = buildButton(1600, 200, 100, 50, "Cancel");
        cancelButton.setVisible(false);
        removeDestinationButton = buildButton(25, 175, 156, 50, "Remove Destination Card");
        skipButton = buildButton(25, 235, 156, 25, "Skip");
        endTurnButton = buildButton(1800, 200, 100, 50, "End Turn");
        endTurnButton.setVisible(false);

        buildBuilding(65,  155, 35, 25, P1);
        buildBuilding(52, 280, 106, 50, MAANJIWE_NENDAMOWINAN);
        buildBuilding(86, 520, 113, 25, DEERFIELD_HALL);
        buildBuilding(14, 621, 35, 25, P7);
        buildBuilding(199, 678, 35, 25, P6);
        buildBuilding(282, 38, 138, 25, INSTRUCTIONAL_CENTRE);
        buildBuilding(296, 280, 93, 25, NORTH_FIELD);
        buildBuilding(270, 571, 113, 50, ERINDALE_STUDIO_THEATRE);
        buildBuilding(563, 108, 138, 50, ACADEMIC_LEARNING_CENTRE);
        buildBuilding(524, 372, 110, 25, ERINDALE_HALL);
        buildBuilding(700, 670, 110, 50, OSCAR_PETERSON_HALL);
        buildBuilding(982, 58, 110, 25, GREENHOUSE);
        buildBuilding(886, 240, 93, 25, CCT_BUILDING);
        buildBuilding(833, 397, 120, 25, STUDENT_CENTRE);
        buildBuilding(958, 543, 64, 25, ANNEX);
        buildBuilding(1111, 597, 35, 25, P5);
        buildBuilding(1277, 2, 35, 25, P9);
        buildBuilding(1196, 215, 64, 25, CABB);
        buildBuilding(1196, 444, 100, 25, KANEFF_CENTRE);
        buildBuilding(1415, 587, 120, 50, MARGRATH_VALLEY_RESIDENCES);
        buildBuilding(1533, 410, 40, 25,P10);
        buildBuilding(1552, 120, 35, 25, P8);
        buildBuilding(1687, 240, 35, 25, P4);
        buildBuilding(1866, 420, 45, 25, P11);
        buildBuilding(1757, 60, 100, 50, SOUTH_PLAYING_FIELD);
        buildBuilding(1757, 654, 128, 50, ALUMNI_HOUSE_AND_PARKING_OFFICE);
        buildPath(750, 85, 77, Color.GREEN, 3,
                ACADEMIC_LEARNING_CENTRE, GREENHOUSE, boardPane);
        buildPath(920, 155, 38, Color.YELLOW, 2,
                CCT_BUILDING, GREENHOUSE, boardPane);
        buildPath(135, 120, 87, Color.GREEN, 5,
                ACADEMIC_LEARNING_CENTRE, P1, boardPane);
        buildPath(135, 80, 60, Color.WHITE, 2,
                INSTRUCTIONAL_CENTRE, P1, boardPane);
        buildPath(460, 10, 91, Color.RED, 6,
                INSTRUCTIONAL_CENTRE, GREENHOUSE, boardPane);
        buildPath(80, 183, 180, Color.ORANGE, 1,
                P1, MAANJIWE_NENDAMOWINAN, boardPane);
        buildPath(140, 163, 105, Color.YELLOW, 3,
                P1, NORTH_FIELD, boardPane);
        buildPath(100, 335, 170, Color.GREEN, 2,
                DEERFIELD_HALL, MAANJIWE_NENDAMOWINAN, boardPane);
        buildPath(450, 220, 90, Color.PURPLE, 5,
                NORTH_FIELD, CCT_BUILDING, boardPane);
        buildPath(430, 260, 115, Color.YELLOW, 2,
                NORTH_FIELD, ERINDALE_HALL, boardPane);
        buildPath(665, 310, 70, Color.ORANGE, 3,
                CCT_BUILDING, ERINDALE_HALL, boardPane);
        buildPath(680, 350, 90, Color.BLACK, 2,
                STUDENT_CENTRE, ERINDALE_HALL, boardPane);
        buildPath(600, 405, 150, Color.BLACK, 3,
                OSCAR_PETERSON_HALL, ERINDALE_HALL, boardPane);
        buildPath(520, 380, 240, Color.LIGHTBLUE, 3,
                ERINDALE_STUDIO_THEATRE, ERINDALE_HALL, boardPane);
        buildPath(220, 250, 85, Color.WHITE, 1,
                NORTH_FIELD, MAANJIWE_NENDAMOWINAN, boardPane);
        buildPath(190, 310, 152, Color.GREEN, 3,
                ERINDALE_STUDIO_THEATRE, MAANJIWE_NENDAMOWINAN, boardPane);
        buildPath(330, 330, 190, Color.RED, 2,
                ERINDALE_STUDIO_THEATRE, NORTH_FIELD, boardPane);
        buildPath(1306, 153, 73, Color.RED, 3,
                CABB, P8, boardPane);
        buildPath(1221, 120, 22, Color.BLACK, 2,
                CABB, P9, boardPane);
        buildPath(1075, 73, 131, Color.RED, 2,
                CABB, GREENHOUSE, boardPane);
        buildPath(1028, 208, 82, Color.LIGHTBLUE, 2,
                CCT_BUILDING, CABB, boardPane);
        buildPath(917, 290, 10, Color.LIGHTBLUE, 1,
                CCT_BUILDING, STUDENT_CENTRE, boardPane);
        buildPath(887, 424, -147, Color.YELLOW, 3,
                OSCAR_PETERSON_HALL, STUDENT_CENTRE, boardPane);
        buildPath(1840, 542, 20, Color.ORANGE, 2,
                ALUMNI_HOUSE_AND_PARKING_OFFICE, P11, boardPane);
        buildPath(1800, 335, -40, Color.WHITE, 2,
                P4, P11, boardPane);
        buildPath(1600, 337, 37, Color.LIGHTBLUE, 2,
                P10, P4, boardPane);
        buildPath(1616, 387, 90, Color.RED, 3,
                P10, P11, boardPane);
        buildPath(1620, 435, 134, Color.WHITE, 3,
                P10, ALUMNI_HOUSE_AND_PARKING_OFFICE, boardPane);
        buildPath(1511, 614, 95, Color.WHITE, 3,
                MARGRATH_VALLEY_RESIDENCES, ALUMNI_HOUSE_AND_PARKING_OFFICE, boardPane);
        buildPath(1444, 498, 43, Color.BLACK, 2,
                MARGRATH_VALLEY_RESIDENCES, P10, boardPane);
        buildPath(829, 590, 65, Color.BLACK, 2,
                OSCAR_PETERSON_HALL, ANNEX, boardPane);
        buildPath(846, 621, 76, Color.PURPLE, 3,
                OSCAR_PETERSON_HALL, P5, boardPane);
        buildPath(1066, 528, -70, Color.WHITE, 1,
                ANNEX, P5, boardPane);
        buildPath(1060, 477, 62, Color.PURPLE, 2,
                ANNEX, KANEFF_CENTRE, boardPane);
        buildPath(1002, 365, 95, Color.PURPLE, 3,
                STUDENT_CENTRE, KANEFF_CENTRE, boardPane);
        buildPath(1022, 258, 119, Color.YELLOW, 3,
                CCT_BUILDING, KANEFF_CENTRE, boardPane);
        buildPath(1250, 342, -18, Color.ORANGE, 2,
                CABB, KANEFF_CENTRE, boardPane);
        buildPath(1315, 352, 42, Color.LIGHTBLUE, 4,
                P8, KANEFF_CENTRE, boardPane);
        buildPath(1362, 400, 85, Color.GREEN, 2,
                P10, KANEFF_CENTRE, boardPane);
        buildPath(1322, 455, 147, Color.WHITE, 2,
                MARGRATH_VALLEY_RESIDENCES, KANEFF_CENTRE, boardPane);
        buildPath(1245, 466, -122, Color.LIGHTBLUE, 2,
                P5, KANEFF_CENTRE, boardPane);
        buildPath(1599, 55, 80, Color.ORANGE, 2,
                P8, SOUTH_PLAYING_FIELD, boardPane);
        buildPath(1500, 64, -71, Color.GREEN, 3,
                P8, P9, boardPane);
        buildPath(1635, 150, 138, Color.LIGHTBLUE, 1,
                P8, P4, boardPane);
        buildPath(1833, 123, 171, Color.PURPLE, 3,
                P11, SOUTH_PLAYING_FIELD, boardPane);
        buildPath(440, 572, 104, Color.WHITE, 3,
                ERINDALE_STUDIO_THEATRE, OSCAR_PETERSON_HALL, boardPane);
        buildPath(275, 646, 91, Color.ORANGE, 5,
                P6, OSCAR_PETERSON_HALL, boardPane);
        buildPath(70, 542, 42, Color.RED, 1,
                P7, DEERFIELD_HALL, boardPane);
        buildPath(113, 565, 81, Color.YELLOW, 2,
                P7, ERINDALE_STUDIO_THEATRE, boardPane);
        buildPath(52, 625, 100, Color.BLACK, 2,
                P7, P6, boardPane);
        buildPath(251, 604, 69, Color.LIGHTBLUE, 1,
                ERINDALE_STUDIO_THEATRE, P6, boardPane);
        Path p = buildPath(424, 190, 59, Color.BLACK, 2,
                NORTH_FIELD, ACADEMIC_LEARNING_CENTRE, boardPane);

        for(Button button: buttonMap.keySet()){
            button.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    switch (state){
                        case FirstRound:
                            if(button == removeDestinationButton){
                                //Needs fix
                                removeDestinationFlag = true;
                                if(cardStage == null){
                                    CardViewer cardViewer = new CardViewer();
                                    cardStage = new Stage();
                                    cardViewer.start(cardStage);
                                }
                                else{
                                    cardStage.requestFocus();
                                }
                            } else if (button == skipButton){
                                game.playTurn(null, false, 0, 0, false, "", "");
                            }
                            if(game.getRound() == 1){
                                state = State.Default;
                                skipButton.setVisible(false);
                                removeDestinationButton.setVisible(false);
                            }
                            break;


                        case Default:
                            int index = buttonMap.get(button);
                            if (index < 6){
                                state = State.FirstCardSelected;
                                firstCardIndex = index;
                                firstCardButton = button;
                                cancelButton.setVisible(true);
                                button.setBorder(new Border(new BorderStroke(buttonColor,
                                        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                            } else if (index == 6){
                                button.setBorder(new Border(new BorderStroke(buttonColor,
                                        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                                cancelButton.setVisible(true);
                                endTurnButton.setVisible(true);
                                state = State.DestinationSelected;
                            } else{
                                if(buildings.containsKey(button.getText())){
                                    List<Path> newPaths = game.findValidPaths(button.getText());
                                    if(newPaths.size() > 0){
                                        state = State.FirstBuildingSelected;
                                        firstBuildingButton = button;
                                        cancelButton.setVisible(true);
                                        button.setBorder(new Border(new BorderStroke(buttonColor,
                                                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                                    }

                                }
                            }
                            break;
                        case DestinationSelected:
                            if (button == endTurnButton){
                                game.playTurn(null, false, 0, 0, true, "","");
                                cancelButton.setVisible(false);
                                endTurnButton.setVisible(false);
                                state = State.Default;
                                button.setBorder(null);
                            }
                            else if (button == cancelButton){
                                cancelButton.setVisible(false);
                                endTurnButton.setVisible(false);
                                state = State.Default;
                                button.setBorder(null);
                            }

                            break;
                        case FirstCardSelected:
                            index = buttonMap.get(button);
                            if (button == cancelButton){
                                state = State.Default;
                                cancelButton.setVisible(false);
                                firstCardButton.setBorder(null);
                            } else if(firstCardIndex == 5 && index == 5 ||
                                    firstCardIndex != index && index < 6){
                                secondCardButton = button;
                                secondCardIndex = index;
                                button.setBorder(new Border(new BorderStroke(buttonColor,
                                        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                                endTurnButton.setVisible(true);
                                state = State.SecondCardSelected;
                            }
                            break;
                        case SecondCardSelected:
                            index = buttonMap.get(button);
                            if (button == cancelButton){
                                state = State.Default;
                                cancelButton.setVisible(false);
                                endTurnButton.setVisible(false);
                                firstCardButton.setBorder(null);
                                secondCardButton.setBorder(null);
                            } else if (button == endTurnButton){
                                if (firstCardIndex != 5){
                                    String name = game.getTableTrainCards()[firstCardIndex].getName();
                                    Image img = new Image(new File(IMAGES_PATH_LOCATION + name + ".png").toURI().toString());
                                    ImageView view = new ImageView(img);
                                    view.setPreserveRatio(true);
                                    buttonIntMap.get(firstCardIndex).setText(getLabel(game.getTableTrainCards()[firstCardIndex].getColor()));
                                    buttonIntMap.get(firstCardIndex).setGraphic(view);
                                    buttonIntMap.get(firstCardIndex).setContentDisplay(ContentDisplay.BOTTOM);


                                    //buttonIntMap.get(firstCardIndex).setText(game.getTableTrainCards()[firstCardIndex].getName());
                                }
                                if (secondCardIndex != 5){
                                    String name = game.getTableTrainCards()[secondCardIndex].getName();
                                    Image img = new Image(new File(IMAGES_PATH_LOCATION + name + ".png").toURI().toString());
                                    ImageView view = new ImageView(img);
                                    view.setPreserveRatio(true);
                                    buttonIntMap.get(secondCardIndex).setGraphic(view);
                                    buttonIntMap.get(secondCardIndex).setContentDisplay(ContentDisplay.BOTTOM);
                                    buttonIntMap.get(secondCardIndex).setText(getLabel(game.getTableTrainCards()[secondCardIndex].getColor()));

                                    // buttonIntMap.get(secondCardIndex).setText(game.getTableTrainCards()[secondCardIndex].getName());
                                }

                                state = State.Default;
                                cancelButton.setVisible(false);
                                endTurnButton.setVisible(false);
                                firstCardButton.setBorder(null);
                                secondCardButton.setBorder(null);
                                game.playTurn(null, true, firstCardIndex, index, false, "","");
                            }
                            break;
                        case FirstBuildingSelected:
                            if (button == cancelButton){
                                state = State.Default;
                                cancelButton.setVisible(false);
                                firstBuildingButton.setBorder(null);
                            } else if(buildings.containsKey(button.getText())){
                                Path path = game.findPath(button.getText(), firstBuildingButton.getText());
                                if (path != null && game.isValidPath(path)){
                                    secondBuildingButton = button;
                                    button.setBorder(new Border(new BorderStroke(buttonColor,
                                            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                                    endTurnButton.setVisible(true);
                                    state = State.SecondBuildingSelected;
                                }

                            }
                            break;
                        case SecondBuildingSelected:
                            index = buttonMap.get(button);
                            if (button == cancelButton){
                                state = State.Default;
                                cancelButton.setVisible(false);
                                endTurnButton.setVisible(false);
                                firstBuildingButton.setBorder(null);
                                secondBuildingButton.setBorder(null);
                            } else if (button == endTurnButton){
                                state = State.Default;
                                cancelButton.setVisible(false);
                                endTurnButton.setVisible(false);
                                firstBuildingButton.setBorder(null);
                                secondBuildingButton.setBorder(null);
                                game.playTurn(null, false, 0, 0, false, firstBuildingButton.getText(),secondBuildingButton.getText());
                            }
                            break;
                        case GameOver:
                            break;

                    }
                    updateBoard();
                }
            });
        }

        updateBoard();
        /*
        anchorPane.getChildren().addAll(menuBar);

        anchorPane.getChildren().addAll(menuBar, splitPane);*/

        Scene scene=new Scene(anchorPane);
        stage.setTitle("Ticket to Ride");
        stage.setScene(scene);


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

        stage.show();
        HashMap<Color, Integer> hashMap = new HashMap();
        for(Path path: paths){
            if(!hashMap.containsKey(path.getColor())){
                hashMap.put(path.getColor(), path.getLength());

            }
            else{
                hashMap.put(path.getColor(), hashMap.get(path.getColor()) + path.getLength());
            }
        }
        for(Color color : hashMap.keySet()){
            System.out.println(color.toString()+ hashMap.get(color));
        }

    }

    private void buildBuilding(int x, int y, int width, int height, String text) {
        Button building = new Button();
        building.setLayoutX(x);
        building.setLayoutY(y);
        building.setPrefWidth(width);
        building.setPrefHeight(height);
        building.setText(text);
        building.setTextAlignment(TextAlignment.CENTER);
        building.setWrapText(true);
        boardPane.getChildren().add(building);
        buildings.put(text, building);
        buttonMap.put(building, buttonCount);
        buttonIntMap.put(buttonCount, building);
        buttonCount += 1;
        //building.getTransforms().add(scale);
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
        buttonMap.put(button, buttonCount);
        buttonIntMap.put(buttonCount, button);
        buttonCount += 1;



        playerPane.getChildren().add(button);
        return button;
    }

    public void updateBoard(){
        playerTurn.setText("Current Turn: Player " + (game.getPlayers().get(game.getTurn()).getNumber()));
        for(int i = 0; i < playerPoints.length; i++){
            playerPoints[i].setText("Player" + game.getPlayers().get(i).getNumber() +  " Points: " + game.getPoints()[i]);
        }
        playerColor.setFill(game.getPlayers().get(game.getTurn()).getColor());
        highlightPaths();
        if(game.getTrainCardDeck().size() <= 2){
            game.shuffleBackDiscard();
        }
        if(game.getDestinationCardDeck().size() == 0){
            destinationDeckButton.setVisible(false);
        }
        if(game.isGameOver()){
            state = state.GameOver;

        }
    }

    private void highlightPaths(){
        for (Path path: paths){
            if(game.isValidPath(path)){
                for(Rectangle road : path.getRoads()){
                    road.setOpacity(1);
                    road.setStroke(Color.DARKBLUE);
                    road.setStrokeWidth(5);
                    road.setStrokeType(StrokeType.OUTSIDE);

                }
            }
            else{
                for(Rectangle road : path.getRoads()){
                    road.setOpacity(0.3);
                    road.setStroke(Color.BLACK);
                    road.setStrokeWidth(1);
                    road.setStrokeType(StrokeType.INSIDE);
                }
            }
        }
    }

    private HBox buildRoad(int x, int y, int rotation, Color color, AnchorPane boardPane){
        Rectangle road = new Rectangle();
        road.setArcHeight(5);
        road.setArcWidth(5);
        road.setFill(color);
        road.setHeight(90);
        road.setWidth(15);
        //road.setLayoutX(x);
        //road.setLayoutY(y);
        road.setRotate(rotation);
        road.setStroke(Color.BLACK);
        road.setStrokeType(StrokeType.INSIDE);
        HBox hBox = new HBox();
        hBox.setLayoutX(x);
        hBox.setLayoutY(y);
        hBox.getChildren().add(road);
        boardPane.getChildren().add(hBox);
        hBox.setAlignment(Pos.CENTER);
        return hBox;
    }

    public void buildTrains(Path p, Color color){
        for(int i = 0; i < p.getLength(); i++){
            buildTrain(0, 0, (int)p.getRoads().get(i).getRotate(),color,
                    p.getHBoxList().get(i));
            Label label = buildTrainLabel();
            p.getHBoxList().get(i).getChildren().add(label);
        }

    }

    private Rectangle buildTrain(int x, int y, int rotation, Color color, HBox hBox){
        Rectangle train = new Rectangle();
        train.setArcHeight(180);
        train.setArcWidth(15);
        train.setFill(color);
        train.setHeight(90);
        train.setWidth(15);
        train.setLayoutX(x);
        train.setLayoutY(y);
        train.setRotate(rotation);
        train.setStroke(Color.SILVER);
        train.setStrokeType(StrokeType.INSIDE);
        hBox.getChildren().add(train);
        train.toFront();

        return train;
    }


    private Path buildPath(int x, int y, int rotation, Color color, int length,
                           String start, String end, AnchorPane boardPane){

        List<Rectangle> roads = new ArrayList<>();
        List<HBox> hBoxes = new ArrayList<>();
        for(int i = 0; i < length; i++){
            HBox roadAnchor = buildRoad(x, y, rotation, color, boardPane);
            hBoxes.add(roadAnchor);
            Rectangle road = (Rectangle) roadAnchor.getChildren().get(0);
            Label label = buildLabel(color);
            roadAnchor.getChildren().add(label);

            roads.add(road);
            x = (int)(x + Math.cos((90 - rotation) * Math.PI/180) * 92);
            y = (int)(y - Math.sin((90 - rotation) * Math.PI/180) *92);

        }
        Path path = new Path(start, end, roads, hBoxes);
        paths.add(path);
        return path;
    }

    private Label buildLabel(Color color){
        for (int i = 0; i < ROAD_COLORS.length; i++){
            if (color == ROAD_COLORS[i]){
                Label label = new Label(Integer.toString(i + 1));
                return label;
            }
        }
        return new Label("0");
    }

    public String getLabel(Color color){
        for (int i = 0; i < ROAD_COLORS.length; i++){
            if (color == ROAD_COLORS[i]){
                return Integer.toString(i + 1);

            }
        }
        return "0";
    }

    private Label buildTrainLabel(){
        Label label = new Label("P" + Integer.toString(game.getPlayers().get(game.getTurn()).getNumber()));
        return label;
    }

    private Shape buildSquare(int x, int y, int rotation, Color color){
        Polygon polygon = new Polygon();
        polygon.setFill(color);
        polygon.getPoints().addAll(
                -5.0, 5.0,
                -5.0, -5.0,
                5.0, -5.0,
                5.0, 5.0);
        polygon.setRotate(rotation);
        polygon.setStroke(Color.BLACK);
        polygon.setStrokeType(StrokeType.INSIDE);
        return polygon;
    }

    private Shape buildTriangle(int x, int y, int rotation, Color color){
        Polygon polygon = new Polygon();
        polygon.setFill(color);
        polygon.getPoints().addAll(
                -5.0, -5.0,
                5.0, -5.0,
                0.0, 5.0);
        polygon.setRotate(rotation);
        polygon.setStroke(Color.BLACK);
        polygon.setStrokeType(StrokeType.INSIDE);
        return polygon;
    }


    private Shape buildCircle(int x, int y, int rotation, Color color){
        Circle circle = new Circle();
        circle.setFill(color);
        circle.setRadius(5);
        circle.setRotate(rotation);
        circle.setStroke(Color.BLACK);
        circle.setStrokeType(StrokeType.INSIDE);
        return circle;
    }

    public List<Path> getPaths(){
        return paths;
    }

    public Color[] getColors(){
        return colors;
    }

    public void setColors(Color[] colors){
        this.colors = colors;
    }

    public Game getGame(){
        return this.game;
    }

    public void setCardStageNull(){
        cardStage = null;
    }

    public boolean getRemoveDestinationFlag(){
        return removeDestinationFlag;
    }


    public void setState(Board.State state) {
        this.state = state;
    }

    public Button getSkipButton(){
        return skipButton;
    }

    public Button getRemoveDestinationButton(){
        return removeDestinationButton;
    }

    public Stage getStage(){
        return this.getStage();
    }
}