package com.example.myapplication.Model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ReunionList {

    private static List<Reunion> mainList = new ArrayList<>();
    private static List<Reunion> filterList = new ArrayList<>(); ;


    public static List<Reunion> getMainList() {
        return mainList;
    }

    public static List<Reunion> getFilterList() { return filterList; }

    /**
     * Ajout d'une reunion a la liste des réunion
     * */
    public static void setMainList(Reunion reunion) {
        mainList.add(reunion);
    }


    /**
     * Parametrage du filtre par lieu
     * */

    public static void setFilterList(String lieu) {
        filterList = new ArrayList<>();
        for(int i=0; i < mainList.size(); i++){
            if(lieu.equals(mainList.get(i).getLieu())){
                filterList.add(mainList.get(i));
            }
        }
    }

    /**
     * Parametrage du filtre par date
     * */
    public static void setFilterList(int day, int month, int year) {
        filterList = new ArrayList<>();
        for(int i=0; i < mainList.size(); i++){
            if(day == mainList.get(i).getDay() && month == mainList.get(i).getMonth() && year == mainList.get(i).getYear()){
                filterList.add(mainList.get(i));
            }
        }
    }

    public static void deleteReunion(Reunion reunion){
        mainList.remove(reunion);
    }
}
