package com.moon.java.shareStudioReservation;

public class Room {
    int maxCapacity;
    int standardPrice;
    TimeTable timeTable;


    public Room(int maxCapacity, int standardPrice) {
        this.maxCapacity = maxCapacity;
        this.standardPrice = standardPrice;
        timeTable = new TimeTable();
    }

    private int getTimeCount(int startTime, int endTime) {
        int timeCount = 0;

        if (startTime > endTime) {
            timeCount = timeTable.length() - (startTime - endTime);
        }
        if (startTime < endTime) {
            timeCount = endTime - startTime;
        }
//        if (startTime-endTime =1 || (timeTable.length() - (startTime - endTime))=1) {
//            timeCount = 1;
//        }
        return timeCount;
    }

    private int theseTimeEmptyCheck(int timeCount, int startTime) {

        int result = -1;

        if (timeCount > timeTable.length()) {
            throw new IndexOutOfBoundsException();
        }

        for (int i = 0, j = startTime; i < timeCount; i++) {


            if (j >= timeTable.length()) {
                j = 0;
            }

            if (timeTable.get(j) != null) {
                result = -1;
                break;
            } else if (timeTable.get(j) == null) {
                result = 1;
            }
            j++;
        } // end of for
        return result;
    }

    public void addTime(int startTime, int endTime, String customer) {

        int timeCount = getTimeCount(startTime, endTime);
        int emptyCheckResult = theseTimeEmptyCheck(timeCount, startTime);

        if (emptyCheckResult == 1) {
            for (int i = 0, j = startTime; i < timeCount; i++, j++) {

                if (j >= timeTable.length()) {
                    j = 0;
                }
                timeTable.add(j, customer);
            }
        } else {
            System.out.println(" it including unavailable Time. ");
        }
    }

    public void removeTime(int startTime, int endTime, String customer) {

        int timeCount = getTimeCount(startTime, endTime);
        int emptyCheckResult = theseTimeEmptyCheck(timeCount, startTime);

        if (emptyCheckResult == -1) {
            for (int i = 0, j = startTime; i < timeCount; i++, j++) {
                if (i >= timeTable.length()) {
                    j = 0;
                }
                if (timeTable.get(j).equals(customer)) {
                    timeTable.remove(j);
                }
            }
        }
    }

    public void clear(){
        for (int i = 0; i < timeTable.length(); i++) {
            timeTable.remove(i);
        }        
    }
    public int getSize(){
        return timeTable.getSize();
    }

}
