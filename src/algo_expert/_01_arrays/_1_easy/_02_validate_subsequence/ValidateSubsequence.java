package algo_expert._01_arrays._1_easy._02_validate_subsequence;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ValidateSubsequence {

    public static boolean isValidSubsequence(List<Integer> array, List<Integer> sequence) {
        // Step 1: Initialize an index pointer for the sequence
        int index = 0;

        // Step 2: Iterate through the array
        for (int i = 0; i < array.size(); i++) {
            // Step 3: Check if the current element matches the sequence's current element
            if (Objects.equals(array.get(i), sequence.get(index))) {
                index++; // Move the pointer to the next element in the sequence
            }

            // Step 4: Check if the sequence is completely found
            if (index == sequence.size()) {
                return true; // All elements in the sequence are matched
            }
        }

        // Step 5: Return false if the sequence was not fully matched
        return false;
    }

    public static void main(String[] args) {
        List<Integer> array = Arrays.asList(5, 1, 22, 25, 6, -1, 8, 10);
        List<Integer> sequence = Arrays.asList(1, 6, -1, 10);

        boolean result = isValidSubsequence(array, sequence);

        System.out.println("Is valid subsequence: " + result);
    }
}
