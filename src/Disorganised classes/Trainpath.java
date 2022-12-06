/**
 * A path where trains can be placed, when any of the spaces are hovered over, they all "glow." When any of the train
 * path pieces are clicked, pieces are placed there.
 */

/**
public class Trainpath {
    private Location loc1;
    private Location loc2;
    public boolean filled;
    public Player filledBy;

    public Trainpath(Location location1, Locaiton location2) {
        this.loc1 = location1;
        this.loc2 = location2;
        location1.updatePaths(this);
        location2.updatePaths(this);
    }
    public fillPath(Player player) {
        this.filledBy = player;
        this.filled = true;
    }
    public String[] getLocations() {
        locations = new String[2];
        locations[0] = this.loc1;
        locations[1] = this.loc2;
        return locations;
    }
}*/