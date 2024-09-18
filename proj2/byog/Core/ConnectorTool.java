package byog.Core;

import byog.TileEngine.TETile;

public class ConnectorTool {
    //return a room has not connected
    public static Room getRoomNotConnected() {
        while (true) {
            if (getRoomRandom().connectionLine == 0) {
                return getRoomRandom();
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
            if (getRoomRandom().connectionLine > 0) {
                return getRoomRandom();
            }
        }
    }

    //return a random room
    private static Room getRoomRandom() {
        int n = MyWorld.random.nextInt(Terrain.rooms.size());
        return Terrain.rooms.get(n);
    }

    //fix the lengthDiffer
    public static int caulLengthDiffer(int Differ) {
        if (Differ > 0) {
            return Differ + 1;
        } else if (Differ < 0) {
            return Differ - 1;
        }
        return 0;
    }

    //room2 -> room1 the direction manipulate by give value
    //seprate it to xx and yy
    public static int xxConnect(TETile[][] World, int xxStep, Room room, int xxSteped, int yySteped) {
        if (xxStep < 0) {
            xxStep = -xxStep;
        }
        Terrain.xxHallway(World, room.pos.xxPos + xxSteped, room.pos.yyPos + yySteped, xxStep);
        return xxStep;
    }

    public static int yyConnect(TETile[][] World, int yyStep, Room room, int xxSteped, int yySteped) {
        if (yyStep < 0) {
            yyStep = -yyStep;
        }
        Terrain.yyHallway(World, room.pos.xxPos + xxSteped, room.pos.yyPos + yySteped, yyStep);
        return yyStep;
    }
}
