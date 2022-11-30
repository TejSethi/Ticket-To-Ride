/**
 * A route card.
 */

/**
public class RCard extends Card {
    private Color color;
    private Location loc1; //TODO make a node class? Or something like that.
    private Location loc2; //TODO make a node class? Or something like that.
    private String imageName;

    public void RCard(String location1, String location2)
        this.loc1 = getNode(location1.toLowerCase());
        this.loc2 = getNode(location2.toLowerCase());
        this.imageName = location1.toLowerCase() + location2.toLowerCase() + ".jpg";   //TODO change if it's not a jpg

    public String getImage() {
        return this.imageName;
    }
    public String[] getLocations() {
        locations = new String[2];
        locations[0] = this.loc1;
        locations[1] = this.loc2;
        return locations;
    }
}*/