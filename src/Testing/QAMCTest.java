package Testing;

import static org.junit.Assert.*;

import Model.QAMC;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * @author: An Nguyen
 * @version: 12/10/2022
 *
 */

public class QAMCTest {

    private QAMC bankMC;
    @Before
    public final void setup(){
        bankMC = new QAMC();
    }

    /**
     * Test for getQuestion("MC", 1)
     */
    @Test
    public final void getQuestion_0() {
        bankMC = new QAMC("MC", 1);
        String expected = "int x= 1; int y = 2; int z= x+y; what print out of z?";

        String question = "";
        question = bankMC.getQuestion("MC", 1);
        assertEquals(expected,question);
    }

    /**
     * Test for getQuestion("MC", 2)
     */
    @Test
    public final void getQuestion_1() {
        bankMC = new QAMC("MC", 2);
        String expected = "what statement does print out ?";

        String question = "";
        question = bankMC.getQuestion("MC", 2);
        assertEquals(expected,question);
    }

    /**
     * Test for getQuestion("TF", 1): tests for different category
     */
    @Test
    public final void getQuestion_2() {
        bankMC = new QAMC("TF", 2);
        String expected = "";

        String question = "";
        question = bankMC.getQuestion("TF", 2);
        assertEquals(expected,question);
    }

    /**
     * Test for getQuestion("MC", 20): tests for ID out of range
     */
    @Test
    public final void getQuestion_3() {
        bankMC = new QAMC("MC", 20);
        String expected = "";

        String question = "";
        question = bankMC.getQuestion("MC", 20);
        assertEquals(expected,question);
    }

    /**
     * Test for getQuestion("", 2): tests for empty category
     */
    @Test
    public final void getQuestion_4() {
        bankMC = new QAMC("", 2);
        String expected = "";

        String question = "";
        question = bankMC.getQuestion("", 2);
        assertEquals(expected,question);
    }

    /**
     * Test for getQuestion("", -2): tests for ID is negative
     */
    @Test
    public final void getQuestion_5() {
        bankMC = new QAMC("MC", -2);
        String expected = "";

        String question = "";
        question = bankMC.getQuestion("MC", -2);
        assertEquals(expected,question);
    }

    /**
     * Test for getQuestion("MC", 2): tests for ID is 0
     */
    @Test
    public final void getQuestion_6() {
        bankMC = new QAMC("MC", 0);
        String expected = "";

        String question = "";
        question = bankMC.getQuestion("MC", 0);
        assertEquals(expected,question);
    }

    /**
     * Test for getQuestion("MC", 2): tests for ID is out of bound,
     * and empty category
     */
    @Test
    public final void getQuestion_7() {
        bankMC = new QAMC("", 20);
        String expected = "";

        String question = "";
        question = bankMC.getQuestion("", 20);
        assertEquals(expected,question);
    }

    /**
     * Test for getQuestion("MC", 2): tests for ID is out of bound,
     * and different category
     */
    @Test
    public final void getQuestion_8() {
        bankMC = new QAMC("SA", 20);
        String expected = "";

        String question = "";
        question = bankMC.getQuestion("SA", 20);
        assertEquals(expected,question);
    }

    /**
     * Test for getAnswer("MC", 1)
     */
    @Test
    public final void getAnswer_0() {
        bankMC = new QAMC("MC", 1);
        String expected = "B";

        String ans = "";
        ans = bankMC.getAnswer("MC", 1);
        assertEquals(expected,ans);
    }

    /**
     * Test for getAnswer("MC", 2)
     */
    @Test
    public final void getAnswer_1() {
        bankMC = new QAMC("MC", 2);
        String expected = "C";

        String ans = "";
        ans = bankMC.getAnswer("MC", 2);
        assertEquals(expected,ans);
    }

    /**
     * Test for getAnswer("TF", 2): tests for different category
     */
    @Test
    public final void getAnswer_2() {
        bankMC = new QAMC("TF", 2);
        String expected = "";

        String ans = "";
        ans = bankMC.getAnswer("TF", 2);
        assertEquals(expected,ans);
    }

    /**
     * Test for getAnswer("MC", 20): tests for ID is out of range
     */
    @Test
    public final void getAnswer_3() {
        bankMC = new QAMC("MC", 20);
        String expected = "";

        String ans = "";
        ans = bankMC.getAnswer("MC", 20);
        assertEquals(expected,ans);
    }

    /**
     * Test for getAnswer("", 2): tests for empty category
     */
    @Test
    public final void getAnswer_4() {
        bankMC = new QAMC("", 2);
        String expected = "";

        String ans = "";
        ans = bankMC.getAnswer("", 2);
        assertEquals(expected,ans);
    }

