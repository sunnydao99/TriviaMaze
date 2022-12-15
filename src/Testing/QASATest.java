package Testing;

import static org.junit.Assert.*;

import Model.QASA;
import org.junit.Before;
import org.junit.Test;

/**
 * This class test for Short Answer Question class
 * @author: An Nguyen
 * @version: 12.10.22
 *
 */

public class QASATest {
    private QASA bankSA;
    @Before
    public final void setup(){
        bankSA = new QASA();
    }

    /**
     * Test for getQuestion("SA", 1)
     */
    @Test
    public final void getQuestion_0() {
        bankSA = new QASA("SA", 1);
        String expected = "Which data type is used to create a variable that should store text?";

        String question = "";
        question = bankSA.getQuestion("SA", 1);
        assertEquals(expected,question);
    }

    /**
     * Test for getQuestion("SA", 10)
     */
    @Test
    public final void getQuestion_1() {
        bankSA = new QASA("SA", 10);
        String expected = "Compiled Java program";

        String question = "";
        question = bankSA.getQuestion("SA", 10);
        assertEquals(expected,question);
    }

    /**
     * Test for getQuestion("MC", 10): test for different category
     */
    @Test
    public final void getQuestion_2() {
        bankSA = new QASA("MC", 10);
        String expected = "";

        String question = "";
        question = bankSA.getQuestion("MC", 10);
        assertEquals(expected,question);
    }

    /**
     * Test for getQuestion("SA", 20): test for ID is out of bound
     */
    @Test
    public final void getQuestion_3() {
        bankSA = new QASA("SA", 20);
        String expected = "";

        String question = "";
        question = bankSA.getQuestion("SA", 20);
        assertEquals(expected,question);
    }

    /**
     * Test for getQuestion("", 2): test for empty category
     */
    @Test
    public final void getQuestion_4() {
        bankSA = new QASA("", 2);
        String expected = "";

        String question = "";
        question = bankSA.getQuestion("", 2);
        assertEquals(expected,question);
    }

    /**
     * Test for getQuestion("SA", -2): tests for ID is negative
     */
    @Test
    public final void getQuestion_5() {
        bankSA = new QASA("SA", -2);
        String expected = "";

        String question = "";
        question = bankSA.getQuestion("SA", -2);
        assertEquals(expected,question);
    }

    /**
     * Test for getQuestion("SA", 0): tests for ID is 0
     */
    @Test
    public final void getQuestion_6() {
        bankSA = new QASA("SA", 0);
        String expected = "";

        String question = "";
        question = bankSA.getQuestion("SA", 0);
        assertEquals(expected,question);
    }

    /**
     * Test for getQuestion("", 20): tests for empty category, and
     * ID is out of bound
     */
    @Test
    public final void getQuestion_7() {
        bankSA = new QASA("", 20);
        String expected = "";

        String question = "";
        question = bankSA.getQuestion("", 20);
        assertEquals(expected,question);
    }

    /**
     * Test for getQuestion("SA", 20): tests for different category, and
     * ID is out of bound
     */
    @Test
    public final void getQuestion_8() {
        bankSA = new QASA("TF", 20);
        String expected = "";

        String question = "";
        question = bankSA.getQuestion("TF", 20);
        assertEquals(expected,question);
    }

    /**
     * Test for getAnswer("SA", 1)
     */
    @Test
    public final void getAnswer_0() {
        bankSA = new QASA("SA", 1);
        String expected = "String";

        String ans = "";
        ans = bankSA.getAnswer("SA", 1);
        assertEquals(expected,ans);
    }

    /**
     * Test for getAnswer("SA", 10)
     */
    @Test
    public final void getAnswer_1() {
        bankSA = new QASA("SA", 10);
        String expected = ".class";

        String ans = "";
        ans = bankSA.getAnswer("SA", 10);
        assertEquals(expected,ans);
    }

    /**
     * Test for getAnswer("MC", 10): tests for different category
     */
    @Test
    public final void getAnswer_2() {
        bankSA = new QASA("MC", 10);
        String expected = "";

        String ans = "";
        ans = bankSA.getAnswer("MC", 10);
        assertEquals(expected,ans);
    }

    /**
     * Test for getAnswer("SA", 20): tests for ID is out of bound
     */
    @Test
    public final void getAnswer_3() {
        bankSA = new QASA("SA", 20);
        String expected = "";

        String ans = "";
        ans = bankSA.getAnswer("SA", 20);
        assertEquals(expected,ans);
    }

