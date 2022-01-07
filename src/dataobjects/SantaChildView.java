package dataobjects;

import database.initialData;
import dataobjects.Child;
import enums.ChildStage;

import java.util.List;

public class SantaChildView extends Child {
    /* Fields */
    ChildStage lifeStage = null;

    /* Constructor */
    public SantaChildView(Child child) {
        this.setId(child.getId());
        this.setAge(child.getAge());
        this.setCity(child.getCity());
        this.setFirstName(child.getFirstName());
        this.setGiftsPreferences(child.getGiftsPreferences());
        this.setNiceScore(child.getNiceScore());
        this.setLastName(child.getLastName());
        setLifeStage();
    }

    /* Methods */
    void setLifeStage() {
        if(this.getAge() < 5) {
            lifeStage = ChildStage.BABY;
        } else if(this.getAge() >= 5 && this.getAge() < 12) {
            lifeStage = ChildStage.KID;
        } else if(this.getAge() >= 12 && this.getAge() <= 18) {
            lifeStage = ChildStage.TEEN;
        } else {
            lifeStage = ChildStage.YOUNG_ADULT;
        }
    }
}
