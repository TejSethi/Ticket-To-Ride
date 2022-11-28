import java.util.ArrayList;

/**
 * A location node on the map. Has paths leading away from it. (The paths update the Location's path list.
 */

public class Location {
    private String name;
    private ArrayList<Trainpath> paths;

    public ArrayList<Trainpath> getPaths() {
        return this.paths;
    }
    public String getName() {
        return this.name;
    }
    public void updatePaths(Trainpath newPath) {
        this.paths.add(newPath);
    }

    public Trainpath findCommonPath(Location secondLocation) {
        for (Trainpath path : this.paths) {
            for (Trainpath secondpath: secondLocation.paths) {
                if (path.equals(secondpath)) {
                    return path;
                }
            }
        }
    }
}