import Model.Inventory;

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println("hello");
        Inventory r1Model = new Inventory();
        r1Model.createDB("Database_QA.db");
    }
}
