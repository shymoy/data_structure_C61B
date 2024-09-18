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
        Room room2 = getRoomNotConnected();
        connectCounter(room1, room2);
        while (checkConnection()) {
            Room room3 = getRoomNotConnected();
            Room room4 = getRoomConnected();
            connectCounter(room3, room4);
        }
    }


    private void connectHelper(Room room1, Room room2) {
        //if positive,should start from room2 to room1
        int xxLengthDiffer = caulLengthDiffer(room1.pos.xxPos - room2.pos.xxPos);
        int yyLengthDiffer = caulLengthDiffer(room1.pos.yyPos - room2.pos.yyPos);

        //to record have steped how long for room1 and room2 respectively
        int xxStepedroom1 = 0;
        int yyStepedroom1 = 0;
        int xxStepedroom2 = 0;
        int yyStepedroom2 = 0;

        //to decide how long will step this time(this for test)
        int xxStep = xxLengthDiffer;
        int yyStep = yyLengthDiffer;

        //draw xxline
        if (xxLengthDiffer > 0) {
            //room2 -> room1
            xxStepedroom2 += xxConnect(world, xxStep, room2, xxStepedroom2, yyStepedroom2);
        } else {
            xxStepedroom1 += xxConnect(world, xxStep, room1, xxStepedroom1, yyStepedroom1);

        }
        //draw yyline
        if (yyLengthDiffer > 0) {
            yyStepedroom2 += yyConnect(world, yyStep, room2, xxStepedroom2, yyStepedroom2);

        } else {
            yyStepedroom2 += yyConnect(world, yyStep, room1, xxStepedroom1, yyStepedroom1);
        }
    }
}
