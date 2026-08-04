package coding_challenges.strings.easy._01_contains_character;

public class ContainsChar {
    public boolean containsChar(String text, String character) {
        if (text == null || character == null || character.length() != 1) {
            return false;
        }

        char target = character.charAt(0);

        int start = 0;
        int end = text.length() - 1;

        while (start <= end) {
            if (text.charAt(start) == target) {
                return true;
            }

            if (text.charAt(end) == target) {
                return true;
            }

            start++;
            end--;
        }

        return false;
    }

    /**
     * Runs the unit tests when this class is executed directly.
     *
     * <p>The project does not currently use a test framework, so these tests
     * use explicit assertions and throw {@link AssertionError} on failure.</p>
     */
    public static void main(String[] args) {
        ContainsChar containsChar = new ContainsChar();

        testMatchesAtEveryPosition(containsChar);
        testCharacterIsAbsent(containsChar);
        testInvalidInput(containsChar);
        testSingleCharacterAndEmptyText(containsChar);
        testCaseSensitivityAndSpecialCharacters(containsChar);

        System.out.println("All ContainsChar tests passed.");
    }

    private static void testMatchesAtEveryPosition(ContainsChar containsChar) {
        assertContains(containsChar, "a", "a", true, "a single-character text");
        assertContains(containsChar, "abc", "a", true, "the first character");
        assertContains(containsChar, "abc", "b", true, "a middle character");
        assertContains(containsChar, "abc", "c", true, "the last character");
        assertContains(containsChar, "banana", "a", true, "a repeated character");
    }

    private static void testCharacterIsAbsent(ContainsChar containsChar) {
        assertContains(containsChar, "abc", "z", false, "an absent character");
        assertContains(containsChar, "", "a", false, "a non-empty target in empty text");
        assertContains(containsChar, "hello", "ll", false, "a target longer than one character");
    }

    private static void testInvalidInput(ContainsChar containsChar) {
        assertContains(containsChar, null, "a", false, "null text");
        assertContains(containsChar, "abc", null, false, "null target");
        assertContains(containsChar, "abc", "", false, "an empty target");
        assertContains(containsChar, "abc", "ab", false, "a two-character target");
    }

    private static void testSingleCharacterAndEmptyText(ContainsChar containsChar) {
        assertContains(containsChar, "x", "x", true, "matching single-character text");
        assertContains(containsChar, "x", "y", false, "non-matching single-character text");
        assertContains(containsChar, "", "", false, "two empty inputs");
    }

    private static void testCaseSensitivityAndSpecialCharacters(ContainsChar containsChar) {
        assertContains(containsChar, "Java", "J", true, "an uppercase match");
        assertContains(containsChar, "Java", "j", false, "case-sensitive comparison");
        assertContains(containsChar, "a b", " ", true, "a space");
        assertContains(containsChar, "a!b", "!", true, "a symbol");
        assertContains(containsChar, "a\nb", "\n", true, "a newline");
    }

    private static void assertContains(
            ContainsChar containsChar,
            String text,
            String character,
            boolean expected,
            String description) {
        boolean actual = containsChar.containsChar(text, character);

        if (actual != expected) {
            throw new AssertionError(
                    description + " failed: expected " + expected + " but was " + actual);
        }
    }
}
