package background;

import boardobjects.Trainpath;

import java.util.ArrayList;


public class TrainPathFactory {
    public static ArrayList<Trainpath> generate(Game game) {
        ArrayList<Trainpath> paths = new ArrayList<>();
        ArrayList<String[]> stringpath = Settings.trainPaths;
        for (int i = 0; i < stringpath.size(); i++){
            ArrayList<Location> locations = game.getLocations(stringpath.get(i));
            paths.add(Trainpath(locations.get(0), locations.get(1)));
        }
        return paths;
    }
}
