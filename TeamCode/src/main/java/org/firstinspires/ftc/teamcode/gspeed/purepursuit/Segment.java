package org.firstinspires.ftc.teamcode.gspeed.purepursuit;

import org.firstinspires.ftc.teamcode.gspeed.Point;

public class Segment {
    private Point start;
    private Point end;

    // Constructor
    public Segment(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    // Get slope
    public double getSlope() {
        return (end.getRow() - start.getRow()) / (end.getColumn() - start.getColumn());
    }

    // Get y-intercept
    public double getYIntercept() {
        return start.getRow() - (getSlope() * start.getColumn());
    }

    // Getters
    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    // Setters
    public void setStart(Point start) {
        this.start = start;
    }

    public void setEnd(Point end) {
        this.end = end;
    }
}
