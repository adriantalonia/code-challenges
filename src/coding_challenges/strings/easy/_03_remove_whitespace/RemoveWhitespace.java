package coding_challenges.strings.easy._03_remove_whitespace;

public class RemoveWhitespace {

    public String trim(String text) {
        // Preserve the behavior expected by this challenge:
        // null and empty input both produce an empty string.
        if (text == null || text.isEmpty()) {
            return "";
        }

        int start = 0;
        int end = text.length() - 1;

        // Find the first non-whitespace character.
        while (
                start <= end
                        && Character.isWhitespace(text.charAt(start))
        ) {
            start++;
        }

        // Find the last non-whitespace character.
        while (
                end >= start
                        && Character.isWhitespace(text.charAt(end))
        ) {
            end--;
        }

        // If the original string has no boundary whitespace,
        // return the same string instead of creating another one.
        if (start == 0 && end == text.length() - 1) {
            return text;
        }

        // substring uses an exclusive ending index.
        return text.substring(start, end + 1);
    }

    /**
     * Runs the unit tests when this class is executed directly.
     *
     * <p>The project does not currently use a test framework, so these tests
     * use explicit assertions and throw {@link AssertionError} on failure.</p>
     */
    public static void main(String[] args) {
        RemoveWhitespace removeWhitespace = new RemoveWhitespace();

        testWhitespaceAtBothEnds(removeWhitespace);
        testNoBoundaryWhitespace(removeWhitespace);
        testWhitespaceOnlyAndEmptyInput(removeWhitespace);
        testInternalWhitespaceIsPreserved(removeWhitespace);
        testDifferentWhitespaceCharacters(removeWhitespace);
        testNullInput(removeWhitespace);

        System.out.println("All RemoveWhitespace tests passed.");
    }

    private static void testWhitespaceAtBothEnds(RemoveWhitespace removeWhitespace) {
        assertTrim(removeWhitespace, "  hola mundo  ", "hola mundo", "spaces at both ends");
        assertTrim(removeWhitespace, "\thola mundo\n", "hola mundo", "tabs and newlines at the ends");
        assertTrim(removeWhitespace, "   typescript   ", "typescript", "multiple spaces at both ends");
    }

    private static void testNoBoundaryWhitespace(RemoveWhitespace removeWhitespace) {
        String text = "sin espacios";

        assertTrim(removeWhitespace, text, text, "text without boundary whitespace");

        if (removeWhitespace.trim(text) != text) {
            throw new AssertionError("text without boundary whitespace should be returned unchanged");
        }
    }

    private static void testWhitespaceOnlyAndEmptyInput(RemoveWhitespace removeWhitespace) {
        assertTrim(removeWhitespace, "", "", "empty text");
        assertTrim(removeWhitespace, "   ", "", "spaces only");
        assertTrim(removeWhitespace, "\t\n\r", "", "whitespace characters only");
    }

    private static void testInternalWhitespaceIsPreserved(RemoveWhitespace removeWhitespace) {
        assertTrim(
                removeWhitespace,
                "  hola   mundo  ",
                "hola   mundo",
                "internal spaces");
        assertTrim(removeWhitespace, "  hola\tmundo  ", "hola\tmundo", "internal tab");
        assertTrim(removeWhitespace, "  hola\nmundo  ", "hola\nmundo", "internal newline");
    }

    private static void testDifferentWhitespaceCharacters(RemoveWhitespace removeWhitespace) {
        assertTrim(removeWhitespace, "\u2003hola\u2003", "hola", "Unicode em spaces");
        assertTrim(removeWhitespace, "\u000Bhello\u000B", "hello", "vertical tabs");
    }

    private static void testNullInput(RemoveWhitespace removeWhitespace) {
        assertTrim(removeWhitespace, null, "", "null text");
    }

    private static void assertTrim(
            RemoveWhitespace removeWhitespace,
            String text,
            String expected,
            String description) {
        String actual = removeWhitespace.trim(text);

        if (!expected.equals(actual)) {
            throw new AssertionError(
                    description + " failed: expected " + printable(expected)
                            + " but was " + printable(actual));
        }
    }

    private static String printable(String text) {
        return text == null ? "null" : '"' + text.replace("\n", "\\n").replace("\t", "\\t") + '"';
    }
}
