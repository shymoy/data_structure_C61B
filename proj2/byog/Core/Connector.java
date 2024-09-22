package byog.Core;

import byog.TileEngine.TETile;

import static byog.Core.ConnectorTool.*;
import static byog.Core.Game.random;


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
        //ensure to choose two different room
        do {
            room2 = getRoomNotConnected();
        } while (!room1.equals(room2));

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

        int xxStepped = 0;
        int yyStepped = 0;

        while (Math.abs(xxStepped) != Math.abs(xxLengthDiffer) || Math.abs(yyStepped) != Math.abs(yyLengthDiffer)) {
            int x;
            if (Math.abs(xxStepped) == Math.abs(xxLengthDiffer)) {
                x = 1;
            } else if (Math.abs(yyStepped) == Math.abs(yyLengthDiffer)) {
                x = 0;
            } else {
                x = random.nextInt(2);
            }

            if (x == 0 && Math.abs(xxStepped) != Math.abs(xxLengthDiffer)) {

                int xxRemain = Math.abs(xxLengthDiffer) - Math.abs(xxStepped);

                int xxStep = random.nextInt(xxRemain) + 1;
                xxStep = xxLengthDiffer > 0 ? -xxStep : xxStep;
                xxConnect(world, xxStep, room1, xxStepped, yyStepped);
                xxStepped += xxStep;
            }

            if (x == 1 && Math.abs(yyStepped) != Math.abs(yyLengthDiffer)) {

                int yyRemain = Math.abs(yyLengthDiffer) - Math.abs(yyStepped);

                int yystep = random.nextInt(yyRemain) + 1;
                yystep = yyLengthDiffer > 0 ? -yystep : yystep;
                yyConnect(world, yystep, room1, xxStepped, yyStepped);
                yyStepped += yystep;
            }
        }

    }
}
