package Testing;
import Model.QATF;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class QATFTest {

    private QATF bankTF;
    ArrayList<String> expected ;
    @Before
    public final void setup(){
        bankTF = new QATF();
        expected  = new ArrayList<String>();
    }


    @Test
    /**
     * Test for getQuestion("TF", 1)
     */
    public final void getQuestion_0() {
        bankTF = new QATF("TF", 1);
        String expected = '"' + "System.in is the input stream connected to the console," + '"' ;

        String question = "";
        question = bankTF.getQuestion("TF", 1);
        assertEquals(expected,question);
    }

    @Test
    /**
     * Test for getQuestion("TF", 2)
     */
    public final void getQuestion_1() {
        bankTF = new QATF("TF", 2);
        String expected = "String is primitive data type";

        String question = "";
        question = bankTF.getQuestion("TF", 2);
        assertEquals(expected,question);
    }

    @Test
    /**
     * Test for getQuestion("", 1): tests for empty category
     */
    public final void getQuestion_2() {
        bankTF = new QATF("", 1);
        String expected = "";

        String question = "";
        question = bankTF.getQuestion("", 1);
        assertEquals(expected,question);
    }
    @Test
    /**
     * Test for getQuestion("MC", 20): tests for ID out of range
     */
    public final void getQuestion_3() {
        bankTF = new QATF("TFE", 20);
        String expected = "";

        String question = "";
        question = bankTF.getQuestion("TFE", 20);
        assertEquals("",question);
    }

    @Test
    /**
     * Test for getQuestion("TF", 4): test for different category
     */
    public final void getQuestion_4() {
        bankTF = new QATF("MC", 4);
        String expected = "";

        String question = "";
        question = bankTF.getQuestion("MC", 4);
        assertEquals("",question);
    }

    @Test
    /**
     * Test for getQuestion("", -2): tests for ID is negative
     */
    public final void getQuestion_5() {
        bankTF = new QATF("", -2);
        String expected = "";

        String question = "";
        question = bankTF.getQuestion("", -2);
        assertEquals("",question);
    }

    @Test
    /**
     * Test for getQuestion("TFE", 0): tests for ID is 0
     */
    public final void getQuestion_6() {
        bankTF = new QATF("TF", 0);
        String expected = "";

        String question = "";
        question = bankTF.getQuestion("TF", 0);
        assertEquals(expected,question);
    }

    /**
     * Test for getQuestion("", 20): tests for ID is out of bound,
     * and empty category
     */
    @Test
    public final void getQuestion_7() {
        bankTF = new QATF("", 20);
        String expected = "";

        String question = "";
        question = bankTF.getQuestion("", 20);
        assertEquals(expected,question);
    }

    /**
     * Test for getQuestion("SA", 2): tests for ID is out of bound,
     * and different category
     */
    @Test
    public final void getQuestion_8() {
        bankTF = new QATF("SA", 20);
        String expected = "";

        String question = "";
        question = bankTF.getQuestion("SA", 20);
        assertEquals(expected,question);
    }

    @Test
    /**
     * Test for getAnswer("TFE", 1)
     */
    public final void getAnswer_0() {
        bankTF = new QATF("TF", 1);
        String expected = "TRUE";

        String ans = "";
        ans = bankTF.getAnswer("TF", 1);
        assertEquals(expected,ans);
    }

    @Test
    /**
     * Test for getAnswer("TF", 2)
     */
    public final void getAnswer_1() {
        bankTF = new QATF("TF", 2);
        String expected = "FALSE";

        String ans = "";
        ans = bankTF.getAnswer("TF", 2);
        assertEquals(expected,ans);
    }

    @Test
    /**
     * Test for getAnswer("SA", 2): tests for different category
     */
    public final void getAnswer_2() {
        bankTF = new QATF("SA", 2);
        String expected = "";

        String ans = "";
        ans = bankTF.getAnswer("SA", 2);
        assertEquals(expected,ans);
    }

    @Test
    /**
     * Test for getAnswer("TFE", 20): tests for ID is out of range
     */
    public final void getAnswer_3() {
        bankTF = new QATF("TF", 20);
        String expected = "";

        String ans = "";
        ans = bankTF.getAnswer("TF", 20);
        assertEquals(expected,ans);
    }

    @Test
    /**
     * Test for getAnswer("", 2): tests for empty category
     */
    public final void getAnswer_4() {
        bankTF = new QATF("", 2);
        String expected = "";

        String ans = "";
        ans = bankTF.getAnswer("", 2);
        assertEquals(expected,ans);
    }

    @Test
    /**
     * Test for getAnswer("TFE", -2): tests for ID is negative
     */
    public final void getAnswer_5() {
        bankTF = new QATF("TF", -2);
        String expected = "";

        String ans = "";
        ans = bankTF.getAnswer("TF", -2);
        assertEquals(expected,ans);
    }

    /**
     * Test for getAnswer("TFE", 0): tests for ID is 0
     */
    @Test
    public final void getAnswer_6() {
        bankTF = new QATF("TF", 0);
        String expected = "";

        String ans = "";
        ans = bankTF.getAnswer("TF", 0);
        assertEquals(expected,ans);
    }

    /**
     * Test for getAnswer("", 20): tests for empty category,
     * and ID is out of bound
     */
    @Test
    public final void getAnswer_7() {
        bankTF = new QATF("", 20);
        String expected = "";

        String ans = "";
        ans = bankTF.getAnswer("", 20);
        assertEquals(expected,ans);
    }

    /**
     * Test for getAnswer("SA", 20): tests for different category,
     * and ID is out of bound
     */
    @Test
    public final void getAnswer_8() {
        bankTF = new QATF("SA", 20);
        String expected = "";

        String ans = "";
        ans = bankTF.getAnswer("SA", 20);
        assertEquals(expected,ans);
    }




    @Test
    /**
     * Test for getChoices("TF", 1)
     */
    public final void getChoices_0() {
        bankTF = new QATF("TF", 1);
        expected = new ArrayList<String>();
        expected.add("TRUE");
        expected.add("FALSE");
        ArrayList<String> ans;
        ans = bankTF.getChoices("TF", 1);
        assertEquals(expected,ans);
    }

    @Test
    /**
     * Test for getChoices("TF", 2)
     */
    public final void getChoices_1() {
        bankTF = new QATF("TF", 2);
        expected = new ArrayList<String>();
        expected.add("TRUE");
        expected.add("FALSE");

        ArrayList<String> ans;
        ans = bankTF.getChoices("TF", 2);
        assertEquals(expected,ans);
    }

    @Test
    /**
     * Test for getChoices("SA", 2)
     */
    public final void getChoices_2() {
        bankTF = new QATF("SA", 2);
        expected = new ArrayList<String>();

        ArrayList<String> ans;
        ans = bankTF.getChoices("SA", 2);
        assertEquals(expected,ans);
    }

    @Test
    /**
     * Test for getChoices("TF", 20): test for ID out of bound
     */
    public final void getChoices_3() {
        bankTF = new QATF("TFE", 20);
        expected = new ArrayList<String>();

        ArrayList<String> ans;
        ans = bankTF.getChoices("TF", 20);
        assertEquals(expected,ans);
    }

    @Test
    /**
     * Test for getChoices("", 2): test for empty category
     */
    public final void getChoices_4() {
        bankTF = new QATF("", 2);
        expected = new ArrayList<String>();

        ArrayList<String> ans;
        ans = bankTF.getChoices("", 2);
        assertEquals(expected,ans);
    }

    @Test
    /**
     * Test for getChoices("TF", -2): test for ID is negative
     */
    public final void getChoices_5() {
        bankTF = new QATF("TF", -2);
        expected = new ArrayList<String>();

        ArrayList<String> ans;
        ans = bankTF.getChoices("TF", -2);
        assertEquals(expected,ans);
    }

    @Test
    /**
     * Test for getChoices("TF", 0): test for ID is 0
     */
    public final void getChoices_6() {
        bankTF = new QATF("TF", 0);
        expected = new ArrayList<String>();

        ArrayList<String> ans;
        ans = bankTF.getChoices("TF", 0);
        assertEquals(expected,ans);
    }

    /**
     * Test for getChoices("", 20): test for empty category, and
     * ID is out of bound
     */
    @Test
    public final void getChoices_7() {
        bankTF = new QATF("", 20);
        expected = new ArrayList<String>();

        ArrayList<String> ans;
        ans = bankTF.getChoices("", 20);
        assertEquals(expected,ans);
    }

    /**
     * Test for getChoices("SA", 20): test for different, and
     * ID is out of bound
     */
    @Test
    public final void getChoices_8() {
        bankTF = new QATF("SA", 20);
        ArrayList<String> expected = new ArrayList<String>();

        ArrayList<String> ans;
        ans = bankTF.getChoices("SA", 20);
        assertEquals(expected,ans);
    }


}


