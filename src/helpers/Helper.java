package helpers;

import database.SantaDatabase;
import dataobjects.AnnualChange;
import dataobjects.Child;
import dataobjects.Gift;
import enums.Category;
import enums.Cities;
import jdk.swing.interop.SwingInterOpUtils;

public class Helper {
    public static Cities getCity(String city_name) {
        switch(city_name) {
            case "Bucuresti":
                return Cities.BUCURESTI;
            case "Constanta":
                return Cities.CONSTANTA;
            case "Braila":
                return Cities.BRAILA;
            case "Brasov":
                return Cities.BRASOV;
            case "Buzau":
                return Cities.BUZAU;
            case "Cluj":
                return Cities.CLUJ;
            case "Craiova":
                return Cities.CRAIOVA;
            case "Iasi":
                return Cities.IASI;
            case "Oradea":
                return Cities.ORADEA;
            case "Timisoara":
                return Cities.TIMISOARA;
            default:
                return null;
        }
    }

    public static String getCityName(Cities city_name) {
        switch(city_name) {
            case BUCURESTI:
                return "Bucuresti";
            case CONSTANTA:
                return "Constanta";
            case BRAILA:
                return "Braila";
            case BRASOV:
                return "Brasov";
            case BUZAU:
                return "Buzau";
            case CLUJ:
                return "Cluj";
            case CRAIOVA:
                return "Craiova";
            case IASI:
                return "Iasi";
            case ORADEA:
                return "Oradea";
            case TIMISOARA:
                return "Timisoara";
            default:
                return null;
        }
    }

    public static Category getCategory(String category) {
        switch (category) {
            case "Board Games":
                return Category.BOARD_GAMES;
            case "Books":
                return Category.BOOKS;
            case "Clothes":
                return Category.CLOTHES;
            case "Sweets":
                return Category.SWEETS;
            case "Technology":
                return Category.TECHNOLOGY;
            case "Toys":
                return Category.TOYS;
            default:
                return null;
        }
    }

    public static void printDatabase(SantaDatabase database) {
        System.out.printf("\n\n========================== DEBUG ==================================\n\n");
        System.out.println("Number of years: " + database.getNumberOfYears());
        System.out.println("SantaBudget: " + database.getSantaBudget());
        System.out.println("Initial data:");
        for(Child my_child : database.getStartingData().getChildrenList()) {
            System.out.println(my_child);
        }
        for(Gift my_gift : database.getStartingData().getGiftsList()) {
            System.out.println(my_gift);
        }
        if(database.getAnnualChanges() != null) {
            for (AnnualChange my_change : database.getAnnualChanges()) {
                System.out.println(my_change);
            }
        }

        System.out.printf("\n\n========================== DEBUG ==================================\n\n");
    }
}
