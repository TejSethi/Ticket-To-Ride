package main.java.com.tickettoride;

import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.List;

public class Path {

    private List<Rectangle> roads;
    private String start;
    private String end;
    private boolean taken;
    private Color color;
    private static int[] points = {1, 2, 4, 7, 10, 15};
    private List<HBox> hBoxes;

    public Path(String start, String end, List<Rectangle> roads, List<HBox> hBoxList){
        this.start = start;
        this.end = end;
        this.roads = roads;
        this.color = (Color) roads.get(0).getFill();
        this.hBoxes = hBoxList;
    }

    public String getStart(){
        return start;
    }

    public String getEnd(){
        return end;
    }

    public List<Rectangle> getRoads(){
        return roads;
    }

    public boolean isTaken(){
        return taken;
    }

    public void setTaken(boolean taken){
        this.taken = taken;
    }

    public int getLength(){
        return roads.size();
    }

    public Color getColor(){
        return color;
    }


    public int getPoints() {
        return points[getLength() - 1];
    }

    public List<HBox> getHBoxList(){
        return hBoxes;
    }
}
