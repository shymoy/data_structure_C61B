package byog.Core;

import byog.TileEngine.TETile;

import java.util.List;

public class DrawRooms {
    public static void drawRooms (TETile[][] world, List<Room> rooms) {
        drawFloor(world, rooms);
        drawWall(world, rooms);
    }

    private static void drawFloor(TETile[][] world, List<Room> rooms) {
        for (Room room : rooms) {
            BasicDraw.drawRoomFloor(world, room.getXxPosition(), room.getYyPosition(),
                    room.getWidth(), room.getHeight());
        }
    }

    private static void drawWall(TETile[][] world, List<Room> rooms) {
        for (Room room : rooms) {
            BasicDraw.drawRoomWall(world, room.getXxPosition(), room.getYyPosition(),
                    room.getWidth(), room.getHeight());
        }
    }
}
