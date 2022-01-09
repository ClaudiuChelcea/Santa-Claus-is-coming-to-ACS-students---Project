package database_interfaces;

public interface Observable {
    void addObserver(Observer new_observer);
    void notifyObservers();
}
