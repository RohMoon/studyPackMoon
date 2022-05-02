package com.moon.java.shareStudioReservation;

import java.util.NoSuchElementException;

public class Studio {
    String local;
    String suburb;
    private Room head;
    private Room tail;
    private int size;
    Studio next;

    public Studio(String local, String suburb) {
        this.local = local;
        this.suburb = suburb;
        this.head = null;
        this.tail = null;
        size = 0;
    }

    public int size(){
        return size;
    }

    private Room search(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Room x = head;

        for (int i = 0; i < index; i++) {
            x = x.next;
        }
        return x;
    }


    private void addRoomAtFirst(String name, int maxCapacity, int standardPrice) {
        Room newRoom = new Room(name,maxCapacity, standardPrice);
        newRoom.next = head;
        head = newRoom;
        size++;

        if (head.next == null) {
            tail = head;
        }
    }

    public void addRoom(String name, int maxCapacity, int standardPrice) {
        Room newRoom = new Room(name, maxCapacity, standardPrice);

        if (size == 0) {
            addRoomAtFirst(name, maxCapacity, standardPrice);
            return;
        }

        tail.next = newRoom;
        tail = newRoom;
        size++;
    }

    private void removeFromHead() {

        Room headNode = head;
        if (headNode == null) {
            throw new NoSuchElementException();
        }

        Room nextRoom = head.next;
        head.maxCapacity = 0;
        head.standardPrice = 0;
        head = nextRoom;
        size--;
        if (size == 0) {
            tail = null;
        }
    }

    public boolean remove(String value) {
        Room prevRoom = head;
        boolean hasValue = false;
        Room x = head;

        for (; x != null; x = x.next) {
            if (value.equals(x.name)) {
                hasValue = true;
                break;
            }
            prevRoom = x;
        }
        if (x == null) {
            return false;
        }

        if (x.equals(head)) {
            removeFromHead();
            return true;

        } else {
            prevRoom.next = x.next;
            if (prevRoom.next == null) {
                tail = prevRoom;
            }
            x.standardPrice = 0;
            x.maxCapacity = 0;
            x.name = null;
            size--;
            return true;
        }
    }

    public void setLocalAndSuburb(String local, String suburb){
        this.local = local;
        this.suburb = suburb;
    }

    public void setLocal(String local){
        this.local = local;
    }

    public void setSuburb(String suburb){
        this.suburb = suburb;
    }

    public String getRoomName(int index){
        return search(index).name;
    }

    public Room getRoom(int index){
        return search(index);
    }

    public boolean isEmpty(){
        return size == 0;
    }

}
