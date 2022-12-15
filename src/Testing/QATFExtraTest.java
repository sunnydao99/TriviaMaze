package Testing;
import Model.QATFExtra;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import static org.junit.Assert.assertEquals;

/**
 * @author: Xuan Dao (Sunny)
 * @version: 12/12/2022
 *
 */

/**
 * This class test for QATFExtra class
 */
public class QATFExtraTest {

    private QATFExtra bankTFE;
    @Before
    public final void setup(){
        bankTFE = new QATFExtra();
    }

    @Test
    /**
     * Test for getQuestion("TFE", 1): tests for different category
     */
    public final void getQuestion_0() {
        bankTFE = new QATFExtra("TFE", 1);
        String expected = "Classes in the same package are implicitly imported into the main " ;

        String question = "";
        question = bankTFE.getQuestion("TFE", 1);
        assertEquals(expected,question);
    }

    @Test
    /**
     * Test for getQuestion("SA", 2): test for different category
     */
    public final void getQuestion_1() {
        bankTFE = new QATFExtra("SA", 2);
        String expected = "";

        String question = "";
        question = bankTFE.getQuestion("SA", 2);
        assertEquals(expected,question);
    }

    @Test
    /**
     * Test for getQuestion("", 1): tests for empty category
     */
    public final void getQuestion_2() {
        bankTFE = new QATFExtra("", 1);
        String expected = "";

        String question = "";
        question = bankTFE.getQuestion("", 1);
        assertEquals(expected,question);
    }
    @Test
    /**
     * Test for getQuestion("MC", 20): tests for ID out of range
     */
    public final void getQuestion_3() {
        bankTFE = new QATFExtra("TFE", 20);
        String expected = "";

        String question = "";
        question = bankTFE.getQuestion("TFE", 20);
        assertEquals("",question);
    }

    @Test
    /**
     * Test for getQuestion("", 4)
     */
    public final void getQuestion_4() {
        bankTFE = new QATFExtra("TFE", 4);
        String expected = "Parentheses () would not be used to clarify a dangling-else.";

        String question = "";
        question = bankTFE.getQuestion("TFE", 4);
        assertEquals(expected,question);
    }

    @Test
    /**
     * Test for getQuestion("", -2): tests for ID is negative
     */
    public final void getQuestion_5() {
        bankTFE = new QATFExtra("", -2);
        String expected = "";

        String question = "";
        question = bankTFE.getQuestion("", -2);
        assertEquals("",question);
    }

    @Test
    /**
     * Test for getQuestion("TFE", 0): tests for ID is 0
     */
    public final void getQuestion_6() {
        bankTFE = new QATFExtra("TFE", 0);
        String expected = "";

        String question = "";
        question = bankTFE.getQuestion("TFE", 0);
        assertEquals(expected,question);
    }

    /**
     * Test for getQuestion("", 20): tests for ID is out of bound,
     * and empty category
     */
    @Test
    public final void getQuestion_7() {
        bankTFE = new QATFExtra("", 20);
        String expected = "";

        String question = "";
        question = bankTFE.getQuestion("", 20);
        assertEquals(expected,question);
    }

    /**
     * Test for getQuestion("SA", 2): tests for ID is out of bound,
     * and different category
     */
    @Test
    public final void getQuestion_8() {
        bankTFE = new QATFExtra("SA", 20);
        String expected = "";

        String question = "";
        question = bankTFE.getQuestion("SA", 20);
        assertEquals(expected,question);
    }

    @Test
    /**
     * Test for getAnswer("TFE", 1)
     */
    public final void getAnswer_0() {
        bankTFE = new QATFExtra("TFE", 1);
        String expected = "FALSE ";

        String ans = "";
        ans = bankTFE.getAnswer("TFE", 1);
        assertEquals(expected,ans);
    }

    @Test
    /**
     * Test for getAnswer("TFE", 2)
     */
    public final void getAnswer_1() {
        bankTFE = new QATFExtra("TFE", 2);
        String expected = "FALSE ";

        String ans = "";
        ans = bankTFE.getAnswer("TFE", 2);
        assertEquals(expected,ans);
    }

    @Test
    /**
     * Test for getAnswer("SA", 2): tests for different category
     */
    public final void getAnswer_2() {
        bankTFE = new QATFExtra("SA", 2);
        String expected = "";

        String ans = "";
        ans = bankTFE.getAnswer("SA", 2);
        assertEquals(expected,ans);
    }

    @Test
    /**
     * Test for getAnswer("TFE", 20): tests for ID is out of range
     */
    public final void getAnswer_3() {
        bankTFE = new QATFExtra("TFE", 20);
        String expected = "";

        String ans = "";
        ans = bankTFE.getAnswer("TFE", 20);
        assertEquals(expected,ans);
    }

