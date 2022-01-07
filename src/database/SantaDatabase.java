package database;

import dataobjects.AnnualChange;
import dataobjects.Child;
import dataobjects.Gift;

import java.util.List;

/* Singleton class to manage the database from the json */
public class SantaDatabase {

    public static SantaDatabase instance = null;

    /* Fields */
    private Integer numberOfYears = 0;
    private Double santaBudget = 0d;
    private initialData startingData = null;
    private List<AnnualChange> annualChanges = null;

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
