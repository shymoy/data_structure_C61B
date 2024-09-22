package byog.Core;

import byog.TileEngine.TETile;

import java.util.ArrayList;
import java.util.List;

public class Terrain {
    public static List<Room> rooms = new ArrayList<>();

    public static void room(TETile[][] world, int xxPos, int yyPos, int tempWidth, int tempHeight) {
        Room room = new Room(xxPos, yyPos);

        if (!rooms.contains(room)) {
            BasicDraw.drawRoom(world, xxPos, yyPos, tempWidth, tempHeight);
            rooms.add(room);
        }
    }

    public static void xxHallway(TETile[][] world, int xxPos, int yyPos, int tempWidth) {
        tempWidth = tempWidth > 0 ? tempWidth + 1 : tempWidth - 1;
        BasicDraw.drawXHallway(world, xxPos, yyPos, tempWidth);
    }

    public static void yyHallway(TETile[][] world, int xxPos, int yyPos, int tempHeight) {
        tempHeight = tempHeight > 0 ? tempHeight + 1 : tempHeight - 1;
        BasicDraw.drawYHallway(world, xxPos, yyPos, tempHeight + 1);
    }
}
