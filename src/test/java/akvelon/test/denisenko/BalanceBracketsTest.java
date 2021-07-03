package akvelon.test.denisenko;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class BalanceBracketsTest {

    BalanceVerificator balanceBrackets;
    ByteArrayOutputStream outputStreamCapture;
    PrintStream standardOut = System.out;

    @BeforeEach
    public void initConsoleCapture() {
        outputStreamCapture = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCapture));
    }

    @AfterEach
    public void restoreSystemOut() {
        System.setOut(standardOut);
    }

    @BeforeEach
    public void init() {
        balanceBrackets = new BalanceBrackets();
    }

    @Test
    @Order(1)
    public void notContainBracketReturnValueTest() {
        int expected = 0;
        String str = "asdf";
        int actual = balanceBrackets.verify(str);
        assertEquals(expected, actual);
    }

    @Test
    @Order(2)
    public void notContainBracketConsoleMessageTest() {
        String expected = "Input string doesn't contain brackets!\n";
        String str = "asdf";
        balanceBrackets.verify(str);
        String actual = outputStreamCapture.toString();
        assertEquals(expected, actual);
    }

    @Test
    @Order(3)
    public void canContainBracketsWithTextConsoleMessageTest() {
        String expected = "BALANCED\n";
        String str = "{asdf}";
        balanceBrackets.verify(str);
        String actual = outputStreamCapture.toString();
        assertEquals(expected, actual);
    }

    @Test
    @Order(4)
    public void correctReturnValueTest() {
        int expected = -1;
        String str = "{([])}";
        int actual = balanceBrackets.verify(str);
        assertEquals(expected, actual);
    }

    @Test
    @Order(5)
    public void correctConsoleMessageTest() {
        String expected = "BALANCED\n";
        String str = "{[{}]}";
        balanceBrackets.verify(str);
        String actual = outputStreamCapture.toString();
        assertEquals(expected, actual);
    }

    @Test
    @Order(6)
    public void unmatchedBracketsReturnValueTest() {
        int expected = 3;
        String str = "{([)}";
        int actual = balanceBrackets.verify(str);
        assertEquals(expected, actual);
    }

    @Test
    @Order(7)
    public void unmatchedBracketsEG1ConsoleMessageTest() {
        String expected = "NOT BALANCED (3)\n";
        String str = "{([)}";
        balanceBrackets.verify(str);
        String actual = outputStreamCapture.toString();
        assertEquals(expected, actual);
    }

    @Test
    @Order(8)
    public void unmatchedBracketsEG2ConsoleMessageTest() {
        String expected = "NOT BALANCED (4)\n";
        String str = "{([]}";
        balanceBrackets.verify(str);
        String actual = outputStreamCapture.toString();
        assertEquals(expected, actual);
    }

    @Test
    @Order(9)
    public void unmatchedBracketsEG3ConsoleMessageTest() {
        String expected = "NOT BALANCED (5)\n";
        String str = "([])}";
        try {
            balanceBrackets.verify(str);
        } catch (IllegalArgumentException e) {
            e.getMessage();
        }
        String actual = outputStreamCapture.toString();
        assertEquals(expected, actual);
    }

    @Test
    @Order(9)
    public void unmatchedBracketsEG4ConsoleMessageTest() {
        String expected = "NOT BALANCED (4)\n";
        String str = "{()]{}}({)[(()]";
        try {
            balanceBrackets.verify(str);
        } catch (IllegalArgumentException e) {
            e.getMessage();
        }
        String actual = outputStreamCapture.toString();
        assertEquals(expected, actual);
    }

    @Test
    @Order(10)
    public void characterNotBelongBracketsTest() {
        String expected = "A character 'a' doesn’t belong to any known brackets type\n";
        String str = "a{}";
        try {
            balanceBrackets.verify(str);
        } catch (IllegalArgumentException e) {
            e.getMessage();
        }
        String actual = outputStreamCapture.toString();
        assertEquals(expected, actual);
    }

    @Test
    @Order(11)
    public void characterNotBelongBracketsEG1Test() {
        String expected = "A character 'a' doesn’t belong to any known brackets type\n";
        String str = "a{}";
        try {
            balanceBrackets.verify(str);
        } catch (IllegalArgumentException e) {
            e.getMessage();
        }
        String actual = outputStreamCapture.toString();
        assertEquals(expected, actual);
    }

    @Test
    @Order(12)
    public void characterNotBelongBracketsEG2Test() {
        String expected = "A character 'a b' doesn’t belong to any known brackets type\n";
        String str = "a{}b";
        try {
            balanceBrackets.verify(str);
        } catch (IllegalArgumentException e) {
            e.getMessage();
        }
        String actual = outputStreamCapture.toString();
        assertEquals(expected, actual);
    }

    @Test
    @Order(13)
    public void characterNotBelongBracketsEG3Test() {
        String expected = "A character 'az bz' doesn’t belong to any known brackets type\n";
        String str = "az{()}bz";
        try {
            balanceBrackets.verify(str);
        } catch (IllegalArgumentException e) {
            e.getMessage();
        }
        String actual = outputStreamCapture.toString();
        assertEquals(expected, actual);
    }

    @Test
    @Order(14)
    public void characterNotBelongBracketsEG4Test() {
        String expected = "A character 'bz' doesn’t belong to any known brackets type\n";
        String str = "{()}bz";
        try {
            balanceBrackets.verify(str);
        } catch (IllegalArgumentException e) {
            e.getMessage();
        }
        String actual = outputStreamCapture.toString();
        assertEquals(expected, actual);
    }

    @Test
    @Order(15)
    public void characterNotBelongBracketsEG5Test() {
        String expected = "A character 'z' doesn’t belong to any known brackets type\n";
        String str = "{}z";
        try {
            balanceBrackets.verify(str);
        } catch (IllegalArgumentException e) {
            e.getMessage();
        }
        String actual = outputStreamCapture.toString();
        assertEquals(expected, actual);
    }

    @Test
    @Order(16)
    public void throwIllegalArgumentExceptionTest(){
        String str = "{}z";
        assertThrows(IllegalArgumentException.class, () -> balanceBrackets.verify(str));
    }

    @Test
    @Order(17)
    public void unmatchedFirstBracketConsoleMessageTest() {
        String expected = "NOT BALANCED (1)\n";
        String str = "}()";
        try {
            balanceBrackets.verify(str);
        } catch (IllegalArgumentException e) {
            e.getMessage();
        }
        String actual = outputStreamCapture.toString();
        assertEquals(expected, actual);
    }

    @Test
    @Order(18)
    public void unmatchedLastBracketConsoleMessageTest() {
        String expected = "NOT BALANCED (3)\n";
        String str = "(){";
        try {
            balanceBrackets.verify(str);
        } catch (IllegalArgumentException e) {
            e.getMessage();
        }
        String actual = outputStreamCapture.toString();
        assertEquals(expected, actual);
    }


}
