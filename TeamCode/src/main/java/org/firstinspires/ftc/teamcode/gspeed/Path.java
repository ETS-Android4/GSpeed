package org.firstinspires.ftc.teamcode.gspeed;

import java.util.ArrayList;

public class Path {

    // Path object used in class
    // TODO make sure having this public doesn't break
    public ArrayList<Point> path;
    // Cost field
    private double cost;
    // Heuristic field
    private double heuristic;

    // Constructor
    public Path() {}

    // Overriding constructor for specifying a path
    public Path(ArrayList<Point> p, int c, double h) {
        path = p;
        cost = c;
        heuristic = h;
    }

    // Accessor for cost
    public double getCost() {
        return cost;
    }
    // Accessor for heuristic
    public double getHeuristic() {
        return heuristic;
    }
    // Accessor for path
    public ArrayList<Point> getPath() {
        return path;
    }
    // Accessor for a point object on a path
    public Point getPoint(int i) {
        return path.get(i);
    }
    // Modifier for adding a point object to a path
    public void addPoint(Point p) {
        path.add(p);
    }
    // Accessor for getting the last point on a path
    public Point getEnd() {
        return path.get(path.size()-1);
    }
    // Modifier for setting the cost of a path
    public void setCost(int c) {
        cost = c;
    }
    // Modifier for setting the heuristic of a path
    public void setHeuristic(int h) {
        heuristic = h;
    }

    // Checks to see if the path
    public boolean containsPoint(Point p) {
        for (int i  = 0; i < path.size(); i++) {
            if (path.get(i).equals(p)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        String output = "";
        for (int i = 0; i < path.size(); i++) {
            output += path.get(i).toString() + ", ";
        }
        output += String.valueOf(cost) + ", ";
        output += String.valueOf(heuristic);
        return output;
    }
}
