
import Model.QA;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
	// An Testing

        QA bankQA = new QA();
        bankQA.createDB("Database_QA.db");
        bankQA.createTableMC();
        bankQA.createTableTF();
        bankQA.createTableSA();

        bankQA.connection();
       /* bankQA.insertTableMC();// completed tableMC
        bankQA.insertTableSA(); //completed tableSA
        bankQA.insertTableTF();*/


    }
}
