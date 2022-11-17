package org.firstinspires.ftc.teamcode;

import android.graphics.Color;
import android.app.Activity;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.SwitchableLight;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Team4008Auto1VisonPark", group="4008")

public class Team4008Auto1VisonPark extends LinearOpMode{
    Team4008HM2023 robot = new Team4008HM2023();
    ElapsedTime Time = new ElapsedTime();
    double multy = 0.3;
    int OPG = 0;
    float[] hsvValues = new float[3];

    @Override
    public void runOpMode() {
        robot.Map(hardwareMap);
        NormalizedRGBA colors = robot.ColorSensor.getNormalizedColors();
        Color.colorToHSV(colors.toColor(), hsvValues);
        telemetry.addLine()
                .addData("H", "%.3f", hsvValues[0]);

        waitForStart();

        if (robot.ColorSensor instanceof SwitchableLight) {
            ((SwitchableLight)robot.ColorSensor).enableLight(true);
        }

        Time.reset();

        while (opModeIsActive() && Time.milliseconds() < 500){
            if (hsvValues[0] >= 50 && hsvValues[0] <= 65 ){
                OPG = 1;
            }
            else if(hsvValues[0] > 65 && hsvValues[0] <= 100){
                OPG = 2;
            }
            else{
                OPG = 3;
            }
        }
        if(OPG == 3){
            //Strafe Left
            double distance = 20;
            multy = 0.4;
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

            //Drive forward
            distance = 20;
            multy = 0.4;
            robot.DriveLeftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.DriveLeftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            tick = (distance * 537.7)/(4 * Math.PI);
            Time.reset();
            robot.DriveRightFront.setPower(multy);
            robot.DriveLeftFront.setPower(multy);
            robot.DriveRightBack.setPower(multy);
            robot.DriveLeftBack.setPower(multy);
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
        }
    }
}
