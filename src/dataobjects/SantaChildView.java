package dataobjects;

import database.SantaDatabase;
import database.initialData;
import dataobjects.Child;
import enums.ChildStage;

import java.net.NoRouteToHostException;
import java.util.ArrayList;
import java.util.List;

public class SantaChildView extends Child {
    /* Fields */
    private ChildStage lifeStage = null;
    private static List<Double> istoricScoruriCumintenie = new ArrayList<>();
    private Double averageScore = 0d;
    private Double my_budget = 0d;

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
        istoricScoruriCumintenie.add(this.getNiceScore());
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
            SantaChildDatabase.newChildList.remove(this);
            SantaDatabase.getInstance().getStartingData().getChildrenList().remove(this);
        }
    }

    /* Calculate budget alocated to this child */
    void calculateBudget() {
        Double budgetUnit = SantaDatabase.getInstance().getSantaBudget() / SantaChildDatabase.getAverageScore();
        this.my_budget = averageScore * budgetUnit;
    }
}
