package org.firstinspires.ftc.teamcode.gspeed;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.TrajectoryBuilder;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

import kotlin.Unit;

@TeleOp
public class SamplePathFollowing extends LinearOpMode {

    // A* Graph Class
    Graph graph = new Graph();
    Point start = new Point(5, 1);
    Point end = new Point(0, 1);

    double startHeading = 0;
    UnitConverter converter = new UnitConverter();

    PathBuilder pathbuilder = new PathBuilder(graph, start, end);
    Path path = pathbuilder.AStar();

    PathFollower follower = new PathFollower(path, start, startHeading);

    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        drive.setPoseEstimate(converter.unitConvertPose(start, startHeading));

        waitForStart();

        if (isStopRequested()) return;

        // Print A* Path
        telemetry.addData("Path:", graph.printPathFinder(path));
        telemetry.update();

        // Build the path (run it physically)
        drive.followTrajectorySequence(follower.followPath(drive));
    }
}
