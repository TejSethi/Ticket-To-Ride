package background;


import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Game settings class. Mainly for testing and debug reasons, not for player-changed settings.
 */

/**
 * Constructor
 * Creates the map HashMap that has String keys and Object values. The object types depend on what the key is.
 */
public class Settings {
    public static int numCardsEachColor;
    public static int numCardsRainbow;
    public static HashMap<String, int[]> locations;
    public static ArrayList<String[]> trainpaths;

    /**
     * Constructor.
     */
    public Settings() {
        // Set numCardsEachColor
        numCardsEachColor = 15;
        // Set numCardsRainbow
        numCardsRainbow = 5;
        // Set locations variable
            // First get LocationArr (an array of locations)
        ArrayList<int[]> locationArr = getCoords();
            // Then get location names, putting <name, location coordinates> into the locations hashmap
        String[] spl = getNames();
        for (int i = 0; i < spl.length; i++) {
            locations.put(spl[i], locationArr.get(i));
        }
        // Set trainPaths
        trainpaths = getTrainPaths();
    }

    private ArrayList<int[]> getCoords() {
        Pattern isNumber = Pattern.compile("[0-9]+");
        Matcher matcher;
        ArrayList<int[]> locationArr = new ArrayList<>();
        locations = new HashMap<>();
        boolean arrInProgress = false;
        try {
            File file = new File("src/background/Coords.csv");
            Scanner sc = new Scanner(file);
            String line = sc.nextLine();
            String[] spl = line.strip().split(",");
            int[] innerArr = new int[2];
            int j = 0;
            for (int i = 0; i < spl.length; i++) {
                matcher = isNumber.matcher(spl[i]);
                if (matcher.find()) {
                    innerArr[j] = Integer.parseInt(spl[i]);
                    j++;
                    arrInProgress = true;
                } else {
                    locationArr.add(innerArr);
                    innerArr = new int[2];
                    j = 0;
                    arrInProgress = false;
                }
            }
            if (arrInProgress) {
                locationArr.add(innerArr);
            }
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return locationArr;
    }

    private String[] getNames() {
        String[] spl = {};
        try {
            File file = new File("src/background/LocationNames.csv");
            Scanner sc = new Scanner(file);
            String line = sc.nextLine();
            spl = line.strip().split(",");
        } catch (IOException e) {
            e.printStackTrace();
            return new String[]{"error"};
        }
        return spl;
    }

    private ArrayList<String[]> getTrainPaths() {
        ArrayList<String[]> pathArr = new ArrayList<>();
        Pattern isQuoted = Pattern.compile("(\")(.+)(\")");
        Matcher matcher;
        String[] spl = {};
        try {
            File file = new File("src/background/Trainpaths.csv");
            Scanner sc = new Scanner(file);
            String line = sc.nextLine();
            spl = line.strip().split(",");
        } catch (IOException e) {
            e.printStackTrace();
        }
        int j = 0;
        String[] path = new String[2];
        for (int i = 0; i < spl.length; i++) {
            if (j == 2) {
                pathArr.add(path);
                path = new String[2];
                j = 0;
            }
            matcher = isQuoted.matcher(spl[i]);
            if (matcher.find()) {
                path[j] = matcher.group(2);
                j++;
            }
        }
        if (j == 2) {
            pathArr.add(path);
        }
        return pathArr;
    }
}