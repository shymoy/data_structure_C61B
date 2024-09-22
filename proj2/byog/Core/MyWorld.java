package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class MyWorld {
    private static final int WIDTH = 90;
    private static final int HEIGHT = 40;
    public static final int SEED = 879;
    //create the seed
    public static final Random random = new Random(SEED);

    //Draw all to black
    private static void DrawBlack(TETile[][] world) {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                world[x][y] = Tileset.NOTHING;
            }
        }
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        //create the Array
        TETile[][] world = new TETile[WIDTH][HEIGHT];

        DrawBlack(world);
        //[1,10]
//        int createTimes = random.nextInt(10) + 1;

        for (int i = 0; i < 2; i++) {
            //[1,WIDTH] 注意等会要处理右边和上面
            int xxPos = random.nextInt(WIDTH) + 1;
            //[1,HEIGHT]
            int yyPos = random.nextInt(HEIGHT) + 1;
            int tempWidth = random.nextInt(WIDTH / 5) + 1;
            int tempHeight = random.nextInt(HEIGHT / 5) + 1;

            if (xxPos < world.length - 1 && yyPos < world[0].length - 1) {
                Terrain.room(world, xxPos, yyPos, tempWidth, tempHeight);
            }
        }

        Connector connector = new Connector(world);

        connector.connect();

        ter.renderFrame(world);
    }
}
