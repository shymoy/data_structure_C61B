package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().

        TETile[][] finalWorldFrame = new TETile[WIDTH][HEIGHT];

        drawBackground(finalWorldFrame);

        Seed seed = getSeed( input.toLowerCase());

        Random rand = seed.getRandom();

        WorldGenerator.generateWorld(finalWorldFrame, rand);
        return finalWorldFrame;
    }

    private Seed getSeed(String input) {
        boolean newGame = false;
        boolean seedCompelete = false;
        //restore the seed as string
        StringBuilder seedBuild = new StringBuilder();
        Seed seed = new Seed();

        for (Character c : input.toCharArray()) {
            if (c.equals('n') && !newGame) {
                newGame = true;
            } else if (c.equals('s') && !seedCompelete && newGame) {
                //set the seed
                seed.setSeed(Long.parseLong(seedBuild.toString()));
                seedCompelete = true;
            } else { seedBuild.append(c); }
        }
        return seed;
    }

    private void drawBackground(TETile[][] worldFrame) {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                worldFrame[x][y] = Tileset.NOTHING;
            }
        }
    }
}
