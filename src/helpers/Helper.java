package helpers;

import enums.Cities;

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
}
