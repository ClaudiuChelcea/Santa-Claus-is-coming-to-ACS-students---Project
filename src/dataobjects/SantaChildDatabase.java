package dataobjects;

import database.SantaDatabase;
import database_interfaces.Observer;

import java.util.ArrayList;
import java.util.List;

public class SantaChildDatabase implements Observer {
    /* Fields */
    public static List<SantaChildView> newChildList = null;

    /* Constructor */
    public SantaChildDatabase(List<Child> children) {
        newChildList = new ArrayList<>();
        for(var child : children) {
            if(child.getAge() > 18)
                continue;
            var new_child = new SantaChildView(child);

            new_child.calculateBudget();
            newChildList.add(new_child);
        }

        for(var el : newChildList) {
            el.calculateBudget();
        }

        SantaDatabase.anual_childs.add(SantaDatabase.anual_childs.size(), newChildList);
    }

    @Override
    public void update(List<Child> children) {
        newChildList = new ArrayList<>();
        for(var child : children) {
            if(child.getAge() > 18)
                continue;
            var new_child = new SantaChildView(child);
            new_child.calculateBudget();
            newChildList.add(new_child);
        }
    }

    public static double getGeneralAverageScore() {
        double averageScoreSum = 0;
        for(var item : newChildList) {
            averageScoreSum += item.getIndividualAverageScore();
        }

        return averageScoreSum;
    }

    public static void giveGifts() {
        for(var child : newChildList) {
            var preferences = child.getGiftsPreferences();
            var our_gifts = SantaDatabase.getInstance().getStartingData().getGiftsList();
            child.calculateBudget();
            var budget = child.getMy_budget();

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
                    if(budget >= our_gifts.get(gift_with_indexes.get(0)).getPrice()) {
                        budget -= our_gifts.get(gift_with_indexes.get(0)).getPrice();
                        // System.out.println("Received " + our_gifts.get(gift_with_indexes.get(0)).getProductName());
                    }
                        /* and allocate gift to him */

                /* More gifts in that category */
                else if(gift_with_indexes.size() > 1) {
                    List<Gift> list_of_gifts = new ArrayList<>();
                    for(int i = 0; i < gift_with_indexes.size(); ++i) {
                        list_of_gifts.add(our_gifts.get(gift_with_indexes.get(i)));
                    }

                    Double min_price = Double.MAX_VALUE;
                    int min_price_index = 0;
                    for(var gift : list_of_gifts) {
                        if(min_price > gift.getPrice()) {
                            min_price = gift.getPrice();
                        }
                        min_price_index++;
                    }

                    if(budget >= min_price) {
                        budget -= min_price;
                        // System.out.println("Received " + our_gifts.get(gift_with_indexes.get(min_price_index)).getProductName());
                    }
                }
            }
        }
    }

    public static void increaseAge() {
        var iterator = SantaDatabase.getInstance().getStartingData().getChildrenList().iterator();
        while(iterator.hasNext()) {
            var tmp = iterator.next();
            int new_age = tmp.getAge() + 1;
            if (new_age > 18) {
                iterator.remove();
            } else {
                tmp.setAge(new_age);
            }
        }

//        var iterator2 = newChildList.iterator();
//        while(iterator2.hasNext()) {
//            var child = iterator2.next();
//            int new_Age = child.getAge() + 1;
//            if(new_Age > 18) {
//                iterator2.remove();
//            } else {
//                child.setAge(new_Age);
//            }
//        }
    }

    public static void executeUpdate(int index) {
        AnnualChange newChange = new AnnualChange(SantaDatabase.getInstance().getAnnualChanges().get(SantaDatabase.updateNumber));
        var base =  SantaDatabase.anual_childs;
        /* Set new budget */
        SantaDatabase.getInstance().setSantaBudget(newChange.getNewSantaBudget());

        /* Add children */
        for(var newchild : newChange.getNewChildren()) {
            if(newchild.getAge() <= 18) {
                var iterator = SantaDatabase.getInstance().getStartingData().getChildrenList().iterator();
                Boolean found = false;
                while (iterator.hasNext()) {
                    var tmp = iterator.next();
                    if (tmp.getId() == newchild.getId()) {
                        found = true;
                        break;
                    }
                }
                if (found == false) {
                    SantaDatabase.getInstance().getStartingData().getChildrenList().add(newchild);
                }
            }
        }

        /* Update the new list */
        newChildList = new ArrayList<>();
        for(var child : SantaDatabase.getInstance().getStartingData().getChildrenList()) {
            var new_child = new SantaChildView(child);
            new_child.calculateBudget();
            newChildList.add(new_child);
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

        /* Add new year step to the list */
        for(var el : newChildList) {
            el.calculateBudget();
        }

        SantaDatabase.anual_childs.add(newChildList);
    }
}
