package org.firstinspires.ftc.teamcode.gspeed;

import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequenceBuilder;

public class PathFollower {

    private Path path;

    private Point startPoint;

    private double startHeading;

    public PathFollower(Path p, Point start, double startingHeading) {
        path = p;
        startPoint = start;
        startHeading = startingHeading;
    }

    private boolean compareSlopes(Point prev, Point curr, Point next) {
        int prevCurrSlope = 0;
        int currNextSlope = 0;

        boolean prevCurrVert = false;
        boolean currNextVert = false;

        if ((curr.getColumn() - prev.getRow()) != 0) {
            prevCurrSlope = (curr.getRow() - prev.getRow()) / (curr.getColumn() - prev.getRow());
        } else {
            prevCurrVert = true;
        }

        if ((next.getColumn() - curr.getRow()) != 0) {
            currNextSlope = (next.getRow() - curr.getRow()) / (next.getColumn() - curr.getRow());
        } else {
            currNextVert = true;
        }

        if (prevCurrVert && currNextVert) {
            return true;
        } else if (prevCurrVert || currNextVert) {
            return false;
        } else {
            return prevCurrSlope == currNextSlope;
        }
    }

    // TODO: this function does not work
    public Path pathGrouper() {
        // Grouped Path
        Path out = new Path();

        // Loop through path and add only points that do not have the same slope
        for (int i = 1; i < path.getPath().size(); i++) {
            if (i != path.getPath().size() - 1 && i != path.getPath().size()) {
                if (!compareSlopes(path.getPath().get(i - 1), path.getPath().get(i), path.getPath().get(i + 1))) {
                    out.addPoint(path.getPath().get(i));
                }
            }
        }

        return out;
    }

    // TODO; uses broken function, default to followPath()
    public TrajectorySequence followGroupedPath(SampleMecanumDrive drive) {

        // Class to Convert Units
        UnitConverter converter = new UnitConverter();

        TrajectorySequenceBuilder traj = drive.trajectorySequenceBuilder(converter.unitConvertPose(startPoint, startHeading));

        for (int i = 0; i < pathGrouper().getPath().size(); i++) {
            Vector2d out = converter.unitConvertVector(pathGrouper().getPath().get(i));
            traj.lineTo(new Vector2d());
        }

        return traj.build();
    }

    public TrajectorySequence followPath(SampleMecanumDrive drive) {

        // Class to Convert Units
        UnitConverter converter = new UnitConverter();

        TrajectorySequenceBuilder traj = drive.trajectorySequenceBuilder(converter.unitConvertPose(startPoint, startHeading));

        for (int i = 1; i < path.path.size(); i++) {
            Vector2d out = converter.unitConvertVector(path.path.get(i));
            traj.lineTo(out);
        }

        return traj.build();
    }
}
