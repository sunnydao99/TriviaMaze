package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author: An Nguyen
 * @version: 11/2/2022
 *
 */

/**
 * Room class create random for ID and category
 */

public class Room implements Serializable {

    private static ArrayList<Integer> myIdList;
    private static ArrayList<String> myCategoryList;
    private ArrayList<String> myRooms;

    public Room() {
        myIdList = new ArrayList<Integer>();
        myCategoryList = new ArrayList<String>();
        QA bankQA = new QA();
        bankQA.connectionDB();

    }

    /**
     * randomID(): creates random ID, and return integer ID
     * @return: int
     */
    public static int randomID() {
        Random rand = new Random();
        int max = 12;
        int min = 1;
        int id = rand.nextInt(max + 1 - min) + min;
        return id;

    }

    /**
     * randomCategory(): This method creates random category, and return category
     * @return: String
     */
    public static String randomCategory() {
        Random rand = new Random();
        int temp = rand.nextInt(3);
        String cate = "";
        if (temp == 0) {
            cate = "MC";
        } else if (temp == 1) {
            cate = "TF";
        } else {
            cate = "SA";
        }
        return cate;
    }

    /**
     * randomIDCategory(): This method randoms both ID and category to display data for Rooms and then store ArrayList
     */
    public static void randomIDCategory() {
        int tempId;
        String tempCate;

        if(myIdList == null || myCategoryList == null) {
            myIdList = new ArrayList<Integer>();
            myCategoryList = new ArrayList<String>();
        }

        do {
            tempCate = randomCategory();
            tempId = randomID();

        } while (checkIDCate(tempCate, tempId));

        myCategoryList.add(tempCate);
        myIdList.add(tempId);
    }

    /**
     * getLastID(): This method gets last ID after random
     * @return: int
     */
    public static int getLastID() {
        return myIdList.get(myIdList.size() - 1);
    }

    /**
     * getLastCategory(): This method gets last category after random
     * @return: String
     */
    public static String getLastCategory() {
        return myCategoryList.get(myCategoryList.size() - 1);
    }

    /**
     * checkIDCate(String, int): check ID and category,
     * if they are correct return true, otherwise false
     * @param cate: category
     * @param id: Id
     * @return: boolean
     */
    public static boolean checkIDCate(String cate, int id) {
        int myid;
        String mycate;

        for (int i = 0; i < myCategoryList.size(); i++) {
            mycate = myCategoryList.get(i);
            myid = myIdList.get(i);
            if (mycate.equals(cate) && myid == id) {
                return true;
            }
        }
        return false;
    }


}
