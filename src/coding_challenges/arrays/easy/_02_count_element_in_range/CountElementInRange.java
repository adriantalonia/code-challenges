package coding_challenges.arrays.easy._02_count_element_in_range;

public class CountElementInRange {
    public int countInRange(int[] numbers, int min, int max) {
        if (numbers == null || numbers.length == 0) {
            return 0;
        }

        int result = 0;

        for (int number : numbers) {
            if (min <= number && number <= max) {
                result++;
            }
        }

        return result;
    }

    /**
     * Runs the unit tests when this class is executed directly.
     *
     * <p>The project does not currently use a test framework, so these tests
     * use explicit assertions and throw {@link AssertionError} on failure.</p>
     */
    public static void main(String[] args) {
        CountElementInRange countElementInRange = new CountElementInRange();

        testValuesInsideAndOutsideRange(countElementInRange);
        testInclusiveBoundaries(countElementInRange);
        testDuplicatesAndSingleValueRanges(countElementInRange);
        testEmptyAndNullInput(countElementInRange);
        testNegativeValuesAndReversedRange(countElementInRange);

        System.out.println("All CountElementInRange tests passed.");
    }

    private static void testValuesInsideAndOutsideRange(CountElementInRange countElementInRange) {
        assertCount(
                countElementInRange,
                new int[]{1, 5, 3, 8, 2, 7},
                2,
                6,
                3,
                "values inside and outside the range");
        assertCount(
                countElementInRange,
                new int[]{10, 20, 30},
                15,
                25,
                1,
                "one value inside the range");
    }

    private static void testInclusiveBoundaries(CountElementInRange countElementInRange) {
        assertCount(
                countElementInRange,
                new int[]{2, 3, 6, 7},
                2,
                6,
                3,
                "both range boundaries are included");
    }

    private static void testDuplicatesAndSingleValueRanges(CountElementInRange countElementInRange) {
        assertCount(
                countElementInRange,
                new int[]{3, 5, 5, 8},
                5,
                5,
                2,
                "duplicate values in a single-value range");
        assertCount(
                countElementInRange,
                new int[]{1, 2, 3},
                4,
                4,
                0,
                "a value that does not occur");
    }

    private static void testEmptyAndNullInput(CountElementInRange countElementInRange) {
        assertCount(countElementInRange, new int[]{}, 1, 10, 0, "empty input");
        assertCount(countElementInRange, null, 1, 10, 0, "null input");
    }

    private static void testNegativeValuesAndReversedRange(CountElementInRange countElementInRange) {
        assertCount(
                countElementInRange,
                new int[]{-10, -5, 0, 5},
                -5,
                0,
                2,
                "negative values");
        assertCount(
                countElementInRange,
                new int[]{1, 2, 3},
                4,
                2,
                0,
                "a reversed range");
    }

    private static void assertCount(
            CountElementInRange countElementInRange,
            int[] numbers,
            int min,
            int max,
            int expected,
            String description) {
        int actual = countElementInRange.countInRange(numbers, min, max);

        if (actual != expected) {
            throw new AssertionError(
                    description + " failed: expected " + expected + " but was " + actual);
        }
    }
}
