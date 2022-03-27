package org.firstinspires.ftc.teamcode.gspeed;

public class Point {
    private double r = 0;
    private double c = 0;

    public Point(double y, double x) {
        r = y;
        c = x;
    }

    public String toString() {
        String output = "";
        output = String.valueOf(r) + "," + String.valueOf(c);
        return output;
    }

    // y axis
    public double getRow() {
        return r;
    }

    // x axis
    public double getColumn() {
        return c;
    }

    public boolean equals(Point p) {
        return p.getRow() == r && p.getColumn() == c;
    }

}