    /**
     * Test for getAnswer("", 2): tests for empty category
     */
    @Test
    public final void getAnswer_4() {
        bankSA = new QASA("", 2);
        String expected = "";

        String ans = "";
        ans = bankSA.getAnswer("", 2);
        assertEquals(expected,ans);
    }

    /**
     * Test for getAnswer("SA", 2): tests for ID is negative
     */
    @Test
    public final void getAnswer_5() {
        bankSA = new QASA("SA", -2);
        String expected = "";

        String ans = "";
        ans = bankSA.getAnswer("SA", -2);
        assertEquals(expected,ans);
    }

    /**
     * Test for getAnswer("SA", 0): tests for ID is 0
     */
    @Test
    public final void getAnswer_6() {
        bankSA = new QASA("SA", 0);
        String expected = "";

        String ans = "";
        ans = bankSA.getAnswer("SA", 0);
        assertEquals(expected,ans);
    }

    /**
     * Test for getAnswer("", 100): tests for empty category, and
     * ID is out of bound
     */
    @Test
    public final void getAnswer_7() {
        bankSA = new QASA("", 100);
        String expected = "";

        String ans = "";
        ans = bankSA.getAnswer("", 100);
        assertEquals(expected,ans);
    }

    /**
     * Test for getAnswer("MC", 100): tests for different category, and
     * ID is out of bound
     */
    @Test
    public final void getAnswer_8() {
        bankSA = new QASA("MC", 100);
        String expected = "";

        String ans = "";
        ans = bankSA.getAnswer("MC", 100);
        assertEquals(expected,ans);
    }

    /**
     * Test for getHintSA("SA", 1):
     */
    @Test
    public final void getHintSA_0() {
        bankSA = new QASA("SA", 1);
        String expected = "Str";

        String hint = "";
        hint = bankSA.getHintSA("SA", 1);
        assertEquals(expected,hint);
    }

    /**
     * Test for getHintSA("SA", 10):
     */
    @Test
    public final void getHintSA_1() {
        bankSA = new QASA("SA", 10);
        String expected = ".cl";

        String hint = "";
        hint = bankSA.getHintSA("SA", 10);
        assertEquals(expected,hint);
    }

    /**
     * Test for getHintSA("MC", 10): tests for different category
     */
    @Test
    public final void getHintSA_2() {
        bankSA = new QASA("MC", 10);
        String expected = "";

        String hint = "";
        hint = bankSA.getHintSA("MC", 10);
        assertEquals(expected,hint);
    }

    /**
     * Test for getHintSA("SA", 20): tests for ID is out of bound
     */
    @Test
    public final void getHintSA_3() {
        bankSA = new QASA("SA", 20);
        String expected = "";

        String hint = "";
        hint = bankSA.getHintSA("SA", 20);
        assertEquals(expected,hint);
    }

    /**
     * Test for getHintSA("", 1): tests for empty category
     */
    @Test
    public final void getHintSA_4() {
        bankSA = new QASA("", 2);
        String expected = "";

        String hint = "";
        hint = bankSA.getHintSA("", 2);
        assertEquals(expected,hint);
    }

    /**
     * Test for getHintSA("SA", -2): tests for ID is negative
     */
    @Test
    public final void getHintSA_5() {
        bankSA = new QASA("SA", -2);
        String expected = "";

        String hint = "";
        hint = bankSA.getHintSA("SA", -2);
        assertEquals(expected,hint);
    }

    /**
     * Test for getHintSA("SA", 0): tests for ID is 0
     */
    @Test
    public final void getHintSA_6() {
        bankSA = new QASA("SA", 0);
        String expected = "";

        String hint = "";
        hint = bankSA.getHintSA("SA", 0);
        assertEquals(expected,hint);
    }

    /**
     * Test for getHintSA("", 100): test for empty category, and
     * ID is out of bound
     */
    @Test
    public final void getHintSA_7() {
        bankSA = new QASA("", 100);
        String expected = "";

        String hint = "";
        hint = bankSA.getHintSA("", 100);
        assertEquals(expected,hint);
    }

    /**
     * Test for getHintSA("TR", 100): test for different category, and
     * ID is out of bound
     */
    @Test
    public final void getHintSA_8() {
        bankSA = new QASA("TF", 100);
        String expected = "";

        String hint = "";
        hint = bankSA.getHintSA("TF", 100);
        assertEquals(expected,hint);
    }
}