package Model;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class QATFTest {

    @Test
    public final void getQuestion_0() {
        QATF bankTF = new QATF("TF", 1);
        bankTF.connection();
        String expected = '"' + "System.in is the input stream connected to the console," + '"';

        String question = "";
        question = bankTF.getQuestion("TF", 1);
        assertEquals(expected,question);
    }

    @Test
    public final void getQuestion_1() {
        QATF bankTF = new QATF("TF", 2);
        bankTF.connection();
        String expected = "String is primitive data type";

        String question = "";
        question = bankTF.getQuestion("TF", 2);
        assertEquals(expected,question);
    }

    @Test
    public final void getQuestion_2() {
        QATF bankTF = new QATF("TF", 3);
        bankTF.connection();
        String expected = "Real is Java primitive data type ";

        String question = "";
        question = bankTF.getQuestion("TF", 3);
        assertEquals(expected,question);
    }
    @Test
    public final void getQuestion_3() {
        QATF bankTF = new QATF("TF", 20);
        bankTF.connection();
        String expected = "";

        String question = "";
        question = bankTF.getQuestion("TF", 20);
        assertEquals("",question);
    }

    @Test
    public final void getQuestion_4() {
        QATF bankTF = new QATF("", 4);
        bankTF.connection();
        String expected = "Is 6/2 * 3 >= 7 ?";

        String question = "";
        question = bankTF.getQuestion("", 4);
        assertEquals("",question);
    }

    @Test
    public final void getQuestion_5() {
        QATF bankTF = new QATF("", -2);
        bankTF.connection();
        String expected = "";

        String question = "";
        question = bankTF.getQuestion("", -2);
        assertEquals("",question);
    }

    @Test
    public final void getAnswer_0() {
        QATF bankTF = new QATF("TF", 1);
        bankTF.connection();
        String expected = "TRUE";

        String ans = "";
        ans = bankTF.getAnswer("TF", 1);
        assertEquals(expected,ans);
    }

    @Test
    public final void getAnswer_1() {
        QATF bankTF = new QATF("TF", 2);
        bankTF.connection();
        String expected = "FALSE";

        String ans = "";
        ans = bankTF.getAnswer("TF", 2);
        assertEquals(expected,ans);
    }

    @Test
    public final void getAnswer_2() {
        QATF bankTF = new QATF("SA", 2);
        bankTF.connection();
        String expected = "";

        String ans = "";
        ans = bankTF.getAnswer("SA", 2);
        assertEquals(expected,ans);
    }

    @Test
    public final void getAnswer_3() {
        QATF bankTF = new QATF("TF", 20);
        bankTF.connection();
        String expected = "";

        String ans = "";
        ans = bankTF.getAnswer("TF", 20);
        assertEquals(expected,ans);
    }

    @Test
    public final void getAnswer_4() {
        QATF bankTF = new QATF("", -2);
        bankTF.connection();
        String expected = "";

        String ans = "";
        ans = bankTF.getAnswer("", -2);
        assertEquals(expected,ans);
    }

    @Test
    public final void getAnswer_5() {
        QATF bankTF = new QATF("TF", -2);
        bankTF.connection();
        String expected = "";

        String ans = "";
        ans = bankTF.getAnswer("TF", -2);
        assertEquals(expected,ans);
    }



    @Test
    public final void getChoices_0() {
        QATF bankTF = new QATF("TF", 1);
        bankTF.connection();
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("TRUE");
        expected.add("FALSE");
        ArrayList<String> ans;
        ans = bankTF.getChoices("TF", 1);
        assertEquals(expected,ans);
    }

    @Test
    public final void getChoices_1() {
        QATF bankTF = new QATF("TF", 2);
        bankTF.connection();
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("TRUE");
        expected.add("FALSE");

        ArrayList<String> ans;
        ans = bankTF.getChoices("TF", 2);
        assertEquals(expected,ans);
    }

    @Test
    public final void getChoices_2() {
        QATF bankTF = new QATF("SA", 2);
        bankTF.connection();
        ArrayList<String> expected = new ArrayList<String>();

        ArrayList<String> ans;
        ans = bankTF.getChoices("SA", 2);
        assertEquals(expected,ans);
    }

    @Test
    public final void getChoices_3() {
        QATF bankTF = new QATF("TF", 20);
        bankTF.connection();
        ArrayList<String> expected = new ArrayList<String>();

        ArrayList<String> ans;
        ans = bankTF.getChoices("TF", 20);
        assertEquals(expected,ans);
    }

    @Test
    public final void getChoices_4() {
        QATF bankTF = new QATF("", 20);
        bankTF.connection();
        ArrayList<String> expected = new ArrayList<String>();

        ArrayList<String> ans;
        ans = bankTF.getChoices("", 20);
        assertEquals(expected,ans);
    }

    @Test
    public final void getChoices_5() {
        QATF bankTF = new QATF("TF", -2);
        bankTF.connection();
        ArrayList<String> expected = new ArrayList<String>();

        ArrayList<String> ans;
        ans = bankTF.getChoices("TF", -2);
        assertEquals(expected,ans);
    }

}


