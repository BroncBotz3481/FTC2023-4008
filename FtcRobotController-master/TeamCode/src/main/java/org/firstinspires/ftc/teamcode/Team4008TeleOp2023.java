package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name = "Team4008TeleOp2023", group = "4008")
public class Team4008TeleOp2023 extends LinearOpMode {
    Team4008HM2023 robot = new Team4008HM2023();


    @Override
    public void runOpMode() {
        robot.Map(hardwareMap);
        telemetry.addData("Say", "TeleOp Starting");
        telemetry.update();
        robot.DriveRightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.DriveRightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.DriveLeftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.DriveLeftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.DriveRightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.DriveRightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.DriveLeftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.DriveLeftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.IntakeLeft.setPosition(0.3);

        int count = 0;
        //robot.Lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.BEATS_PER_MINUTE_PARTY_PALETTE);
        waitForStart();

        while (opModeIsActive()) {
            boolean speedslow = gamepad1.right_bumper;
            double mag = speedslow ? 0.5 : 1.0;

            double y = gamepad1.left_stick_y; // Remember, this is reversed!
            double x = -gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            double rx = -gamepad1.right_stick_x;


          /*  if (robot.Touched.isPressed()) {
                telemetry.addData("Touch", robot.Touched.isPressed());
                telemetry.update();
            } */

            // hello
            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio, but only when
            // at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1.5);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;

            telemetry.addData("RightFront", robot.DriveRightFront.getCurrentPosition());
            telemetry.addData("RightBack", robot.DriveRightBack.getCurrentPosition());
            telemetry.addData("LeftFront", robot.DriveLeftFront.getCurrentPosition());
            telemetry.addData("LeftBack", robot.DriveLeftBack.getCurrentPosition());
            telemetry.update();

            robot.DriveLeftFront.setPower(frontLeftPower * mag);
            robot.DriveLeftBack.setPower(backLeftPower * mag);
            robot.DriveRightFront.setPower(frontRightPower * mag);
            robot.DriveRightBack.setPower(backRightPower * mag);

            double TurretPower = gamepad2.right_stick_x*0.5;
            double ElevatorPower = gamepad2.left_stick_y;
            robot.Turret.setPower(TurretPower);
            robot.Elevator.setPower(-ElevatorPower);
            // a is open, b is close
            if (gamepad2.b) {
                robot.IntakeLeft.setPosition(0.8);
            }
            if (gamepad2.a){
                robot.IntakeLeft.setPosition(0.3);
            }

            if(gamepad2.y){
                robot.Arm.setPower(.9);
            }
            else if (gamepad2.x){
                robot.Arm.setPower(-1);
            }
            else {
                robot.Arm.setPower(0);
            }
        }
    }
}