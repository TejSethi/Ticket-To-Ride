

package main.java.com.tickettoride;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.*;

public class Board extends Application {
    private Map<String, Button> buildings = new HashMap();
    private List<Path> paths = new ArrayList();

    public Board() {
    }

    public void start(Stage stage) {
        final AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefWidth(1920.0);
        anchorPane.setPrefHeight(1060.0);
        MenuBar menuBar = new MenuBar();
        menuBar.setLayoutX(29.0);
        menuBar.setLayoutY(30.0);
        AnchorPane.setLeftAnchor(menuBar, 0.0);
        AnchorPane.setRightAnchor(menuBar, 0.0);
        AnchorPane.setTopAnchor(menuBar, 0.0);
        Menu file = new Menu("File");
        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Stage stage = (Stage)anchorPane.getScene().getWindow();
                stage.close();
                Main.mainStage.show();
            }
        });
        file.getItems().addAll(new MenuItem[]{exit});
        menuBar.getMenus().addAll(new Menu[]{file});
        SplitPane splitPane = new SplitPane();
        splitPane.setDividerPositions(new double[]{0.7});
        splitPane.setLayoutX(441.0);
        splitPane.setLayoutY(473.0);
        splitPane.setOrientation(Orientation.VERTICAL);
        splitPane.setPrefHeight(1080.0);
        splitPane.setPrefWidth(1920.0);
        AnchorPane.setBottomAnchor(splitPane, 0.0);
        AnchorPane.setLeftAnchor(splitPane, 0.0);
        AnchorPane.setRightAnchor(splitPane, 0.0);
        AnchorPane.setTopAnchor(splitPane, 25.0);
        AnchorPane boardPane = new AnchorPane();
        AnchorPane playerPane = new AnchorPane();
        splitPane.getItems().addAll(new Node[]{boardPane, playerPane});
        this.buildBuilding(65, 155, 35, 25, "P1", boardPane);
        this.buildBuilding(52, 280, 106, 50, "Maanjiwe Nendamowinan", boardPane);
        this.buildBuilding(86, 520, 113, 25, "Deerfield Hall", boardPane);
        this.buildBuilding(14, 621, 35, 25, "P7", boardPane);
        this.buildBuilding(199, 678, 35, 25, "P6", boardPane);
        this.buildBuilding(282, 38, 138, 25, "Instructional Centre", boardPane);
        this.buildBuilding(296, 280, 93, 25, "North Field", boardPane);
        this.buildBuilding(270, 571, 113, 50, "Erindale Studio Theatre", boardPane);
        this.buildBuilding(563, 108, 138, 50, "Academic Learning Centre", boardPane);
        this.buildBuilding(524, 372, 110, 25, "Erindale Hall", boardPane);
        this.buildBuilding(700, 670, 110, 50, "Oscar Peterson Hall", boardPane);
        this.buildBuilding(982, 58, 110, 25, "Greenhouse", boardPane);
        this.buildBuilding(886, 240, 93, 25, "CCT Building", boardPane);
        this.buildBuilding(853, 397, 100, 25, "Student Centre", boardPane);
        this.buildBuilding(958, 543, 64, 25, "Annex", boardPane);
        this.buildBuilding(1111, 597, 35, 25, "P5", boardPane);
        this.buildBuilding(1277, 2, 35, 25, "P9", boardPane);
        this.buildBuilding(1196, 215, 64, 25, "CABB", boardPane);
        this.buildBuilding(1196, 444, 100, 25, "Kaneff Centre", boardPane);
        this.buildBuilding(1415, 587, 120, 50, "Margrath Valley Residences", boardPane);
        this.buildBuilding(1533, 410, 40, 25, "P10", boardPane);
        this.buildBuilding(1552, 120, 35, 25, "P8", boardPane);
        this.buildBuilding(1687, 240, 35, 25, "P4", boardPane);
        this.buildBuilding(1866, 420, 45, 25, "P11", boardPane);
        this.buildBuilding(1757, 60, 83, 50, "South Playing Field", boardPane);
        this.buildBuilding(1757, 654, 128, 50, "Alumni House and Parking Office", boardPane);
        this.buildPath(750, 85, 77, Color.GOLD, 3, "Academic Learning Centre", "Greenhouse", boardPane);
        this.buildPath(920, 155, 38, Color.LIGHTBLUE, 2, "CCT Building", "Greenhouse", boardPane);
        this.buildPath(135, 120, 87, Color.GREEN, 5, "Academic Learning Centre", "P1", boardPane);
        this.buildPath(135, 80, 60, Color.GOLD, 2, "Instructional Centre", "P1", boardPane);
        this.buildPath(460, 10, 91, Color.RED, 6, "Instructional Centre", "Greenhouse", boardPane);
        this.buildPath(80, 183, 180, Color.ORANGE, 1, "P1", "Maanjiwe Nendamowinan", boardPane);
        this.buildPath(140, 163, 105, Color.LIGHTBLUE, 3, "P1", "North Field", boardPane);
        this.buildPath(100, 335, 170, Color.GRAY, 2, "Deerfield Hall", "Maanjiwe Nendamowinan", boardPane);
        this.buildPath(450, 220, 90, Color.PURPLE, 5, "North Field", "CCT Building", boardPane);
        this.buildPath(430, 260, 115, Color.LIGHTBLUE, 2, "North Field", "Erindale Hall", boardPane);
        this.buildPath(665, 310, 70, Color.ORANGE, 3, "CCT Building", "Erindale Hall", boardPane);
        this.buildPath(680, 350, 90, Color.GOLD, 2, "Student Centre", "Erindale Hall", boardPane);
        this.buildPath(600, 405, 150, Color.GREEN, 3, "Oscar Peterson Hall", "Erindale Hall", boardPane);
        this.buildPath(520, 380, 240, Color.LIGHTBLUE, 3, "Erindale Studio Theatre", "Erindale Hall", boardPane);
        this.buildPath(220, 250, 85, Color.WHITE, 1, "North Field", "Maanjiwe Nendamowinan", boardPane);
        this.buildPath(190, 310, 152, Color.GREEN, 3, "Erindale Studio Theatre", "Maanjiwe Nendamowinan", boardPane);
        this.buildPath(330, 330, 190, Color.RED, 2, "Erindale Studio Theatre", "North Field", boardPane);
        this.buildPath(1306, 153, 73, Color.GRAY, 3, "CABB", "P8", boardPane);
        this.buildPath(1221, 120, 22, Color.BLACK, 2, "CABB", "P9", boardPane);
        this.buildPath(1075, 73, 131, Color.GOLD, 2, "CABB", "Greenhouse", boardPane);
        this.buildPath(1028, 208, 82, Color.LIGHTBLUE, 2, "CCT Building", "Greenhouse", boardPane);
        this.buildPath(917, 290, 10, Color.GOLD, 1, "CCT Building", "Student Centre", boardPane);
        this.buildPath(887, 424, -147, Color.RED, 3, "Oscar Peterson Hall", "Student Centre", boardPane);
        this.buildPath(1840, 542, 20, Color.ORANGE, 2, "Alumni House and Parking Office", "P11", boardPane);
        this.buildPath(1800, 335, -40, Color.WHITE, 2, "P4", "P11", boardPane);
        this.buildPath(1600, 337, 37, Color.LIGHTBLUE, 2, "P10", "P4", boardPane);
        this.buildPath(1616, 387, 90, Color.RED, 3, "P10", "P11", boardPane);
        this.buildPath(1620, 435, 134, Color.PURPLE, 3, "P10", "Alumni House and Parking Office", boardPane);
        this.buildPath(1511, 614, 95, Color.GOLD, 3, "Margrath Valley Residences", "Alumni House and Parking Office", boardPane);
        this.buildPath(1444, 498, 43, Color.BLACK, 2, "Margrath Valley Residences", "P10", boardPane);
        this.buildPath(829, 590, 65, Color.GOLD, 2, "Oscar Peterson Hall", "Annex", boardPane);
        this.buildPath(846, 621, 76, Color.PURPLE, 3, "Oscar Peterson Hall", "P5", boardPane);
        this.buildPath(1066, 528, -70, Color.WHITE, 1, "Annex", "P5", boardPane);
        this.buildPath(1060, 477, 62, Color.GOLD, 2, "Annex", "Kaneff Centre", boardPane);
        this.buildPath(1002, 365, 95, Color.PURPLE, 3, "Student Centre", "Kaneff Centre", boardPane);
        this.buildPath(1022, 258, 119, Color.YELLOW, 3, "CCT Building", "Kaneff Centre", boardPane);
        this.buildPath(1250, 342, -18, Color.GOLD, 2, "CABB", "Kaneff Centre", boardPane);
        this.buildPath(1315, 352, 42, Color.LIGHTBLUE, 4, "P8", "Kaneff Centre", boardPane);
        this.buildPath(1362, 400, 85, Color.GREEN, 2, "P10", "Kaneff Centre", boardPane);
        this.buildPath(1322, 455, 147, Color.WHITE, 2, "Margrath Valley Residences", "Kaneff Centre", boardPane);
        this.buildPath(1245, 466, -122, Color.LIGHTBLUE, 2, "P5", "Kaneff Centre", boardPane);
        this.buildPath(1599, 55, 80, Color.RED, 2, "P8", "South Playing Field", boardPane);
        this.buildPath(1500, 64, -71, Color.GREEN, 3, "P8", "P9", boardPane);
        this.buildPath(1635, 150, 138, Color.GOLD, 1, "P8", "P4", boardPane);
        this.buildPath(1833, 123, 171, Color.PURPLE, 3, "P11", "South Playing Field", boardPane);
        this.buildPath(440, 572, 104, Color.WHITE, 3, "Erindale Studio Theatre", "Oscar Peterson Hall", boardPane);
        this.buildPath(275, 646, 91, Color.ORANGE, 5, "P6", "Oscar Peterson Hall", boardPane);
        this.buildPath(70, 542, 42, Color.RED, 1, "P7", "Deerfield Hall", boardPane);
        this.buildPath(113, 565, 81, Color.YELLOW, 2, "P7", "Erindale Studio Theatre", boardPane);
        this.buildPath(52, 625, 100, Color.BLACK, 2, "P7", "P6", boardPane);
        this.buildPath(251, 604, 69, Color.LIGHTBLUE, 1, "Erindale Studio Theatre", "P6", boardPane);
        this.buildPath(424, 190, 59, Color.BLACK, 2, "North Field", "Academic Learning Centre", boardPane);
        this.buildButton(25, 100, 100, 50, "View Hand", playerPane);
        int startX = 200;

        anchorPane.getChildren().addAll(new Node[]{menuBar, splitPane});
        Scene scene = new Scene(anchorPane);
        stage.setTitle("Ticket to Ride");
        stage.setScene(scene);
        stage.show();
    }

    private void buildBuilding(int x, int y, int width, int height, String text, AnchorPane boardPane) {
        final Button building = new Button();
        building.setStyle("-fx-border-color: black; -fx-border-width: 1 1 1 1; -fx-font-weight: normal;");
        building.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                building.setStyle("-fx-border-color: gold; -fx-border-width: 2 2 2 2; -fx-font-weight: bold;");
            }
        });
        building.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                building.setStyle("-fx-border-color: black; -fx-border-width: 1 1 1 1; -fx-font-weight: normal;");
            }
        });
        building.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                Iterator var2 = Board.this.buildings.keySet().iterator();

                while(var2.hasNext()) {
                    String buildingx = (String)var2.next();
                    ((Button)Board.this.buildings.get(buildingx)).setStyle("-fx-border-color: black; -fx-border-width: 1 1 1 1; -fx-font-weight: normal;");
                }

                var2 = Board.this.paths.iterator();

                while(var2.hasNext()) {
                    Path p = (Path)var2.next();
                    if (p.start.equals(building.getText())) {
                        ((Button)Board.this.buildings.get(p.end)).setStyle("-fx-border-color: red; -fx-border-width: 2 2 2 2; -fx-font-weight: bold;");
                    }

                    if (p.end.equals(building.getText())) {
                        ((Button)Board.this.buildings.get(p.start)).setStyle("-fx-border-color: red; -fx-border-width: 1 1 1 1; -fx-font-weight: bold;");
                    }
                }

            }
        });
        building.setLayoutX((double)x);
        building.setLayoutY((double)y);
        building.setPrefWidth((double)width);
        building.setPrefHeight((double)height);
        building.setText(text);
        building.setTextAlignment(TextAlignment.CENTER);
        building.setWrapText(true);
        boardPane.getChildren().add(building);
        this.buildings.put(text, building);
    }

    private void buildButton(int x, int y, int width, int height, String text, AnchorPane boardPane) {
        Button button = new Button();
        button.setLayoutX((double)x);
        button.setLayoutY((double)y);
        button.setPrefWidth((double)width);
        button.setPrefHeight((double)height);
        button.setText(text);
        button.setTextAlignment(TextAlignment.CENTER);
        button.setWrapText(true);
        boardPane.getChildren().add(button);
    }

    private Rectangle buildRoad(int x, int y, int rotation, Color color, AnchorPane boardPane) {
        Rectangle road = new Rectangle();
        road.setArcHeight(5.0);
        road.setArcWidth(5.0);
        road.setFill(color);
        road.setHeight(90.0);
        road.setWidth(15.0);
        road.setLayoutX((double)x);
        road.setLayoutY((double)y);
        road.setRotate((double)rotation);
        road.setStroke(Color.BLACK);
        road.setStrokeType(StrokeType.INSIDE);
        boardPane.getChildren().add(road);
        return road;
    }

    private void buildPath(int x, int y, int rotation, Color color, int length, String start, String end, AnchorPane boardPane) {
        Path path = new Path(start, end);

        for(int i = 0; i < length; ++i) {
            this.buildRoad(x, y, rotation, color, boardPane);
            x = (int)((double)x + Math.cos((double)(90 - rotation) * Math.PI / 180.0) * 92.0);
            y = (int)((double)y - Math.sin((double)(90 - rotation) * Math.PI / 180.0) * 92.0);
        }

        this.paths.add(path);
    }

    private static class Path {
        private List<Rectangle> roads;
        private String start;
        private String end;

        public Path(String start, String end) {
            this.start = start;
            this.end = end;
            this.roads = new ArrayList();
        }
    }
}

