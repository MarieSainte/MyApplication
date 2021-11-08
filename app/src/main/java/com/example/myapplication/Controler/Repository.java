package com.example.myapplication.Controler;

import com.example.myapplication.Model.ActiveDirectory;
import com.example.myapplication.Model.Reunion;
import com.example.myapplication.Model.ReunionList;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Repository {

    public static void setMainList(Reunion reunion) {
        ReunionList.setMainList(reunion);
    }

    public static List<Reunion> getMainList() {
        return ReunionList.getMainList();
    }

    public static void deleteReunion(Reunion reunion) { ReunionList.deleteReunion(reunion); }

    public static String[] getGuestArray() { return ActiveDirectory.getGuestArray(); }

    public static List<Reunion> getFilterList(){ return ReunionList.getFilterList(); }

    public static void setFilterList(String lieu){ ReunionList.setFilterList(lieu); }

    public static void setFilterList(int day, int month, int year){ ReunionList.setFilterList(day,month,year); }

    public static String[] getRooms() { return ActiveDirectory.getRooms(); }

    public static  String getStringGuestList(ArrayList<Integer> GuestListIndex) {return Reunion.getStringGuestList(GuestListIndex);}

    public static boolean checkRoomAvailability(String lieu, Calendar startMeeting, Calendar endMeeting){return ReunionList.checkRoomAvailability(lieu,startMeeting,endMeeting);}

}
