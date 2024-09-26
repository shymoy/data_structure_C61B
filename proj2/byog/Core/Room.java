package byog.Core;

public class Room {
    private final int width;
    private final int height;
    private final int xxPosition;
    private final int yyPosition;

    public Room (int width, int height, int xxPosition, int yyPosition) {
        this.width = width;
        this.height = height;
        this.xxPosition = xxPosition;
        this.yyPosition = yyPosition;
    }
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getXxPosition() {
        return xxPosition;
    }

    public int getYyPosition() {
        return yyPosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;

        return getXxPosition() == room.getXxPosition() &&
                getYyPosition() == room.getYyPosition();
    }
}
