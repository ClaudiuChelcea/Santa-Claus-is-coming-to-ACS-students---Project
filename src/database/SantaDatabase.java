package database;

import dataobjects.Child;
import dataobjects.Gift;

import java.util.List;

/* Singleton class to manage the database from the json */
public class SantaDatabase {

    public static SantaDatabase instance = null;

    /* Fields */
    int numberOfYears = 0;
    double santaBudget = 0d;
    struct initialData {
        
    }
    List<Child> childrenList = null;
    List<Gift> giftsList = null;
    List<String> citiesList = null;

    /* Don't allow object creation */
    private SantaDatabase() {};

    /* Return the unique instance of the singleton */
    public static SantaDatabase getInstance() {
        if(instance == null) {
            instance = new SantaDatabase();
        }
        return instance;
    }
}
