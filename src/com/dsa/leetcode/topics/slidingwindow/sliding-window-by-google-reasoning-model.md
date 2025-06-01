# Sliding Window Patterns 🖼️

The core idea of a sliding window is to efficiently process a contiguous segment of data. The window "slides" over the data, and we update calculations based on the elements entering and leaving the window.

---

## Pattern 1: Fixed-Size Sliding Window

Here, the size of the window (k) is predetermined and remains constant as it slides. You usually need to calculate something for every window of this size.

**Concept:**  
Maintain a window of a fixed number of elements. When the window slides, one element is removed from the beginning, and one element is added to the end.

**Use Cases:**  
Problems asking for calculations (max, min, sum, average, specific properties) over all contiguous subarrays/substrings of a given fixed length.

### Questions for Fixed-Size Sliding Window:

- **Maximum Sum Subarray of Size K:**  
  Given an array of integers and a number k, find the maximum sum of any contiguous subarray of size k.  
  *Concept Focus:* Basic window movement, updating a sum efficiently.

- **Minimum Sum Subarray of Size K:**  
  Similar to the above, but find the minimum sum.  
  *Concept Focus:* Reinforces the basic mechanism.

- **Averages of Subarrays of Size K:**  
  Given an array, find the average of all contiguous subarrays of size k.  
  *Concept Focus:* Extends sum calculation to averages.

- **Find All Anagrams in a String:**  
  Given a string s and a non-empty string p, find all the start indices of p's anagrams in s. The window size is `len(p)`.  
  *Concept Focus:* Using frequency maps (hash maps or arrays) for characters within the fixed window and comparing them.

- **Count Occurrences of a Specific Substring of Length K with a Property:**  
  For example, count substrings of length k that have more than m vowels.  
  *Concept Focus:* Combining fixed window with specific property checking.

- **First Negative Integer in Every Window of Size K:**  
  Given an array `arr` and an integer `k`, find the first negative integer for each and every window of size `k`. If a window does not contain a negative integer, then print 0 for that window.  
  *Concept Focus:* Introduces the need for a helper data structure (like a deque) to efficiently query the window.

- **Maximum of All Subarrays of Size K (Sliding Window Maximum):**  
  Given an array `nums` and a window size `k`, find the maximum value in each sliding window.  
  *Concept Focus:* Requires an efficient way (often a deque) to find the maximum in the current window without iterating through it each time.

- **Find Subarrays with a Given Sum and Fixed Size:**  
  Given an array and an integer `k` and a target sum `S`, find if any subarray of size `k` sums to `S`.  
  *Concept Focus:* Fixed window sum checking.

- **DNA Sequence Analysis:**  
  Given a string representing a DNA sequence, find all the 10-letter-long sequences (substrings) that occur more than once. (Window size is 10).  
  *Concept Focus:* Using a set or hash map to store encountered fixed-size windows (substrings) to check for duplicates.

- **Calculate Rolling Hash:**  
  For a string and a fixed window size `k`, calculate the hash value for each window. (Useful in string matching algorithms like Rabin-Karp).  
  *Concept Focus:* Efficiently updating a hash value as the window slides.

---

## Pattern 2: Dynamic-Size Sliding Window (Condition-Based Expansion/Contraction)

Here, the window size is not fixed. It expands by moving the right pointer and contracts by moving the left pointer based on certain conditions. Often used to find the longest or shortest window satisfying a property.

**Concept:**  
The right end of the window (`end` or `right`) expands to include more elements. The left end (`start` or `left`) contracts the window if a certain condition is violated, or to find a minimal window that satisfies a condition.

**Use Cases:**  
Problems asking for the longest/shortest contiguous subarray/substring satisfying some criteria (e.g., sum constraint, character count constraint).

### Questions for Dynamic-Size Sliding Window:

- **Smallest Subarray with Sum Greater than or Equal to Target (S):**  
  Given an array of positive integers and a positive number `S`, find the length of the smallest contiguous subarray whose sum is greater than or equal to `S`. Return 0 if no such subarray exists.  
  *Concept Focus:* Expanding the window to meet the sum, then contracting from the left to find the minimal length.

- **Longest Substring Without Repeating Characters:**  
  Given a string `s`, find the length of the longest substring without repeating characters.  
  *Concept Focus:* Expand window; if a character repeats, contract from the left until the substring is unique again. Use a set or map to track characters in the current window.

