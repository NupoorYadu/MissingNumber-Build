package com.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive test suite for MissingNumber finder
 * Tests all algorithms with various scenarios
 */
@DisplayName("Missing Number Finder Tests")
public class MissingNumberTest {

    // ===== BASIC FUNCTIONALITY TESTS =====

    @Test
    @DisplayName("XOR: Find missing from simple array [3,0,1]")
    void testXorSimpleCase() {
        int[] nums = { 3, 0, 1 };
        assertEquals(2, MissingNumber.findMissingByXor(nums));
    }

    @Test
    @DisplayName("Math: Find missing from array [9,6,4,2,3,5,7,0,1]")
    void testMathSimpleCase() {
        int[] nums = { 9, 6, 4, 2, 3, 5, 7, 0, 1 };
        assertEquals(8, MissingNumber.findMissingByMath(nums));
    }

    @Test
    @DisplayName("HashSet: Find missing from simple array")
    void testHashSetSimpleCase() {
        int[] nums = { 0, 1, 3 };
        assertEquals(2, MissingNumber.findMissingByHashSet(nums));
    }

    // ===== MISSING AT BEGINNING =====

    @Test
    @DisplayName("Missing at beginning (0)")
    void testMissingAtBeginning() {
        int[] nums = { 1, 2, 3, 4, 5 };
        assertEquals(0, MissingNumber.findMissingByXor(nums));
        assertEquals(0, MissingNumber.findMissingByMath(nums));
        assertEquals(0, MissingNumber.findMissingByHashSet(nums));
    }

    // ===== MISSING AT END (N) =====

    @Test
    @DisplayName("Missing at end (n)")
    void testMissingAtEnd() {
        int[] nums = { 0, 1, 2, 3, 4 };
        assertEquals(5, MissingNumber.findMissingByXor(nums));
        assertEquals(5, MissingNumber.findMissingByMath(nums));
        assertEquals(5, MissingNumber.findMissingByHashSet(nums));
    }

    // ===== SINGLE ELEMENT ARRAYS =====

    @Test
    @DisplayName("Single element [0] - missing is 1")
    void testSingleElementZero() {
        int[] nums = { 0 };
        assertEquals(1, MissingNumber.findMissingByXor(nums));
        assertEquals(1, MissingNumber.findMissingByMath(nums));
    }

    @Test
    @DisplayName("Single element [1] - missing is 0")
    void testSingleElementOne() {
        int[] nums = { 1 };
        assertEquals(0, MissingNumber.findMissingByXor(nums));
        assertEquals(0, MissingNumber.findMissingByMath(nums));
    }

    // ===== EDGE CASES =====

    @Test
    @DisplayName("Two elements [0,1] - missing is 2")
    void testTwoElementsComplete() {
        int[] nums = { 0, 1 };
        assertEquals(2, MissingNumber.findMissingByXor(nums));
        assertEquals(2, MissingNumber.findMissingByMath(nums));
    }

    @Test
    @DisplayName("Two elements [0,2] - missing is 1")
    void testTwoElementsMissingInMiddle() {
        int[] nums = { 0, 2 };
        assertEquals(1, MissingNumber.findMissingByXor(nums));
        assertEquals(1, MissingNumber.findMissingByMath(nums));
    }

    @Test
    @DisplayName("Two elements [1,2] - missing is 0")
    void testTwoElementsMissingAtStart() {
        int[] nums = { 1, 2 };
        assertEquals(0, MissingNumber.findMissingByXor(nums));
        assertEquals(0, MissingNumber.findMissingByMath(nums));
    }

    // ===== LARGE ARRAYS =====

    @Test
    @DisplayName("Large array (1000 elements)")
    void testLargeArray1000() {
        int[] nums = new int[999];
        for (int i = 0; i < 999; i++) {
            nums[i] = i + 1; // 1 to 999, missing 0
        }
        assertEquals(0, MissingNumber.findMissingByXor(nums));
        assertEquals(0, MissingNumber.findMissingByMath(nums));
    }

    @Test
    @DisplayName("Large array with middle element missing")
    void testLargeArrayMissingInMiddle() {
        int[] nums = new int[999];
        int idx = 0;
        for (int i = 0; i <= 999; i++) {
            if (i != 500) {
                nums[idx++] = i;
            }
        }
        assertEquals(500, MissingNumber.findMissingByXor(nums));
        assertEquals(500, MissingNumber.findMissingByMath(nums));
    }

    // ===== ALGORITHM COMPARISON =====

    @Test
    @DisplayName("All algorithms should return same result")
    void testAllAlgorithmsConsistency() {
        int[] nums = { 0, 1, 2, 4, 5 };
        int xorResult = MissingNumber.findMissingByXor(nums);
        int mathResult = MissingNumber.findMissingByMath(nums);
        int hashResult = MissingNumber.findMissingByHashSet(nums);
        int sortResult = MissingNumber.findMissingBySorting(nums);

        assertEquals(3, xorResult);
        assertEquals(3, mathResult);
        assertEquals(3, hashResult);
        assertEquals(3, sortResult);
        assertEquals(xorResult, mathResult);
        assertEquals(mathResult, hashResult);
        assertEquals(hashResult, sortResult);
    }

