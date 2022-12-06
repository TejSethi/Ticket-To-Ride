package main.java.com.tickettoride;

import javafx.scene.paint.Color;

public class TrainCard {
    private Color color;
    private String name;

    public TrainCard(Color color, String name){
        this.color = color;
        this.name = name;
    }


    public Color getColor(){
        return this.color;
    }

    public String getName(){
        return this.name;
    }


}
