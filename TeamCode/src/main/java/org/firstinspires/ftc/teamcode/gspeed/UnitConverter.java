package org.firstinspires.ftc.teamcode.gspeed;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

public class UnitConverter {

    // Constructor
    public UnitConverter() {
    }

    public Vector2d unitConvertVector(Point p) {
        return new Vector2d(24 * (p.getColumn() + 1) - 84, (-24) * (p.getRow() + 1) + 84);
    }

    public Pose2d unitConvertPose(Point p, double heading) {
        return new Pose2d(24 * (p.getColumn() + 1) - 84, (-24) * (p.getRow() + 1) + 84, Math.toRadians(heading));
    }
}
