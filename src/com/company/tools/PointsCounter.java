package com.company.tools;

/**
 * author @pater
 */
public class PointsCounter {
    private static PointsCounter ourInstance = new PointsCounter();
    private int count;
    
    public static PointsCounter getInstance() {
        return ourInstance;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }
    
    private PointsCounter() {
    }
    
    public void decrease() {
        --count;
    }
    
}
