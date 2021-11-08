package com.example.myapplication.Model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ReunionList {

    private static final List<Reunion> mainList = new ArrayList<>();
    private static List<Reunion> filterList = new ArrayList<>();


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
            if(day == mainList.get(i).getStartMeeting().get(Calendar.DATE) && month == mainList.get(i).getStartMeeting().get(Calendar.MONTH) && year == mainList.get(i).getStartMeeting().get(Calendar.YEAR)){
                filterList.add(mainList.get(i));
            }
        }
    }

    public static boolean checkRoomAvailability(String lieu,Calendar startMeeting, Calendar endMeeting){
        for(int i=0; i < mainList.size(); i++){
            //Check Place and Date
            if(lieu.equals(mainList.get(i).getLieu()) && startMeeting.get(Calendar.DATE) == mainList.get(i).getStartMeeting().get(Calendar.DATE)
                    && startMeeting.get(Calendar.MONTH) == mainList.get(i).getStartMeeting().get(Calendar.MONTH)
                    && startMeeting.get(Calendar.YEAR) == mainList.get(i).getStartMeeting().get(Calendar.YEAR)){
                // Check start meeting
                if(((startMeeting.get(Calendar.HOUR_OF_DAY) == mainList.get(i).getStartMeeting().get(Calendar.HOUR_OF_DAY)
                        && startMeeting.get(Calendar.MINUTE) >= mainList.get(i).getStartMeeting().get(Calendar.MINUTE)) || startMeeting.get(Calendar.HOUR_OF_DAY) > mainList.get(i).getStartMeeting().get(Calendar.HOUR_OF_DAY))
                        && ((startMeeting.get(Calendar.HOUR_OF_DAY) == mainList.get(i).getEndMeeting().get(Calendar.HOUR_OF_DAY)
                        && startMeeting.get(Calendar.MINUTE) <= mainList.get(i).getEndMeeting().get(Calendar.MINUTE)) || startMeeting.get(Calendar.HOUR_OF_DAY) < mainList.get(i).getEndMeeting().get(Calendar.HOUR_OF_DAY))){

                    return false;
                }
                //Check end meeting
                if(((endMeeting.get(Calendar.HOUR_OF_DAY) == mainList.get(i).getStartMeeting().get(Calendar.HOUR_OF_DAY)
                        && endMeeting.get(Calendar.MINUTE) >= mainList.get(i).getStartMeeting().get(Calendar.MINUTE)) || endMeeting.get(Calendar.HOUR_OF_DAY) > mainList.get(i).getStartMeeting().get(Calendar.HOUR_OF_DAY))
                        && ((endMeeting.get(Calendar.HOUR_OF_DAY) == mainList.get(i).getEndMeeting().get(Calendar.HOUR_OF_DAY)
                        && endMeeting.get(Calendar.MINUTE) <= mainList.get(i).getEndMeeting().get(Calendar.MINUTE)) || endMeeting.get(Calendar.HOUR_OF_DAY) < mainList.get(i).getEndMeeting().get(Calendar.HOUR_OF_DAY))){

                    return false;
                }
            }
        }
        return true;
    }

    public static void deleteReunion(Reunion reunion){
        mainList.remove(reunion);
    }
}
