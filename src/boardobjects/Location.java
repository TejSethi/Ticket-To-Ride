package boardobjects;

import java.util.ArrayList;

/**
 * A location node on the map. Has paths leading away from it. (The paths update the Location's path list.
 */

public class Location {
    private String name;
    private ArrayList<Trainpath> paths;
    private float x;
    private float y;

    public Location(float x, float y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }
    public ArrayList<Trainpath> getPaths() {
        return this.paths;
    }
    public String getName() {
        return this.name;
    }
    protected void updatePaths(Trainpath newPath) {
        this.paths.add(newPath);
    }
    public float[] getCoords() {
        return new float[]{this.x, this.y};
    }
}