- **Longest Substring with At Most K Distinct Characters:**  
  Given a string `s` and an integer `k`, find the length of the longest substring of `s` that contains at most `k` distinct characters.  
  *Concept Focus:* Expand window; if distinct characters exceed `k`, contract from the left. Use a frequency map for characters.

- **Longest Substring with At Least K Repeating Characters:**  
  Given a string `s` and an integer `k`, find the length of the longest substring of `s` such that the frequency of each character in that substring is greater than or equal to `k`.  
  *Concept Focus:* More complex condition checking, often involving multiple passes or recursive splitting.

- **Minimum Window Substring:**  
  Given two strings `s` and `t`, find the minimum window in `s` which will contain all the characters in `t`.  
  *Concept Focus:* Advanced. Track character counts needed from `t` and current window counts. Expand to find a valid window, then contract to find the minimum.

- **Fruit Into Baskets (Longest Substring with At Most 2 Distinct Characters):**  
  You are visiting a farm. The farm has a single row of fruit trees, `fruits[i]` is the type of fruit the i-th tree produces. You want to collect as much fruit as possible.  
  *Concept Focus:* Direct application of "Longest Substring with At Most K Distinct Characters" where `k=2`.

- **Longest Repeating Character Replacement:**  
  You are given a string `s` and an integer `k`. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most `k` times.  
  *Concept Focus:* The window is valid if `window_length - max_frequency_character_in_window <= k`. Expand, and if invalid, contract.

- **Subarray Product Less Than K:**  
  Given an array of positive integers `nums` and an integer `k`, return the number of contiguous subarrays where the product of all the elements in the subarray is strictly less than `k`.  
  *Concept Focus:* Expand window. If product ≥ `k`, contract by dividing by `nums[left]`. Add `right - left + 1` to count.

- **Maximum Consecutive Ones III:**  
  Given a binary array `nums` and an integer `k`, return the maximum number of consecutive 1's in the array if you can flip at most `k` 0's.  
  *Concept Focus:* The window is valid if the count of zeros within it is ≤ `k`. Expand, and if zeros > `k`, contract.

- **Shortest Subarray with Sum at Least K (allows negative numbers):**  
  Return the length of the shortest, non-empty, contiguous subarray of `A` with sum at least `K`. If there is no such subarray, return -1.  
  *Concept Focus:* Advanced. Combines dynamic window with an auxiliary data structure (deque) to efficiently find the left pointer that satisfies the sum condition with `prefix_sum[right]`.


# 🧠 Two Pointers Patterns 👈 👉

This technique uses two pointers to iterate through a data structure. Their movement and interaction depend on the problem. It's not always about a "window" of elements like in the Sliding Window pattern.

---

## 🔁 Pattern 1: Converging Pointers (Opposite Ends)

- **Concept**: Pointers start at opposite ends of a (usually sorted) data structure and move toward each other based on some condition.
- **Use Cases**:
    - Finding pairs with specific sums in sorted arrays
    - Palindrome checks
    - Problems where the order from both ends matters

### ✅ Problems:

1. **Two Sum II - Input Array Is Sorted**
    - 🔍 If sum is too small → `left++`, too large → `right--`.

2. **Valid Palindrome**
    - 🔄 Compare `s[left]` and `s[right]`, skip non-alphanumeric chars.

3. **Reverse String**
    - 🔁 Swap characters at `left` and `right`, then move inward.

4. **Reverse Vowels of a String**
    - 🔄 Swap vowels only when both pointers hit vowels.

5. **Container With Most Water**
    - 📏 Area = `min(height[left], height[right]) * (right - left)`
    - Move the pointer with the smaller height.

6. **3Sum**
    - 🎯 For each `nums[i]`, use two pointers to find pairs that sum to `-nums[i]`.

7. **3Sum Closest**
    - 🧮 Similar to 3Sum but track the sum closest to target.

8. **Squares of a Sorted Array**
    - 🟪 Square elements from both ends, insert from end of new array.

9. **Remove Element (Two Pointers)**
    - 🚮 One pointer for placing valid elements, another for scanning.

10. **Sort Array By Parity**
    - 🔄 Swap even to front, odd to back using two pointers.

---

## 🐢 Pattern 2: Fast and Slow Pointers (Same Direction)

- **Concept**: Both pointers start at or near the beginning. Fast pointer moves faster or under different rules.
- **Use Cases**:
    - Cycle detection in linked lists
    - Finding middle of list
    - Removing duplicates
    - "Leader-follower" problems

### ✅ Problems:

1. **Linked List Cycle (Floyd’s Algorithm)**
    - 🌀 Fast moves 2 steps, slow moves 1 step. If they meet → cycle.

