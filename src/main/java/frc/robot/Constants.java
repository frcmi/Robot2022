// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
//import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

/** Add your docs here. */
public class Constants {
    //Motor IDs
    public static final int FRONT_LEFT_MOTOR_ID = 4;
    public static final int REAR_LEFT_MOTOR_ID = 3;
    public static final int FRONT_RIGHT_MOTOR_ID = 1;
    public static final int REAR_RIGHT_MOTOR_ID = 2;

    public static final int INTAKE_MOTOR_ID = 6;

    public static final int FEED_MOTOR_ID = 6;
    public static final int SHOOTER_MOTOR_ID = 5;

    public static final int HANGER_MOTOR_ID = 0;
    public static final float GEAR_RATIO_HANGER = (float) 1/36; //1 falcon rotation = 1/36 wheel rotation

    //Drivetrain constants
    public static final int UNITS_PER_ROTATION = 2048; //not needed??
    public static final double SPEED_MULTIPLIER = 1.0;
    public static final double ROTATION_MULTIPLIER = .65;

    public static final float GEAR_RATIO_DRIVETRAIN = (float) 11/120; //1 falcon rotation = 11/120 wheel rotation
    public static final double WHEEL_RADIUS_METERS = 0.0762;
    public static final double DISTANCE_PER_WHEEL_ROTATION = 2 * WHEEL_RADIUS_METERS * Math.PI;
    public static final double DISTANCE_PER_MOTOR_ROTATION = GEAR_RATIO_DRIVETRAIN * DISTANCE_PER_WHEEL_ROTATION;

    //Shooter constants
    public static WPI_TalonFX shooterMotorTalon = new WPI_TalonFX(SHOOTER_MOTOR_ID);
    public static MotorController shooterMotor = shooterMotorTalon; 
    public static final double SHOOT_HIGH_SPEED = -0.45;
    public static final double SHOOT_LOW_SPEED = -0.25;
    
    //Feeder constants
    public static final double SPIT_SPEED = 0.35;
    public static final double FEED_SPEED = -0.35;

    //Intake constants
    public static final double INTAKE_SPEED = -.35;
    public static final double OUTTAKE_SPEED = -.35;

}
