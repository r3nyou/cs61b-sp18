public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        LinkedListDeque deque = new LinkedListDeque();
        for (int i = 0; i < word.length(); i++) {
            deque.addLast(word.charAt(i));
        }
        return deque;
    }

    public boolean isPalindrome(String word) {
        return isPalindrome(wordToDeque(word));
    }

    private boolean isPalindrome(Deque<Character> deque) {
        while (deque.size() > 1) {
            return deque.removeFirst() == deque.removeLast() && isPalindrome(deque);
        }

        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        return isPalindrome(wordToDeque(word), cc);
    }

    private boolean isPalindrome(Deque<Character> deque, CharacterComparator cc) {
        while (deque.size() > 1) {
            return cc.equalChars(deque.removeFirst(), deque.removeLast()) && isPalindrome(deque, cc);
        }
        return true;
    }
}