2. **Linked List Cycle II**
    - 🔁 After detecting cycle, reset one pointer to head and move both 1 step at a time.

3. **Middle of the Linked List**
    - 🧭 When fast reaches end, slow is at middle.

4. **Remove Duplicates from Sorted Array**
    - 🔄 `slow` tracks next unique position; `fast` scans input.

5. **Remove Duplicates from Sorted Array II**
    - ✌️ Allow each element at most twice using condition on `slow - 2`.

6. **Remove Duplicates from Sorted List**
    - ⏩ Skip nodes if `current.val == current.next.val`.

7. **Remove Nth Node From End of List**
    - 🎯 Move `fast` n steps, then move both `slow` and `fast` until `fast` hits end.

8. **Palindrome Linked List**
    - 🔁 Find middle, reverse second half, compare both halves.

9. **Reorder List**
    - 🧬 Find middle → Reverse second half → Merge both.

10. **Find the Duplicate Number**
    - 🌀 Treat array as linked list and detect cycle using Floyd's algorithm.

---

## 🔀 Pattern 3: K-Way Merge (Pointer per List)

- **Concept**: Maintain a pointer per input list (usually sorted) and use a min-heap to select and advance pointers.
- **Use Cases**:
    - Merging sorted lists
    - Finding smallest range covering all lists

### ✅ Problems:

1. **Merge K Sorted Lists**
    - 📚 Use a min-heap to keep smallest node from k lists.

2. **Smallest Range Covering Elements from K Lists**
    - 🔍 Use min-heap + max tracker to update smallest window.

3. **Find K Pairs with Smallest Sums**
    - 🧮 Use min-heap, initially add all `nums1[i], nums2[0]`.

4. **Kth Smallest Element in a Sorted Matrix**
    - 🧱 Treat each row as sorted list, use min-heap or binary search.

---
## Pattern 4: Partitioning Pointers (e.g., for in-place sorting/rearrangement)

These problems involve rearranging an array into segments based on some property, often around a pivot value or condition. Sometimes uses more than two pointers (e.g., low, mid, high).

**Concept:** Pointers are used to demarcate boundaries between different segments of the array (e.g., elements less than pivot, equal to pivot, greater than pivot). Elements are swapped to move them into their correct partitions.  
**Use Cases:** In-place sorting algorithms (like QuickSort's partition step), specific array rearrangement problems.

---

### Questions for Partitioning Pointers:

---

**Sort Colors (Dutch National Flag Problem):**  
Given an array nums with n objects colored red, white, or blue (represented by 0, 1, 2), sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.  
**Concept Focus:** Use three pointers (low, mid, high) to partition into 0s, 1s, and 2s.

---

**Move Zeroes:**  
Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.  
**Concept Focus:** One pointer (insertPos or slow) tracks the position for the next non-zero element. Another pointer (current or fast) iterates through the array. If nums[current] is non-zero, swap/place it at nums[insertPos] and increment insertPos.

---

**Partition Array According to Given Pivot:**  
Given an array nums and an integer pivot, partition nums into three parts: elements less than pivot, elements equal to pivot, and elements greater than pivot. The relative order of elements within each part does not matter.  
**Concept Focus:** Similar to Sort Colors, but with a generic pivot.

---

**QuickSort Partition Logic:**  
Implement the partitioning step of the QuickSort algorithm.  
**Concept Focus:** Choose a pivot, then rearrange the array such that all elements smaller than the pivot are to its left, and all elements larger are to its right.

---

**Segregate Even and Odd Numbers:**  
Given an array of integers, segregate even and odd numbers. All even numbers should come first, followed by all odd numbers. The order within evens and odds doesn't matter.  
**Concept Focus:** Use two pointers, left starting from the beginning and right from the end. left looks for an odd number, right looks for an even number. Swap when found.


## 🧠 Summary Table

| Pattern | Common Use Cases | Examples |
|--------|------------------|----------|
| Converging Pointers | Sorted arrays, Palindromes, Opposite ends | Two Sum II, Reverse Vowels, Container With Most Water |
| Fast/Slow Pointers | Linked lists, Duplicate removal, Palindrome checks | Linked List Cycle, Middle of Linked List, Remove Duplicates |
| K-Way Merge | Merging sorted data | Merge K Sorted Lists, Smallest Range |
| Partitioning | In-place sorting | Sort Colors, Move Zeroes |

---

📘 **Pro Tip**: Always visualize the pointer movement. Dry-run on examples to grasp the pattern intuitively!
