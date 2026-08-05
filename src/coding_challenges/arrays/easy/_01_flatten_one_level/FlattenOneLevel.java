package coding_challenges.arrays.easy._01_flatten_one_level;

import java.util.List;
import java.util.ArrayList;

public class FlattenOneLevel {
    public List<Object> flattenOneLevel(List<Object> array) {

        if (array == null || array.isEmpty()) {
            return new ArrayList<>();
        }

        List<Object> result = new ArrayList<>();

        for (Object obj : array) {
            if (obj instanceof List<?> nestedList) {
                for (Object nestedObject : nestedList) {
                    result.add(nestedObject);
                }
            } else {
                result.add(obj);
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
        FlattenOneLevel flattenOneLevel = new FlattenOneLevel();

        testFlattenDirectNestedLists(flattenOneLevel);
        testDeeperNestingRemainsUntouched(flattenOneLevel);
        testEmptyAndNullInput(flattenOneLevel);
        testMixedValuesAndEmptyNestedLists(flattenOneLevel);
        testInputIsNotModified(flattenOneLevel);

        System.out.println("All FlattenOneLevel tests passed.");
    }

    private static void testFlattenDirectNestedLists(FlattenOneLevel flattenOneLevel) {
        assertFlatten(
                flattenOneLevel,
                List.of(1, List.of(2, 3), List.of(4, 5)),
                List.of(1, 2, 3, 4, 5),
                "direct nested lists");
        assertFlatten(
                flattenOneLevel,
                List.of(List.of(1, 2), List.of(3, 4)),
                List.of(1, 2, 3, 4),
                "nested lists at the beginning");
    }

    private static void testDeeperNestingRemainsUntouched(FlattenOneLevel flattenOneLevel) {
        List<Object> deeperList = List.of(3, 4);

        assertFlatten(
                flattenOneLevel,
                List.of(1, List.of(2, deeperList), 5),
                List.of(1, 2, deeperList, 5),
                "deeper nesting remains as one element");
        assertFlatten(
                flattenOneLevel,
                List.of(List.of(deeperList)),
                List.of(deeperList),
                "only one level is removed");
    }

    private static void testEmptyAndNullInput(FlattenOneLevel flattenOneLevel) {
        assertFlatten(flattenOneLevel, List.of(), List.of(), "empty input");
        assertFlatten(flattenOneLevel, null, List.of(), "null input");
    }

    private static void testMixedValuesAndEmptyNestedLists(FlattenOneLevel flattenOneLevel) {
        assertFlatten(
                flattenOneLevel,
                List.of("Java", List.of(25, true), 3.14),
                List.of("Java", 25, true, 3.14),
                "mixed value types");
        assertFlatten(
                flattenOneLevel,
                List.of(1, List.of(), 2),
                List.of(1, 2),
                "empty nested list");
        assertFlatten(
                flattenOneLevel,
                List.of(1, 2, 3),
                List.of(1, 2, 3),
                "input without nested lists");
    }

    private static void testInputIsNotModified(FlattenOneLevel flattenOneLevel) {
        List<Object> input = new ArrayList<>();
        input.add(1);
        input.add(new ArrayList<>(List.of(2, 3)));

        List<Object> actual = flattenOneLevel.flattenOneLevel(input);

        if (!input.equals(List.of(1, List.of(2, 3)))) {
            throw new AssertionError("input list should not be modified");
        }

        if (actual == input) {
            throw new AssertionError("result should be a new list");
        }
    }

    private static void assertFlatten(
            FlattenOneLevel flattenOneLevel,
            List<Object> input,
            List<Object> expected,
            String description) {
        List<Object> actual = flattenOneLevel.flattenOneLevel(input);

        if (!expected.equals(actual)) {
            throw new AssertionError(
                    description + " failed: expected " + expected + " but was " + actual);
        }
    }
}
