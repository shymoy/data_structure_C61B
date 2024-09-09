public class OffByOne implements CharacterComparator {

    @Override
    public boolean equalChars(char x, char y) {
        int difference = Math.abs(Character.toLowerCase(x) - Character.toLowerCase(y));
        return difference == 1;
    }

}

