package dataobjects;

import enums.Category;
import enums.Cities;

public class Child {
    /* Fields */
    private int id = 0;
    private String lastName = "";
    private String firstName = "";
    private int age = 0;
    private Cities city = null;
    private double niceScore = 0;
    private Category giftsPreferences = null;

    /* Builder pattern */
    public static class Builder {
        private int id = 0;
        private String lastName = "";
        private String firstName = "";
        private int age = 0;
        private Cities city = null;
        private double niceScore = 0;
        private Category giftsPreferences = null;

        public Builder(int id, String lastName, String firstName, int age, Cities city, double niceScore, Category giftsPreferences) {
            this.id = id;
            this.lastName = lastName;
            this.firstName = firstName;
            this.age = age;
            this.city = city;
            this.niceScore = niceScore;
            this.giftsPreferences = giftsPreferences;
        }

        public Builder idSet(int id) {
            this.id = id;
            return this;
        }

        public Builder lastNameSet(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder firstNameSet(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder ageSet(int age) {
            this.age = age;
            return this;
        }

        public Builder citySet(Cities city) {
            this.city = city;
            return this;
        }

        public Builder niceScoreSet(double niceScore) {
            this.niceScore = niceScore;
            return this;
        }

        public Builder giftsPreferencesSet(Category giftsPreferences) {
            this.giftsPreferences = giftsPreferences;
            return this;
        }

        public Child build() {
            return new Child(this);
        }
    }

    /* Create objects only from builder */
    private Child(Builder builder) {
        this.id = builder.id;
        this.lastName = builder.lastName;
        this.firstName = builder.firstName;
        this.age = builder.age;
        this.city = builder.city;
        this.niceScore = builder.niceScore;
        this.giftsPreferences = builder.giftsPreferences;
    }

    /* Getters and setters for extra modifications */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Cities getCity() {
        return city;
    }

    public void setCity(Cities city) {
        this.city = city;
    }

    public double getNiceScore() {
        return niceScore;
    }

    public void setNiceScore(double niceScore) {
        this.niceScore = niceScore;
    }

    public Category getGiftsPreferences() {
        return giftsPreferences;
    }

    public void setGiftsPreferences(Category giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
    }
}
