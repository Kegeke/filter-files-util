package org.example.statistic;

public class StringData {
    private int count = 0;
    private int maxLength = 0;
    private int minLength = Integer.MAX_VALUE;

    public StringData() {
    }

    public void collect(String str) {
        count++;
        maxLength = Math.max(maxLength, str.length());
        minLength = Math.min(minLength, str.length());
    }

    public void dropStats() {
        if (count > 0) {
            System.out.printf("""
                    Strings {
                        Count: %d
                        Max length: %d
                        Min length: %d
                    }
                    """, count, maxLength, minLength);
        }
    }

    public int getCount() {
        return count;
    }
}
