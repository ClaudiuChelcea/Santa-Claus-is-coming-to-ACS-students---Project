package inputoutput;

import database.SantaDatabase;
import database.initialData;
import dataobjects.AnnualChange;
import dataobjects.Child;
import dataobjects.ChildUpdate;
import dataobjects.Gift;
import enums.Category;
import enums.Cities;
import helpers.Helper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class InfoReaderWriter {
    /* Constructor */
    public InfoReaderWriter() {}

    /* Methods */
    public void readInfo(SantaDatabase database, String inputFile) {

        JSONParser jsonParser = new JSONParser();

        try {
            FileReader reader = new FileReader(inputFile);

            /* Hold the content of the JSON file in an object */
            Object obj = jsonParser.parse(reader);

            /* Get components */
            JSONObject jsonObj = (JSONObject) obj;

            /* Get number of years */
            long output = (long) jsonObj.get("numberOfYears");
            Integer numberOfYears = (int) output;
            database.setNumberOfYears(numberOfYears);

            /* Get Santa`s budget */
            output = (long) jsonObj.get("santaBudget");
            double santaBudget = (double) output;
            database.setSantaBudget(santaBudget);

            /* Get initial data */
            initialData startingData = new initialData();
            JSONObject initialData = (JSONObject) jsonObj.get("initialData");

            /* Children */
            JSONArray children = (JSONArray) initialData.get("children");
            List<Child> childList = new ArrayList<>();
            for (Object child : children) {
                /* Get each child */
                JSONObject my_child = (JSONObject) child;
                // .out.println(my_child);

                /* Get id */
                output = (long) my_child.get("id");
                Integer id = (int) output;

                /* Get first name and last name */
                String lastName = (String) my_child.get("lastName");
                String firstName = (String) my_child.get("firstName");

                /* Get age */
                output = (long) my_child.get("age");
                Integer age = (int) output;

                /* Get city */
                Cities city = Helper.getCity((String) my_child.get("city"));

                /* Get nice score */
                output = (long) my_child.get("niceScore");
                double niceScore = (double) output;

                /* Get gift preferences */
                List<Category> giftPreferences = new ArrayList<>();
                JSONArray giftArray = (JSONArray) my_child.get("giftsPreferences");
                for (var item : giftArray) {
                    giftPreferences.add(Helper.getCategory((String) item));
                }

                /* Add the child to the list */
                childList.add(new Child.Builder(id, lastName, firstName, age, city, niceScore, giftPreferences).build());
            }

            startingData.setChildrenList(childList);

            /* Gift list */
            JSONArray giftsArray = (JSONArray) initialData.get("santaGiftsList");
            List<Gift> giftList = new ArrayList<>();
            for (Object o : giftsArray) {
                /* Get each gift */
                JSONObject my_gift = (JSONObject) o;

                /* Get product name */
                String product_name = (String) my_gift.get("productName");

                /* Get price */
                output = (long) my_gift.get("price");
                double price = (double) output;

                /* Get category */
                Category category = Helper.getCategory((String) my_gift.get("category"));

                /* Add the gift to the list */
                giftList.add(new Gift(product_name, price, category));
            }

            startingData.setGiftsList(giftList);

            /* Set initial data */
            database.setStartingData(startingData);

            /* Get annual changes */
            JSONArray changes = (JSONArray) jsonObj.get("annualChanges");
            List<AnnualChange> annualChanges = new ArrayList<>();

            /* Get each change */
            for (Object change : changes) {

                /* Get each change */
                JSONObject my_change = (JSONObject) change;

                /* Get new budget */
                output = (long) my_change.get("newSantaBudget");
                double newSantaBudget = (double) output;

                /* Get new gifts */
                giftsArray = (JSONArray) my_change.get("newGifts");
                giftList = new ArrayList<>();
                for (Object o : giftsArray) {
                    /* Get each gift */
                    JSONObject my_gift = (JSONObject) o;

                    /* Get product name */
                    String product_name = (String) my_gift.get("productName");

                    /* Get price */
                    output = (long) my_gift.get("price");
                    double price = (double) output;

                    /* Get category */
                    Category category = Helper.getCategory((String) my_gift.get("category"));

                    /* Add the gift to the list */
                    giftList.add(new Gift(product_name, price, category));
                }

                /* Get new children */
                children = (JSONArray) my_change.get("newChildren");
                childList = new ArrayList<>();
                for (Object child : children) {
                    /* Get each child */
                    JSONObject my_child = (JSONObject) child;

                    /* Get id */
                    output = (long) my_child.get("id");
                    Integer id = (int) output;

                    /* Get first name and last name */
                    String lastName = (String) my_child.get("lastName");
                    String firstName = (String) my_child.get("firstName");

                    /* Get age */
                    output = (long) my_child.get("age");
                    Integer age = (int) output;

                    /* Get city */
                    Cities city = Helper.getCity((String) my_child.get("city"));

                    /* Get nice score */
                    output = (long) my_child.get("niceScore");
                    var tmp_obj = my_child.get("niceScore");
                    if (tmp_obj == null)
                        continue;
                    double niceScore = (double) output;

                    /* Get gift preferences */
                    List<Category> giftPreferences = new ArrayList<>();
                    JSONArray giftArray = (JSONArray) my_child.get("giftsPreferences");
                    for (var item : giftArray) {
                        giftPreferences.add(Helper.getCategory((String) item));
                    }

                    /* Add the child to the list */
                    childList.add(new Child.Builder(id, lastName, firstName, age, city, niceScore, giftPreferences).build());
                }

                /* Get new updates */
                JSONArray updates = (JSONArray) my_change.get("childrenUpdates");
                List<ChildUpdate> updateList = new ArrayList<>();

                for (Object update : updates) {
                    /* Get each update */
                    JSONObject my_update = (JSONObject) update;

                    /* Get id */
                    var tmp = my_update.get("id");
                    if (tmp == null)
                        continue;
                    output = (long) my_update.get("id");
                    int id = (int) output;

                    /* Get new nice score */
                    var tmp_obj = my_update.get("niceScore");
                    if (tmp_obj == null)
                        continue;
                    output = (long) my_update.get("niceScore");
                    double niceScore = (double) output;

                    /* Get new list preferences */
                    List<Category> newPreferences = new ArrayList<>();
                    JSONArray myPreferencesArray = (JSONArray) my_update.get("giftsPreferences");

                    for (var item : myPreferencesArray) {
                        newPreferences.add(Helper.getCategory((String) item));
                    }
                    updateList.add(new ChildUpdate(id, niceScore, newPreferences));
                }

                /* Add the new update to the list of updates */
                annualChanges.add(new AnnualChange(newSantaBudget, giftList, childList, updateList));
            }

            /* Apply the modification */
            database.setAnnualChanges(annualChanges);

        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void writeInfo(SantaDatabase database, String outputFile) {

        /* The big box */
        JSONObject obj = new JSONObject();

        /* Array of arrays of children */
        JSONArray annualChildren = new JSONArray();

        for(var list_of_children : SantaDatabase.anual_childs) {

            /* Create object for each year */
            JSONObject year = new JSONObject();

            /* Create children array */
            JSONArray children = new JSONArray();

            /* Add childs */
            // System.out.println("=========== NEW GENERATION ===============");
            for (var child : list_of_children) {
               //  System.out.println(child);
                // System.out.println(child.getBudget());
                var final_budget = child.getBudget();
                JSONObject child_json = new JSONObject();

                /* Add fields */
                child_json.put("id", child.getId());
                child_json.put("lastName", child.getLastName());
                child_json.put("firstName", child.getFirstName());
                child_json.put("city", Helper.getCityName(child.getCity()));
                child_json.put("age", child.getAge());

                JSONArray giftsPreferences = new JSONArray();
                for (var preferences : child.getGiftsPreferences()) {
                    giftsPreferences.add(Helper.getCategoryName(preferences));
                }

                child_json.put("giftsPreferences", giftsPreferences);
                child_json.put("averageScore", child.getIndividualAverageScore());

                JSONArray niceScoreHistory = new JSONArray();
                for (var scoreHistory : child.getIstoricScoruriCumintenie()) {
                    niceScoreHistory.add(scoreHistory);
                }
                child_json.put("niceScoreHistory", niceScoreHistory);
                child_json.put("assignedBudget", final_budget);

                JSONArray receivedGifts = new JSONArray();

                for (var gift : child.getMy_gifts()) {
                    JSONObject simple_gift = new JSONObject();
                    simple_gift.put("productName", gift.getProductName());
                    simple_gift.put("price", gift.getPrice());
                    simple_gift.put("category", Helper.getCategoryName(gift.getGiftCategory()));
                    receivedGifts.add(simple_gift);
                }
                child_json.put("receivedGifts", receivedGifts);
                children.add(child_json);
            }

            /* Add the children list to the year */
            year.put("children", children);

            /* Adaugam array-ul year in array-ul annualChildren */
            annualChildren.add(year);
        }
        /* Adaugam array-ul annualChildren ca singura componenta a obiectului principal */
        obj.put("annualChildren", annualChildren);

        FileWriter file = null;
        try {
            /* Create file writer */
            file = new FileWriter(outputFile);
            file.write(obj.toJSONString());

            /* Close file */
            file.flush();
            file.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
