package main.java.com.tickettoride;

import javafx.scene.paint.Color;

import java.util.*;

public class Player {
    private List<DestinationCard> destinationCards;
    private List<TrainCard> trainCards;
    private Color color;
    private int trainCars;
    private List<Path> paths;
    private int number;
    private int finalScore;

    /*
As a player, when
the game is about
to start, I want to
have my train cars
given to me so that
I can place them in
the future
     */
    public Player(Color color, int number){
        this.number = number;
        destinationCards = new ArrayList<>();
        trainCards = new ArrayList<>();
        this.color = color;
        trainCars = 45;
        paths = new ArrayList<>();

    }

    public void addTrainCard(TrainCard card){
        trainCards.add(card);
  }

    public  void addDestinationCard(DestinationCard card){
        destinationCards.add(card);
  }

    public  DestinationCard removeDestinationCard(DestinationCard card){
        int index = destinationCards.indexOf(card);
        return destinationCards.remove(index);
    }



    public TrainCard removeTrainCard(Color color){
        for(int i = 0; i < trainCards.size(); i++){
            if (trainCards.get(i).getColor() == color){
                return trainCards.remove(i);
            }
        }
        throw new RuntimeException("Should not occur");
    }


    public List<TrainCard> getTrainCards(){
        return trainCards;
  }

  public List<DestinationCard> getDestinationCards(){
        return destinationCards;
  }


    public void placeTrains(Path path, Board board) {
        // TODO: in 2.11
    }

    public int getNumber(){
        return number;
    }

    public Color getColor(){
        return color;
    }

    public int getCardCount(Color color){
        int count = 0;
        for(TrainCard card: trainCards){
            if(card.getColor() == color){
                count += 1;
            }
        }
        return count;
    }

    public int getTrainCars(){
        return trainCars;
    }

    public boolean isReachable(String start, String end){
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList();
        List<Path> newPaths = new ArrayList<>(paths);
        queue.add(start);
        while(!queue.isEmpty()){
            String place = queue.remove();
            visited.add(place);
            if (place.equals(end)){
                return true;
            }
            for(int i = 0; i < newPaths.size(); i++){
                Path path = newPaths.get(i);
                if(path.getStart().equals(place)){
                    newPaths.remove(i);
                    if(!visited.contains(path.getEnd())){
                        queue.add(path.getEnd());
                    }
                }
                else if(path.getEnd().equals(place)){
                    newPaths.remove(i);
                    if(!visited.contains(path.getStart())){
                        queue.add(path.getStart());
                    }
                }
            }
        }
        return  false;
    }

    public int destinationPoints(){
        int points = 0;
        for (DestinationCard destinationCard :destinationCards){
            if (isReachable(destinationCard.getStart(), destinationCard.getEnd())){
                points += destinationCard.getValue();
            }
        }
        return points;
    }

    public void setFinalScore(int finalScore){
        this.finalScore = finalScore;
    }

    public int getFinalScore(){
        return finalScore;
    }
}
