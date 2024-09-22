package byog.Core;

import byog.TileEngine.TETile;

public class ConnectorTool {

    //return a room has not connected
    public static Room getRoomNotConnected() {
        while (true) {
            Room room = getRoomRandom();
            if (room.connectionLine == 0) {
                return room;
            }
        }
    }

    //at least one room has not connected return false
    public static boolean checkConnection() {
        for (Room room : Terrain.rooms) {
            if (room.connectionLine == 0) {
                return true;
            }
        }
        return false;
    }

    //return a room has got connected
    public static Room getRoomConnected() {
        while (true) {
            Room room = getRoomRandom();
            if (room.connectionLine > 0) {
                return room;
            }
        }
    }

    //return a random room
    private static Room getRoomRandom() {
        int n = Game.random.nextInt(Terrain.rooms.size());
        return Terrain.rooms.get(n);
    }

    //room2 -> room1 the direction manipulate by give value
    //seprate it to xx and yy
    public static void xxConnect(TETile[][] World, int xxStep, Room room, int xxSteped, int yySteped) {
        Terrain.xxHallway(World, room.pos.xxPos + xxSteped, room.pos.yyPos + yySteped, xxStep);
    }

    public static void yyConnect(TETile[][] World, int yyStep, Room room, int xxSteped, int yySteped) {
        Terrain.yyHallway(World, room.pos.xxPos + xxSteped, room.pos.yyPos + yySteped, yyStep);
    }
}
