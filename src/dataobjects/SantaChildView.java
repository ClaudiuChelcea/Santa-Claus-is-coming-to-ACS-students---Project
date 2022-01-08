package dataobjects;

import database.SantaDatabase;
import database.initialData;
import dataobjects.Child;
import enums.Category;
import enums.ChildStage;
import helpers.Helper;

import java.net.NoRouteToHostException;
import java.util.ArrayList;
import java.util.List;

public class SantaChildView extends Child {
    /* Fields */
    private ChildStage lifeStage = null;
    private List<Double> istoricScoruriCumintenie = new ArrayList<>();
    private Double averageScore = 0d;
    private Double my_budget = 0d;

    /* Constructor */
    public SantaChildView(Child child) {
        if((Child) this != child) {
            /* Gets default fields */
            this.setId(child.getId());
            this.setAge(child.getAge());
            this.setCity(child.getCity());
            this.setFirstName(child.getFirstName());
            this.setGiftsPreferences(child.getGiftsPreferences());
            this.setNiceScore(child.getNiceScore());
            this.setLastName(child.getLastName());

            /* Applies new modifications */
            setLifeStage();
            if (this.getNiceScore() != 0)
                istoricScoruriCumintenie.add(this.getNiceScore());
            calculateAverageScore();
            calculateBudget();
        }
    }

    /* Methods */
    /* Decide what stage the child is in */
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

    /* Calculate averageScore */
    void calculateAverageScore() {
        if (this.lifeStage == ChildStage.BABY) {
            this.averageScore = 10d;
        } else if (this.lifeStage == ChildStage.KID) {
            Double sum_grades = 0d;
            for (Double grade : istoricScoruriCumintenie) {
                sum_grades += grade;
            }
            this.averageScore = sum_grades / istoricScoruriCumintenie.size();
        } else if (this.lifeStage == ChildStage.TEEN) {
            Double sum_grades = 0d;
            int counter = 1;
            int sum_ct = 0;
            for (int i = 0; i < istoricScoruriCumintenie.size(); ++i) {
                sum_grades = sum_grades + istoricScoruriCumintenie.get(i) * counter;
                sum_ct += counter;
                ++counter;
            }
            averageScore = sum_grades / sum_ct;
        } else { /* young adult */
            var tmp = this;
            SantaDatabase.getInstance().getStartingData().getChildrenList().remove(tmp);
            SantaChildDatabase.newChildList.remove(tmp);
        }
    }

    /* Calculate budget alocated to this child */
    void calculateBudget() {
        Double budgetUnit = SantaDatabase.getInstance().getSantaBudget() / SantaChildDatabase.getAverageScore();
        this.my_budget = averageScore * budgetUnit;
    }

    /* Getters and setters */
    public ChildStage getLifeStage() {
        return lifeStage;
    }

    public void setLifeStage(ChildStage lifeStage) {
        this.lifeStage = lifeStage;
    }

    public List<Double> getIstoricScoruriCumintenie() {
        return istoricScoruriCumintenie;
    }

    public Double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(Double averageScore) {
        this.averageScore = averageScore;
    }

    public Double getMy_budget() {
        return my_budget;
    }

    public void setMy_budget(Double my_budget) {
        this.my_budget = my_budget;
    }

    @Override
    public String toString() {
        String out = "Child id: " + this.getId() +
                " | Name: " + this.getLastName() + " " + this.getFirstName() + " " +
                " | Age: " + this.getAge() + " | City: " + Helper.getCityName(this.getCity()) + " | NiceScore: " + this.getNiceScore() + " | Gift Preferences: ";
        for(Category giftPreference: this.getGiftsPreferences()) {
            if(giftPreference == this.getGiftsPreferences().get(this.getGiftsPreferences().size() - 1))
                out = out + giftPreference;
            else
                out = out + giftPreference + ", ";
        }

        out += " | Life stage: " + this.lifeStage.toString() + " | ";
        out += "Istoric: ";
        for(var el : istoricScoruriCumintenie) {
            out = out + el + " ";
        }

        calculateAverageScore();
        out += "| Average score: " + averageScore;
        calculateBudget();
        out += " | Budget: " + my_budget + ".";

        return out;
    }
}
