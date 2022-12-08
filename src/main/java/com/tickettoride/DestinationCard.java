package main.java.com.tickettoride;



public class DestinationCard {
    private String start;
    private String end;
    private int value;

    public DestinationCard(String start, String end, int value){
        this.start = start;
        this.end = end;
        this.value = value;
    }

    public String getStart(){
        return start;
    }

    public String getEnd(){
        return end;
    }

    public int getValue(){
        return value;
    }


}
