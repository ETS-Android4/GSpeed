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

    /*
    private ArrayList<Point> pathGrouper() {
        ArrayList<Point> out = new ArrayList<Point>();
        Point current = new Point(0, 0);
        Point forward1 = new Point(0, 0);
        Point back1 = new Point(0, 0);
        for (int i = 0; i < path.getPath().size(); i++) {
            if (i == 1) {
                out.add(new Point(path.getPath().get(i).getRow(), path.getPath().get(i).getColumn()));
            } else {

                TODO how does this work?
                back1 = current;

                // y is before x
                current = new Point(path.getPath().get(i).getRow(), path.getPath().get(i).getColumn());
                forward1 = new Point(path.getPath().get(i + 1).getRow(), path.getPath().get(i + 1).getColumn());

                // Conditions

                TODO fix
                why isn't there a condition for similar rows and not just columns?
                do we even need this? don't we only need to compare slopes?

                if (current.getColumn() == back1.getColumn() && current.getColumn() == forward1.getColumn()) {
                    // Don't Add
                } else {
                    if (!compareSlopes(back1, current, forward1)) {
                        out.add(path.getPath().get(i));
                    }
                }

            }
        }
        return out;
    }
    */

    /* TODO: refactor to match followPath after fixed original grouping method
    public TrajectoryBuilder followPathWithGrouping(SampleMecanumDrive drive) {
        TrajectoryBuilder traj = drive.trajectoryBuilder(new Pose2d());

        // Class to Convert Units
        UnitConverter converter = new UnitConverter();

        for (int i=0; i<pathGrouper().size(); i++) {
            Point point = new Point(pathGrouper().get(i).getRow(), pathGrouper().get(i).getColumn());
            Vector2d out = converter.unitConvertVector(point);
            traj.lineTo(new Vector2d());
        }
        return traj;
    }
    */

    public TrajectorySequence followPath(SampleMecanumDrive drive) {

        // Class to Convert Units
        UnitConverter converter = new UnitConverter();

        TrajectorySequenceBuilder traj = drive.trajectorySequenceBuilder(converter.unitConvertPose(startPoint, startHeading));

        for (int i = 1; i < path.path.size(); i++) {
            Vector2d out = converter.unitConvertVector(path.path.get(i));
            traj.lineTo(out);
        }

        TrajectorySequence movement = traj.build();

        return movement;
    }
}
