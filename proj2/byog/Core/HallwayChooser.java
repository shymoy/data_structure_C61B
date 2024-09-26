package byog.Core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HallwayChooser {
    private final List<Room> connectedRooms;
    private final List<Room> rooms;
    private final Random rand;

    public HallwayChooser(List<Room> rooms, Random rand) {
        this.rooms = rooms;
        this.rand = rand;
        connectedRooms = new ArrayList<>();
    }

    public Room getNotConnectedRoom() {
        return getNotConnectedRoomHelper();
    }

    private Room getNotConnectedRoomHelper() {
        Room room = rooms.get(rand.nextInt(rooms.size()));
        if (isAllConnected()) {
             return null;
        } else {
          while (connectedRooms.contains(room)) {
              room = rooms.get(rand.nextInt(rooms.size()));
          }
          return room;
        }
    }

    public Room getConnectedRoom() {
        return getConnectedRoomHelper();
    }

    private Room getConnectedRoomHelper() {
        return connectedRooms.get(rand.nextInt(connectedRooms.size()));
    }

    public boolean isAllConnected() {
        return connectedRooms.size() == rooms.size();
    }

    public void connectRoom(Room room) {
        connectedRooms.add(room);
    }
}
