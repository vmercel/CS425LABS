package edu.miu.cs.cs425.lab13;

import edu.miu.cs.cs425.lab13.service.ArrayFlattenerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class ArrayReversorTest {

    private ArrayReversor arrayReversor;
    private ArrayFlattenerService flattenerService;

    @BeforeEach
    public void setUp() {
        flattenerService = Mockito.mock(ArrayFlattenerService.class);
        arrayReversor = new ArrayReversor(flattenerService);
        System.out.println("Test setup completed: ArrayReversor and mock ArrayFlattenerService initialized");
    }

    @Test
    public void testReverseArrayWithValidInput() {
        System.out.println("\n=== Starting testReverseArrayWithValidInput ===");

        int[][] input = {{1, 3}, {0}, {4, 5, 9}};
        int[] flattened = {1, 3, 0, 4, 5, 9};
        int[] expected = {9, 5, 4, 0, 3, 1};

        System.out.println("Input 2D array: " + Arrays.deepToString(input));
        System.out.println("Mock flattened array: " + Arrays.toString(flattened));
        System.out.println("Expected reversed array: " + Arrays.toString(expected));

        when(flattenerService.flattenArray(input)).thenReturn(flattened);
        int[] result = arrayReversor.reverseArray(input);

        System.out.println("Actual result: " + Arrays.toString(result));

        assertArrayEquals(expected, result);
        verify(flattenerService, times(1)).flattenArray(input);

        System.out.println("Verification: flattenArray() was called " +
                times(1).toString() + " time(s)");
        System.out.println("Test result: " +
                (Arrays.equals(expected, result) ? "PASSED" : "FAILED"));
        System.out.println("=== Finished testReverseArrayWithValidInput ===\n");
    }

    @Test
    public void testReverseArrayWithNullInput() {
        System.out.println("\n=== Starting testReverseArrayWithNullInput ===");

        System.out.println("Input: null");
        System.out.println("Expected result: null");

        when(flattenerService.flattenArray(null)).thenReturn(null);
        int[] result = arrayReversor.reverseArray(null);

        System.out.println("Actual result: " +
                (result == null ? "null" : Arrays.toString(result)));

        assertNull(result);
        verify(flattenerService, times(1)).flattenArray(null);

        System.out.println("Verification: flattenArray() was called " +
                times(1).toString() + " time(s)");
        System.out.println("Test result: " +
                (result == null ? "PASSED" : "FAILED"));
        System.out.println("=== Finished testReverseArrayWithNullInput ===\n");
    }
}