    /**
     * Test for getAnswer("MC", -2): tests for ID is negative
     */
    @Test
    public final void getAnswer_5() {
        bankMC = new QAMC("MC", -2);
        String expected = "";

        String ans = "";
        ans = bankMC.getAnswer("MC", -2);
        assertEquals(expected,ans);
    }

    /**
     * Test for getAnswer("", 2): tests for ID is 0
     */
    @Test
    public final void getAnswer_6() {
        bankMC = new QAMC("MC", 0);
        String expected = "";

        String ans = "";
        ans = bankMC.getAnswer("MC", 0);
        assertEquals(expected,ans);
    }

    /**
     * Test for getAnswer("", 2): tests for empty category,
     * and ID is out of bound
     */
    @Test
    public final void getAnswer_7() {
        bankMC = new QAMC("", 20);
        String expected = "";

        String ans = "";
        ans = bankMC.getAnswer("", 20);
        assertEquals(expected,ans);
    }

    /**
     * Test for getAnswer("", 2): tests for different category,
     * and ID is out of bound
     */
    @Test
    public final void getAnswer_8() {
        bankMC = new QAMC("SA", 20);
        String expected = "";

        String ans = "";
        ans = bankMC.getAnswer("SA", 20);
        assertEquals(expected,ans);
    }

    /**
     * Test for getChoices("MC", 1)
     */
    @Test
    public final void getChoices_0() {
        bankMC = new QAMC("MC", 1);
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("2");
        expected.add("3");
        expected.add("4");
        expected.add("5");

        ArrayList<String> ans;
        ans = bankMC.getChoices("MC", 1);
        assertEquals(expected,ans);
    }

    /**
     * Test for getChoices("MC", 2)
     */
    @Test
    public final void getChoices_1() {
        bankMC = new QAMC("MC", 2);
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("Sytem.in");
        expected.add("print out");
        expected.add("System.out.println() ");
        expected.add("consle.out");

        ArrayList<String> ans;
        ans = bankMC.getChoices("MC", 2);
        assertEquals(expected,ans);
    }

    /**
     * Test for getChoices("MC", 2)
     */
    @Test
    public final void getChoices_2() {
        bankMC = new QAMC("TF", 2);
        ArrayList<String> expected = new ArrayList<String>();

        ArrayList<String> ans;
        ans = bankMC.getChoices("TF", 2);
        assertEquals(expected,ans);
    }

    /**
     * Test for getChoices("MC", 20): test for ID out of bound
     */
    @Test
    public final void getChoices_3() {
        bankMC = new QAMC("MC", 20);
        ArrayList<String> expected = new ArrayList<String>();

        ArrayList<String> ans;
        ans = bankMC.getChoices("MC", 20);
        assertEquals(expected,ans);
    }

    /**
     * Test for getChoices("", 2): test for empty category
     */
    @Test
    public final void getChoices_4() {
        bankMC = new QAMC("", 2);
        ArrayList<String> expected = new ArrayList<String>();

        ArrayList<String> ans;
        ans = bankMC.getChoices("", 2);
        assertEquals(expected,ans);
    }

    /**
     * Test for getChoices("MC", -2): test for ID is negative
     */
    @Test
    public final void getChoices_5() {
        bankMC = new QAMC("MC", -2);
        ArrayList<String> expected = new ArrayList<String>();

        ArrayList<String> ans;
        ans = bankMC.getChoices("MC", -2);
        assertEquals(expected,ans);
    }

    /**
     * Test for getChoices("MC", 0): test for ID is 0
     */
    @Test
    public final void getChoices_6() {
        bankMC = new QAMC("MC", 0);
        ArrayList<String> expected = new ArrayList<String>();

        ArrayList<String> ans;
        ans = bankMC.getChoices("MC", 0);
        assertEquals(expected,ans);
    }

    /**
     * Test for getChoices("", 20): test for empty category, and
     * ID is out of bound
     */
    @Test
    public final void getChoices_7() {
        bankMC = new QAMC("", 20);
        ArrayList<String> expected = new ArrayList<String>();

        ArrayList<String> ans;
        ans = bankMC.getChoices("", 20);
        assertEquals(expected,ans);
    }

    /**
     * Test for getChoices("", 20): test for different, and
     * ID is out of bound
     */
    @Test
    public final void getChoices_8() {
        bankMC = new QAMC("SA", 20);
        ArrayList<String> expected = new ArrayList<String>();

        ArrayList<String> ans;
        ans = bankMC.getChoices("SA", 20);
        assertEquals(expected,ans);
    }

}