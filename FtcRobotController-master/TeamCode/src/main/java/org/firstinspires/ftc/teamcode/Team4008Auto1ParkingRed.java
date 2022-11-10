package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Team4008Auto1ParkingRed", group="4008")

public class Team4008Auto1ParkingRed extends LinearOpMode{
    Team4008HM2023 robot = new Team4008HM2023();
    ElapsedTime Time = new ElapsedTime();
    double multy = 0.3;

    @Override
    public void runOpMode() {
        robot.Map(hardwareMap);
        waitForStart();

        //Strafes Left
        double distance = 10;
        multy = 0.5;
        robot.DriveLeftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.DriveLeftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        double tick = (distance * 537.7)/(4 * Math.PI);
        Time.reset();
        robot.DriveRightFront.setPower(-multy);
        robot.DriveLeftFront.setPower(multy);
        robot.DriveRightBack.setPower(multy);
        robot.DriveLeftBack.setPower(-multy);
        while(opModeIsActive() && Time.milliseconds() < 2000 && robot.DriveLeftFront.getCurrentPosition() < tick) {
            telemetry.addData("Encoder Val", robot.DriveLeftFront.getCurrentPosition());
            telemetry.update();
        }
        robot.DriveRightFront.setPower(0);
        robot.DriveLeftFront.setPower(0);
        robot.DriveRightBack.setPower(0);
        robot.DriveLeftBack.setPower(0);
        robot.DriveLeftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.DriveLeftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        sleep(1000);

        //Strafes Right
        distance = 10;
        multy = 0.5;
        robot.DriveRightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.DriveRightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        tick = (distance * 537.7)/(4 * Math.PI);
        Time.reset();
        robot.DriveRightFront.setPower(multy);
        robot.DriveLeftFront.setPower(-multy);
        robot.DriveRightBack.setPower(-multy);
        robot.DriveLeftBack.setPower(multy);
        while(opModeIsActive() && Time.milliseconds() < 2000 && robot.DriveRightFront.getCurrentPosition() < tick) {
            telemetry.addData("Encoder Val", robot.DriveRightFront.getCurrentPosition());
            telemetry.update();
        }
        robot.DriveRightFront.setPower(0);
        robot.DriveLeftFront.setPower(0);
        robot.DriveRightBack.setPower(0);
        robot.DriveLeftBack.setPower(0);
        robot.DriveRightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.DriveRightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        //Drives Forward
        distance = 10;
        multy = 0.5;
        robot.DriveRightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.DriveRightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        tick = (distance * 537.7)/(4 * Math.PI);
        Time.reset();
        robot.DriveRightFront.setPower(-multy);
        robot.DriveLeftFront.setPower(-multy);
        robot.DriveRightBack.setPower(-multy);
        robot.DriveLeftBack.setPower(-multy);
        while(opModeIsActive() && Time.milliseconds() < 4000 && robot.DriveRightFront.getCurrentPosition() > -tick) {
            telemetry.addData("Encoder Val", robot.DriveRightFront.getCurrentPosition());
            telemetry.update();
        }
        robot.DriveRightFront.setPower(0);
        robot.DriveLeftFront.setPower(0);
        robot.DriveRightBack.setPower(0);
        robot.DriveLeftBack.setPower(0);
        robot.DriveRightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.DriveRightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        sleep(750);
    }
}
