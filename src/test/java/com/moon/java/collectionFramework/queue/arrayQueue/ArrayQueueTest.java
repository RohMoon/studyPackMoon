package com.moon.java.collectionFramework.queue.arrayQueue;

import com.moon.java.collectionFramework.queue.arrayQueue.ArrayQueue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayQueueTest {

    //생성자 이용한 생성시 용적 확인
    @Test
    public void isQueueLengthExistTest(){
        //A
        ArrayQueue<Integer> arrayQueue =null;
        ArrayQueue<Integer> arrayQueue2 = null;
        //AA
        arrayQueue = new ArrayQueue<Integer>();
        arrayQueue2 = new ArrayQueue<Integer>(100);

        //AAA
        assertTrue(arrayQueue.length()==64);
        assertTrue(arrayQueue2.length()==100);

    }

    /*
    * 큐에 offer를 이용해서 추가하고 정상적으로 값이 확인이 되는가?
    * */
    @Test
    public void isQueueOfferWorking(){
        // A
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<Integer>();

        System.out.println("arrayQueue.length == > "+arrayQueue.length());
        // AA
//        for (int i = 0; i < arrayQueue.length(); i++) { // resize로 용적 늘어나는것 확인.
        for (int i = 0, j=0; i < 65; i++,j++) {
            arrayQueue.offer(i);
            if (j%5==0){
                System.out.println("\n");
            }
            System.out.print(arrayQueue.get(i)+"   ");
        }

        //AA
        assertEquals(63,arrayQueue.get(64));
        assertEquals(65,arrayQueue.size());
    }
    @Test
    public void isPollWorking(){
        // A
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<Integer>();

        // AA
        for (int i = 0, j=0; i < 65; i++,j++) {
            arrayQueue.offer(i);
            if (j%5==0){
                System.out.println("\n");
            }
            System.out.print(arrayQueue.get(i)+"   ");
        }
        arrayQueue.poll();
        // AAA
        assertEquals(1,arrayQueue.peek());
    }

    @Test
    public void isContainsWorking(){

        // A
        ArrayQueue<Integer> arrayQueue ;

        // AA
        arrayQueue = new ArrayQueue<Integer>();

        // AAA
        assertTrue(arrayQueue.isEmpty());
    }

}