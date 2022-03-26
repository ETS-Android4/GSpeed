package org.firstinspires.ftc.teamcode.gspeed;

import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequenceBuilder;

public class PathFollower {

    private Path path = new Path();

    private Point startPoint = new Point(0, 0);

    private double startHeading = 0;

    public PathFollower(Path p, Point start, double startingHeading) {
        path = p;
        startPoint = start;
        startHeading = startingHeading;
    }

    private boolean compareSlopes(Point prev, Point curr, Point next) {
        int prevCurrSlope = (curr.getRow() - prev.getRow()) / (curr.getColumn() - prev.getRow());
        int currNextSlope = (next.getRow() - curr.getRow()) / (next.getColumn() - curr.getRow());
        return prevCurrSlope == currNextSlope;
    }

    private Path pathGrouper() {
        // Grouped Path
        Path out = new Path();

        // Loop through path and add only points that do not have the same slope
        for (int i = 1; i < path.getPath().size(); i++) {
            if (!compareSlopes(path.getPath().get(i - 1), path.getPath().get(i), path.getPath().get(i + 1))) {
                out.addPoint(path.getPath().get(i));
            }
        }

        /* OLD CODE
        Point current = new Point(0, 0);
        for (int i = 1; i < path.getPath().size(); i++) {
            // Runs once
            if (i == 1) {
                out.addPoint(path.getPath().get(i));
                // Set up point for else statement below
                current = path.getPath().get(i+1);
            } else {
                // Loop through path until a point is found that does not match the slope of the previous point
                while (compareSlopes(path.getPath().get(i - 1), current, path.getPath().get(i))) {
                    out.addPoint(current);
                }
                if (current.getColumn() == back1.getColumn() && current.getColumn() == forward1.getColumn()) {
                    // Don't Add
                } else {
                    if (!compareSlopes(back1, current, forward1)) {
                        out.add(path.getPath().get(i));
                    }
                }
                back1 = path.getPath().get(i - 1);
                current = new Point(path.getPath().get(i).getRow(), path.getPath().get(i).getColumn());
                forward1 = new Point(path.getPath().get(i + 1).getRow(), path.getPath().get(i + 1).getColumn());
            }
        }
        */

        return out;
    }

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
