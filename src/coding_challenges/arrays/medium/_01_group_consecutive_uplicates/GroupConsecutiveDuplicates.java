package coding_challenges.arrays.medium._01_group_consecutive_uplicates;

import java.util.ArrayList;
import java.util.List;

public class GroupConsecutiveDuplicates {
    public int[][] groupConsecutiveDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0][];
        }

        List<int[]> groups = new ArrayList<>();
        int count = 1;
        int currentNumber = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int number = nums[i];
            if (number == currentNumber) {
                count++;
            } else {
                groups.add(createGroup(currentNumber, count));
                count = 1;
                currentNumber = number;
            }
        }

        groups.add(createGroup(currentNumber, count));

        return groups.toArray(new int[groups.size()][]);
    }

    /**
     * Runs the unit tests when this class is executed directly.
     *
     * <p>The project does not currently use a test framework, so these tests
     * use explicit assertions and throw {@link AssertionError} on failure.</p>
     */
    public static void main(String[] args) {
        GroupConsecutiveDuplicates groupConsecutiveDuplicates = new GroupConsecutiveDuplicates();

        testConsecutiveRuns(groupConsecutiveDuplicates);
        testSingletonAndAllEqualGroups(groupConsecutiveDuplicates);
        testSeparatedDuplicates(groupConsecutiveDuplicates);
        testEmptyAndNullInput(groupConsecutiveDuplicates);
        testNegativeValuesAndInputIsNotModified(groupConsecutiveDuplicates);

        System.out.println("All GroupConsecutiveDuplicates tests passed.");
    }

    private static void testConsecutiveRuns(GroupConsecutiveDuplicates groupConsecutiveDuplicates) {
        assertGroups(
                groupConsecutiveDuplicates,
                new int[]{1, 1, 2, 3, 3, 3},
                new int[][]{{1, 1}, {2}, {3, 3, 3}},
                "multiple consecutive runs");
    }

    private static void testSingletonAndAllEqualGroups(
            GroupConsecutiveDuplicates groupConsecutiveDuplicates) {
        assertGroups(
                groupConsecutiveDuplicates,
                new int[]{1, 2, 3},
                new int[][]{{1}, {2}, {3}},
                "all values are different");
        assertGroups(
                groupConsecutiveDuplicates,
                new int[]{5, 5, 5},
                new int[][]{{5, 5, 5}},
                "all values are equal");
    }

    private static void testSeparatedDuplicates(GroupConsecutiveDuplicates groupConsecutiveDuplicates) {
        assertGroups(
                groupConsecutiveDuplicates,
                new int[]{1, 1, 2, 1, 1},
                new int[][]{{1, 1}, {2}, {1, 1}},
                "equal values separated by another value");
        assertGroups(
                groupConsecutiveDuplicates,
                new int[]{2, 2, 1, 2, 2, 3},
                new int[][]{{2, 2}, {1}, {2, 2}, {3}},
                "repeated runs preserve their order");
    }

    private static void testEmptyAndNullInput(GroupConsecutiveDuplicates groupConsecutiveDuplicates) {
        assertGroups(groupConsecutiveDuplicates, new int[]{}, new int[][]{}, "empty input");
        assertGroups(groupConsecutiveDuplicates, null, new int[][]{}, "null input");
    }

    private static void testNegativeValuesAndInputIsNotModified(
            GroupConsecutiveDuplicates groupConsecutiveDuplicates) {
        assertGroups(
                groupConsecutiveDuplicates,
                new int[]{-2, -2, -1, 0, 0},
                new int[][]{{-2, -2}, {-1}, {0, 0}},
                "negative and zero values");

        int[] input = {1, 1, 2, 3, 3};
        int[] original = input.clone();
        groupConsecutiveDuplicates.groupConsecutiveDuplicates(input);

        if (!java.util.Arrays.equals(input, original)) {
            throw new AssertionError("input array should not be modified");
        }
    }

    private static void assertGroups(
            GroupConsecutiveDuplicates groupConsecutiveDuplicates,
            int[] numbers,
            int[][] expected,
            String description) {
        int[][] actual = groupConsecutiveDuplicates.groupConsecutiveDuplicates(numbers);

        if (!java.util.Arrays.deepEquals(expected, actual)) {
            throw new AssertionError(
                    description
                            + " failed: expected "
                            + java.util.Arrays.deepToString(expected)
                            + " but was "
                            + java.util.Arrays.deepToString(actual));
        }
    }

    private int[] createGroup(int number, int count) {
        int[] group = new int[count];

        for (int i = 0; i < group.length; i++) {
            group[i] = number;
        }

        return group;
    }
}
