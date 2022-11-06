
import Model.QA;
import Model.QAMC;
import Model.QATF;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
	// An Testing

        /*QA bankQA = new QA();
        bankQA.createDB("Database_QA.db");
        bankQA.createTableMC();
        bankQA.createTableTF();
        bankQA.createTableSA();*/

       /* bankQA.connection();*/
       /* bankQA.insertTableMC();// completed tableMC
        bankQA.insertTableSA();*/ //completed tableSA
       /* bankQA.insertTableTF();*/
        /*QAMC bankMC = new QAMC();
        bankMC.getDataMC(2,"MC");
        bankMC.corrAnsAndRedchoice();
        System.out.println(bankMC.getMyQuesMC());
        System.out.println(bankMC.getMyCorrAnsMC());
        bankMC.printChoices();
        bankMC.printRedChoiceMC();*/

        //testQATF
        QATF bankTF = new QATF();
        bankTF.getDataMC(2, "TF");




    }
}
