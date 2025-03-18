package edu.miu.cs.cs425.lab13;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SelectClasses({
        ArrayFlattenerTest.class // Add other test classes as needed
})
@SuiteDisplayName("Array Flattener Test Suite")
public class ArrayFlattenerTestSuite {
    // No additional code needed; the suite runs the selected classes.
}