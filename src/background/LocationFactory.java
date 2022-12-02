package background;

import boardobjects.Location;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class LocationFactory {
    public static ArrayList<Location> generate(Game game) {
        ArrayList<Location> list = new ArrayList<>();
        HashMap<String, int[]> setup = game.set.locations;
        Object[] keys = setup.keySet().toArray();
        Object[] vals = setup.values().toArray();
        for (int i = 0; i < setup.size(); i++) {
            int x = ((int[]) vals[i])[0];
            int y = ((int[]) vals[i])[1];
            String name = (String) keys[i];
            list.add(new Location(x, y, name));
        }
        return list;
    }
}
