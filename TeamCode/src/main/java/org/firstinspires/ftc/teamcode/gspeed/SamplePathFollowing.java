package org.firstinspires.ftc.teamcode.gspeed;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

/* This opmode tells the robot to face forward
towards the blue alliance and navigate around three obstacles
 */
@TeleOp
public class SamplePathFollowing extends LinearOpMode {

    // A* Graph Class
    Graph graph = new Graph();
    Point start = new Point(1, 1);
    Point end = new Point(3, 6);

    double startHeadingDeg = 90;
    UnitConverter converter = new UnitConverter();

    PathBuilder pathbuilder = new PathBuilder(graph, start, end);
    Path path = pathbuilder.AStar();

    PathFollower follower = new PathFollower(path, start, startHeadingDeg);

    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        drive.setPoseEstimate(converter.unitConvertPose(start, startHeadingDeg));

        waitForStart();

        if (isStopRequested()) return;

        // Print A* Path
        telemetry.addData("Path:", graph.printPathFinder(path));
        telemetry.update();

        // Build the path (run it physically)
        drive.followTrajectorySequence(follower.followPath(drive));
    }
}
