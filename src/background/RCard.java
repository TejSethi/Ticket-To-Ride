package background;

import boardobjects.Location;

/**
 * A route card.
 */

public class RCard extends Card {
    private COLOR color;
    private Location loc1; //TODO make a node class? Or something like that.
    private Location loc2; //TODO make a node class? Or something like that.
    private String imageName;

    public void RCard(Location location1, Location location2) {
        this.loc1 = location1;   //TODO not sure if I should make Game.getNode or change
        this.loc2 = location2;   //TODO location1 and location2 to Location objects
        this.imageName = location1.getName().toLowerCase() + location2.getName().toLowerCase() + ".jpg";   //TODO change if it's not a jpg
    }

    public String getImageName() {
        return this.imageName;
    }
    public Location[] getLocations() {
        return new Location[]{this.loc1, this.loc2};
    }
}