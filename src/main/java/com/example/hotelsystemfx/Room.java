package com.example.hotelsystemfx;

import java.util.Map;
import java.util.TreeMap;

public class Room {
    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    private int roomNo;
    private String roomName;

    public Person getPayee() {
        return payee;
    }

    public void setPayee(Person payee) {
        this.payee = payee;
    }

    Person payee;


    public int getNoOfGuests() {
        return noOfGuests;
    }

    public void setNoOfGuests(int noOfGuests) {
        this.noOfGuests = noOfGuests;
    }



    private int noOfGuests;

    public Room(int no,String roomName,Person payee,int noGuests) {
        this.roomNo=no;
        this.roomName=roomName;
        this.payee=payee;
        this.noOfGuests= noGuests;
    }
}
