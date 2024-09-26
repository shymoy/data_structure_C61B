package byog.Core;

import byog.TileEngine.TETile;

import java.util.ArrayList;
import java.util.Random;

public class RoomsGenerator {
    private static ArrayList<Room> rooms = new ArrayList<>();

    public void generateRooms(TETile[][] world, Random rand) {
        int roomNum = RandomUtils.uniform(rand,2,10);

        for (int i = 0; i < roomNum; i++) {
            //room position and size
            Room room = generateRoom(world, rand);

            boolean isDuplicate = false;
            for (Room origin : rooms) {
                if (origin.equals(room)) {
                    isDuplicate = true;
                    break;
                }
            }
            if (!isDuplicate) {
                rooms.add(room);
            } else {
                i--;
            }
        }
    }

    private Room generateRoom(TETile[][] world, Random rand) {
        //wall on the floor
        int width = RandomUtils.uniform(rand, 1, world.length/5);
        int height = RandomUtils.uniform(rand, 1, world[0].length/3);
        int xxPosition = RandomUtils.uniform(rand, 1,world.length - width - 1);
        int yyPosition = RandomUtils.uniform(rand,1, world[0].length - height - 1);
        return new Room(width, height, xxPosition, yyPosition);
    }

    public static ArrayList<Room> getRooms() {
        return rooms;
    }
}
