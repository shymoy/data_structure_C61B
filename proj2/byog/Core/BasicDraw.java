package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class BasicDraw {
    public static void drawRoomFloor(TETile[][] world, int xxPosition, int yyPosition, int width, int height) {
       drawTile(world, xxPosition, yyPosition, width + xxPosition, height + yyPosition, Tileset.FLOOR);
    }

    public static void drawRoomWall(TETile[][] world, int xxPosition, int yyPosition, int width, int height) {
        drawTile(world, xxPosition - 1, yyPosition - 1, xxPosition + width + 1, yyPosition + height + 1,  Tileset.WALL);
    }

    public static void drawHallway(TETile[][] world, int xxPosition, int yyPosition, int width, int height) {
        int xxStart = width > 0 ? xxPosition : xxPosition + width;
        int yyStart = height > 0 ? yyPosition : yyPosition + height;
        int xxEnd = width > 0 ? xxPosition + width : xxPosition;
        int yyEnd = height > 0 ? yyPosition + height: yyPosition;
        drawHallwayFloor(world, xxStart, yyStart, xxEnd, yyEnd);
        drawHallwayWall(world, xxStart - 1, yyStart - 1, xxEnd + 1, yyEnd + 1);
    }

    private static void drawHallwayFloor(TETile[][] world, int xxStart, int yyStart, int xxEnd, int yyEnd) {

        drawTile(world, xxStart, yyStart, xxEnd, yyEnd, Tileset.FLOOR);
    }

    private static void drawHallwayWall(TETile[][] world, int xxPosition, int yyPosition, int width, int height) {
        drawTile(world, xxPosition, yyPosition, width, height, Tileset.WALL);
    }

    private static void drawTile(TETile[][] world, int xxStart, int yyStart, int xxEnd, int yyEnd, TETile tile) {
        for (int i = xxStart; i < xxEnd && i < world.length && i > -1; i++) {
            for (int j = yyStart; j < yyEnd && j < world[0].length && j > -1; j++) {
                if (world[i][j] != Tileset.FLOOR) {
                    world[i][j] = tile;
                }
            }
        }
    }
}
