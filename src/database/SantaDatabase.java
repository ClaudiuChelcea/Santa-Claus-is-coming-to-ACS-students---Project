package database;

import database_interfaces.Observable;
import database_interfaces.Observer;
import dataobjects.AnnualChange;
import dataobjects.SantaChildView;

import java.util.ArrayList;
import java.util.List;

/* Singleton class to manage the database from the json */
public class SantaDatabase implements Observable {

    public static SantaDatabase instance = null;

    /* Fields */
    private Integer numberOfYears = 0;
    private Double santaBudget = 0d;
    private initialData startingData = null;
    private List<AnnualChange> annualChanges = null;
    private List<Observer> observers = new ArrayList<>();
    public static int updateNumber = 0;
    public static List<List<SantaChildView>> anual_childs = new ArrayList<>();

    /* Don't allow object creation */
    private SantaDatabase() {}

    /* Return the unique instance of the singleton */
    public static SantaDatabase getInstance() {
        if(instance == null) {
            instance = new SantaDatabase();
        }

        /* Return the current instance */
        return instance;
    }

    /* Getters and setters */
    public Integer getNumberOfYears() {
        return numberOfYears;
    }

    public void setNumberOfYears(Integer numberOfYears) {
        this.numberOfYears = numberOfYears;
    }

    public Double getSantaBudget() {
        return santaBudget;
    }

    public void setSantaBudget(Double santaBudget) {
        this.santaBudget = santaBudget;
    }

    public initialData getStartingData() {
        return startingData;
    }

    public void setStartingData(initialData startingData) {
        this.startingData = startingData;
    }

    public List<AnnualChange> getAnnualChanges() {
         return annualChanges;
    }

    public void setAnnualChanges(List<AnnualChange> annualChanges) {
        this.annualChanges = annualChanges;
    }

    public void clear() {
        numberOfYears = 0;
        santaBudget = 0d;
        startingData = null;
        annualChanges = null;
        observers = new ArrayList<>();
        updateNumber = 0;
        anual_childs = new ArrayList<>();
    }

    /* Observer methods */
    @Override
    public void addObserver(Observer new_observer) {
        /* Limit to only one obsever in this project step */
        if(observers.size() == 1) {
            observers.clear();
        }
        observers.add(new_observer);
        notifyObservers();
    }

    @Override
    public void notifyObservers() {
        if(observers != null && !observers.isEmpty()) {
            for (var observer : observers) {
                observer.update(startingData.getChildrenList());
            }
        }
    }
}
