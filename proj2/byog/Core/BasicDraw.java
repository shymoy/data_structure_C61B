package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class BasicDraw {
    public static void drawRoom(TETile[][] world, int xxPos, int yyPos, int tempWidth, int tempHeight) {
        drawFloor(world, xxPos, yyPos, tempWidth, tempHeight);
        drawWall(world, xxPos, yyPos, tempWidth, tempHeight);
    }

    private static void drawFloor(TETile[][] world, int xxPos, int yyPos, int tempWidth, int tempHeight) {
        //take care of the boundary since their bound = [1, Width]
        int xxStart = tempWidth > 0 ? xxPos : xxPos + tempWidth;
        int xxEnd = tempWidth > 0 ? xxPos + tempWidth : xxPos;
        int yyStart = tempHeight > 0 ? yyPos : yyPos + tempHeight;
        int yyEnd = tempHeight > 0 ? yyPos + tempHeight : yyPos;

        for (int x = xxStart; x < xxEnd && x < world.length - 1; x++) {
            for (int y = yyStart; y < world[0].length - 1 && y < yyEnd; y++) {
                world[x][y] = Tileset.FLOOR;
            }
        }
    }

    private static void drawWall(TETile[][] world, int xxPos, int yyPos, int tempWidth, int tempHeight) {
        //To wrap the floor
        int xxStart = tempWidth > 0 ? xxPos : xxPos + tempWidth;
        int xxEnd = tempWidth > 0 ? xxPos + tempWidth : xxPos;
        int yyStart = tempHeight > 0 ? yyPos : yyPos + tempHeight;
        int yyEnd = tempHeight > 0 ? yyPos + tempHeight : yyPos;

        for (int x = xxStart - 1; x < world.length && x < xxEnd + 1; x++) {
            for (int y = yyStart - 1; y < world[0].length && y < yyEnd + 1; y++) {
                if (world[x][y] != Tileset.WALL && world[x][y] != Tileset.FLOOR) {
                    world[x][y] = Tileset.WALL;
                }
            }
        }
    }

    public static void drawXHallway(TETile[][] world, int xxPos, int yyPos, int tempWidth) {
        drawFloor(world, xxPos, yyPos, tempWidth, 1);
        drawWall(world, xxPos, yyPos, tempWidth, 1);
    }

    public static void drawYHallway(TETile[][] world, int xxPos, int yyPos, int tempHeight) {
        drawFloor(world, xxPos, yyPos, 1, tempHeight);
        drawWall(world, xxPos, yyPos, 1, tempHeight);
    }
}