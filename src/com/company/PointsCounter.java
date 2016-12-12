package com.company;

/**
 * author @pater
 */
public class PointsCounter {
    private static PointsCounter ourInstance = new PointsCounter();

    public static PointsCounter getInstance() {
        return ourInstance;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    int count;
    
    private PointsCounter() {
    }


    public void decrease() {
        --count;
    }
    
}
