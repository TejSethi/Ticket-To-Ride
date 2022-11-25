/**
 * A location node on the map. Has paths leading away from it. (The paths update the Location's path list.
 */

public class Location {
    private String name;
    private ArrayList<Trainpath> paths;

    public ArrayList<Trainpath> getPaths() {
        return this.paths;
    }
    public getName() {
        return this.name;
    }
    protected updatePaths(Trainpath newPath) {
        this.paths.add(newPath);
    }
}