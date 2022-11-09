
import Model.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
	// An Testing

        QA bankQA = new QA();
       /* bankQA.createDB("Database_QA.db");*/
      /*  bankQA.connection();*/
      /*  bankQA.createTableMC();*/
        /*bankQA.createTableTF();*/
      /*  bankQA.createTableSA();*/

        bankQA.connection();
     /*   bankQA.insertTableMC();// completed tableMC
        bankQA.insertTableSA();//completed tableSA
        bankQA.insertTableTF();
*/




       /* ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i=0; i<10; i++){
            list.add(i);
        }
        Collections.shuffle(list);
        for (int i=0; i<7; i++){
            System.out.println("idx: "+ i + "-"+list.get(i));

        }*/




        //Test QAMC
       /* QAMC bankMC = new QAMC();
        System.out.println(bankMC.getQuestionMC(2, "MC"));
        System.out.println(bankMC.getCorrAnsMC(2, "MC"));
        bankMC.getArrChoicesMC(2, "MC");
        System.out.println("print Array Choice:");
        bankMC.printChoicesMC();
        bankMC.getArrRedChoiceMC(2, "MC");
        System.out.println("print Array Reduce Choice:");
        bankMC.printRedChoiceMC();*/

        //testQATF
        /*QATF bankTF = new QATF();
        System.out.println(bankTF.getQuestionTF(3, "TF"));
        System.out.println(bankTF.getCorrAnsTF(3, "TF"));
        bankTF.getArrChoicesTF(3, "TF");
        System.out.print("print Arr choices TF: ");
        bankTF.printChoicesTF();*/

        //test QASA
       /* QASA bankSA = new QASA();
        System.out.println(bankSA.getQuestionSA(2, "SA"));
        System.out.println(bankSA.getCorrAnsSA(2, "SA"));
        System.out.println(bankSA.getHintSA(2, "SA"));*/

        //TestRoom

        /*room.printCatelist();
        room.printIDlist();*/

        /*for(int i = 0; i < id_list.size(); i++){
            System.out.print( id_list.get(i) + ", ");
        }
        System.out.println();*/
        QA bank = new QA();
        /*bank.getQues("MC", 2);
        bank.getAns("MC", 2);
        bank.getArrChoices("MC", 2);
        bank.getArrRedChoice("MC", 2);*/

        /*bank.getQues("TF", 2);
        bank.getAns("TF", 2);
        bank.getArrChoices("TF", 2);
        bank.getArrRedChoice("MC", 2);*/

        bank.getQues("SA", 2);
        bank.getAns("SA", 2);
        System.out.println(bank.getHintSA("SA", 2));
        /*bank.getArrChoices("TF", 2);
        bank.getArrRedChoice("MC", 2);*/



    }
}
