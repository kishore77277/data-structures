# Sliding Window and Two Pointers Algorithms 🚀

Welcome! This guide provides a conceptual overview of two powerful algorithmic techniques: **Sliding Window** and **Two Pointers**. These techniques are essential for optimizing solutions, particularly for problems involving arrays, strings, and linked lists, often reducing time complexity to linear time.

---

## 🖼️ Sliding Window Algorithm

The **Sliding Window** technique involves operating on a specific-sized (fixed or dynamic) **contiguous** block of elements—the "window"—as it slides through a data structure. It's efficient because it avoids redundant computations by updating results based on elements entering and leaving the window.

### Core Idea:
- Define a "window" over a segment of data.
- Slide the window, either by a fixed amount or dynamically.
- Process elements within the window efficiently, reusing previous calculations.

### Patterns & Example Questions:

#### 1. Fixed-Size Sliding Window
The window size `k` is predetermined and constant.
* **Concept**: Maintain a window of `k` elements. As it slides, remove one element from the start and add one to the end, updating calculations.
* **Example Questions**:
    * Maximum/Minimum Sum Subarray of Size K
    * Averages of Subarrays of Size K
    * Find All Anagrams in a String (window size = pattern length)
    * Maximum of All Subarrays of Size K (Sliding Window Maximum)

#### 2. Dynamic-Size Sliding Window (Condition-Based)
The window size expands or shrinks based on specific conditions, often to find the *longest* or *shortest* segment satisfying a property.
* **Concept**: The right pointer expands the window. The left pointer contracts it if a condition is violated (e.g., too many distinct characters) or to find a minimal valid window (e.g., smallest sum >= target).
* **Example Questions**:
    * Smallest Subarray with Sum Greater than or Equal to Target
    * Longest Substring Without Repeating Characters
    * Longest Substring with At Most K Distinct Characters
    * Minimum Window Substring
    * Fruit Into Baskets (Longest Substring with At Most 2 Distinct Characters)
    * Subarray Product Less Than K

---

## 👈 👉 Two Pointers Algorithm

The **Two Pointers** technique uses two iterators (pointers) that traverse a data structure in a coordinated way. Their movement can be towards each other, in the same direction (but at different speeds), or used for partitioning.

### Core Idea:
- Use two distinct pointers to track positions in data.
- Move pointers based on conditions to explore pairs, segments, or specific properties.

### Patterns & Example Questions:

#### 1. Converging Pointers (Opposite Ends)
Pointers start at opposite ends (e.g., of a sorted array) and move towards each other.
* **Concept**: `left` pointer starts at the beginning, `right` at the end. They move inwards based on comparisons or sums.
* **Example Questions**:
    * Two Sum II - Input Array Is Sorted
    * Valid Palindrome
    * Reverse String/Array
    * Container With Most Water
    * 3Sum (after sorting)

#### 2. Fast and Slow Pointers (Same Direction)
Pointers usually start near the same position, but one (fast) moves ahead of the other (slow).
* **Concept**: `slow` advances step-by-step, while `fast` leaps ahead. Useful for detecting cycles, finding midpoints, or processing elements with a lag.
* **Example Questions**:
    * Linked List Cycle Detection (Floyd's Tortoise and Hare)
    * Middle of the Linked List
    * Remove Duplicates from Sorted Array/List
    * Remove Nth Node From End of List
    * Palindrome Linked List

#### 3. Partitioning Pointers
Pointers (sometimes more than two) are used to divide an array into segments based on conditions (e.g., around a pivot).
* **Concept**: Pointers demarcate boundaries. Elements are swapped to move them into correct partitions.
* **Example Questions**:
    * Sort Colors (Dutch National Flag Problem)
    * Move Zeroes
    * Partition Array According to Given Pivot
    * Segregate Even and Odd Numbers

#### 4. K-Way Merge / Pointer per List
When dealing with multiple sorted lists, use one pointer for each list.
* **Concept**: Maintain one pointer for each of the `k` lists. Select the smallest/largest among the current elements pointed to and advance the respective pointer. Often uses a min-heap.
* **Example Questions**:
    * Merge k Sorted Lists
    * Smallest Range Covering Elements from K Lists
    * Kth Smallest Element in a Sorted Matrix

---

### Relationship: Sliding Window as a Two Pointer Specialization

The Sliding Window technique can be seen as a specialized form of the Two Pointers technique. In sliding windows, the two pointers (`start` and `end`, or `left` and `right`) define the boundaries of the **contiguous** window, and the elements *within* this window are the primary focus. Two Pointers is a broader concept where pointers might not always define such a "window" of elements being processed collectively.

---

## 💡 How to Use This Guide

1.  **Understand the Core Concepts**: Get familiar with the main idea behind each technique and its common patterns.
2.  **Study the Patterns**: Recognize the typical scenarios where each pattern is applied.
3.  **Practice with Example Questions**: Work through the listed questions (and find more!) to solidify your understanding and build problem-solving skills.

Happy Coding!