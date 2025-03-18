package edu.miu.cs.cs425.lab13;
import org.junit.jupiter.api.DisplayName;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;


@Suite
@SelectClasses({
        ArrayReversorTest.class, // Add other test classes as needed

        // Example: AnotherTest.class,
})
@DisplayName("Array Reversor Test Suite")
public class ArrayReversorTestSuite {
    // No additional code is needed; the suite will run the selected classes.
}