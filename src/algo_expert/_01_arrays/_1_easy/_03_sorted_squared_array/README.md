# Sorted Squared Array

<!-- TOC -->

* [Sorted Squared Array](#sorted-squared-array)
    * [Difficulty: 🟢 Easy](#difficulty--easy)
    * [Problem Description](#problem-description)
    * [Sample Input](#sample-input)
    * [Problem Analysis](#problem-analysis)
    * [Approach: Iterative Computation and Sorting](#approach-iterative-computation-and-sorting)
    * [Java Code with Step-by-Step Explanation](#java-code-with-step-by-step-explanation)
    * [Time/Space Complexity](#timespace-complexity)

<!-- TOC -->

## Difficulty: 🟢 Easy

**Category**: Arrays

## Problem Description

Write a function that takes in a non-empty array of integers sorted in ascending order and returns a new array of the
same length with the squares of the original integers also sorted in ascending order.

## Sample Input

```plaintext
array = [1, 2, 3, 5, 6, 8, 9]
```

**Sample Output:**

```plaintext
[1, 4, 9, 25, 36, 64, 81]
```

## Problem Analysis

The goal is to compute the squares of the input array elements and ensure the result is sorted in ascending order. Since
the input array is already sorted, the negative numbers (if any) will need special handling because their squares can be
larger than some positive numbers. A direct square-and-sort approach is simplest but not the most optimal.

**Key constraints:**

- The input array is non-empty.
- The array elements are sorted in ascending order.

## Approach: Iterative Computation and Sorting

1. **Why this approach?**

    - This approach first squares each element of the array and then sorts the resulting array. While not the most
      efficient, it is straightforward to implement.

2. **Logic:**

    - Iterate through the array, squaring each element in place.
    - Sort the modified array.
    - Return the sorted array.

## Java Code with Step-by-Step Explanation

```java
import java.util.*;

class Program {
    public int[] sortedSquaredArray(int[] array) {
        // Step 1: Square each element in the array
        for (int i = 0; i < array.length; i++) {
            array[i] *= array[i]; // Square the element
        }

        // Step 2: Sort the squared array
        Arrays.sort(array);

        // Step 3: Return the sorted array
        return array;
    }

    public static void main(String[] args) {
        Program program = new Program();
        int[] array = {1, 2, 3, 5, 6, 8, 9};
        int[] result = program.sortedSquaredArray(array);

        System.out.println(Arrays.toString(result));
    }
}
```

## Time/Space Complexity

- **Time Complexity**: **O(n log n)** due to the sorting step. Squaring each element takes **O(n)**.
- **Space Complexity**: **O(1)** if sorting is done in place; otherwise, **O(n)** if a new array is created during
  sorting.

