package boardobjects;

import background.Player;

import background.TRAIN_COLOR;

import java.util.ArrayList;
/**
 * A path where trains can be placed, when any of the spaces are hovered over, they all "glow." When any of the train
 * path pieces are clicked, pieces are placed there.
 */

public class Trainpath {
    public Location loc1;
    public Location loc2;
    public ArrayList<trainPiece> trainPieces;

    public int length;

    public TRAIN_COLOR colour;

    public boolean filled;
    public Player filledBy;

    public Trainpath(Location location1, Location location2, int length, TRAIN_COLOR colour) {
        this.loc1 = location1;
        this.loc2 = location2;
        this.length = length;
        this.trainPieces = new ArrayList<>();
        for (int i = 0; i < this.length; i ++) {
            this.trainPieces.add(new trainPiece());
            this.trainPieces.get(i).setColor(colour);
        }
        location1.updatePaths(this);
        location2.updatePaths(this);
    }
    public void fillPath(Player player) {
        for (trainPiece piece: this.trainPieces) {
            piece.fillPiece(player);
        }
        this.filledBy = player;
        this.filled = true;
    }
    public Location[] getLocations() {
        return new Location[]{this.loc1, this.loc2};
    }
}

