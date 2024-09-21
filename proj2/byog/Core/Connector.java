package byog.Core;

import byog.TileEngine.TETile;

import static byog.Core.ConnectorTool.*;


public class Connector {
    private final TETile[][] world;

    public Connector(TETile[][] world) {
        this.world = world;
    }

    public void connect() {
        connectChooser();
    }

    //first connect two rooms,then choose one no connectionline and another connected room
    private void connectCounter(Room room1, Room room2) {
        connectHelper(room1, room2);
        room1.connectionLine++;
        room2.connectionLine++;
    }

    private void connectChooser() {
        Room room1 = getRoomNotConnected();
        Room room2;
        while (true) {
            room2 = getRoomNotConnected();
            if (!room2.equals(room1)) {
                break;
            }
        }
        connectCounter(room1, room2);
        while (checkConnection()) {
            Room room3 = getRoomNotConnected();
            Room room4 = getRoomConnected();
            connectCounter(room3, room4);
        }
    }


    private void connectHelper(Room room1, Room room2) {
        //if positive,should start from room2 to room1
        int xxLengthDiffer = room1.pos.xxPos - room2.pos.xxPos;
        int yyLengthDiffer = room1.pos.yyPos - room2.pos.yyPos;

        int xxStep = -xxLengthDiffer;
        int yyStep = -yyLengthDiffer;

        int xxStepped = 0;
        int yyStepped = 0;
        //如果是正的,就要往负方向走, 如果是负的,就要往正方向走,step是为了缩小距离!!!!
        xxStepped += xxConnect(world, xxStep, room1, xxStepped, yyStepped);
        yyStepped += yyConnect(world, yyStep, room1, xxStepped, yyStepped);

    }
}
