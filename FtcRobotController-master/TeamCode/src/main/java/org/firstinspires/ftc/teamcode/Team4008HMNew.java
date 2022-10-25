package org.firstinspires.ftc.teamcode.BaseCode.New;


import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

import static java.lang.Thread.sleep;


public class Team4008HMNew
{

    public DcMotor DriveRightBack = null;
    public DcMotor DriveLeftBack = null;
    public DcMotor DriveLeftFront = null;
    public DcMotor DriveRightFront = null;

    public DcMotor Intake = null;
    public DcMotor IntakeWheel = null;
    public DcMotor DuckLeft = null;
    public DcMotor DuckRight = null;

    TouchSensor Touch;
    //public Servo Capper = null;
    public Servo NewCapper = null;
   // public RevBlinkinLedDriver Lights = null;
    //MAKE IT
    HardwareMap hwMap           =  null;
    //public ElapsedTime period  = new ElapsedTime();

   // public Team4008HMNew() {}

    //FIX AND USE IT
    public void Map(HardwareMap hardwareMap)
    {
        hwMap = hardwareMap;
        Touch = hardwareMap.get(TouchSensor.class, "Touch");
        DriveLeftFront = hwMap.get(DcMotor.class,"DriveLeftFront");
        DriveRightFront = hwMap.get(DcMotor.class,"DriveRightFront");
        DriveLeftBack = hwMap.get(DcMotor.class,"DriveLeftBack");
        DriveRightBack = hwMap.get(DcMotor.class,"DriveRightBack");
        NewCapper = hwMap.get(Servo.class,"NewCapper");
     //   Lights = hwMap.get(RevBlinkinLedDriver.class,"Lights");

        DriveLeftFront.setDirection(DcMotor.Direction.FORWARD);
        DriveLeftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        DriveLeftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        DriveRightFront.setDirection(DcMotor.Direction.REVERSE);
        DriveRightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        DriveRightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        DriveLeftBack.setDirection(DcMotor.Direction.FORWARD);
        DriveLeftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        DriveLeftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        DriveRightBack.setDirection(DcMotor.Direction.REVERSE);
        DriveRightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        DriveRightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        Intake = hwMap.get(DcMotor.class,"Intake");
        Intake.setDirection(DcMotorSimple.Direction.REVERSE);
        Intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        IntakeWheel = hwMap.get(DcMotor.class,"IntakeWheel");
        //IntakeLift.setDirection(DcMotor.Direction.REVERSE);

        DuckLeft = hwMap.get(DcMotor.class,"DuckLeft");
        DuckRight = hwMap.get(DcMotor.class,"DuckRight");
        DuckRight.setDirection(DcMotorSimple.Direction.REVERSE);

    }
}
