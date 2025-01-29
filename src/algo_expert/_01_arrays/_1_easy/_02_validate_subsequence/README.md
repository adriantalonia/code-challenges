# Validate Subsequence

<!-- TOC -->

* [Validate Subsequence](#validate-subsequence)
    * [Difficulty: 🟢 Easy](#difficulty--easy)
    * [Problem Description](#problem-description)
    * [Sample Input](#sample-input)
    * [Problem Analysis](#problem-analysis)
    * [Approach: Iterative Comparison](#approach-iterative-comparison)
    * [Java Code with Step-by-Step Explanation](#java-code-with-step-by-step-explanation)
    * [Time/Space Complexity](#timespace-complexity)

<!-- TOC -->

## Difficulty: 🟢 Easy

**Category**: Arrays

## Problem Description

Given two non-empty arrays of integers, write a function that determines whether the second array is a subsequence of
the first one.

A subsequence of an array is a set of numbers that aren’t necessarily adjacent in the array but that are in the same
order as they appear in the array. For instance, the numbers `[1, 3, 4]` form a subsequence of the array `[1, 2, 3, 4]`,
as do the numbers `[2, 4]`. A single number in an array and the array itself are both valid subsequences of the array.

## Sample Input

```plaintext
array = [5, 1, 22, 25, 6, -1, 8, 10]
sequence = [1, 6, -1, 10]
```

**Sample Output:**

```plaintext
true
```

## Problem Analysis

The task is to check whether all elements in the sequence appear in the array in the same order. The elements do not
have to be adjacent, but they must appear in order.

**Key constraints:**

- Both arrays are non-empty.
- The sequence may or may not appear entirely in the array.
- The sequence cannot contain elements in a different order compared to the array.

## Approach: Iterative Comparison

1. **Why Iterative Comparison?**

    - By iterating through the array and checking for matching elements with the sequence, we can efficiently determine
      if the sequence is valid.
    - This approach avoids unnecessary storage and computations, providing an optimal solution.

2. **Logic:**

    - Use a pointer (`index`) to track the current element of the sequence we are looking for in the array.
    - Traverse the array:
        - If the current element of the array matches the element at `index` in the sequence, move the pointer `index`
          forward.
        - If `index` reaches the end of the sequence, all elements of the sequence have been found in the correct order.
    - If the loop completes and `index` hasn’t reached the end of the sequence, the sequence is not valid.

## Java Code with Step-by-Step Explanation

```java
import java.util.*;

class Program {
    public static boolean isValidSubsequence(List<Integer> array, List<Integer> sequence) {
        // Step 1: Initialize an index pointer for the sequence
        int index = 0;

        // Step 2: Iterate through the array
        for (int i = 0; i < array.size(); i++) {
            // Step 3: Check if the current element matches the sequence's current element
            if (array.get(i) == sequence.get(index)) {
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
```

## Time/Space Complexity

- **Time Complexity**: **O(n)**, where `n` is the size of the array. We traverse the array once, checking for elements
  of the sequence.
- **Space Complexity**: **O(1)**. The algorithm uses a constant amount of extra space regardless of the input size.