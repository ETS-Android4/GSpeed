package org.firstinspires.ftc.teamcode.gspeed.purepursuit;

import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.gspeed.Point;

import java.util.ArrayList;

public class RobotMovement {

    // Constructor
    public RobotMovement() {
    }

    /** Combination of the rest of the pure pursuit functions to create robot movement
     * @param pathPoints The points the robot will travel to (A* path)
     * @param xPos The x position of the robot
     * @param yPos The y position of the robot
     * @param heading The heading of the robot (in radians)
     * @param followRadius The radius the robot will follow the path at/look ahead distance
     * @return
     */
    public static CurvePoint getFollowPointPath(ArrayList<CurvePoint> pathPoints, double xPos, double yPos, double heading, double followRadius) {
        // CurvePoint followMe = new CurvePoint(pathPoints.get(0));

        // Loop through the path points
        for (int i = 0; i < pathPoints.size()-1; i++) {
            CurvePoint startLine = pathPoints.get(i);
            CurvePoint endLine = pathPoints.get(i + 1);

            ArrayList<Point> intersections = new Circle(followRadius, xPos, yPos).getIntersection(new Segment(new Point(startLine.x, startLine.y), new Point(endLine.x, endLine.y)));

            // Find closest angle
            double closestAngle = Double.POSITIVE_INFINITY;

            for (Point thisIntersection : intersections) {
                double angle = Math.atan2(thisIntersection.getRow() - yPos, thisIntersection.getColumn() - xPos);
                // TODO: learn how this works
                double deltaAngle = Math.abs(AngleWrap(angle - heading));
            }
        }
    }

    // TODO: confirm drive parameter is passed correctly for odometry
    public static void moveToPosition(double x, double y, double speed, double theta, SampleMecanumDrive drive) {

        // Difference between current position and target position
        double xDiff = x - drive.getPoseEstimate().getX();
        double yDiff = y - drive.getPoseEstimate().getY();

        // Distance between target position and current position
        double distance = Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2));

        // Absolute angle to target position
        double angle = Math.atan2(yDiff, xDiff);

        // Relative angle to target position (in radians)
        double relativeAngle = AngleWrap(angle - drive.getPoseEstimate().getHeading());

        // Relative turn angle
        double turnAngle = relativeAngle + theta;
        double movementTurn = Range.clip(turnAngle / Math.toRadians(30), -1, 1) * speed;

        // TODO: tune this value until it works
        if (distance < 10) {
            movementTurn = 0;
        }

        // Get the relateive x and y distances from the current position to the target position
        double xRelative = Math.cos(relativeAngle) * distance;
        double yRelative = Math.sin(relativeAngle) * distance;

        double movementXPower = xRelative / (Math.abs(xRelative) + Math.abs(yRelative));
        double movementYPower = yRelative / (Math.abs(xRelative) + Math.abs(yRelative));

        double xPower = movementXPower * speed;
        double yPower = movementYPower * speed;
    }

    // Make sure an angle is between -180 and 180
    public static double AngleWrap(double angle) {
        while (angle < -Math.PI) {
            angle += 2 * Math.PI;
        }
        while (angle > Math.PI) {
            angle -= 2 * Math.PI;
        }
        return angle;
    }
}
