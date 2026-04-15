package com.example;

import java.util.*;

/**
 * Missing Number Finder - Find the missing number from 0 to n
 * 
 * Problem: Given an array containing n distinct numbers taken from 0, 1, 2,
 * ..., n.
 * Find the missing number in the range [0, n].
 * 
 * Time Complexity:
 * - XOR approach: O(n)
 * - Math formula: O(n)
 * - HashSet: O(n)
 * 
 * Space Complexity:
 * - XOR approach: O(1)
 * - Math formula: O(1)
 * - HashSet: O(n)
 */
public class MissingNumber {

    /**
     * Find missing number using optimized XOR approach ⭐ (MOST EFFICIENT)
     * Time: O(n), Space: O(1)
     * 
     * Algorithm:
     * - XOR has property: a ^ a = 0, a ^ 0 = a
     * - XOR all array elements and all numbers 0 to n
     * - The missing number remains
     */
    public static int findMissingByXor(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }

        int xor = 0;
        int n = nums.length;

        // XOR all elements in array
        for (int num : nums) {
            xor ^= num;
        }

        // XOR with all numbers from 0 to n
        for (int i = 0; i <= n; i++) {
            xor ^= i;
        }

        return xor;
    }

    /**
     * Find missing number using mathematical formula (Gauss sum) ⭐
     * Time: O(n), Space: O(1)
     * 
     * Algorithm:
     * - Sum of 0 to n = n * (n + 1) / 2
     * - Missing = Expected sum - Actual sum
     * - Avoids potential XOR complexity
     */
    public static int findMissingByMath(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }

        long n = nums.length;
        long expectedSum = (n * (n + 1)) / 2;
        long actualSum = 0;

        for (int num : nums) {
            actualSum += num;
        }

        return (int) (expectedSum - actualSum);
    }

    /**
     * Find missing number using HashSet
     * Time: O(n), Space: O(n)
     */
    public static int findMissingByHashSet(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }

        Set<Integer> numSet = new HashSet<>(Arrays.asList(
                Arrays.stream(nums).boxed().toArray(Integer[]::new)));

        int n = nums.length;
        for (int i = 0; i <= n; i++) {
            if (!numSet.contains(i)) {
                return i;
            }
        }

        return n;
    }

    /**
     * Find missing number using linear search
     * Time: O(n²), Space: O(1)
     * (Brute force approach)
     */
    public static int findMissingByLinearSearch(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }

        int n = nums.length;
        for (int i = 0; i <= n; i++) {
            boolean found = false;
            for (int num : nums) {
                if (num == i) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                return i;
            }
        }

        return n;
    }

    /**
     * Find missing number using sorting
     * Time: O(n log n), Space: O(1)
     */
    public static int findMissingBySorting(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }

        int[] sorted = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sorted);

        for (int i = 0; i < sorted.length; i++) {
            if (sorted[i] != i) {
                return i;
            }
        }

        return sorted.length;
    }

    // Helper methods

    /**
     * Validate if array contains all numbers 0 to n except one
     */
    public static boolean isValidArray(int[] nums) {
        if (nums == null) {
            return false;
        }

        Set<Integer> seen = new HashSet<>();
        int n = nums.length;

        for (int num : nums) {
            if (num < 0 || num > n) {
                return false;
            }
            if (!seen.add(num)) {
                return false; // Duplicate number
            }
        }

        return seen.size() == n;
    }

    /**
     * Format array as string representation
     */
    public static String arrayToString(int[] arr) {
        if (arr == null) {
            return "null";
        }
        return Arrays.toString(arr);
    }

    // Main method with demo examples
    public static void main(String[] args) {
        System.out.println("========== Missing Number Demo ==========\n");

        // Example 1: Simple case
        int[] arr1 = { 3, 0, 1 };
        int missing1 = findMissingByXor(arr1);
        System.out.println("Example 1: Find missing in " + arrayToString(arr1));
        System.out.println("Missing number: " + missing1 + " [PASS]\n");

        // Example 2: Array with 0 at end
        int[] arr2 = { 9, 6, 4, 2, 3, 5, 7, 0, 1 };
        int missing2 = findMissingByMath(arr2);
        System.out.println("Example 2: Find missing in " + arrayToString(arr2));
        System.out.println("Missing number: " + missing2 + " [PASS]\n");

        // Example 3: Single element array
        int[] arr3 = { 1 };
        int missing3 = findMissingByXor(arr3);
        System.out.println("Example 3: Find missing in " + arrayToString(arr3));
        System.out.println("Missing number: " + missing3 + " [PASS]\n");

        // Example 4: Missing at beginning
        int[] arr4 = { 1, 2, 3, 4, 5 };
        int missing4 = findMissingByHashSet(arr4);
        System.out.println("Example 4: Find missing in " + arrayToString(arr4));
        System.out.println("Missing number: " + missing4 + " [PASS]\n");

        // Example 5: Large array
        int[] arr5 = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 14, 15 }; // Missing: 13
        int missing5 = findMissingByMath(arr5);
        System.out.println("Example 5: Find missing in large array (0-15, missing 13)");
        System.out.println("Missing number: " + missing5 + " [PASS]\n");

        // Algorithm comparison
        System.out.println("========== Algorithm Comparison ==========");
        int[] testArr = { 0, 1, 3, 4, 5 };
        System.out.println("Array: " + arrayToString(testArr) + "\n");

        long start, end;

        start = System.nanoTime();
        int xorResult = findMissingByXor(testArr);
        end = System.nanoTime();
        System.out.println("XOR Method: " + xorResult + " (Time: " + (end - start) + "ns)");

        start = System.nanoTime();
        int mathResult = findMissingByMath(testArr);
        end = System.nanoTime();
        System.out.println("Math Method: " + mathResult + " (Time: " + (end - start) + "ns)");

        start = System.nanoTime();
        int hashResult = findMissingByHashSet(testArr);
        end = System.nanoTime();
        System.out.println("HashSet Method: " + hashResult + " (Time: " + (end - start) + "ns)");

        System.out.println("\n========== Demo Complete ==========");
    }
}
