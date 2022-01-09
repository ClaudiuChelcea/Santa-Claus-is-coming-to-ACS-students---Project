package dataobjects;

import java.util.List;

public class AnnualChange {
    /* Fields */
    private Double newSantaBudget;
    private List<Gift> newGifts;
    private List<Child> newChildren;
    private List<ChildUpdate> childrenUpdates;

    /* Constructor */
    public AnnualChange(Double newSantaBudget, List<Gift> newGifts, List<Child> newChildren, List<ChildUpdate> childrenUpdates) {
        this.newSantaBudget = newSantaBudget;
        this.newGifts = newGifts;
        this.newChildren = newChildren;
        this.childrenUpdates = childrenUpdates;
    }

    public AnnualChange(AnnualChange new_annual_change) {
        this.newSantaBudget = new_annual_change.newSantaBudget;
        this.newGifts = new_annual_change.newGifts;
        this.newChildren = new_annual_change.newChildren;
        this.childrenUpdates = new_annual_change.childrenUpdates;
    }

    /* Getters & setters */
    public Double getNewSantaBudget() {
        return newSantaBudget;
    }

    public void setNewSantaBudget(Double newSantaBudget) {
        this.newSantaBudget = newSantaBudget;
    }

    public List<Gift> getNewGifts() {
        return newGifts;
    }

    public void setNewGifts(List<Gift> newGifts) {
        this.newGifts = newGifts;
    }

    public List<Child> getNewChildren() {
        return newChildren;
    }

    public void setNewChildren(List<Child> newChildren) {
        this.newChildren = newChildren;
    }

    public List<ChildUpdate> getChildrenUpdates() {
        return childrenUpdates;
    }

    public void setChildrenUpdates(List<ChildUpdate> childrenUpdates) {
        this.childrenUpdates = childrenUpdates;
    }

    @Override
    public String toString() {

        String out = "AnnualChange with new budget: " + newSantaBudget;
        for(var Gift: newGifts) {
            out = out + " " + Gift;
        }
        for(var Child : newChildren) {
            out += " " + Child;
        }

        for(var Update : childrenUpdates) {
            out += " " + Update;
        }

        return out;
    }
}
