public class OffByN implements CharacterComparator {

    private int exceptDiffrence;

    public OffByN(int N) {

        exceptDiffrence = N;

    }

    @Override
    public boolean equalChars(char x, char y) {

        int diffrence = Math.abs(Character.toLowerCase(x) - Character.toLowerCase(y));

        return exceptDiffrence == diffrence;
    }
}
