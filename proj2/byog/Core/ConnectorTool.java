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

    //check this room has connected Yes,return true
    public static boolean isConnected(Room room) {
        return room.connectionLine > 0;
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

    public static int xxConnectPositive(TETile[][] world, int xxStep, Room room2, int yyStepedroom2) {
        Terrain.xxHallway(world, room2.pos.xxPos, room2.pos.yyPos + yyStepedroom2, xxStep);
        return xxStep;
    }

    public static int xxConnectNegative(TETile[][] world, int xxStep, Room room1, int yyStepedroom1) {
        xxStep = -xxStep;
        Terrain.xxHallway(world, room1.pos.xxPos, room1.pos.yyPos + yyStepedroom1, xxStep);
        return xxStep;
    }


    public static int yyConnectPositive(TETile[][] world, int yyStep, Room room2, int xxStepedroom2) {
        Terrain.yyHallway(world, room2.pos.xxPos + xxStepedroom2, room2.pos.yyPos, yyStep);
        return yyStep;
    }

    public static int yyConnectNegative(TETile[][] world, int yyStep, Room room1, int xxStepedroom1) {
        yyStep = -yyStep;
        Terrain.yyHallway(world, room1.pos.xxPos + xxStepedroom1, room1.pos.yyPos, yyStep);
        return yyStep;
    }
}
