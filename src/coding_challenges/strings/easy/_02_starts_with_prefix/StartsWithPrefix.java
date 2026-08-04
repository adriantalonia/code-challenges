package coding_challenges.strings.easy._02_starts_with_prefix;

public class StartsWithPrefix {
    public boolean startsWith(String text, String prefix) {
        if (text == null || prefix == null) {
            return false;
        }

        if (prefix.isEmpty()) {
            return true;
        }

        if (prefix.length() > text.length()) {
            return false;
        }

        for (int i = 0; i < prefix.length(); i++) {
            if (text.charAt(i) != prefix.charAt(i)) {
                return false;
            }
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
        StartsWithPrefix startsWithPrefix = new StartsWithPrefix();

        testMatchingPrefixes(startsWithPrefix);
        testNonMatchingPrefixes(startsWithPrefix);
        testEmptyAndLongPrefixes(startsWithPrefix);
        testNullInputs(startsWithPrefix);
        testWhitespaceAndCaseSensitivity(startsWithPrefix);

        System.out.println("All StartsWithPrefix tests passed.");
    }

    private static void testMatchingPrefixes(StartsWithPrefix startsWithPrefix) {
        assertStartsWith(startsWithPrefix, "hola mundo", "hola", true, "a matching prefix");
        assertStartsWith(startsWithPrefix, "TypeScript", "Type", true, "a prefix shorter than text");
        assertStartsWith(startsWithPrefix, "Java", "Java", true, "text equal to prefix");
        assertStartsWith(startsWithPrefix, "abc", "a", true, "a one-character prefix");
    }

    private static void testNonMatchingPrefixes(StartsWithPrefix startsWithPrefix) {
        assertStartsWith(startsWithPrefix, "hola mundo", "Hola", false, "a case mismatch");
        assertStartsWith(startsWithPrefix, "TypeScript", "script", false, "a matching suffix");
        assertStartsWith(startsWithPrefix, "abcdef", "abcdeg", false, "a mismatch at the final character");
        assertStartsWith(startsWithPrefix, "abcdef", "z", false, "a mismatch at the first character");
    }

    private static void testEmptyAndLongPrefixes(StartsWithPrefix startsWithPrefix) {
        assertStartsWith(startsWithPrefix, "abc", "", true, "an empty prefix");
        assertStartsWith(startsWithPrefix, "", "", true, "empty text and empty prefix");
        assertStartsWith(startsWithPrefix, "Java", "JavaScript", false, "a prefix longer than text");
        assertStartsWith(startsWithPrefix, "", "a", false, "a non-empty prefix for empty text");
    }

    private static void testNullInputs(StartsWithPrefix startsWithPrefix) {
        assertStartsWith(startsWithPrefix, null, "abc", false, "null text");
        assertStartsWith(startsWithPrefix, "abc", null, false, "null prefix");
        assertStartsWith(startsWithPrefix, null, null, false, "both inputs null");
    }

    private static void testWhitespaceAndCaseSensitivity(StartsWithPrefix startsWithPrefix) {
        assertStartsWith(startsWithPrefix, " hello", " ", true, "a leading space prefix");
        assertStartsWith(startsWithPrefix, " hello", "hello", false, "text after leading space");
        assertStartsWith(startsWithPrefix, "   ", "  ", true, "multiple leading spaces");
        assertStartsWith(startsWithPrefix, "Java", "java", false, "case-sensitive comparison");
        assertStartsWith(startsWithPrefix, "a\nb", "a\n", true, "a newline in the prefix");
    }

    private static void assertStartsWith(
            StartsWithPrefix startsWithPrefix,
            String text,
            String prefix,
            boolean expected,
            String description) {
        boolean actual = startsWithPrefix.startsWith(text, prefix);

        if (actual != expected) {
            throw new AssertionError(
                    description + " failed: expected " + expected + " but was " + actual);
        }
    }
}
