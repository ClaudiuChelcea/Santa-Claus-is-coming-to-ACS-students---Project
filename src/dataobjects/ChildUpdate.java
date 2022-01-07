package dataobjects;

import enums.Category;

import java.util.List;

public class ChildUpdate {
    /* Fields */
    private Integer id = 0;
    private Double niceScore = 0d;
    private List<Category> giftsPreferences = null;

    /* Constructor */
    public ChildUpdate(Integer id, Double niceScore, List<Category> giftsPreferences) {
        this.id = id;
        this.niceScore = niceScore;
        this.giftsPreferences = giftsPreferences;
    }

    /* Getters & setters */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getNiceScore() {
        return niceScore;
    }

    public void setNiceScore(Double niceScore) {
        this.niceScore = niceScore;
    }

    public List<Category> getGiftsPreferences() {
        return giftsPreferences;
    }

    public void setGiftsPreferences(List<Category> giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
    }
}
