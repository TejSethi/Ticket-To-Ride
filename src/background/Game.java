package background;
/**
 * The game class. Plays the game based on the State, calling methods as required and then updating when the state
 * becomes updated. The game is what will be referenced by the display.
 */

public class Game {
    public Settings set;
    public Game() {
        this.set = new Settings();
    }
}