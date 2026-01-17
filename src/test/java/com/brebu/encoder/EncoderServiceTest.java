package com.brebu.encoder;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class EncoderServiceTest {

    private final EncoderService encoder = new EncoderService();

    @Test
    @DisplayName("Test Example 1: aaaabbbccc should encode to a4b3c3")
    public void testExample1() {
        String input = "aaaabbbccc";
        String expected = "a4b3c3";
        String actual = encoder.encode(input);
        assertEquals(expected, actual,
                "Failed to encode: " + input);
    }

    @Test
    @DisplayName("Test Example 2: abbbcdddd should encode to a1b3c1d4")
    public void testExample2() {
        String input = "abbbcdddd";
        String expected = "a1b3c1d4";
        String actual = encoder.encode(input);
        assertEquals(expected, actual,
                "Failed to encode: " + input);
    }

    @Test
    @DisplayName("Test Example 3: wwwwaaadexxxxxx should encode to w4a3d1e1x6")
    public void testExample3() {
        String input = "wwwwaaadexxxxxx";
        String expected = "w4a3d1e1x6";
        String actual = encoder.encode(input);
        assertEquals(expected, actual,
                "Failed to encode: " + input);
    }

    @Test
    @DisplayName("Test single character should encode to char1")
    public void testSingleCharacter() {
        String input = "a";
        String expected = "a1";
        String actual = encoder.encode(input);
        assertEquals(expected, actual,
                "Single character should be encoded as char1");
    }

    @Test
    @DisplayName("Test empty string should return empty string")
    public void testEmptyString() {
        String input = "";
        String expected = "";
        String actual = encoder.encode(input);
        assertEquals(expected, actual,
                "Empty string should return empty string");
    }

    @Test
    @DisplayName("Test null input should throw IllegalArgumentException")
    public void testNullInput() {
        assertThrows(IllegalArgumentException.class,
                () -> encoder.encode(null),
                "Null input should throw IllegalArgumentException");
    }

    @Test
    @DisplayName("Test all same characters")
    public void testAllSameCharacters() {
        String input = "aaaaaaaaa";
        String expected = "a9";
        String actual = encoder.encode(input);
        assertEquals(expected, actual,
                "All same characters should be encoded correctly");
    }

    @Test
    @DisplayName("Test all different characters")
    public void testAllDifferentCharacters() {
        String input = "abcdefgh";
        String expected = "a1b1c1d1e1f1g1h1";
        String actual = encoder.encode(input);
        assertEquals(expected, actual,
                "All different characters should each have count 1");
    }

    @Test
    @DisplayName("Test alternating characters")
    public void testAlternatingCharacters() {
        String input = "ababab";
        String expected = "a1b1a1b1a1b1";
        String actual = encoder.encode(input);
        assertEquals(expected, actual,
                "Alternating characters should be encoded correctly");
    }

    @Test
    @DisplayName("Test with numbers")
    public void testWithNumbers() {
        String input = "111222333";
        String expected = "132333";
        String actual = encoder.encode(input);
        assertEquals(expected, actual,
                "Numbers should be encoded correctly");
    }

    @Test
    @DisplayName("Test with special characters")
    public void testWithSpecialCharacters() {
        String input = "!!!@@@###";
        String expected = "!3@3#3";
        String actual = encoder.encode(input);
        assertEquals(expected, actual,
                "Special characters should be encoded correctly");
    }

    @Test
    @DisplayName("Test with spaces")
    public void testWithSpaces() {
        String input = "aaa   bbb";
        String expected = "a3 3b3";
        String actual = encoder.encode(input);
        assertEquals(expected, actual,
                "Spaces should be encoded correctly");
    }

    @Test
    @DisplayName("Test with mixed case")
    public void testWithMixedCase() {
        String input = "AAAaaa";
        String expected = "A3a3";
        String actual = encoder.encode(input);
        assertEquals(expected, actual,
                "Mixed case should be treated as different characters");
    }

    @ParameterizedTest
    @CsvSource({
            "'a', 'a1'",
            "'aa', 'a2'",
            "'aaa', 'a3'",
            "'aabb', 'a2b2'",
            "'abc', 'a1b1c1'",
            "'aaabbbccc', 'a3b3c3'"
    })
    @DisplayName("Parameterized test for various inputs")
    public void testParameterized(String input, String expected) {
        String actual = encoder.encode(input);
        assertEquals(expected, actual, "Failed to encode: " + input);
    }

    @Test
    @DisplayName("Test large string performance")
    public void testLargeString() {
        String input = "a".repeat(10000);
        String expected = "a10000";
        long startTime = System.nanoTime();
        String actual = encoder.encode(input);
        long endTime = System.nanoTime();
        assertEquals(expected, actual, "Large string should be encoded correctly");
        long duration = (endTime - startTime) / 1000000; // Convert to milliseconds
        System.out.println("Time taken to encode 10000 characters: " + duration + "ms");
        assertTrue(duration < 100, "Encoding should complete in less than 100ms");
    }

    @Test
    @DisplayName("Test both implementations produce same result")
    public void testBothImplementations() {
        String input = "aaaabbbccc";
        String result1 = encoder.encode(input);
        String result2 = encoder.encode(input);
        assertEquals(result1, result2, "Both implementations should produce the same result");
    }

    @Test
    @DisplayName("Test Unicode characters")
    public void testUnicodeCharacters() {
        String input = "😊😊😊AAA";
        String expected = "😊3A3";
        String actual = encoder.encode(input);
        assertEquals(expected, actual,
                "Unicode characters should be encoded correctly");
    }
}
