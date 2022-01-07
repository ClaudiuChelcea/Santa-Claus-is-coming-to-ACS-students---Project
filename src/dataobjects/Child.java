package dataobjects;

import enums.Category;
import enums.Cities;

import java.util.List;

public class Child {
    /* Fields */
    private int id = 0;
    private String lastName = "";
    private String firstName = "";
    private int age = 0;
    private Cities city = null;
    private double niceScore = 0;
    private List<Category> giftsPreferences = null;

    /* Builder pattern */
    public static class Builder {
        private Integer id = 0;
        private String lastName = "";
        private String firstName = "";
        private Integer age = 0;
        private Cities city = null;
        private Double niceScore = 0d;
        private List<Category> giftsPreferences = null;

        public Builder(Integer id, String lastName, String firstName, Integer age, Cities city, Double niceScore, List<Category> giftsPreferences) {
            this.id = id;
            this.lastName = lastName;
            this.firstName = firstName;
            this.age = age;
            this.city = city;
            this.niceScore = niceScore;
            this.giftsPreferences = giftsPreferences;
        }

        public Builder idSet(Integer id) {
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

        public Builder ageSet(Integer age) {
            this.age = age;
            return this;
        }

        public Builder citySet(Cities city) {
            this.city = city;
            return this;
        }

        public Builder niceScoreSet(Double niceScore) {
            this.niceScore = niceScore;
            return this;
        }

        public Builder giftsPreferencesSet(List<Category> giftsPreferences) {
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

    public void setId(Integer id) {
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

    public void setAge(Integer age) {
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

    public void setNiceScore(Double niceScore) {
        this.niceScore = niceScore;
    }

    public List<Category> getGiftsPreferences() {
        return giftsPreferences;
    }

    public void setGiftsPreferences(List<Category> giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
    }

    @Override
    public String toString() {
        String out = "Child id: " + id +
                " name: " + lastName + " " + firstName + " " +
                " age: " + age + " city: " + city + " niceScore: " + niceScore + " giftPreferences: ";
        for(Category giftPreference: giftsPreferences) {
            out = out + giftPreference + ", ";
        }

        return out;
    }

    /* Default constructor to be able to extend the class */
    public Child() {};
}
