public class Palindrome {

    //create a Deque contains String to single char like ['o','l','d']
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> L = new LinkedListDeque<>();

        for (int i = 0; i < word.length(); i++) {
            L.addLast(word.charAt(i));
        }

        return L;
    }

    private String isPalindromeHelper_positive(Deque<Character> L) {
        if(L.isEmpty()){
            return "";
        }
        else {
            return L.removeFirst()+isPalindromeHelper_positive(L);
        }
    }

    private String isPalindromeHelper_negtive(Deque<Character> L) {
        if(L.isEmpty()){
            return "";
        }
        else {
            return L.removeLast()+isPalindromeHelper_negtive(L);
        }
    }

    public boolean isPalindrome(String word) {
        Deque<Character> L = wordToDeque(word);
        Deque<Character> R = wordToDeque(word);

        String A = isPalindromeHelper_positive(L);
        String B = isPalindromeHelper_negtive(R);

        return A.equals(B);
    }


    public boolean isPalindrome(String word,CharacterComparator cc){
        Deque<Character> L = wordToDeque(word);
        Deque<Character> R = wordToDeque(word);

        boolean result = true;
        for(int i = 0; i < word.length()/2; i++){

            if(!cc.equalChars(L.removeFirst(),R.removeLast())) {
                result = false;
            }
        }
        return result;
    }
}