    @Test
    @DisplayName("Compare multiple algorithms on same array")
    void testAlgorithmComparison() {
        int[] testArray = { 0, 2, 3, 4, 5, 6, 7, 8, 9 };

        int xor = MissingNumber.findMissingByXor(testArray);
        int math = MissingNumber.findMissingByMath(testArray);
        int hash = MissingNumber.findMissingByHashSet(testArray);
        int linear = MissingNumber.findMissingByLinearSearch(testArray);
        int sort = MissingNumber.findMissingBySorting(testArray);

        assertEquals(1, xor);
        assertEquals(1, math);
        assertEquals(1, hash);
        assertEquals(1, linear);
        assertEquals(1, sort);
    }

    // ===== EXCEPTION HANDLING =====

    @Test
    @DisplayName("Null array throws IllegalArgumentException")
    void testNullArrayXor() {
        assertThrows(IllegalArgumentException.class, () -> {
            MissingNumber.findMissingByXor(null);
        });
    }

    @Test
    @DisplayName("Empty array throws IllegalArgumentException")
    void testEmptyArrayXor() {
        assertThrows(IllegalArgumentException.class, () -> {
            MissingNumber.findMissingByXor(new int[0]);
        });
    }

    @Test
    @DisplayName("Null array throws error in Math method")
    void testNullArrayMath() {
        assertThrows(IllegalArgumentException.class, () -> {
            MissingNumber.findMissingByMath(null);
        });
    }

    @Test
    @DisplayName("Empty array throws error in HashSet method")
    void testEmptyArrayHashSet() {
        assertThrows(IllegalArgumentException.class, () -> {
            MissingNumber.findMissingByHashSet(new int[0]);
        });
    }

    // ===== VALIDATION TESTS =====

    @Test
    @DisplayName("Valid array detection")
    void testValidArrayDetection() {
        assertTrue(MissingNumber.isValidArray(new int[] { 0, 1, 2, 3 }));
        assertTrue(MissingNumber.isValidArray(new int[] { 3, 0, 1, 2 }));
        assertTrue(MissingNumber.isValidArray(new int[] { 0, 2 }));
    }

    @Test
    @DisplayName("Invalid array detection - out of range")
    void testInvalidArrayOutOfRange() {
        assertFalse(MissingNumber.isValidArray(new int[] { 0, 1, 5 })); // 5 > n
    }

    @Test
    @DisplayName("Invalid array detection - duplicate")
    void testInvalidArrayDuplicate() {
        assertFalse(MissingNumber.isValidArray(new int[] { 0, 1, 1 })); // duplicate 1
    }

    @Test
    @DisplayName("Invalid array - null input")
    void testInvalidArrayNull() {
        assertFalse(MissingNumber.isValidArray(null));
    }

    // ===== HELPER METHOD TESTS =====

    @Test
    @DisplayName("Array to string conversion")
    void testArrayToString() {
        int[] nums = { 0, 1, 2, 3 };
        String result = MissingNumber.arrayToString(nums);
        assertEquals("[0, 1, 2, 3]", result);
    }

    @Test
    @DisplayName("Null array to string")
    void testNullArrayToString() {
        String result = MissingNumber.arrayToString(null);
        assertEquals("null", result);
    }

    // ===== RANDOMIZED TESTS =====

    @Test
    @DisplayName("Random arrays - XOR vs Math")
    void testRandomArrays() {
        java.util.Random rand = new java.util.Random(42); // Fixed seed for reproducibility

        for (int test = 0; test < 50; test++) {
            int n = rand.nextInt(100) + 2; // 2 to 101
            int[] nums = new int[n];
            int missingVal = rand.nextInt(n + 1);

            int idx = 0;
            for (int i = 0; i <= n; i++) {
                if (i != missingVal) {
                    nums[idx++] = i;
                }
            }

            int xorResult = MissingNumber.findMissingByXor(nums);
            int mathResult = MissingNumber.findMissingByMath(nums);

            assertEquals(missingVal, xorResult,
                    "XOR failed for array size " + n + ", missing " + missingVal);
            assertEquals(missingVal, mathResult,
                    "Math failed for array size " + n + ", missing " + missingVal);
        }
    }

    // ===== PERFORMANCE TESTS =====

    @Test
    @DisplayName("Performance: XOR method on 10000 elements < 10ms")
    void testXorPerformance() {
        int n = 10000;
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i + 1; // 1 to n, missing 0
        }

        long start = System.currentTimeMillis();
        int result = MissingNumber.findMissingByXor(nums);
        long end = System.currentTimeMillis();

        assertEquals(0, result);
        assertTrue((end - start) < 10, "XOR took " + (end - start) + "ms");
    }

    @Test
    @DisplayName("Performance: Math method on 10000 elements < 10ms")
    void testMathPerformance() {
        int n = 10000;
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i + 1;
        }

        long start = System.currentTimeMillis();
        int result = MissingNumber.findMissingByMath(nums);
        long end = System.currentTimeMillis();

        assertEquals(0, result);
        assertTrue((end - start) < 10, "Math took " + (end - start) + "ms");
    }

    // ===== OVERFLOW TESTS =====

    @Test
    @DisplayName("Large numbers without overflow (Math formula)")
    void testNoOverflow() {
        // Use Math formula which handles large sums safely
        int[] nums = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        int result = MissingNumber.findMissingByMath(nums);
        assertEquals(11, result);
    }
}
