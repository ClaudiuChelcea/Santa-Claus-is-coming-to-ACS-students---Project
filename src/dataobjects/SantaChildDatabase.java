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
            giveGiftToChild(el);
        }

        SantaDatabase.anual_childs.add(SantaDatabase.anual_childs.size(), newChildList);
    }

    @Override
    public void update(List<Child> children) {
        var oldChildList = newChildList;
        newChildList = new ArrayList<>();

        for(var child : children) {
            if(child.getAge() > 18)
                continue;
            var new_child = new SantaChildView(child);
            new_child.calculateBudget();
            new_child.setMy_gifts(getChildById(oldChildList, child.getId()).getMy_gifts());
            newChildList.add(new_child);
        }
    }

    private static SantaChildView getChildById(int id) {
        for(var child : newChildList) {
            if(child.getId() == id)
                return child;
        }

        return null;
    }

    public static double getGeneralAverageScore() {
        double averageScoreSum = 0;

        List<Integer> id_list = new ArrayList<>();
        for(var child : newChildList)
            id_list.add(child.getId());

        for(int i = 0; i < id_list.size(); ++i) {
            averageScoreSum += getChildById(id_list.get(i)).getIndividualAverageScore();
        }

        return averageScoreSum;
    }

    private static void giveGiftToChild(SantaChildView child) {
        ArrayList<Gift> mygifts = new ArrayList<>();
        var preferences = child.getGiftsPreferences();
        var our_gifts = SantaDatabase.getInstance().getStartingData().getGiftsList();
        var budget = child.getMy_budget();

        for (var preference : preferences) {
            ArrayList<Integer> gift_with_indexes = new ArrayList<>();
            int ind = 0;
            for (var gift : our_gifts) {
                if (preference == gift.getGiftCategory()) {
                    gift_with_indexes.add(ind);
                }
                ind++;
            }

            /* No gift in that category */
            if (gift_with_indexes.size() == 0) {
                continue;
            }

            /* Only one gift in that category */
            else if (gift_with_indexes.size() == 1) {
                if (budget >= our_gifts.get(gift_with_indexes.get(0)).getPrice()) {
                    budget -= our_gifts.get(gift_with_indexes.get(0)).getPrice();
                    mygifts.add(our_gifts.get(gift_with_indexes.get(0)));
                }
            }

            /* More gifts in that category */
            else {
                List<Gift> list_of_gifts = new ArrayList<>();
                for (int k = 0; k < gift_with_indexes.size(); ++k) {
                    list_of_gifts.add(our_gifts.get(gift_with_indexes.get(k)));
                }

                Double min_price = Double.MAX_VALUE;
                for (var gift : list_of_gifts) {
                    if (min_price > gift.getPrice()) {
                        min_price = gift.getPrice();
                    }
                }

                for (var gift : list_of_gifts) {
                    if (budget >= min_price && min_price == gift.getPrice()) {
                        mygifts.add(gift);
                        budget -= min_price;
                        break;
                    }
                }
            }
        }
        child.setMy_gifts(mygifts);
    }

    public static void giveGifts() {
        for(var child : newChildList) {
            giveGiftToChild(child);
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
    }

    private static SantaChildView getChildById(List<SantaChildView> list, int id) {
        for(var el : list) {
            if(el.getId() == id)
                return el;
        }
        return null;
    }

    public static void executeUpdate(int index) {
        SantaChildDatabase.increaseAge();

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
        var old_list = newChildList;
        newChildList = new ArrayList<>();
        for(var child : SantaDatabase.getInstance().getStartingData().getChildrenList()) {
            var old_child = getChildById(old_list, child.getId());
            if(old_child == null)
                continue;
            var new_child = new SantaChildView(child);

            var old_history = old_child.getNiceScoreHistory();
            new_child.calculateBudget();
            new_child.setMy_gifts(old_child.getMy_gifts());
            new_child.setNiceScoreHistory(old_history);
            newChildList.add(new_child);
        }

        /* Apply children updates */
        for(var update : newChange.getChildrenUpdates()) {
            var child = getChildById(update.getId());
            if(child == null)
                continue;
            if(update.getNiceScore() != null)
                child.getNiceScoreHistory().add(update.getNiceScore());

                for(var new_preference : update.getGiftsPreferences()) {
                    if(child.getGiftsPreferences().contains(new_preference));
                        child.getGiftsPreferences().remove(new_preference);
                }
                for(int i = update.getGiftsPreferences().size() - 1; i >=0; --i) {
                    child.getGiftsPreferences().add(0, update.getGiftsPreferences().get(i));
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

        for(var child : newChildList)
            giveGiftToChild(child);
        SantaDatabase.anual_childs.add(newChildList);
    }
}
