package main.java.com.tickettoride;

import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.shuffle;


public class Game {
    public static final String P1 = "P1";
    public static final String MAANJIWE_NENDAMOWINAN = "Maanjiwe Nendamowinan";
    public static final String DEERFIELD_HALL = "Deerfield Hall";
    public static final String P7 = "P7";
    public static final String P6 = "P6";
    public static final String INSTRUCTIONAL_CENTRE = "Instructional Centre";
    public static final String NORTH_FIELD = "North Field";
    public static final String ERINDALE_STUDIO_THEATRE = "Erindale Studio Theatre";
    public static final String ACADEMIC_LEARNING_CENTRE = "Academic Learning Centre";
    public static final String ERINDALE_HALL = "Erindale Hall";
    public static final String OSCAR_PETERSON_HALL = "Oscar Peterson Hall";
    public static final String GREENHOUSE = "Greenhouse";
    public static final String CCT_BUILDING = "CCT Building";
    public static final String STUDENT_CENTRE = "Student Centre";
    public static final String ANNEX = "Annex";
    public static final String P5 = "P5";
    public static final String P9 = "P9";
    public static final String CABB = "CABB";
    public static final String KANEFF_CENTRE = "Kaneff Centre";
    public static final String MARGRATH_VALLEY_RESIDENCES = "Margrath Valley Residences";
    public static final String P10 = "P10";
    public static final String P8 = "P8";
    public static final String P4 = "P4";
    public static final String P11 = "P11";
    public static final String SOUTH_PLAYING_FIELD = "South Playing Field";
    public static final String ALUMNI_HOUSE_AND_PARKING_OFFICE = "Alumni House and Parking Office";

    public static final DestinationCard[] DESTINATION_CARDS = {
            new DestinationCard(P1, ALUMNI_HOUSE_AND_PARKING_OFFICE, 5),
            new DestinationCard(MAANJIWE_NENDAMOWINAN, CABB, 11),
            new DestinationCard(P8, ERINDALE_STUDIO_THEATRE, 3),
            new DestinationCard(P6, GREENHOUSE, 16),
            new DestinationCard(GREENHOUSE, ALUMNI_HOUSE_AND_PARKING_OFFICE, 9),
            new DestinationCard(P11, NORTH_FIELD, 7),
            new DestinationCard(DEERFIELD_HALL, P8, 12),
            new DestinationCard(P9, OSCAR_PETERSON_HALL, 13),
            new DestinationCard(SOUTH_PLAYING_FIELD, DEERFIELD_HALL, 0),
            new DestinationCard(INSTRUCTIONAL_CENTRE, P10, 17),
            new DestinationCard(P11, ACADEMIC_LEARNING_CENTRE, 13),
            new DestinationCard(ERINDALE_STUDIO_THEATRE, CABB, 11),
            new DestinationCard(P4, P1, 12),
            new DestinationCard(P7, P11, 15),
            new DestinationCard(ALUMNI_HOUSE_AND_PARKING_OFFICE, INSTRUCTIONAL_CENTRE, 13),
            new DestinationCard(CCT_BUILDING, SOUTH_PLAYING_FIELD, 9)
    };

    public static final Color[] ROAD_COLORS = {
            Color.PURPLE,
            Color.LIGHTBLUE,
            Color.RED,
            Color.ORANGE,
            Color.BLACK,
            Color.GREEN,
            Color.YELLOW,
            Color.WHITE
    };

    public static final String[] TRAIN_NAMES = {
            "Purple",
            "Blue",
            "Red",
            "Orange",
            "Black",
            "Green",
            "Yellow",
            "White"
    };

    public static final String[] TRAIN_IMAGES = {
            "Purple.png",
            "Blue.png",
            "Temp.png",
            "Orange.png",
            "Black.png",
            "Green.png",
            "Yellow.png",
            "White.png"
    };

    public static String IMAGES_PATH_LOCATION = "src/main/java/com/tickettoride/resources/";

    private CardDeck<TrainCard> trainCardsDeck;
    private CardDeck<DestinationCard> destinationCardsDeck;

    private List<Player> players;
    private TrainCard[] tableTrainCards;
    private List<Path> paths;
    private int round;
    private int turn;
    private int[] points;
    private Board board;
    private boolean gameOverFlag;

    public Game(Board board){
        gameOverFlag = false;
        this.board = board;
        this.paths = board.getPaths();
        trainCardsDeck = makeTrainCardsDeck();
        destinationCardsDeck = makeDestinationCardsDeck();
        players = makePlayers(board.getColors());
        tableTrainCards = new TrainCard[5];
        points = new int[players.size()];
        round = 0;
        setUp();
    }

    private List<Player> makePlayers(Color[] colors){
        List<Player> players = new ArrayList<>();
        for(int i = 0; i < colors.length; i++){
            players.add(new Player(colors[i], i + 1));
        }
        shuffle(players);
        turn = 0;
        return players;
    }

    private CardDeck<TrainCard> makeTrainCardsDeck(){
        CardDeck<TrainCard> trainCardsDeck = new CardDeck<>(true);

        for (int i = 0; i < ROAD_COLORS.length; i++){
            for(int j = 0; j < 12; j++){
                trainCardsDeck.addCard(new TrainCard(ROAD_COLORS[i], TRAIN_NAMES[i]));
            }
        }
        shuffle(trainCardsDeck.getCardList());
        return trainCardsDeck;
    }

