package edu.miu.cs.cs425.lab13;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ArrayFlattenerTest {

    private ArrayFlattener arrayFlattener;

    @BeforeEach
    public void setUp() {
        arrayFlattener = new ArrayFlattener();
        System.out.println("Test setup completed: ArrayFlattener initialized");
    }

    @Test
    public void testFlattenArrayWithValidInput() {
        System.out.println("\n=== Starting testFlattenArrayWithValidInput ===");

        int[][] input = {{1, 3}, {0}, {4, 5, 9}};
        int[] expected = {1, 3, 0, 4, 5, 9};

        System.out.println("Input 2D array: " + Arrays.deepToString(input));
        System.out.println("Expected flattened array: " + Arrays.toString(expected));

        int[] result = arrayFlattener.flattenArray(input);

        System.out.println("Actual result: " + Arrays.toString(result));

        assertArrayEquals(expected, result);

        System.out.println("Test result: " +
                (Arrays.equals(expected, result) ? "PASSED" : "FAILED"));
        System.out.println("=== Finished testFlattenArrayWithValidInput ===\n");
    }

    @Test
    public void testFlattenArrayWithNullInput() {
        System.out.println("\n=== Starting testFlattenArrayWithNullInput ===");

        System.out.println("Input: null");
        System.out.println("Expected result: null");

        int[] result = arrayFlattener.flattenArray(null);

        System.out.println("Actual result: " +
                (result == null ? "null" : Arrays.toString(result)));

        assertNull(result);

        System.out.println("Test result: " +
                (result == null ? "PASSED" : "FAILED"));
        System.out.println("=== Finished testFlattenArrayWithNullInput ===\n");
    }
}