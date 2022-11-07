
import Model.QA;
import Model.QAMC;
import Model.QASA;
import Model.QATF;

import java.io.FileNotFoundException;

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
       /* bankQA.insertTableMC();*/// completed tableMC
        /*bankQA.insertTableSA();*///completed tableSA
       /* bankQA.insertTableTF();*/
        int id;
        String cate = bankQA.randomCategory();

        System.out.println("before: "+ " - " + cate);
        if (cate.equals("MC")){
            QAMC testMC = new QAMC();
            id = bankQA.randomID();
            System.out.println(id + " - " + cate);
            System.out.println("Question: ");
            testMC.getQuestionMC(id, cate);
        }
        else if(cate.equals("TF")){
            QATF testTF = new QATF();
            id = bankQA.randomID();
            System.out.println(id + " - " + cate);
            System.out.println("Question: ");
            testTF.getQuestionTF(id, cate);
        }
        else {
            QASA testSA = new QASA();
            id = bankQA.randomID();
            System.out.println(id + " - " + cate);
            System.out.println("Question: ");
            testSA.getQuestionSA(id, cate);
        }


        //Test QAMC
        /*QAMC bankMC = new QAMC();
        System.out.println(bankMC.getQuestionMC(2, "MC"));
        System.out.println(bankMC.getCorrAnsMC(2, "MC"));*/
        /*bankMC.getArrChoicesMC(2, "MC");
        System.out.println("print Array Choice:");
        bankMC.printChoices();
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
        QASA bankSA = new QASA();
        System.out.println(bankSA.getQuestionSA(2, "SA"));
        System.out.println(bankSA.getCorrAnsSA(2, "SA"));
        System.out.println(bankSA.getHintSA(2, "SA"));

    }
}
