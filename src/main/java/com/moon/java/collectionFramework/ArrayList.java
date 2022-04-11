package com.moon.java.collectionFramework;

public class ArrayList<T> {
    Object[] arr = null;
    int capacity = 0;
    int size = 0;

    public ArrayList(int capacity){
        this.capacity = capacity;
        arr = new Object[capacity];
        size = 0;
    }

    public ArrayList(){
        capacity = 1;
        arr = new Object[capacity];
        size = 0;
    }

    public void add(T element){
        if(size == capacity){
            expandArray();
        }

        arr[size++] = element;
    }

    public void addFirst(T element){
        add(0, element);
    }

    public void add(int index, T element){
        if(size == capacity){
            expandArray();
        }

        for(int i = size; i >= index; i--)
            arr[i] = arr[i-1];

        arr[index] = element;
        size++;
    }

    private void expandArray(){
        capacity *= 2;
        Object[] tempArr = new Object[capacity];
        copyArr(tempArr, arr);
        arr = new Object[capacity];
        copyArr(arr, tempArr);
    }

    private void copyArr(Object[] arr1, Object[] arr2){
        for(int i = 0; i < arr2.length; i++){
            arr1[i] = arr2[i];
        }
    }

    public T get(int index){
        if(size <= 0){
            System.out.println("배열이 비어있습니다.");
            return null;
        }

        return (T) arr[index];
    }

    public T remove(int index){
        /*
            크기 초과, 이미 비어있는지 등 조건문은 원하는대로 추가해주면 된다.
        */
        T result = (T) arr[index];
        arr[index] = null;
        size--;
        if(size > 0) {
        /*
        	삭제한 이후부터 앞으로 한칸씩 땡긴다.
        */
            for(int i = index; i < size; i++) {
                arr[i] = arr[i+1];
            }
        }


        return result;
    }
    public void reset(){
        arr = new Object[capacity];
        size = 0;
    }

    public int size() {
        return this.size;
    }

    public int getCapacity() {
        return this.capacity;
    }
}
