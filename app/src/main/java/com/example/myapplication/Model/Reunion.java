package com.example.myapplication.Model;

import static com.example.myapplication.Model.ActiveDirectory.GuestArray;

import java.util.ArrayList;
import java.util.Calendar;

public class Reunion {

    /**
     * Declaration
     * */
    private final String sujet;
    private final String Lieu;
    private final String Guests;
    private final Calendar startMeeting;
    private final Calendar endMeeting;


    /**
     * Constructeur
     * */
    public Reunion(String sujet, String lieu, Calendar startMeeting,Calendar endMeeting, String GuestList) {
        this.sujet = sujet;
        this.Lieu = lieu;
        this.startMeeting = startMeeting;
        this.endMeeting = endMeeting;
        this.Guests = GuestList;
    }

    public String getSujet() {
        return sujet;
    }

    public String getLieu() { return Lieu; }

    public String getGuest() { return Guests; }

    public Calendar getStartMeeting() { return startMeeting; }

    public Calendar getEndMeeting() { return endMeeting; }

    public static String getStringGuestList(ArrayList<Integer> GuestListIndex){
        StringBuilder stringBuilder = new StringBuilder();

        for (int j=0; j<GuestListIndex.size();j++){
            stringBuilder.append(GuestArray[GuestListIndex.get(j)]);
            if(j!=GuestListIndex.size()-1){
                stringBuilder.append("; ");
            }
        }

        return stringBuilder.toString();
    }

}
