package org.example.statistic;

public class IntegerData {
    private int count = 0;
    private long max = 0;
    private long min = Integer.MAX_VALUE;
    private long sum = 0;

    public IntegerData() {
    }

    public void collect(long number) {
        count++;
        max = Math.max(number, max);
        min = Math.min(number, min);
        sum += number;
    }

    public void dropStats() {
        if (count > 0) {
            System.out.printf("""
                    Integers {
                        Count: %s
                        Max: %s
                        Min: %s
                        Sum: %s
                        Avg: %s
                    }
                    """, count, max, min, sum, sum / count);
        }
    }

    public int getCount() {
        return count;
    }
}
