package byog.Core;

import byog.TileEngine.TETile;

import java.util.Random;

public class Connector {
    Room room1;
    Room room2;
    int xxCenterDiffer;
    int yyCenterDiffer;
    int xxStepped;
    int yyStepped;
    TETile[][] world;
    Random rand;

    public Connector(TETile[][] world, Room room1, Room room2, Random rand) {
        this.world = world;
       xxCenterDiffer = room1.getXxPosition() - room2.getXxPosition();
       yyCenterDiffer = room1.getYyPosition() - room2.getYyPosition();
       this.rand = rand;
       xxStepped = 0;
       yyStepped = 0;
       this.room1 = room1;
       this.room2 = room2;
       connect();
    }

    public void connect() {
        while (xxStepped != -xxCenterDiffer || yyStepped != -yyCenterDiffer) {
            int xxRemain = -xxCenterDiffer - xxStepped;
            int yyRemain = -yyCenterDiffer - yyStepped;
            if (yyStepped == -yyCenterDiffer) {
                int xxStep = xxCenterDiffer > 0 ? RandomUtils.uniform(rand, xxRemain, 0) : RandomUtils.uniform(rand, 0, xxRemain) + 1;

                xxConnection(xxStep);
                xxStepped += xxStep;
            } else if (xxStepped == -xxCenterDiffer) {
                int yyStep = yyCenterDiffer > 0 ? RandomUtils.uniform(rand, yyRemain, 0)  : RandomUtils.uniform(rand, 0, yyRemain) + 1;

                yyConnection(yyStepped);
                yyStepped += yyStep;
            } else {
                int xxStep = xxCenterDiffer > 0 ? RandomUtils.uniform(rand, xxRemain, 0)  : RandomUtils.uniform(rand, 0, xxRemain) + 1;

                int yyStep = yyCenterDiffer > 0 ? RandomUtils.uniform(rand, yyRemain, 0)  : RandomUtils.uniform(rand, 0, yyRemain) + 1 ;

                randomConnection(xxStep, yyStep);
            }
        }
    }

    private void randomConnection(int xxStep, int yyStep) {
        int x = RandomUtils.uniform(rand, 2);
        if (x == 0) {
            xxConnection(xxStep);
            xxStepped += xxStep;
        }else if (x == 1) {
            yyConnection(yyStep);
            yyStepped += yyStep;
        }
    }
    private void xxConnection(int xxStep) {
        BasicDraw.drawHallway(world, room1.getXxPosition() + xxStepped, room1.getYyPosition() + yyStepped, xxStep, 1);
    }

    private void yyConnection(int yyStep) {
        BasicDraw.drawHallway(world, room1.getXxPosition() + xxStepped, room1.getYyPosition() + yyStepped, 1, yyStep);
    }
}
