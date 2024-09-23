package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Game {
    public static TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;
    public static long SEED;
    public static Random random;


    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public static void playWithKeyboard() {
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
     *
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public static TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().

        TETile[][] finalWorldFrame = new TETile[WIDTH][HEIGHT];

        input = input.toLowerCase();

        boolean newGame = false;
        boolean seedCompelete = false;
        boolean loadGame = false;

        StringBuilder seedBuilder = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (ch == 'n') {
                newGame = true;
            } else if (Character.isDigit(ch) && !seedCompelete && newGame) {
                seedBuilder.append(ch);
            } else if (ch == 's' && !seedCompelete && newGame) {
                SEED = Long.parseLong(seedBuilder.toString());
                seedCompelete = true;
            } else if (ch == ':' && seedCompelete && newGame) {
                try (FileWriter fw = new FileWriter("./saveGame.txt")) {
                    // 将 SEED 作为字符串写入文件
                    fw.write(String.valueOf(SEED));
                    System.out.println("Game saved successfully.");
                } catch (IOException e) {
                    // 保存失败时的提示
                    System.out.println("ERROR: Failed to save the game.");
                    e.printStackTrace();
                }
            } else if (ch == 'q' && seedCompelete && newGame) {
                System.exit(0);
            } else if (ch == 'l' && !seedCompelete && !newGame) {
                try (BufferedReader br = new BufferedReader(new FileReader("./saveGame.txt"))) {
                    SEED = Long.parseLong(br.readLine());
                    loadGame = true;
                    System.out.println("Game Load successfully.");
                } catch (IOException e) {
                    System.out.println("ERROR: Failed to Load the game.");
                    e.printStackTrace();
                    System.exit(0);
                }
            }
        }

        if (!seedCompelete && !loadGame) {
            System.out.println("ERROR: Make sure you input a seed with 'N' before and 'S' after");
            SEED = 1;
        }

        random = new Random(SEED);
        worldGenerator(finalWorldFrame);
        return finalWorldFrame;
    }

    private static void worldGenerator(TETile[][] worldFrame) {
        DrawBlack(worldFrame);
        for (int i = 0; i < random.nextInt(15) + 1; i++) {
            //[1,WIDTH] 注意等会要处理右边和上面
            int xxPos = random.nextInt(WIDTH) + 1;
            //[1,HEIGHT]
            int yyPos = random.nextInt(HEIGHT) + 1;
            int tempWidth = random.nextInt(WIDTH / 5) + 1;
            int tempHeight = random.nextInt(HEIGHT / 5) + 1;

            if (xxPos < worldFrame.length - 1 && yyPos < worldFrame[0].length - 1) {
                Terrain.room(worldFrame, xxPos, yyPos, tempWidth, tempHeight);
            }
        }

        Connector connector = new Connector(worldFrame);

        connector.connect();
    }

    private static void DrawBlack(TETile[][] world) {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                world[x][y] = Tileset.NOTHING;
            }
        }
    }

    public static void main(String[] args) {
        ter.initialize(WIDTH, HEIGHT);

        //create the Array

        ter.renderFrame(playWithInputString("l"));
    }
}
