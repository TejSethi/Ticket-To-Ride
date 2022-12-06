package background;

import boardobjects.Location;
import boardobjects.TrainPath;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The game class. Plays the game based on the State, calling methods as required and then updating when the state
 * becomes updated. The game is what will be referenced by the display.
 */

public class Game {
    public Settings set;
    private HashMap<String, Location> locationMap;
    private ArrayList<TrainPath> allPaths;
    public Game() {
        this.set = new Settings();
        setupBoard();
    }
    public ArrayList<Location> getLocations(String[] locationNames) {
        ArrayList<Location> output = new ArrayList<>();
        for (int i = 0; i < locationNames.length; i++) {
            output.add(locationMap.get(locationNames[i]));
        }
        return output;
    }
    private void setupBoard() {
        // Setup locationMap
        ArrayList<Location> locations = LocationFactory.generate(this);
        String name;
        this.locationMap = new HashMap<>();
        for (int i = 0; i < locations.size(); i++) {
            name = locations.get(i).getName();
            this.locationMap.put(name, locations.get(i));
        }
        // Generate Paths
        this.allPaths = TrainPathFactory.generate(this);
    }
    public ArrayList<TrainPath> getPaths() {
        return this.allPaths;
    }
}