public class OffByN implements CharacterComparator {

    private int except_Diffrence;

    public OffByN(int x) {

        except_Diffrence = x;

    }

    @Override
    public boolean equalChars(char x, char y) {

        int diffrence = Math.abs(Character.toLowerCase(x) - Character.toLowerCase(y));

        return except_Diffrence == diffrence;
    }
}