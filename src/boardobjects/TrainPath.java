package boardobjects;

import background.Player;
/**
 * A path where trains can be placed, when any of the spaces are hovered over, they all "glow." When any of the train
 * path pieces are clicked, pieces are placed there.
 */

public class TrainPath {
    private Location loc1;
    private Location loc2;
    public boolean filled;
    public Player filledBy;

    public TrainPath(Location location1, Location location2) {
        this.loc1 = location1;
        this.loc2 = location2;
        location1.updatePaths(this);
        location2.updatePaths(this);
    }
    public boolean fillPath(Player player) {
        if (this.filled) {
            return false;
        }
        this.filledBy = player;
        this.filled = true;
        return true;
    }
    public Location[] getLocations() {
        return new Location[]{this.loc1, this.loc2};
    }
}