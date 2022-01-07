package database_interfaces;

import database.initialData;
import dataobjects.Child;

import java.util.List;

public interface Observer {
    public void update(List<Child> children);
}
