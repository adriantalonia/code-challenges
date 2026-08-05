# Group Consecutive Duplicates

<!-- TOC -->
* [Group Consecutive Duplicates](#group-consecutive-duplicates)
  * [Difficulty: 🟡 Medium](#difficulty--medium)
  * [Category](#category)
  * [Problem Description](#problem-description)
  * [Examples](#examples)
    * [Example 1](#example-1)
    * [Example 2](#example-2)
    * [Example 3](#example-3)
    * [Example 4](#example-4)
    * [Example 5](#example-5)
  * [Important Rules](#important-rules)
  * [Problem Analysis](#problem-analysis)
  * [How to Think About Consecutive Groups](#how-to-think-about-consecutive-groups)
  * [Approach: Single Pass with Current-Group State](#approach-single-pass-with-current-group-state)
    * [Same value](#same-value)
    * [Different value](#different-value)
  * [Algorithm](#algorithm)
  * [Final Java Solution](#final-java-solution)
  * [Step-by-Step Explanation](#step-by-step-explanation)
    * [Imports](#imports)
    * [Method declaration](#method-declaration)
    * [Null and empty validation](#null-and-empty-validation)
    * [Dynamic collection of groups](#dynamic-collection-of-groups)
    * [Initialize the first group](#initialize-the-first-group)
    * [Start from index 1](#start-from-index-1)
    * [Read the current number](#read-the-current-number)
    * [Continue the current group](#continue-the-current-group)
    * [Close the current group](#close-the-current-group)
    * [Add the final group](#add-the-final-group)
    * [Convert the list to a two-dimensional array](#convert-the-list-to-a-two-dimensional-array)
    * [Helper method](#helper-method)
    * [Create the inner array](#create-the-inner-array)
    * [Fill the group](#fill-the-group)
    * [Return the completed group](#return-the-completed-group)
  * [Execution Walkthrough](#execution-walkthrough)
    * [Iteration 1](#iteration-1)
    * [Iteration 2](#iteration-2)
    * [Iteration 3](#iteration-3)
    * [Iteration 4](#iteration-4)
    * [Iteration 5](#iteration-5)
  * [Why the Last Group Is Added After the Loop](#why-the-last-group-is-added-after-the-loop)
  * [Why an ArrayList Is Used](#why-an-arraylist-is-used)
  * [Evaluation of the Final Solution](#evaluation-of-the-final-solution)
    * [Strengths](#strengths)
      * [Correct consecutive grouping](#correct-consecutive-grouping)
      * [Correct state initialization](#correct-state-initialization)
      * [Single traversal of the input](#single-traversal-of-the-input)
      * [Preserves order](#preserves-order)
      * [Handles separated duplicates](#handles-separated-duplicates)
      * [Handles single-element groups](#handles-single-element-groups)
      * [Correct final-group handling](#correct-final-group-handling)
      * [Clear separation of responsibilities](#clear-separation-of-responsibilities)
      * [Does not modify the input](#does-not-modify-the-input)
    * [Minor considerations](#minor-considerations)
      * [Null behavior](#null-behavior)
      * [Temporary list](#temporary-list)
      * [Helper method cost](#helper-method-cost)
  * [Alternative with Arrays.fill](#alternative-with-arraysfill)
  * [Correctness Explanation](#correctness-explanation)
    * [Loop invariant](#loop-invariant)
    * [Initialization](#initialization)
    * [Maintenance](#maintenance)
      * [Case 1: The value equals `currentNumber`](#case-1-the-value-equals-currentnumber)
      * [Case 2: The value differs from `currentNumber`](#case-2-the-value-differs-from-currentnumber)
    * [Termination](#termination)
  * [Time and Space Complexity](#time-and-space-complexity)
    * [Time Complexity](#time-complexity)
    * [Auxiliary Space Complexity](#auxiliary-space-complexity)
    * [Output Space Complexity](#output-space-complexity)
  * [Edge Cases](#edge-cases)
    * [Null input](#null-input)
    * [Empty input](#empty-input)
    * [One element](#one-element)
    * [All values equal](#all-values-equal)
    * [All values different](#all-values-different)
    * [Repeated values separated by another value](#repeated-values-separated-by-another-value)
    * [Negative values](#negative-values)
    * [Zero values](#zero-values)
    * [Alternating values](#alternating-values)
  * [Test Cases](#test-cases)
  * [Interview Discussion](#interview-discussion)
    * [Why does `count` start at 1?](#why-does-count-start-at-1)
    * [Why does the loop start at index 1?](#why-does-the-loop-start-at-index-1)
    * [Why is the last group added outside the loop?](#why-is-the-last-group-added-outside-the-loop)
    * [Why use a list instead of creating `int[][]` immediately?](#why-use-a-list-instead-of-creating-int-immediately)
    * [Is the solution really `O(n)` even though `createGroup` has a loop?](#is-the-solution-really-on-even-though-creategroup-has-a-loop)
    * [Could the problem be solved with two passes?](#could-the-problem-be-solved-with-two-passes)
    * [What reusable pattern does this problem teach?](#what-reusable-pattern-does-this-problem-teach)
  * [Diagram](#diagram)
<!-- TOC -->

## Difficulty: 🟡 Medium

## Category

**Arrays / Grouping / Sequential Processing**

## Problem Description

Given an array of integers, group all **equal consecutive elements** into separate sub-arrays and return an array
containing those groups.

Elements belong to the same group only when they:

1. Have the same value.
2. Appear next to each other in the original array.

If the same value appears again later after another value, it must start a new group.

The expected method signature is:

```java
public int[][] groupConsecutiveDuplicates(int[] nums)
```

The returned value is a two-dimensional integer array:

```java
int[][]
```

Each inner array represents one consecutive group.

## Examples

### Example 1

```java
groupConsecutiveDuplicates(
    new int[] {
    1, 1, 2, 3, 3, 3
}
);
```

Result:

```java
[[1,1],[2],[3,3,3]]
```

### Example 2

```java
groupConsecutiveDuplicates(
    new int[] {
    1, 2, 3
}
);
```

Result:

```java
[[1],[2],[3]]
```

Every value is different from the value before it, so every element forms its own group.

### Example 3

```java
groupConsecutiveDuplicates(
    new int[] {
    5, 5, 5
}
);
```

Result:

```java
[[5,5,5]]
```

All values are equal and consecutive, so the result contains one group.

### Example 4

```java
groupConsecutiveDuplicates(
    new int[] {
    1, 1, 2, 1, 1
}
);
```

Result:

```java
[[1,1],[2],[1,1]]
```

The two sequences of `1` values are separate because the value `2` appears between them.

### Example 5

```java
groupConsecutiveDuplicates(new int[] {
});
```

Result:

```java
[]
```

## Important Rules

1. Group only equal values that are consecutive.
2. Preserve the original order of the elements.
3. Preserve the original order of the groups.
4. The same value may appear in multiple separate groups.
5. Every input element must appear exactly once in the result.
6. A group containing one element is valid.
7. If the input array is empty, return an empty two-dimensional array.
8. The submitted implementation also returns an empty result when the input is `null`.
9. The number of groups is not known before processing the input.
10. The original input array must not be modified.

## Problem Analysis

The main challenge is not counting how many times a number appears in the complete array.

The problem asks for groups based on **adjacency**.

Consider:

```java
[1,1,2,1,1]
```

The value `1` appears four times in total, but the correct result is not:

```java
[[1,1,1,1],[2]]
```

That would group elements by their value without respecting their original positions.

The correct result is:

```java
[[1,1],[2],[1,1]]
```

The array contains three consecutive runs:

```text
1, 1
2
1, 1
```

The algorithm therefore needs to maintain information about the group currently being processed.

For each new element, it must answer one question:

```text
Is this value equal to the value of the current group?
```

- If yes, extend the current group.
- If no, close the current group and start a new one.

This is a common pattern known as processing **runs**, **segments**, or **consecutive groups**.

## How to Think About Consecutive Groups

Instead of trying to construct the complete result immediately, focus on one group at a time.

While traversing the array, maintain two pieces of state:

```java
int currentNumber;
int count;
```

Their meaning is:

```text
currentNumber = the value of the group currently being built
count         = the number of elements in that current group
```

For example, while processing:

```java
[3,3,3,5]
```

just before reading `5`, the state is:

```text
currentNumber = 3
count = 3
```

The value `5` is different from `3`, so it marks a group boundary.

At that moment, the algorithm must:

1. Create `[3, 3, 3]`.
2. Add it to the result.
3. Start a new group with `5`.

The new state becomes:

```text
currentNumber = 5
count = 1
```

This state definition acts as an invariant:

> At the beginning of each iteration, `currentNumber` and `count` describe the complete consecutive group seen so far
> that has not yet been added to the result.

## Approach: Single Pass with Current-Group State

The solution uses a single traversal of the input array.

Because the first value already starts the first group, initialize:

```java
int currentNumber = nums[0];
int count = 1;
```

Then begin iterating from index `1`.

For each value:

```java
int number = nums[i];
```

Compare it with `currentNumber`.

### Same value

```java
if(number ==currentNumber)
```

The current group continues, so increment its size:

```java
count++;
```

### Different value

A different value means that the current group has ended.

Create the group, add it to the result, and initialize the next group:

```java
groups.add(createGroup(currentNumber, count));
count =1;
currentNumber =number;
```

After the loop ends, add the final group because no later value exists to trigger its insertion.

## Algorithm

1. Check whether `nums` is `null` or empty.
    - Return an empty `int[][]`.
2. Create an empty `List<int[]>` called `groups`.
3. Initialize the current group with the first element:
    - `currentNumber = nums[0]`
    - `count = 1`
4. Iterate from index `1` to the end of the array.
5. For each number:
    - If it equals `currentNumber`, increment `count`.
    - Otherwise:
        - Create an array containing `currentNumber` exactly `count` times.
        - Add that array to `groups`.
        - Start a new group with the current number.
6. Add the final pending group after the loop.
7. Convert `List<int[]>` into `int[][]`.
8. Return the result.

Pseudocode:

```text
function groupConsecutiveDuplicates(nums):

    if nums is null or empty:
        return empty two-dimensional array

    groups = empty list of integer arrays

    currentNumber = nums[0]
    count = 1

    for i from 1 to nums.length - 1:
        number = nums[i]

        if number equals currentNumber:
            count = count + 1
        else:
            group = create an array with currentNumber repeated count times
            add group to groups

            currentNumber = number
            count = 1

    group = create an array with currentNumber repeated count times
    add group to groups

    return groups converted to int[][]
```

## Final Java Solution

```java
import java.util.List;
import java.util.ArrayList;

public class Solution {
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

    private int[] createGroup(int number, int count) {
        int[] group = new int[count];

        for (int i = 0; i < group.length; i++) {
            group[i] = number;
        }

        return group;
    }
}
```

## Step-by-Step Explanation

### Imports

```java
import java.util.List;
import java.util.ArrayList;
```

`List` is used as the abstraction for the collection of groups.

`ArrayList` is used because groups are discovered dynamically while traversing the input.

### Method declaration

```java
public int[][] groupConsecutiveDuplicates(int[] nums)
```

The method receives a one-dimensional integer array and returns a two-dimensional integer array.

Each position in the returned outer array contains one consecutive group:

```text
int[][]
  ├── int[] group 1
  ├── int[] group 2
  └── int[] group 3
```

### Null and empty validation

```java
if(nums ==null||nums.length ==0){
        return new int[0][];
        }
```

The `||` operator uses short-circuit evaluation.

If `nums == null` is `true`, Java does not evaluate:

```java
nums.length ==0
```

This prevents a `NullPointerException`.

The returned value:

```java
new int[0][]
```

is an outer array containing zero groups.

### Dynamic collection of groups

```java
List<int[]> groups = new ArrayList<>();
```

The number of output groups is unknown before processing the input.

An `ArrayList` can grow dynamically, so each completed `int[]` group can be added as it is discovered.

### Initialize the first group

```java
int count = 1;
int currentNumber = nums[0];
```

The first element already belongs to the first group.

Therefore, `count` starts at `1`, not `0`.

At this point, the state means:

```text
The current group contains one occurrence of nums[0].
```

### Start from index 1

```java
for(int i = 1;
i<nums.length;i++)
```

Index `0` was already used to initialize the first group, so the traversal begins at the second element.

### Read the current number

```java
int number = nums[i];
```

This local variable makes the comparison easier to read.

### Continue the current group

```java
if(number ==currentNumber){
count++;
        }
```

When both values are equal, the current consecutive group becomes one element larger.

No array is created yet because the group may continue growing.

### Close the current group

```java
else{
        groups.add(createGroup(currentNumber, count));
count =1;
currentNumber =number;
}
```

A different value indicates that the previous group has ended.

The completed group is created and added to `groups`.

Then a new group begins.

The new group starts with:

```java
count =1;
```

because `number` is already its first element.

### Add the final group

```java
groups.add(createGroup(currentNumber, count));
```

The final group must be added after the loop because the loop only closes a group when a different value is found.

At the end of the array, there is no next value to trigger that logic.

### Convert the list to a two-dimensional array

```java
return groups.toArray(new int[groups.size()][]);
```

`groups` is a:

```java
List<int[]>
```

The return type must be:

```java
int[][]
```

The expression:

```java
new int[groups.

size()][]
```

creates an outer array with one position for each group.

`toArray` copies the references to the inner `int[]` arrays into that outer array.

### Helper method

```java
private int[] createGroup(int number, int count)
```

This method creates one completed group.

It separates two responsibilities:

- The main method detects where groups begin and end.
- The helper method builds an array for one group.

### Create the inner array

```java
int[] group = new int[count];
```

The group size is known when the method is called.

For example:

```text
number = 4
count = 3
```

creates:

```java
new int[3]
```

Initially, its values are:

```java
[0,0,0]
```

### Fill the group

```java
for(int i = 0;
i<group.length;i++){
group[i]=number;
}
```

Every position receives the group value.

For:

```text
number = 4
count = 3
```

it produces:

```java
[4,4,4]
```

### Return the completed group

```java
return group;
```

The returned array is then added to the list of groups.

## Execution Walkthrough

Consider:

```java
int[] nums = {1, 1, 2, 3, 3, 3};
```

Initial state:

```text
groups = []
currentNumber = 1
count = 1
```

### Iteration 1

Current value:

```text
number = 1
```

Comparison:

```text
1 == currentNumber
```

The current group continues:

```text
count = 2
```

State:

```text
groups = []
currentNumber = 1
count = 2
```

### Iteration 2

Current value:

```text
number = 2
```

Comparison:

```text
2 != currentNumber
```

The group containing `1` is complete.

Create:

```java
[1,1]
```

Add it to the result and initialize the next group:

```text
groups = [[1, 1]]
currentNumber = 2
count = 1
```

### Iteration 3

Current value:

```text
number = 3
```

Comparison:

```text
3 != currentNumber
```

Close the current group:

```java
[2]
```

New state:

```text
groups = [[1, 1], [2]]
currentNumber = 3
count = 1
```

### Iteration 4

Current value:

```text
number = 3
```

The current group continues:

```text
count = 2
```

### Iteration 5

Current value:

```text
number = 3
```

The current group continues:

```text
count = 3
```

State after the loop:

```text
groups = [[1, 1], [2]]
currentNumber = 3
count = 3
```

The final group is still pending.

Create and add:

```java
[3,3,3]
```

Final result:

```java
[[1,1],[2],[3,3,3]]
```

## Why the Last Group Is Added After the Loop

Inside the loop, a group is added only when a different value is encountered.

Consider:

```java
[5,5,5]
```

Every comparison is equal, so the `else` block never executes.

Without this line after the loop:

```java
groups.add(createGroup(currentNumber, count));
```

the result would incorrectly remain empty.

The same issue appears with a one-element input:

```java
[7]
```

The loop does not execute at all, but `[7]` is still a valid group.

This is a common sequential-processing pattern:

```text
Detect and close completed groups during the loop.
Flush the final pending group after the loop.
```

## Why an ArrayList Is Used

The required return type is:

```java
int[][]
```

However, Java arrays have a fixed size.

Before traversing the input, the number of groups is unknown.

For example:

```java
[1,1,1,1]
```

contains one group.

But:

```java
[1,2,3,4]
```

contains four groups.

Both inputs have the same length, but require outer arrays of different sizes.

Using:

```java
List<int[]> groups = new ArrayList<>();
```

allows the algorithm to add groups dynamically.

After all groups have been discovered, the list is converted to the required type:

```java
groups.toArray(new int[groups.size()][])
```

A valid alternative would be to make two passes:

1. Count the number of groups.
2. Allocate the exact `int[][]` size and fill it.

That approach would also have `O(n)` time complexity, but the `ArrayList` solution is straightforward and readable.

## Evaluation of the Final Solution

The final solution is correct and appropriate for the problem.

### Strengths

#### Correct consecutive grouping

The comparison:

```java
number ==currentNumber
```

ensures that only adjacent equal values remain in the same group.

#### Correct state initialization

```java
int count = 1;
int currentNumber = nums[0];
```

correctly represents the first open group.

#### Single traversal of the input

The main loop processes the array from left to right exactly once.

#### Preserves order

Groups are added in the same order in which their elements appear.

#### Handles separated duplicates

Input:

```java
[1,1,2,1,1]
```

correctly produces:

```java
[[1,1],[2],[1,1]]
```

#### Handles single-element groups

Input:

```java
[1,2,3]
```

correctly produces:

```java
[[1],[2],[3]]
```

#### Correct final-group handling

The pending group is added after the traversal.

#### Clear separation of responsibilities

The helper method:

```java
createGroup(...)
```

removes duplicated group-construction logic from the main method.

#### Does not modify the input

The algorithm reads from `nums` and creates new arrays for the output.

### Minor considerations

#### Null behavior

The challenge may only require handling an empty array, but the implementation also accepts `null` and returns an empty
result.

That is a reasonable defensive choice, although production code should document whether `null` is considered valid
input.

#### Temporary list

The solution uses an `ArrayList` before constructing the final outer array.

This is a practical tradeoff because the number of groups is unknown.

#### Helper method cost

`createGroup` fills each inner array with a loop.

Across all calls, the total number of assignments equals the number of elements in the input, so the complete algorithm
remains linear.

## Alternative with Arrays.fill

The explicit loop in `createGroup`:

```java
for(int i = 0;
i<group.length;i++){
group[i]=number;
}
```

can be replaced with:

```java
Arrays.fill(group, number);
```

Alternative solution:

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public int[][] groupConsecutiveDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0][];
        }

        List<int[]> groups = new ArrayList<>();
        int currentNumber = nums[0];
        int count = 1;

        for (int i = 1; i < nums.length; i++) {
            int number = nums[i];

            if (number == currentNumber) {
                count++;
            } else {
                groups.add(createGroup(currentNumber, count));
                currentNumber = number;
                count = 1;
            }
        }

        groups.add(createGroup(currentNumber, count));

        return groups.toArray(new int[groups.size()][]);
    }

    private int[] createGroup(int number, int count) {
        int[] group = new int[count];
        Arrays.fill(group, number);
        return group;
    }
}
```

Both versions have the same asymptotic complexity.

The explicit loop is useful while learning because it shows every assignment clearly.

`Arrays.fill` is shorter and idiomatic when the intention is to assign one value to every array position.

## Correctness Explanation

We can show that the algorithm returns exactly the required consecutive groups.

### Loop invariant

Before each loop iteration:

1. `groups` contains all complete consecutive groups that ended before the current position.
2. Those groups preserve the original order.
3. `currentNumber` is the value of the current unfinished group.
4. `count` is exactly the number of consecutive occurrences in that unfinished group.

### Initialization

Before the loop begins:

```java
currentNumber =nums[0];
count =1;
```

No complete group has ended, so `groups` is empty.

The unfinished group contains exactly the first element.

Therefore, the invariant is true.

### Maintenance

For every next value, there are two cases.

#### Case 1: The value equals `currentNumber`

```java
number ==currentNumber
```

The value belongs to the current group.

The algorithm increments `count`, so the unfinished group remains correctly represented.

No completed group is added prematurely.

#### Case 2: The value differs from `currentNumber`

```java
number !=currentNumber
```

The current group has ended immediately before this value.

The algorithm creates that group and adds it to `groups`.

It then starts a new unfinished group containing the current value:

```java
currentNumber =number;
count =1;
```

The invariant remains true.

### Termination

When the loop ends, `groups` contains every completed group except the current unfinished group.

The algorithm adds it with:

```java
groups.add(createGroup(currentNumber, count));
```

Therefore, every input element appears in exactly one group, consecutive group boundaries are respected, and order is
preserved.

The returned `int[][]` is correct.

## Time and Space Complexity

Let:

```text
n = number of elements in nums
g = number of consecutive groups
```

### Time Complexity

```text
O(n)
```

The main loop reads each input element once.

The helper method writes every value into exactly one output position.

Although `createGroup` contains another loop, all helper-loop iterations combined equal `n`:

```text
size(group 1) + size(group 2) + ... + size(group g) = n
```

The final conversion copies `g` array references into the outer array.

Because `g <= n`, the total remains:

```text
O(n)
```

### Auxiliary Space Complexity

Excluding the returned inner arrays, the temporary list stores one reference per group:

```text
O(g)
```

The scalar state uses constant space:

```text
O(1)
```

### Output Space Complexity

The output stores all `n` integer values and `g` group references:

```text
O(n + g)
```

Since `g <= n`, this simplifies to:

```text
O(n)
```

## Edge Cases

### Null input

```java
groupConsecutiveDuplicates(null);
```

Result:

```java
[]
```

### Empty input

```java
groupConsecutiveDuplicates(new int[] {
});
```

Result:

```java
[]
```

### One element

```java
groupConsecutiveDuplicates(new int[] {
    7
});
```

Result:

```java
[[7]]
```

The loop does not execute, but the final group is added afterward.

### All values equal

```java
groupConsecutiveDuplicates(new int[] {
    5, 5, 5
});
```

Result:

```java
[[5,5,5]]
```

### All values different

```java
groupConsecutiveDuplicates(new int[] {
    1, 2, 3
});
```

Result:

```java
[[1],[2],[3]]
```

### Repeated values separated by another value

```java
groupConsecutiveDuplicates(new int[] {
    1, 1, 2, 1, 1
});
```

Result:

```java
[[1,1],[2],[1,1]]
```

### Negative values

```java
groupConsecutiveDuplicates(new int[] {
    -2, -2, -1, -1, -1
});
```

Result:

```java
[[-2,-2],[-1,-1,-1]]
```

### Zero values

```java
groupConsecutiveDuplicates(new int[] {
    0, 0, 1, 0
});
```

Result:

```java
[[0,0],[1],[0]]
```

### Alternating values

```java
groupConsecutiveDuplicates(new int[] {
    1, 2, 1, 2
});
```

Result:

```java
[[1],[2],[1],[2]]
```

## Test Cases

```java
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        print(
                solution.groupConsecutiveDuplicates(
                        new int[]{1, 1, 2, 3, 3, 3}
                )
        );
        // [[1, 1], [2], [3, 3, 3]]

        print(
                solution.groupConsecutiveDuplicates(
                        new int[]{1, 2, 3}
                )
        );
        // [[1], [2], [3]]

        print(
                solution.groupConsecutiveDuplicates(
                        new int[]{5, 5, 5}
                )
        );
        // [[5, 5, 5]]

        print(
                solution.groupConsecutiveDuplicates(
                        new int[]{1, 1, 2, 1, 1}
                )
        );
        // [[1, 1], [2], [1, 1]]

        print(
                solution.groupConsecutiveDuplicates(
                        new int[]{7}
                )
        );
        // [[7]]

        print(
                solution.groupConsecutiveDuplicates(
                        new int[]{}
                )
        );
        // []

        print(
                solution.groupConsecutiveDuplicates(
                        new int[]{-2, -2, -1, -1, -1}
                )
        );
        // [[-2, -2], [-1, -1, -1]]

        print(
                solution.groupConsecutiveDuplicates(null)
        );
        // []
    }

    private static void print(int[][] groups) {
        System.out.println(Arrays.deepToString(groups));
    }
}
```

## Interview Discussion

A clear interview explanation could be:

> I traverse the array from left to right while maintaining the value and size of the current consecutive group. If the
> next value is equal, I increment the group size. If it changes, I create and store the completed group, then initialize
> a new one. I add the final pending group after the loop. Because the number of groups is unknown beforehand, I collect
> them in a `List<int[]>` and convert it to `int[][]` at the end. Each input value is read once and written once, so the
> solution runs in `O(n)` time.

Useful follow-up questions to consider:

### Why does `count` start at 1?

Because `nums[0]` has already been selected as the first element of the current group.

### Why does the loop start at index 1?

Index `0` was already used during initialization.

### Why is the last group added outside the loop?

A group is closed inside the loop only when a different value appears. The final group has no later value to trigger
that action.

### Why use a list instead of creating `int[][]` immediately?

The number of groups is unknown until the input is processed, and Java arrays have fixed sizes.

### Is the solution really `O(n)` even though `createGroup` has a loop?

Yes. Across all groups, the helper writes exactly `n` values in total.

### Could the problem be solved with two passes?

Yes. The first pass could count groups and their sizes, and the second pass could construct the exact `int[][]`. That is
also `O(n)`, but the current approach is simpler to express.

### What reusable pattern does this problem teach?

It teaches how to process consecutive runs by maintaining state, detecting boundaries, and flushing the final pending
state after traversal.

## Diagram

```mermaid
flowchart TD
    A[Start] --> B{Input is null or empty?}
    B -- Yes --> C[Return empty int[][]]
B -- No --> D[Create groups list]

D --> E[Set currentNumber to nums 0 and count to 1]
E --> F[Read next number starting at index 1]

F --> G{number equals currentNumber?}

G -- Yes --> H[Increment count]
H --> I{More input elements?}

G -- No --> J[Create group with currentNumber repeated count times]
J --> K[Add group to groups]
K --> L[Set currentNumber to number and count to 1]
L --> I

I -- Yes --> F
I -- No --> M[Create and add final pending group]
M --> N[Convert List of int arrays to int[][]]
N --> O[Return result]
```