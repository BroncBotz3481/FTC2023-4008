package org.firstinspires.ftc.teamcode;

import android.graphics.Color;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.SwitchableLight;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Team4008Auto1VisionPark", group="4008")

public class Team4008Auto1VisionPark extends LinearOpMode{


    Team4008HM2023 robot = new Team4008HM2023();
    ElapsedTime Time = new ElapsedTime();

    double multy = 0.15;
    int OPG = 0; // 1 = Orange, 2 = Green, 3 = Purple
    float[] hsvValues = new float[3];
    int count = 0;

    @Override
    public void runOpMode() {
        robot.Map(hardwareMap);
        double distance = 20;
        double tick;

        if (robot.ColorSensor instanceof SwitchableLight) {
            ((SwitchableLight)robot.ColorSensor).enableLight(true);
        }


        waitForStart();


        //Drive forward
        distance = 20;
        multy = 0.20;
       // robot.DriveLeftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      //  robot.DriveLeftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        tick = (distance * 537.7)/(4 * Math.PI);
        Time.reset();
        robot.DriveRightFront.setPower(multy);
        robot.DriveLeftFront.setPower(multy);
        robot.DriveRightBack.setPower(multy);
        robot.DriveLeftBack.setPower(multy);
        while(opModeIsActive() && Time.milliseconds() < 2500) {
            telemetry.addData("Encoder Val", robot.DriveLeftFront.getCurrentPosition());
           // telemetry.update();
        }
        robot.DriveRightFront.setPower(0);
        robot.DriveLeftFront.setPower(0);
        robot.DriveRightBack.setPower(0);
        robot.DriveLeftBack.setPower(0);
      //  robot.DriveLeftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      //  robot.DriveLeftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        sleep(1500);

        Time.reset();
        while (opModeIsActive() && count < 5) {
            if (robot.ColorSensor instanceof SwitchableLight) {
                SwitchableLight light = (SwitchableLight) robot.ColorSensor;
                light.enableLight(!light.isLightOn());
            }
            NormalizedRGBA colors = robot.ColorSensor.getNormalizedColors();

            Color.colorToHSV(colors.toColor(), hsvValues);

            telemetry.addLine()
                    .addData("H", "%.3f", hsvValues[0])
                    .addData("S", "%.3f", hsvValues[1])
                    .addData("V", "%.3f", hsvValues[2]);
            telemetry.addLine()
                    .addData("a", "%.3f", colors.alpha)
                    .addData("r", "%.3f", colors.red)
                    .addData("g", "%.3f", colors.green)
                    .addData("b", "%.3f", colors.blue);
            telemetry.update();
            /** We also display a conversion of the colors to an equivalent Android color integer.
             * @see Color */
            int color = colors.toColor();
            /*telemetry.addLine("in color: ")
                    .addData("color", color);
            telemetry.addLine("raw Android color: ")
                    .addData("a", "%02x", Color.alpha(color))
                    .addData("r", "%02x", Color.red(color))
                    .addData("g", "%02x", Color.green(color))
                    .addData("b", "%02x", Color.blue(color));*/

            float max = Math.max(Math.max(Math.max(colors.red, colors.green), colors.blue), colors.alpha);
            colors.red /= max;
            colors.green /= max;
            colors.blue /= max;
            color = colors.toColor();

           /* telemetry.addLine("normalized color:  ")
                    .addData("a", "%02x", Color.alpha(color))
                    .addData("r", "%02x", Color.red(color))
                    .addData("g", "%02x", Color.green(color))
                    .addData("b", "%02x", Color.blue(color));
            telemetry.update(); */
            count++;
        }


        Time.reset();
        while (opModeIsActive() && Time.milliseconds() < 300){
            int Hvalue = (int) hsvValues[0];
            if (Hvalue >= 0 && Hvalue <= 135){
                OPG = 1;
            }
            else if(Hvalue > 135 && Hvalue <= 162) {
                OPG = 2;
            }
            else{
                OPG = 3;
            }
        }
        //Drive forward
        distance = 20;
        multy = 0.15;
        //  robot.DriveLeftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //  robot.DriveLeftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        tick = (distance * 537.7)/(4 * Math.PI);
        Time.reset();
        robot.DriveRightFront.setPower(multy);
        robot.DriveLeftFront.setPower(multy);
        robot.DriveRightBack.setPower(multy);
        robot.DriveLeftBack.setPower(multy);
        while(opModeIsActive() && Time.milliseconds() < 1200) {
            telemetry.addData("Encoder Val", robot.DriveLeftFront.getCurrentPosition());
            //telemetry.update();
        }
        robot.DriveRightFront.setPower(0);
        robot.DriveLeftFront.setPower(0);
        robot.DriveRightBack.setPower(0);
        robot.DriveLeftBack.setPower(0);
        //  robot.DriveLeftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //  robot.DriveLeftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        sleep(1500);

        if(OPG == 1){
            //Strafe Left
            distance = 20;
            multy = 0.30;
           // robot.DriveLeftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
          //  robot.DriveLeftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            tick = (distance * 537.7)/(4 * Math.PI);
            Time.reset();
            robot.DriveRightFront.setPower(+multy);
            robot.DriveLeftFront.setPower(-multy);
            robot.DriveRightBack.setPower(-multy);
            robot.DriveLeftBack.setPower(multy);
            while(opModeIsActive() && Time.milliseconds() < 2250) {
                telemetry.addData("Encoder Val", robot.DriveLeftFront.getCurrentPosition());
                //telemetry.update();
            }
            robot.DriveRightFront.setPower(0);
            robot.DriveLeftFront.setPower(0);
            robot.DriveRightBack.setPower(0);
            robot.DriveLeftBack.setPower(0);
          //  robot.DriveLeftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
          //  robot.DriveLeftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            sleep(1500);
        }
        else if (OPG == 3){
            //Strafe Right
            distance = 20;
            multy = 0.30;
            // robot.DriveLeftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            //  robot.DriveLeftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            tick = (distance * 537.7)/(4 * Math.PI);
            Time.reset();
            robot.DriveRightFront.setPower(-multy);
            robot.DriveLeftFront.setPower(multy);
            robot.DriveRightBack.setPower(multy);
            robot.DriveLeftBack.setPower(-multy);
            while(opModeIsActive() && Time.milliseconds() < 2250) {
                telemetry.addData("Encoder Val", robot.DriveLeftFront.getCurrentPosition());
                //telemetry.update();
            }
            robot.DriveRightFront.setPower(0);
            robot.DriveLeftFront.setPower(0);
            robot.DriveRightBack.setPower(0);
            robot.DriveLeftBack.setPower(0);
            //  robot.DriveLeftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            //  robot.DriveLeftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            sleep(1500);

            //Back
            /*distance = 20;
            multy = 0.25;
            // robot.DriveLeftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            //  robot.DriveLeftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            tick = (distance * 537.7)/(4 * Math.PI);
            Time.reset();
            robot.DriveRightFront.setPower(multy);
            robot.DriveLeftFront.setPower(multy);
            robot.DriveRightBack.setPower(multy);
            robot.DriveLeftBack.setPower(multy);
            while(opModeIsActive() && Time.milliseconds() < 500 && robot.DriveLeftFront.getCurrentPosition() < tick) {
                telemetry.addData("Encoder Val", robot.DriveLeftFront.getCurrentPosition());
                //telemetry.update();
            }
            robot.DriveRightFront.setPower(0);
            robot.DriveLeftFront.setPower(0);
            robot.DriveRightBack.setPower(0);
            robot.DriveLeftBack.setPower(0);
            //  robot.DriveLeftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            //  robot.DriveLeftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            sleep(1000); */
        }
    }
}
