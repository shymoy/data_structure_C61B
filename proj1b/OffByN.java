public class OffByN implements CharacterComparator {

    private int exceptDiffrence;

    public OffByN(int x) {

        exceptDiffrence = x;

    }

    @Override
    public boolean equalChars(char x, char y) {

        int diffrence = Math.abs(x - y);

        return exceptDiffrence == diffrence;
    }
}
