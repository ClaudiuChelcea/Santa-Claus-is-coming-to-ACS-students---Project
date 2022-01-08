package dataobjects;

import database.SantaDatabase;
import database.initialData;
import database_interfaces.Observer;
import enums.Category;

import java.util.ArrayList;
import java.util.List;

public class SantaChildDatabase implements Observer {
    /* Fields */
    public static List<SantaChildView> newChildList = null;

    /* Constructor */
    public SantaChildDatabase(List<Child> children) {
        newChildList = new ArrayList<>();
        for(var child : children) {
            var new_child = new SantaChildView(child);
            newChildList.add(new_child);
        }
    }

    @Override
    public void update(List<Child> children) {
        newChildList = new ArrayList<>();
        for(var child : children) {
            var new_child = new SantaChildView(child);
            newChildList.add(new_child);
        }
    }

    static Double getAverageScore() {
        Double averageScoreSum = 0d;
        for(var child : newChildList) {
            averageScoreSum += child.getNiceScore();
        }
        return averageScoreSum / newChildList.size();
    }

    public static void giveGifts() {
        for(var child : newChildList) {
            var preferences = child.getGiftsPreferences();
            var our_gifts = SantaDatabase.getInstance().getStartingData().getGiftsList();
            var budget = SantaDatabase.getInstance().getSantaBudget();

            for(var preference : preferences) {
                ArrayList<Integer> gift_with_indexes = new ArrayList<>();
                int ind = 0;
                for(var gift : our_gifts) {
                    if(preference == gift.getGiftCategory()) {
                        gift_with_indexes.add(ind);
                    }
                    ind++;
                }

                /* No gift in that category */
                if(gift_with_indexes.size() == 0)
                    continue;

                /* Only one gift in that category */
                if(gift_with_indexes.size() == 1)
                    if(budget >= our_gifts.get(gift_with_indexes.get(0)).getPrice())
                        budget -= our_gifts.get(gift_with_indexes.get(0)).getPrice();
                        /* and allocate gift to him */

                /* More gifts in that category */
                else if(gift_with_indexes.size() > 1) {
                    List<Gift> list_of_gifts = new ArrayList<>();
                    for(int i = 0; i < gift_with_indexes.size(); ++i) {
                        list_of_gifts.add(our_gifts.get(gift_with_indexes.get(i)));
                    }

                    Double min_price = Double.MAX_VALUE;
                    for(var gift : list_of_gifts) {
                        if(min_price > gift.getPrice()) {
                            min_price = gift.getPrice();
                        }
                    }

                    if(budget >= min_price)
                        budget -= min_price;
                }
            }
        }
    }

    public static void increaseAge() {
        for(var child : newChildList) {
            int new_Age = child.getAge() + 1;
            if(new_Age > 18) {
                SantaDatabase.getInstance().getStartingData().getChildrenList().remove(child);
            }
        }
    }

    public static void executeUpdate() {
        AnnualChange newChange = new AnnualChange(SantaDatabase.getInstance().getAnnualChanges().get(SantaDatabase.updateNumber));

        /* Set new budget */
        SantaDatabase.getInstance().setSantaBudget(newChange.getNewSantaBudget());

        /* Add children */
        SantaDatabase.anual_childs.add(SantaDatabase.getInstance().getStartingData().getChildrenList());
        for(var newchild : newChange.getNewChildren()) {
            if(newchild.getAge() <= 18) {
                SantaDatabase.getInstance().getStartingData().getChildrenList().add(newchild);
            }
        }

        /* Apply children updates */
        for(var update : newChange.getChildrenUpdates()) {
            for(var child : SantaDatabase.getInstance().getStartingData().getChildrenList()) {
                if(child.getId() == update.getId()) {
                    if(update.getNiceScore() != null) {
                        child.setNiceScore(update.getNiceScore());

                        for(var new_preference : update.getGiftsPreferences()) {
                            if(child.getGiftsPreferences().contains(new_preference));
                                child.getGiftsPreferences().remove(new_preference);
                        }
                        for(int i = update.getGiftsPreferences().size() - 1; i >=0; --i) {
                            child.getGiftsPreferences().add(0, update.getGiftsPreferences().get(i));
                        }
                    }
                }
            }
        }

        /* Add new gifts */
        for(var new_gift : newChange.getNewGifts()) {
            if(SantaDatabase.getInstance().getStartingData().getGiftsList().contains(new_gift) == false)
                SantaDatabase.getInstance().getStartingData().getGiftsList().add(new_gift);
        }

        ++SantaDatabase.updateNumber;
    }

    public static void calculateAverageScore() {
        for(var child : newChildList) {
            child.calculateAverageScore();
            child.calculateBudget();
        }
    }
}
