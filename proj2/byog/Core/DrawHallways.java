package byog.Core;

import byog.TileEngine.TETile;

import java.util.List;
import java.util.Random;

public class DrawHallways {
    TETile[][] world;
    Random rand;
    List<Room> rooms;

    public DrawHallways(TETile[][] world, List<Room> rooms,Random rand) {
        this.world = world;
        this.rooms = rooms;
        this.rand = rand;
    }
    public void drawHallways() {
        //choose two room to draw the hallway
        //first choose two random rooms,second choose one is connected and one is not
        HallwayChooser hallwayChooser = new HallwayChooser(rooms,rand);
        drawHallwayHelper(hallwayChooser);
    }

    private void drawHallwayHelper(HallwayChooser hallwayChooser) {
        //connect two rooms' center
        Room room1 = hallwayChooser.getNotConnectedRoom();
        Room room2 = hallwayChooser.getNotConnectedRoom();

        while (room1.equals(room2)) {
            room2 = hallwayChooser.getNotConnectedRoom();
        }

        new Connector(world, room1, room2, rand);

        hallwayChooser.connectRoom(room1);
        hallwayChooser.connectRoom(room2);

        while (!hallwayChooser.isAllConnected()) {
            room1 = hallwayChooser.getNotConnectedRoom();
            room2 = hallwayChooser.getConnectedRoom();
            new Connector(world, room1, room2, rand);
            hallwayChooser.connectRoom(room1);
        }
    }
}
