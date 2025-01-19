package algo_expert._01_arrays._1_easy._01_two_number_sum;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TwoNumberSumSolution {
    public static int[] twoNumberSum(int[] array, int targetSum) {
        // Step 1: Create a HashSet to store the numbers we have seen so far
        Set<Integer> nums = new HashSet<Integer>();

        // Step 2: Iterate through the array
        for (int num : array) {
            // Calculate the complement needed to reach the targetSum
            int complement = targetSum - num;

            // Step 3: Check if the complement is already in the HashSet
            if (nums.contains(complement)) {
                // If found, return the pair [complement, num]
                return new int[]{complement, num};
            } else {
                // If not found, add the current number to the HashSet
                nums.add(num);
            }
        }

        // Step 4: If no pair is found, return an empty array
        return new int[0];
    }

    // Main method to test the solution
    public static void main(String[] args) {
        int[] array = {3, 5, -4, 8, 11, 1, -1, 6}; // Input array
        int targetSum = 10; // Target sum to find

        // Call the function and store the result
        int[] result = twoNumberSum(array, targetSum);

        System.out.println("Array: " + Arrays.toString(array));
        System.out.println("Target Sum: " + targetSum);

        // Print the result
        if (result.length == 0) {
            System.out.println("No pair found.");
        } else {
            System.out.println("Pair found: " + Arrays.toString(result));
        }

        int[] array2 = {4, 5, 3, 2}; // Input array
        int targetSum2 = 6; // Target sum to find

        System.out.println("####################################");

        System.out.println("Array2: " + Arrays.toString(array2));
        System.out.println("Target Sum2: " + targetSum2);

        int[] result2 = twoNumberSum(array2, targetSum2);

        // Print the result
        if (result2.length == 0) {
            System.out.println("No pair found.");
        } else {
            System.out.println("Pair found: " + Arrays.toString(result2));
        }
    }
}