    @Test
    /**
     * Test for getAnswer("", 2): tests for empty category
     */
    public final void getAnswer_4() {
        bankTFE = new QATFExtra("", -2);
        String expected = "";

        String ans = "";
        ans = bankTFE.getAnswer("", -2);
        assertEquals(expected,ans);
    }

    @Test
    /**
     * Test for getAnswer("TFE", -2): tests for ID is negative
     */
    public final void getAnswer_5() {
        bankTFE = new QATFExtra("TFE", -2);
        String expected = "";

        String ans = "";
        ans = bankTFE.getAnswer("TFE", -2);
        assertEquals(expected,ans);
    }

    /**
     * Test for getAnswer("TFE", 0): tests for ID is 0
     */
    @Test
    public final void getAnswer_6() {
        bankTFE = new QATFExtra("TFE", 0);
        String expected = "";

        String ans = "";
        ans = bankTFE.getAnswer("TFE", 0);
        assertEquals(expected,ans);
    }

    /**
     * Test for getAnswer("", 20): tests for empty category,
     * and ID is out of bound
     */
    @Test
    public final void getAnswer_7() {
        bankTFE = new QATFExtra("", 20);
        String expected = "";

        String ans = "";
        ans = bankTFE.getAnswer("", 20);
        assertEquals(expected,ans);
    }

    /**
     * Test for getAnswer("SA", 20): tests for different category,
     * and ID is out of bound
     */
    @Test
    public final void getAnswer_8() {
        bankTFE = new QATFExtra("SA", 20);
        String expected = "";

        String ans = "";
        ans = bankTFE.getAnswer("SA", 20);
        assertEquals(expected,ans);
    }




    @Test
    /**
     * Test for getChoices("TFE", 1)
     */
    public final void getChoices_0() {
        bankTFE = new QATFExtra("TFE", 1);
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("TRUE");
        expected.add("FALSE");
        ArrayList<String> ans;
        ans = bankTFE.getChoices("TFE", 1);
        assertEquals(expected,ans);
    }

    @Test
    /**
     * Test for getChoices("TFE", 2)
     */
    public final void getChoices_1() {
        bankTFE = new QATFExtra("TFE", 2);
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("TRUE");
        expected.add("FALSE");

        ArrayList<String> ans;
        ans = bankTFE.getChoices("TFE", 2);
        assertEquals(expected,ans);
    }

    @Test
    /**
     * Test for getChoices("SA", 2)
     */
    public final void getChoices_2() {
        bankTFE = new QATFExtra("SA", 2);
        ArrayList<String> expected = new ArrayList<String>();

        ArrayList<String> ans;
        ans = bankTFE.getChoices("SA", 2);
        assertEquals(expected,ans);
    }

    @Test
    /**
     * Test for getChoices("TFE", 20): test for ID out of bound
     */
    public final void getChoices_3() {
        bankTFE = new QATFExtra("TFE", 20);
        ArrayList<String> expected = new ArrayList<String>();

        ArrayList<String> ans;
        ans = bankTFE.getChoices("TF", 20);
        assertEquals(expected,ans);
    }

    @Test
    /**
     * Test for getChoices("", 2): test for empty category
     */
    public final void getChoices_4() {
        bankTFE = new QATFExtra("", 20);
        ArrayList<String> expected = new ArrayList<String>();

        ArrayList<String> ans;
        ans = bankTFE.getChoices("", 20);
        assertEquals(expected,ans);
    }

    @Test
    /**
     * Test for getChoices("TFE", -2): test for ID is negative
     */
    public final void getChoices_5() {
        bankTFE = new QATFExtra("TFE", -2);
        ArrayList<String> expected = new ArrayList<String>();

        ArrayList<String> ans;
        ans = bankTFE.getChoices("TFE", -2);
        assertEquals(expected,ans);
    }

    @Test
    /**
     * Test for getChoices("TFE", 0): test for ID is 0
     */
    public final void getChoices_6() {
        bankTFE = new QATFExtra("TFE", 0);
        ArrayList<String> expected = new ArrayList<String>();

        ArrayList<String> ans;
        ans = bankTFE.getChoices("TFE", 0);
        assertEquals(expected,ans);
    }

    /**
     * Test for getChoices("", 20): test for empty category, and
     * ID is out of bound
     */
    @Test
    public final void getChoices_7() {
        bankTFE = new QATFExtra("", 20);
        ArrayList<String> expected = new ArrayList<String>();

        ArrayList<String> ans;
        ans = bankTFE.getChoices("", 20);
        assertEquals(expected,ans);
    }

    /**
     * Test for getChoices("SA", 20): test for different, and
     * ID is out of bound
     */
    @Test
    public final void getChoices_8() {
        bankTFE = new QATFExtra("SA", 20);
        ArrayList<String> expected = new ArrayList<String>();

        ArrayList<String> ans;
        ans = bankTFE.getChoices("SA", 20);
        assertEquals(expected,ans);
    }


}


