// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.controller.PIDController;

/** Add your docs here. */
public class Constants {
    //Motor IDs
    public static final int FRONT_LEFT_MOTOR_ID = 4;
    public static final int REAR_LEFT_MOTOR_ID = 3;
    public static final int FRONT_RIGHT_MOTOR_ID = 1;
    public static final int REAR_RIGHT_MOTOR_ID = 2;

    public static final int INTAKE_MOTOR_ID = 8;
    public static final int CBELT_MOTOR_ID = 5;

    public static final int FEED_MOTOR_ID = 6;
    public static final int SHOOTER_MOTOR_ID = 7;

    //Drivetrain constants
    public static final int UNITS_PER_ROTATION = 2048; //not needed??

    public static final float GEAR_RATIO = (float) 11/120; //1 falcon rotation = 11/120 wheel rotation
    public static final double WHEEL_RADIUS_METERS = 0.0762;
    public static final double DISTANCE_PER_WHEEL_ROTATION = 2 * WHEEL_RADIUS_METERS * Math.PI;
    public static final double DISTANCE_PER_MOTOR_ROTATION = GEAR_RATIO * DISTANCE_PER_WHEEL_ROTATION;

    //Shooter constants
    public static final double AUTOSETPOINT = 0.75;
    public static final double TELEOPSETPOINT = 1.0;

    public static final PIDController SHOOTER_PID_AUTO = new PIDController(1, 1, 0); //adjust
    public static final PIDController SHOOTER_PID_TELEOP = new PIDController(1, 1, 0); //adjust

    //Autnomous constants using preprogrammed names. CHANGE THEM!
    public static final double ksVolts = 0.22;
    public static final double kvVoltSecondsPerMeter = 1.98;
    public static final double kaVoltSecondsSquaredPerMeter = 0.2;
    public static final double kPDriveVel = 8.5;

    public static final double kMaxSpeedMetersPerSecond = 3;
    public static final double kMaxAccelerationMetersPerSecondSquared = 3;

    public static final double kTrackwidthMeters = 0.69; //dunno what this is
    public static final DifferentialDriveKinematics kDriveKinematics = new DifferentialDriveKinematics(kTrackwidthMeters);

    public static final double kRamseteB = 2;
    public static final double kRamseteZeta = 0.7;
}
