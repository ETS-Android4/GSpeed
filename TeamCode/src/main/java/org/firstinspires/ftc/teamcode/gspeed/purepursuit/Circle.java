package org.firstinspires.ftc.teamcode.gspeed.purepursuit;

import org.firstinspires.ftc.teamcode.gspeed.Point;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;

public class Circle {

    private double radius;
    private double centerX;
    private double centerY;

    // Constructor
    public Circle(double radius, double x, double y) {
        this.radius = radius;
        this.centerX = x;
        this.centerY = y;
    }


    // Get the intersection of a segment and the circle
    public ArrayList<Point> getIntersection(Segment seg) {
        // This method was lifted from FTCLib.;

        double baX = seg.getEnd().getColumn() - seg.getStart().getColumn();
        double baY = seg.getEnd().getRow() - seg.getStart().getRow();
        double caX = centerX - seg.getStart().getColumn();
        double caY = centerY - seg.getStart().getRow();

        double a = baX * baX + baY * baY;
        double bBy2 = baX * caX + baY * caY;
        double c = caX * caX + caY * caY - radius * radius;

        double pBy2 = bBy2 / a;
        double q = c / a;

        double disc = pBy2 * pBy2 - q;
        if (disc < 0) {
            return new ArrayList<Point>();
        }

        double tmpSqrt = Math.sqrt(disc);
        double abScalingFactor1 = -pBy2 + tmpSqrt;
        double abScalingFactor2 = -pBy2 - tmpSqrt;

        ArrayList<Point> allPoints = null;

        Point p1 = new Point(seg.getStart().getColumn() - baX * abScalingFactor1, seg.getStart().getRow() - baY * abScalingFactor1);
        if (disc == 0) {
            allPoints.add(p1);
        }

        if (allPoints == null) {
            Point p2 = new Point(seg.getStart().getColumn() - baX * abScalingFactor2, seg.getStart().getRow() - baY * abScalingFactor2);
            allPoints.add(p1);
            allPoints.add(p2);
        }

        double maxX = Math.max(seg.getStart().getColumn(), seg.getEnd().getColumn());
        double maxY = Math.max(seg.getStart().getRow(), seg.getEnd().getRow());
        double minX = Math.min(seg.getStart().getColumn(), seg.getEnd().getColumn());
        double minY = Math.min(seg.getStart().getRow(), seg.getEnd().getRow());

        ArrayList<Point> boundedPoints = new ArrayList<Point>();

        for (Point point : allPoints) {

            if (point.getColumn() <= maxX && point.getColumn() >= minX)
                if (point.getRow() <= maxY && point.getRow() >= minY)
                    boundedPoints.add(point);

        }

        return boundedPoints;
    }
}
