package com.example.hotelsystemfx;

import java.util.Map;
import java.util.TreeMap;

public  class Hotel {
    static final int noOfRooms=8;
    public static Map<Integer,Room> HotelRooms;

    static {
        HotelRooms = new TreeMap<Integer, Room>();
    }

}
