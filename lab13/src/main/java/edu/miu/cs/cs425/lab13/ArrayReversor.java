package edu.miu.cs.cs425.lab13;

import edu.miu.cs.cs425.lab13.service.ArrayFlattenerService;

public class ArrayReversor {
    private final ArrayFlattenerService flattenerService;

    public ArrayReversor(ArrayFlattenerService flattenerService) {
        this.flattenerService = flattenerService;
    }

    public int[] reverseArray(int[][] nestedArray) {
        int[] flattened = flattenerService.flattenArray(nestedArray);
        if (flattened == null) {
            return null;
        }

        int[] reversed = new int[flattened.length];
        for (int i = 0; i < flattened.length; i++) {
            reversed[i] = flattened[flattened.length - 1 - i];
        }
        return reversed;
    }
}