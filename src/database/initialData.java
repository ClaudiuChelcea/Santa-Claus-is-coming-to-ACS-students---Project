package database;

import dataobjects.Child;
import dataobjects.Gift;
import enums.Cities;

import java.util.List;

/* Class to be delegated in the database */
public class initialData {
    /* Fields */
    private List<Child> childrenList = null;
    private List<Gift> giftsList = null;
    private List<Cities> citiesList = null;

    /* Constructor */
    public initialData() {}

    /* Getters and setters */
    public List<Child> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<Child> childrenList) {
        this.childrenList = childrenList;
    }

    public List<Gift> getGiftsList() {
        return giftsList;
    }

    public void setGiftsList(List<Gift> giftsList) {
        this.giftsList = giftsList;
    }

    public List<Cities> getCitiesList() {
        return citiesList;
    }

        public void setCitiesList(List<Cities> citiesList) {
        this.citiesList = citiesList;
    }
}
