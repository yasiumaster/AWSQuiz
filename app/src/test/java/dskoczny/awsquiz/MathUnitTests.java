package dskoczny.awsquiz;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MathUnitTests {

    @Test
    public void gettingNumberForm_1_isQuestion() throws Exception {
        int bit = 1;
        List<Integer> numbers = QuizOpenHelper.getNumbersFromBit(bit);
        assertEquals(1, numbers.size());
        assertTrue(numbers.contains(1));
    }

    @Test
    public void gettingNumberForm_8_isQuestion() throws Exception {
        int bit = 8;
        List<Integer> numbers = QuizOpenHelper.getNumbersFromBit(bit);
        assertEquals(1, numbers.size());
        assertTrue(numbers.contains(8));
    }

    @Test
    public void gettingNumberForm_12_isQuestion() throws Exception {
        int bit = 12;
        List<Integer> numbers = QuizOpenHelper.getNumbersFromBit(bit);
        assertEquals(2, numbers.size());
        assertTrue(numbers.contains(4));
        assertTrue(numbers.contains(8));
    }

    @Test
    public void gettingNumberForm_13_isQuestion() throws Exception {
        int bit = 13;
        List<Integer> numbers = QuizOpenHelper.getNumbersFromBit(bit);
        assertEquals(3, numbers.size());
        assertTrue(numbers.contains(1));
        assertTrue(numbers.contains(4));
        assertTrue(numbers.contains(8));
    }

    @Test
    public void gettingNumberForm_32_isQuestion() throws Exception {
        int bit = 32;
        List<Integer> numbers = QuizOpenHelper.getNumbersFromBit(bit);
        assertEquals(1, numbers.size());
        assertTrue(numbers.contains(32));
    }

    @Test
    public void gettingNumberForm_33_isQuestion() throws Exception {
        int bit = 33;
        List<Integer> numbers = QuizOpenHelper.getNumbersFromBit(bit);
        assertEquals(2, numbers.size());
        assertTrue(numbers.contains(32));
        assertTrue(numbers.contains(1));
    }

}