    private CardDeck<DestinationCard> makeDestinationCardsDeck(){
        List<DestinationCard> initialDestinationCards = new ArrayList<>(Arrays.asList(DESTINATION_CARDS));
        CardDeck<DestinationCard> destinationCardsDeck = new CardDeck<>(initialDestinationCards, false);

        shuffle(destinationCardsDeck.getCardList());
        return destinationCardsDeck;
    }

    public TrainCard drawTrainCardFromDeck(){
        return trainCardsDeck.removeCard();
    }

    public DestinationCard drawDestinationCard(){
        return destinationCardsDeck.removeCard();
    }

    /*
    As a player in this
    game, I want to
    receive my initial 4
    train car cards so
    that I can make my
    initial move on my
    first turn when the
    game starts.

    As a player in this
    game, I want to
    receive my initial 2
    or 3 destination
    ticket cards so that I
    know what pairs of
    destinations need
    to connected by the
    end of the game.
    */
    public void setUp(){
        for (Player player: players){
            for(int i = 0; i < 3; i++){
                player.addDestinationCard(drawDestinationCard());
                player.addTrainCard(drawTrainCardFromDeck());
            }
            player.addTrainCard(drawTrainCardFromDeck());
        }
        /*
        As a player in this
        game, I want to see
        the initial 5 public
        face up train car
        cards so that I can
        draw them in future
        turns.*/
        int startX = 200;
        for(int i = 0; i < 5; i++){
            tableTrainCards[i] = drawTrainCardFromDeck();
            String name = tableTrainCards[i].getName();
            Button button = board.buildButton(startX, 20, 156, 242, name);
            button.setText(Board.getInstance().getLabel(tableTrainCards[i].getColor()));
            button.setContentDisplay(ContentDisplay.BOTTOM);
            name = IMAGES_PATH_LOCATION +  name + ".png";
            Image img = new Image(new File(name).toURI().toString());
            ImageView view = new ImageView(img);
            view.setPreserveRatio(true);
            button.setGraphic(view);
            startX += 180;
        }
    }

    public void playTurn(DestinationCard destinationCard, boolean drawTrain,
                         int train1, int train2,
                         boolean drawDestination, String start, String end){
        Player player = players.get(turn);
        /*
        As a player in this
    game, I want to
    receive my initial 2
    or 3 destination
    ticket cards so that I
    know what pairs of
    destinations need
    to connected by the
    end of the game.
    */
        if (round == 0){
            if(destinationCard != null){
                player.removeDestinationCard(destinationCard);
            }
        } else{
            if (drawTrain) {
                if (train1 < 5){
                    player.addTrainCard(tableTrainCards[train1]);
                    tableTrainCards[train1] = drawTrainCardFromDeck();
                } else{
                    player.addTrainCard(drawTrainCardFromDeck());
                }
                if (train2 < 5){
                    player.addTrainCard(tableTrainCards[train2]);
                    tableTrainCards[train2] = drawTrainCardFromDeck();
                } else{
                    player.addTrainCard(drawTrainCardFromDeck());
                }
            }
            else if (drawDestination) {
                player.addDestinationCard(drawDestinationCard());
            }
            else{
                Path path = findPath(start, end);
                path.setTaken(true);
                player.placeTrains(path, board);
                points[turn] += path.getPoints();
                for(int i = 0; i < path.getLength(); i++){
                    trainCardsDeck.addToDiscardedList(player.removeTrainCard(path.getColor()));
                }
            }
            gameOverFlag = getGameOver();
            if(gameOverFlag){
                for (int i = 0; i < points.length; i++){
                    points[i] += players.get(i).destinationPoints();
                    players.get(i).setFinalScore(points[i]);

                }
            }
        }
        turn = (turn + 1) % players.size();
        if (turn == 0){
            round += 1;
        }

    }

    public Path findPath(String start, String end){
        for (Path path: paths){
            if (path.getStart().equals(start) && path.getEnd().equals(end)||
                    path.getStart().equals(end) && path.getEnd().equals(start)){
                return path;
            }
        }
        return null;
    }

    public List<Path> findPaths(String start){
        List<Path> newPaths = new ArrayList<>();
        for(Path path: paths){
            if (path.getStart().equals(start) || path.getEnd().equals(start)) {
                newPaths.add(path);
            }
        }
        return newPaths;
    }

    public List<Path> findValidPaths(String start){
        List<Path> newPaths = new ArrayList<>();
        for(Path path: paths){
            if (path.getStart().equals(start) || path.getEnd().equals(start)) {
                if (isValidPath(path)){
                    newPaths.add(path);
                }
            }
        }
        return newPaths;
    }

    public TrainCard[] getTableTrainCards(){
        return tableTrainCards;
    }

    public int getTurn(){
        return turn;
    }

    public List<Player> getPlayers(){
        return players;
    }

    public int[] getPoints(){
        return points;
    }

    public int getRound(){
        return round;
    }

    public boolean isValidPath(Path path){
        Player player = players.get(getTurn());
        if(path.isTaken()){
            return  false;
        }
        int count = player.getCardCount(path.getColor());
        if(count >= path.getLength() && player.getTrainCars() >= path.getLength()){
            return true;
        }
        return false;
    }

    private boolean noValidPaths(){
        for (Path path: paths) {
            if (!path.isTaken()){
                return  false;
            }
        }
        return true;
    }

    private boolean getGameOver(){
        for(Player player: players){
            if (player.getTrainCars() <= 2){
                return true;
            }
        }
        if (noValidPaths()){
            return true;
        }
        return false;
    }

    public boolean isGameOver(){
        return gameOverFlag;
    }

    public boolean isDestinationCardDeckEmpty(){
        return destinationCardsDeck.getCardList().isEmpty();
    }
}

