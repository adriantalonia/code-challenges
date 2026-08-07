package coding_challenges.functions.medium._02_factorial;

public class Factorial {
    public long factorial(int value) {

        if (value < 0) {
            throw new IllegalArgumentException(
                    "Factorial is not defined for negative integers"
            );
        }

        long result = 1L;

        for (int i = 2; i <= value; i++) {
            result *= i;
        }

        return result;
    }

    /** Runs the assertion-based unit tests when this class is executed directly. */
    public static void main(String[] args) {
        Factorial factorial = new Factorial();

        testBaseCases(factorial);
        testSmallValues(factorial);
        testLargerValues(factorial);
        testNegativeValues(factorial);

        System.out.println("All Factorial tests passed.");
    }

    private static void testBaseCases(Factorial factorial) {
        assertFactorial(factorial, 0, 1L, "zero factorial");
        assertFactorial(factorial, 1, 1L, "one factorial");
    }

    private static void testSmallValues(Factorial factorial) {
        assertFactorial(factorial, 2, 2L, "two factorial");
        assertFactorial(factorial, 3, 6L, "three factorial");
        assertFactorial(factorial, 5, 120L, "five factorial");
    }

    private static void testLargerValues(Factorial factorial) {
        assertFactorial(factorial, 10, 3_628_800L, "ten factorial");
        assertFactorial(factorial, 13, 6_227_020_800L, "thirteen factorial");
        assertFactorial(factorial, 20, 2_432_902_008_176_640_000L, "twenty factorial");
    }

    private static void testNegativeValues(Factorial factorial) {
        assertThrowsIllegalArgument(factorial, -1, "negative one");
        assertThrowsIllegalArgument(factorial, Integer.MIN_VALUE, "the smallest int");
    }

    private static void assertFactorial(
            Factorial factorial, int value, long expected, String description) {
        long actual = factorial.factorial(value);

        if (actual != expected) {
            throw new AssertionError(
                    description + " failed: expected " + expected + " but was " + actual);
        }
    }

    private static void assertThrowsIllegalArgument(
            Factorial factorial, int value, String description) {
        try {
            factorial.factorial(value);
            throw new AssertionError(description + " failed: expected IllegalArgumentException");
        } catch (IllegalArgumentException exception) {
            // Expected outcome.
        }
    }
}
