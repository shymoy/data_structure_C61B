public class OffByOne implements CharacterComparator {

    @Override
    public boolean equalChars(char x, char y) {
        int difference = Math.abs(x - y);
        return difference == 1;
    }

}

