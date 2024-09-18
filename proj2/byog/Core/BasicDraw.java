package byog.Core;

import byog.TileEngine.*;

public class BasicDraw {
    public static void drawRoom(TETile[][] world, int xxPos, int yyPos, int tempWidth, int tempHeight) {
        drawFloor(world, xxPos, yyPos, tempWidth, tempHeight);
        drawWall(world, xxPos, yyPos, tempWidth, tempHeight);
    }

    private static void drawFloor(TETile[][] world, int xxPos, int yyPos, int tempWidth, int tempHeight) {
        //take care of the boundary since their bound = [1, Width]
        for (int x = xxPos; x < world.length - 1 && x < xxPos + tempWidth; x++) {
            for (int y = yyPos; y < world[0].length - 1 && y < yyPos + tempHeight; y++) {
                world[x][y] = Tileset.FLOOR;
            }
        }
    }

    private static void drawWall(TETile[][] world, int xxPos, int yyPos, int tempWidth, int tempHeight) {
        //To wrap the floor
        for (int x = xxPos - 1; x < world.length && x < xxPos + tempWidth + 1; x++) {
            for (int y = yyPos - 1; y < world[0].length && y < yyPos + tempHeight + 1; y++) {
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