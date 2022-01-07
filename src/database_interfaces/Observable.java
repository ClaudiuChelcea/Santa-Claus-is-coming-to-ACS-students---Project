package database_interfaces;

public interface Observable {
    public void addObserver(Observer new_observer);
    public void removeObserver(Observer new_observer);
    public void notifyObservers();
}
