package com.moon.java.collectionFramework.arrayList;

public class ArrayList3<T> {
    int size = 0;
    int capacity = 0;
    Object[] array = {};

    public ArrayList3() {
        this.size = 0;
    }

    public ArrayList3(int capacity) {
        this.capacity = capacity;
        array = new Object[capacity];
        this.size = 0;
    }

    public void expandArray(){
        this.capacity *= 2;
        Object[] tempArr = new Object[this.capacity];
        copyArray(tempArr ,array);
        array = new Object[this.capacity];
        copyArray(array, tempArr);


    }
    private void copyArray(Object[] copiedArray, Object[] copyTargetArray){
        for (int i = 0; i < copyTargetArray.length; i++) {
            copiedArray[i] = copyTargetArray[i];
        }
    }

    // 특정 위치에 요소 추가
    public void add(int index, T value) {
        if (size == capacity){
            this.expandArray();
        }
        for (int i = index; i < size ; i++) {
            array[i+1] = array[i];
        }
        array[index] = value;
        size++;

    }
    // 마지막 위치에 요소 추가
    public void add(T value){
        if (size == capacity){
            this.expandArray();
        }
        this.array[size] = value;
        this.size++;

    }

    public T get(int index){

        return (T) array[index];
    }
}
