package background;

import boardobjects.Location;
import boardobjects.TrainPath;

import java.util.ArrayList;


public class TrainPathFactory {
    public static ArrayList<TrainPath> generate(Game game) {
        ArrayList<TrainPath> paths = new ArrayList<>();
        ArrayList<String[]> stringpath = Settings.trainPaths;
        for (int i = 0; i < stringpath.size(); i++){
            ArrayList<Location> locations = game.getLocations(stringpath.get(i));
            TrainPath trainPath = new TrainPath(locations.get(0), locations.get(1));
            locations.get(0).updatePaths(trainPath);
            locations.get(1).updatePaths(trainPath);
            paths.add(trainPath);
        }
        return paths;
    }
}
