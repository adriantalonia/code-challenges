package coding_challenges.arrays.easy._03_count_above_average;

public class CountAboveAverage {
    public int countAboveAverage(double[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return 0;
        }

        double sum = 0.0;

        for (double number : numbers) {
            sum += number;
        }

        double average = sum / numbers.length;
        int count = 0;

        for (double number : numbers) {
            if (number > average) {
                count++;
            }
        }

        return count;
    }

    /**
     * Runs the unit tests when this class is executed directly.
     *
     * <p>The project does not currently use a test framework, so these tests
     * use explicit assertions and throw {@link AssertionError} on failure.</p>
     */
    public static void main(String[] args) {
        CountAboveAverage countAboveAverage = new CountAboveAverage();

        testValuesAboveAverage(countAboveAverage);
        testEqualValuesAndValuesEqualToAverage(countAboveAverage);
        testNegativeAndDecimalValues(countAboveAverage);
        testEmptyAndNullInput(countAboveAverage);
        testInputIsNotModified(countAboveAverage);

        System.out.println("All CountAboveAverage tests passed.");
    }

    private static void testValuesAboveAverage(CountAboveAverage countAboveAverage) {
        assertCount(
                countAboveAverage,
                new double[]{1, 2, 3, 4, 5},
                2,
                "values above the average");
        assertCount(countAboveAverage, new double[]{5}, 0, "a single value");
    }

    private static void testEqualValuesAndValuesEqualToAverage(CountAboveAverage countAboveAverage) {
        assertCount(countAboveAverage, new double[]{10, 10, 10}, 0, "equal values");
        assertCount(countAboveAverage, new double[]{1, 2, 3}, 1, "a value equal to the average");
    }

    private static void testNegativeAndDecimalValues(CountAboveAverage countAboveAverage) {
        assertCount(countAboveAverage, new double[]{-5, -2, -1}, 2, "negative values");
        assertCount(countAboveAverage, new double[]{1.5, 2.5, 3.5}, 1, "decimal values");
    }

    private static void testEmptyAndNullInput(CountAboveAverage countAboveAverage) {
        assertCount(countAboveAverage, new double[]{}, 0, "empty input");
        assertCount(countAboveAverage, null, 0, "null input");
    }

    private static void testInputIsNotModified(CountAboveAverage countAboveAverage) {
        double[] input = {1.5, 2.5, 3.5};
        double[] original = input.clone();

        countAboveAverage.countAboveAverage(input);

        if (!java.util.Arrays.equals(input, original)) {
            throw new AssertionError("input array should not be modified");
        }
    }

    private static void assertCount(
            CountAboveAverage countAboveAverage,
            double[] numbers,
            int expected,
            String description) {
        int actual = countAboveAverage.countAboveAverage(numbers);

        if (actual != expected) {
            throw new AssertionError(
                    description + " failed: expected " + expected + " but was " + actual);
        }
    }
}
