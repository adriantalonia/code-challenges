package coding_challenges.strings.easy._04_is_palindrome;

public class Palindrome {
    public boolean isPalindrome(String text) {

        if (text == null) {
            return false;
        }

        if (text.isEmpty()) {
            return true;
        }

        int start = 0;
        int end = text.length() - 1;

        while (start <= end) {
            if (
                    Character.toLowerCase(text.charAt(start))
                            != Character.toLowerCase(text.charAt(end))
            ) {
                return false;
            }

            start++;
            end--;
        }

        return true;
    }

    /**
     * Runs the unit tests when this class is executed directly.
     *
     * <p>The project does not currently use a test framework, so these tests
     * use explicit assertions and throw {@link AssertionError} on failure.</p>
     */
    public static void main(String[] args) {
        Palindrome palindrome = new Palindrome();

        testPalindromes(palindrome);
        testNonPalindromes(palindrome);
        testEmptyAndNullInput(palindrome);
        testCaseInsensitivity(palindrome);
        testSpacesAndPunctuation(palindrome);

        System.out.println("All Palindrome tests passed.");
    }

    private static void testPalindromes(Palindrome palindrome) {
        assertPalindrome(palindrome, "radar", true, "an odd-length palindrome");
        assertPalindrome(palindrome, "abba", true, "an even-length palindrome");
        assertPalindrome(palindrome, "a", true, "a single character");
        assertPalindrome(palindrome, "aa", true, "two equal characters");
    }

    private static void testNonPalindromes(Palindrome palindrome) {
        assertPalindrome(palindrome, "hello", false, "a non-palindrome");
        assertPalindrome(palindrome, "ab", false, "two different characters");
        assertPalindrome(palindrome, "abca", false, "a mismatch away from the ends");
    }

    private static void testEmptyAndNullInput(Palindrome palindrome) {
        assertPalindrome(palindrome, "", true, "an empty string");
        assertPalindrome(palindrome, null, false, "null input");
    }

    private static void testCaseInsensitivity(Palindrome palindrome) {
        assertPalindrome(palindrome, "Radar", true, "mixed letter casing");
        assertPalindrome(palindrome, "LeVeL", true, "mixed casing throughout");
        assertPalindrome(palindrome, "Java", false, "a case-insensitive mismatch");
    }

    private static void testSpacesAndPunctuation(Palindrome palindrome) {
        assertPalindrome(palindrome, "nurses run", false, "spaces treated as characters");
        assertPalindrome(palindrome, "a b a", true, "symmetric spaces");
        assertPalindrome(palindrome, "a!b!a", true, "symmetric punctuation");
        assertPalindrome(palindrome, "A man, a plan, a canal: Panama", false,
                "punctuation and spaces are not ignored");
    }

    private static void assertPalindrome(
            Palindrome palindrome,
            String text,
            boolean expected,
            String description) {
        boolean actual = palindrome.isPalindrome(text);

        if (actual != expected) {
            throw new AssertionError(
                    description + " failed: expected " + expected + " but was " + actual);
        }
    }
}
