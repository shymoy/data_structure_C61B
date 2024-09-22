package byog.Core;


import java.util.Objects;

public class Room {
    int connectionLine;
    Position pos;

    public static class Position {
        int xxPos;
        int yyPos;

        public Position(int xxPos, int yyPos) {
            this.xxPos = xxPos;
            this.yyPos = yyPos;
        }
    }

    public Room(int xxPos, int yyPos) {
        connectionLine = 0;
        pos = new Position(xxPos, yyPos);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return pos.xxPos == room.pos.xxPos && pos.yyPos == room.pos.yyPos;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pos.xxPos, pos.yyPos);
    }
}
