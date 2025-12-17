package org.example.statistic;

public class FloatData {
    private int count = 0;
    private double max = 0;
    private double min = Double.MAX_VALUE;
    private double sum = 0;

    public FloatData() {
    }

    public void collect(double number) {
        count++;
        max = Math.max(number, max);
        min = Math.min(number, min);
        sum += number;
    }

    public void dropStats() {
        if (count > 0) {
            System.out.printf("""
                    Floats {
                        Count: %d
                        Max: %g
                        Min: %g
                        Sum: %.2f
                        Avg: %.2f
                    }
                    """, count, max, min, sum, sum / count);
        }
    }

    public int getCount() {
        return count;
    }
}
