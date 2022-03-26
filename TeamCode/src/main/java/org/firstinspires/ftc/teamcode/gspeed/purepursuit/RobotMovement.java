package org.firstinspires.ftc.teamcode.gspeed.purepursuit;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

public class RobotMovement {

    // Constructor
    public RobotMovement() {
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

        // Get the relateive x and y distances from the current position to the target position
        double xRelative = Math.cos(relativeAngle) * distance;
        double yRelative = Math.sin(relativeAngle) * distance;

        double xPower = xRelative / (Math.abs(xRelative) + Math.abs(yRelative));
        double yPower = yRelative / (Math.abs(xRelative) + Math.abs(yRelative));
    }

    // Make sure an angle is between -180 and 180
    public static double AngleWrap(double angle) {
        while(angle < -Math.PI) {
            angle += 2 * Math.PI;
        }
        while(angle > Math.PI) {
            angle -= 2 * Math.PI;
        }
        return angle;
    }
}
