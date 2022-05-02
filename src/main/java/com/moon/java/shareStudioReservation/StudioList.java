package com.moon.java.shareStudioReservation;

import java.util.Arrays;

public class StudioList {
    private Object[] list;
    private int capacity = 0;
    private int defaultCapacity = 10;
    private int size;

    public StudioList() {
        this.list = new Object[defaultCapacity];
        this.capacity = defaultCapacity;
        this.size = 0;
    }

    private void resize(){
        int listCapacity = list.length;

        if (listCapacity == 0){
            list = new Object[defaultCapacity];
            return;
        }
        if (size == listCapacity){
            expandList();
        }
        if (size < (listCapacity/2)){
            Object[] tempList = new Object[listCapacity/2];

            copyList(tempList,list);
        return;
        }

    }

    private void copyList(Object[] copiedList, Object[] copyTargetList){

        for (int i = 0; i < copyTargetList.length; i++) {
            copiedList[i] = copyTargetList[i];
        }

    }
    private void expandList(){

        this.capacity *=2;
        Object[] tempList = new Object[this.capacity];
        copyList(tempList, list);
        list = new Object[this.capacity];
        copyList(list,tempList);

    }

    public void add(Studio studio){
        if (size == this.capacity){
            this.expandList();
        }
        this.list[size] = studio;
        this.size++;
    }
    public void remove(int index){
        if (index >=size || index < 0){
            throw new IndexOutOfBoundsException();
        }
        list[index] = null;

        for (int i = index; i < size; i++) {
            list[i] = list[i+1];
            list[i+1] = null;
        }
        size --;
        resize();
    }

    public Studio get(int index){
        return (Studio) list[index];
    }

    public int size (){
        return size;
    }
}
