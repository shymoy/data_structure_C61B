package byog.Core;

import byog.TileEngine.*;

import java.util.List;
import java.util.Random;


public class WorldGenerator {
    //receive a TETil[][] and draw it
    public static void generateWorld(TETile[][] world, Random rand) {
        //random num of rooms
        generateRooms(world, rand);
        generateHallways(world, rand);
    }

    private static void generateRooms(TETile[][] world, Random rand) {
        RoomsGenerator roomsGenerator = new RoomsGenerator();
        roomsGenerator.generateRooms(world, rand);
        List<Room> roomList = RoomsGenerator.getRooms();
        DrawRooms.drawRooms(world, roomList);
    }

    private static void generateHallways(TETile[][] world, Random rand) {
        List<Room> roomList = RoomsGenerator.getRooms();
        DrawHallways drawHallways = new DrawHallways(world,roomList,rand);
        drawHallways.drawHallways();
    }
}
