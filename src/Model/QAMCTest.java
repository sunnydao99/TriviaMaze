package Model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class QAMCTest {

    @Test
    public final void getQuestion_0() {
        QAMC bankMC = new QAMC("MC", 1);
        bankMC.connection();
        String expected = "int x= 1; int y = 2; int z= x+y; what print out of z?";

        String question = "";
        question = bankMC.getQuestion("MC", 1);
        assertEquals(expected,question);
    }

    @Test
    public final void getQuestion_1() {
        QAMC bankMC = new QAMC("MC", 2);
        bankMC.connection();
        String expected = "what statement does print out ?";

        String question = "";
        question = bankMC.getQuestion("MC", 2);
        assertEquals(expected,question);
    }

    @Test
    public final void getQuestion_2() {
        QAMC bankMC = new QAMC("TF", 2);
        bankMC.connection();
        String expected = "";

        String question = "";
        question = bankMC.getQuestion("TF", 2);
        assertEquals("",question);
    }

    @Test
    public final void getQuestion_3() {
        QAMC bankMC = new QAMC("MC", 20);
        bankMC.connection();
        String expected = "";

        String question = "";
        question = bankMC.getQuestion("MC", 20);
        assertEquals("",question);
    }

    @Test
    public final void getQuestion_4() {
        QAMC bankMC = new QAMC("", 2);
        bankMC.connection();
        String expected = "";

        String question = "";
        question = bankMC.getQuestion("", 2);
        assertEquals("",question);
    }

    @Test
    public final void getQuestion_5() {
        QAMC bankMC = new QAMC("", -2);
        bankMC.connection();
        String expected = "";

        String question = "";
        question = bankMC.getQuestion("", -2);
        assertEquals("",question);
    }

    @Test
    public final void getAnswer_0() {
        QAMC bankMC = new QAMC("MC", 1);
        bankMC.connection();
        String expected = "B";

        String ans = "";
        ans = bankMC.getAnswer("MC", 1);
        assertEquals(expected,ans);
    }

    @Test
    public final void getAnswer_1() {
        QAMC bankMC = new QAMC("MC", 2);
        bankMC.connection();
        String expected = "C";

        String ans = "";
        ans = bankMC.getAnswer("MC", 2);
        assertEquals(expected,ans);
    }

    @Test
    public final void getAnswer_2() {
        QAMC bankMC = new QAMC("TF", 2);
        bankMC.connection();
        String expected = "";

        String ans = "";
        ans = bankMC.getAnswer("TF", 2);
        assertEquals(expected,ans);
    }

    @Test
    public final void getAnswer_3() {
        QAMC bankMC = new QAMC("MC", 20);
        bankMC.connection();
        String expected = "";

        String ans = "";
        ans = bankMC.getAnswer("MC", 20);
        assertEquals(expected,ans);
    }

    @Test
    public final void getAnswer_4() {
        QAMC bankMC = new QAMC("", -2);
        bankMC.connection();
        String expected = "";

        String ans = "";
        ans = bankMC.getAnswer("", -2);
        assertEquals(expected,ans);
    }

    @Test
    public final void getAnswer_5() {
        QAMC bankMC = new QAMC("MC", -2);
        bankMC.connection();
        String expected = "";

        String ans = "";
        ans = bankMC.getAnswer("MC", -2);
        assertEquals(expected,ans);
    }



    @Test
    public final void getChoices_0() {
        QAMC bankMC = new QAMC("MC", 1);
        bankMC.connection();
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("2");
        expected.add("3");
        expected.add("4");
        expected.add("5");

        ArrayList<String> ans;
        ans = bankMC.getChoices("MC", 1);
        assertEquals(expected,ans);
    }

    @Test
    public final void getChoices_1() {
        QAMC bankMC = new QAMC("MC", 2);
        bankMC.connection();
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("Sytem.in");
        expected.add("print out");
        expected.add("System.out.println() ");
        expected.add("consle.out");

        ArrayList<String> ans;
        ans = bankMC.getChoices("MC", 2);
        assertEquals(expected,ans);
    }

    @Test
    public final void getChoices_2() {
        QAMC bankMC = new QAMC("TF", 2);
        bankMC.connection();
        ArrayList<String> expected = new ArrayList<String>();

        ArrayList<String> ans;
        ans = bankMC.getChoices("TF", 2);
        assertEquals(expected,ans);
    }

    @Test
    public final void getChoices_3() {
        QAMC bankMC = new QAMC("TF", 20);
        bankMC.connection();
        ArrayList<String> expected = new ArrayList<String>();

        ArrayList<String> ans;
        ans = bankMC.getChoices("TF", 20);
        assertEquals(expected,ans);
    }

    @Test
    public final void getChoices_4() {
        QAMC bankMC = new QAMC("", 20);
        bankMC.connection();
        ArrayList<String> expected = new ArrayList<String>();

        ArrayList<String> ans;
        ans = bankMC.getChoices("", 20);
        assertEquals(expected,ans);
    }

    @Test
    public final void getChoices_5() {
        QAMC bankMC = new QAMC("MC", -2);
        bankMC.connection();
        ArrayList<String> expected = new ArrayList<String>();

        ArrayList<String> ans;
        ans = bankMC.getChoices("MC", -2);
        assertEquals(expected,ans);
    }

}