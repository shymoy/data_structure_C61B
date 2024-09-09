public class Palindrome {

    //create a Deque contains String to single char like ['o','l','d']
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> L = new LinkedListDeque<>();

        for (int i = 0; i < word.length(); i++) {
            L.addLast(word.charAt(i));
        }
        return L;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> L = wordToDeque(word);
        Deque<Character> R = wordToDeque(word);

        if (word == null || word.length() <= 1) {
            return true;
        }
        for (int i = 0; i < word.length(); i++) {
            if (L.removeFirst() != R.removeLast()) {
                return false;
            }
        }
        return true;
    }


    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> L = wordToDeque(word);
        Deque<Character> R = wordToDeque(word);

        if (word == null || word.length() <= 1) {
            return true;
        }
        for (int i = 0; i < word.length() / 2; i++) {

            if (!cc.equalChars(L.removeFirst(), R.removeLast())) {
                return false;
            }
        }
        return true;
    }
}
