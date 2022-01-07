package database;

import database_interfaces.Observable;
import database_interfaces.Observer;
import dataobjects.SantaChildDatabase;
import dataobjects.AnnualChange;

import java.util.List;

/* Singleton class to manage the database from the json */
public class SantaDatabase implements Observable {

    public static SantaDatabase instance = null;

    /*
        For now, I will only use one observer.
        In the future, I can add more observers if needed, since the
        code is written this way, for more observers.
     */
    private static Observer my_observer = null;

    /* Fields */
    private Integer numberOfYears = 0;
    private Double santaBudget = 0d;
    private initialData startingData = null;
    private List<AnnualChange> annualChanges = null;
    private List<Observer> observers = null;

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
        notifyObservers();
    }

    public List<AnnualChange> getAnnualChanges() {
        return annualChanges;
    }

    public void setAnnualChanges(List<AnnualChange> annualChanges) {
        this.annualChanges = annualChanges;
        notifyObservers();
    }

    /* Observer methods */
    @Override
    public void addObserver(Observer new_observer) {
        observers.add(new_observer);
    }

    @Override
    public void removeObserver(Observer new_observer) {
        observers.remove(new_observer);
    }

    @Override
    public void notifyObservers() {
        if(observers != null && observers.isEmpty() == false) {
            for (var observer : observers) {
                observer.update(startingData.getChildrenList());
            }
        }
    }
}
