public class OffByN implements CharacterComparator {

    int except_Diffrence;

    public OffByN(int x) {

        except_Diffrence = x;

    }

    @Override
    public boolean equalChars(char x, char y) {

        int diffrence = Math.abs(x - y);

        return except_Diffrence == diffrence;
    }
}