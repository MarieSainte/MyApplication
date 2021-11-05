package com.example.myapplication.Model;

public class ActiveDirectory {

    static String[] GuestArray = {
            "John@gmail.com",
            "Joy@gmail.com",
            "Jordan@gmail.com",
            "James@gmail.com",
            "Joe@gmail.com",
            "Dude@gmail.com",
            "Guy@gmail.com",
            "Man@gmail.com",
            "Woman@gmail.com"
    };

    static String[] Rooms = {
            "Paris",
            "London",
            "Madrid",
            "Rome",
            "Berlin",
            "Pekin",
            "Tokyo",
            "Seoul",
            "Bangkok",
            "Sydney"
    };

    public static String[] getRooms() { return Rooms; }

    public static String[] getGuestArray() { return GuestArray ; }
}
