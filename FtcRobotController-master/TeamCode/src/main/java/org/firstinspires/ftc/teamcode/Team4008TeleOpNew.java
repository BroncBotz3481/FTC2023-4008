package org.firstinspires.ftc.teamcode.BaseCode.New;

import android.text.method.Touch;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Light;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.BaseCode.New.Team4008HMNew;

@TeleOp(name="Team4008TeleOpNew", group="4008")
public class Team4008TeleOpNew extends LinearOpMode
{
    Team4008HMNew robot  = new Team4008HMNew();
    enum Position {
        UP, // position 0
        DOWN, // position .9
        MIDDLE // position .5
    }
    Position position = Position.DOWN;

    @Override
    public void runOpMode()
    {
        robot.Map(hardwareMap);
        telemetry.addData("Say", "TeleOp Starting");
        telemetry.update();
        robot.NewCapper.setPosition(0.9);
        robot.DriveRightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.DriveRightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.DriveLeftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.DriveLeftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.DriveRightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.DriveRightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.DriveLeftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.DriveLeftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.Intake.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.Intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        //robot.Lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.BEATS_PER_MINUTE_PARTY_PALETTE);
        waitForStart();

        while (opModeIsActive()) {
            boolean speedslow = gamepad1.right_bumper;
            double mag = speedslow ? 0.3 : 1.0;

            boolean speedslow1 = gamepad1.left_bumper;
            double mag1 = speedslow1 ? 0.45 : 1.0;

            double y = gamepad1.left_stick_y; // Remember, this is reversed!
            double x = -gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            double rx = -gamepad1.right_stick_x;


            if (robot.Touch.isPressed()) {
                telemetry.addData("Touch", robot.Touch.isPressed());
                telemetry.update();
            }

            // hello
            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio, but only when
            // at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;

            telemetry.addData("RightFront", robot.DriveRightFront.getCurrentPosition());
            telemetry.addData("RightBack", robot.DriveRightBack.getCurrentPosition());
            telemetry.addData("LeftFront", robot.DriveLeftFront.getCurrentPosition());
            telemetry.addData("LeftBack", robot.DriveLeftBack.getCurrentPosition());
            telemetry.addData("Intake Encoder", robot.Intake.getCurrentPosition());
            telemetry.addData("Intake Power", robot.Intake.getPower());
            telemetry.update();

            robot.DriveLeftFront.setPower(frontLeftPower * mag * mag1);
            robot.DriveLeftBack.setPower(backLeftPower * mag * mag1);
            robot.DriveRightFront.setPower(frontRightPower * mag * mag1);
            robot.DriveRightBack.setPower(backRightPower * mag * mag1);

            robot.Intake.setPower(gamepad2.right_stick_y * 0.75);

            //Servo Capper
            if (gamepad2.dpad_up)
                position = Position.UP;
            else if (gamepad2.dpad_down)
                position = Position.DOWN;
            else if (gamepad2.dpad_right)
                position = Position.MIDDLE;

            if (position == Position.UP)
                robot.NewCapper.setPosition(0);
            else if (position == Position.DOWN)
                robot.NewCapper.setPosition(0.85);
            else if (position == Position.MIDDLE)
                robot.NewCapper.setPosition(0.6);

            //IntakeWheel (For picking up stuff)
            if (gamepad2.left_bumper) { // Outtake -> Power is positive
                robot.IntakeWheel.setPower(0.5);
            } else if (gamepad2.right_bumper) { // Intake -> power is negative
                robot.IntakeWheel.setPower(-1);
            } else {
                robot.IntakeWheel.setPower(0);
            }

            //Duck Spinners (DuckLeft)
            if (gamepad2.a) {
                robot.DuckLeft.setPower(-0.75);
            } else {
                robot.DuckLeft.setPower(0);
            }

            if (gamepad2.b) {
                robot.DuckRight.setPower(-0.75);
            } else {
                robot.DuckRight.setPower(0);
            }
            //Duck Spinners (DuckRight)
     /*       if (gamepad2.x) {
                robot.NewCapper.setPosition(-0.75);
            }
            else {
                robot.NewCapper.setPosition(0);
            }
            if (gamepad2.y) {
                robot.NewCapper.setPosition(0.75);
            }
            else {
                robot.NewCapper.setPosition(0); */

        }
    }
}