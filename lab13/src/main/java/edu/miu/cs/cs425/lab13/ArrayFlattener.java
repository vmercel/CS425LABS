package edu.miu.cs.cs425.lab13;

import java.util.Arrays;

public class ArrayFlattener {
    public int[] flattenArray(int[][] nestedArray) {
        if (nestedArray == null) {
            return null;
        }
        return Arrays.stream(nestedArray)
                .flatMapToInt(Arrays::stream)
                .toArray();
    }
}