package background;


import java.io.*;
import java.lang.reflect.Array;
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

    /**
     * Constructor.
     */
    public Settings() {
        numCardsEachColor = 15;
        numCardsRainbow = 5;
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
        try {
            File file = new File("src/background/LocationNames.csv");
            Scanner sc = new Scanner(file);
            String line = sc.nextLine();
            String[] spl = line.strip().split(",");
            for (int i = 0; i < spl.length; i++) {
                locations.put(spl[i], locationArr.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}