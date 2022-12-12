package Model;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class QATFExtraTest {

    @Test
    public final void getQuestion_0() {
        QATFExtra bankTFE = new QATFExtra("TFE", 1);
        bankTFE.connection();
        String expected = "Classes in the same package are implicitly imported into the main " ;

        String question = "";
        question = bankTFE.getQuestion("TFE", 1);
        assertEquals(expected,question);
    }

    @Test
    public final void getQuestion_1() {
        QATFExtra bankTFE = new QATFExtra("TFE", 2);
        bankTFE.connection();
        String expected = "Instance variable can be declared anywhere inside the class ";

        String question = "";
        question = bankTFE.getQuestion("TFE", 2);
        assertEquals(expected,question);
    }

    @Test
    public final void getQuestion_2() {
        QATFExtra bankTFE = new QATFExtra("TFE", 3);
        bankTFE.connection();
        String expected = "Void methods must have at least one parameter.";

        String question = "";
        question = bankTFE.getQuestion("TFE", 3);
        assertEquals(expected,question);
    }
    @Test
    public final void getQuestion_3() {
        QATFExtra bankTFE = new QATFExtra("TFE", 20);
        bankTFE.connection();
        String expected = "";

        String question = "";
        question = bankTFE.getQuestion("TFE", 20);
        assertEquals("",question);
    }

    @Test
    public final void getQuestion_4() {
        QATFExtra bankTFE = new QATFExtra("", 4);
        bankTFE.connection();
        String expected = "Parentheses () would not be used to clarify a dangling-else.";

        String question = "";
        question = bankTFE.getQuestion("", 4);
        assertEquals("",question);
    }

    @Test
    public final void getQuestion_5() {
        QATFExtra bankTFE = new QATFExtra("", -2);
        bankTFE.connection();
        String expected = "";

        String question = "";
        question = bankTFE.getQuestion("", -2);
        assertEquals("",question);
    }

    @Test
    public final void getAnswer_0() {
        QATFExtra bankTFE = new QATFExtra("TFE", 1);
        bankTFE.connection();
        String expected = "FALSE ";

        String ans = "";
        ans = bankTFE.getAnswer("TFE", 1);
        assertEquals(expected,ans);
    }

    @Test
    public final void getAnswer_1() {
        QATFExtra bankTFE = new QATFExtra("TFE", 2);
        bankTFE.connection();
        String expected = "FALSE ";

        String ans = "";
        ans = bankTFE.getAnswer("TFE", 2);
        assertEquals(expected,ans);
    }

    @Test
    public final void getAnswer_2() {
        QATFExtra bankTFE = new QATFExtra("SA", 2);
        bankTFE.connection();
        String expected = "";

        String ans = "";
        ans = bankTFE.getAnswer("SA", 2);
        assertEquals(expected,ans);
    }

    @Test
    public final void getAnswer_3() {
        QATFExtra bankTFE = new QATFExtra("TFE", 20);
        bankTFE.connection();
        String expected = "";

        String ans = "";
        ans = bankTFE.getAnswer("TFE", 20);
        assertEquals(expected,ans);
    }

    @Test
    public final void getAnswer_4() {
        QATFExtra bankTFE = new QATFExtra("", -2);
        bankTFE.connection();
        String expected = "";

        String ans = "";
        ans = bankTFE.getAnswer("", -2);
        assertEquals(expected,ans);
    }

    @Test
    public final void getAnswer_5() {
        QATFExtra bankTFE = new QATFExtra("TFE", -2);
        bankTFE.connection();
        String expected = "";

        String ans = "";
        ans = bankTFE.getAnswer("TFE", -2);
        assertEquals(expected,ans);
    }



    @Test
    public final void getChoices_0() {
        QATFExtra bankTFE = new QATFExtra("TFE", 1);
        bankTFE.connection();
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("TRUE");
        expected.add("FALSE");
        ArrayList<String> ans;
        ans = bankTFE.getChoices("TFE", 1);
        assertEquals(expected,ans);
    }

    @Test
    public final void getChoices_1() {
        QATFExtra bankTFE = new QATFExtra("TFE", 2);
        bankTFE.connection();
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("TRUE");
        expected.add("FALSE");

        ArrayList<String> ans;
        ans = bankTFE.getChoices("TFE", 2);
        assertEquals(expected,ans);
    }

    @Test
    public final void getChoices_2() {
        QATFExtra bankTFE = new QATFExtra("SA", 2);
        bankTFE.connection();
        ArrayList<String> expected = new ArrayList<String>();

        ArrayList<String> ans;
        ans = bankTFE.getChoices("SA", 2);
        assertEquals(expected,ans);
    }

    @Test
    public final void getChoices_3() {
        QATFExtra bankTFE = new QATFExtra("TFE", 20);
        bankTFE.connection();
        ArrayList<String> expected = new ArrayList<String>();

        ArrayList<String> ans;
        ans = bankTFE.getChoices("TF", 20);
        assertEquals(expected,ans);
    }

    @Test
    public final void getChoices_4() {
        QATFExtra bankTFE = new QATFExtra("", 20);
        bankTFE.connection();
        ArrayList<String> expected = new ArrayList<String>();

        ArrayList<String> ans;
        ans = bankTFE.getChoices("", 20);
        assertEquals(expected,ans);
    }

    @Test
    public final void getChoices_5() {
        QATFExtra bankTFE = new QATFExtra("TFE", -2);
        bankTFE.connection();
        ArrayList<String> expected = new ArrayList<String>();

        ArrayList<String> ans;
        ans = bankTFE.getChoices("TFE", -2);
        assertEquals(expected,ans);
    }

}


