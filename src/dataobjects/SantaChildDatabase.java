package dataobjects;

import database.initialData;
import database_interfaces.Observer;

import java.util.List;

public class SantaChildDatabase implements Observer {
    /* Fields */
    List<SantaChildView> newChildList = null;

    /* Constructor */
    public SantaChildDatabase(List<Child> children) {
        for(var child : children) {
            var new_child = new SantaChildView(child);
            newChildList.add(new_child);
        }
    }

    @Override
    public void update(List<Child> children) {
        newChildList = null;
        for(var child : children) {
            var new_child = new SantaChildView(child);
            newChildList.add(new_child);
        }
    }
}
