package background;

import boardobjects.Location;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class LocationFactory {
    public static ArrayList<Location> generate(Game game) {
        ArrayList<Location> list = new ArrayList<>();
        HashMap<String, int[]> setup = Settings.locations;
        String[] keys = setup.keySet().toArray(new String[setup.size()]);
        int[][] vals = setup.values().toArray(new int[setup.size()][2]);
        for (int i = 0; i < setup.size(); i++) {
            int x = vals[i][0];
            int y = vals[i][1];
            String name = keys[i];
            list.add(new Location(x, y, name));
        }
        return list;
    }
}
