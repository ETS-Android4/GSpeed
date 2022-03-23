package org.firstinspires.ftc.teamcode.gspeed;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@TeleOp
public class SamplePathFollowing extends LinearOpMode {

    // A* Graph Class
    Graph graph = new Graph();
    Point start = new Point(0, 0);
    Point end = new Point(5, 5);

    PathBuilder pathbuilder = new PathBuilder(graph, start, end);
    Path path = pathbuilder.AStar();

    PathFollower follower = new PathFollower(path);

    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        drive.setPoseEstimate(new Pose2d(0, 0, Math.toRadians(0)));

        waitForStart();

        if (isStopRequested()) return;
        telemetry.addData("Path:", graph.printPathFinder(path));
        telemetry.update();

        // Run A* Path
        follower.followPath(drive);

    }
}
