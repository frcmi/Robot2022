// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;

/** Add your docs here. */
public class Constants {
    //Drivetrain constants
    public static final int FRONT_LEFT_MOTOR_ID = 4;
    public static final int REAR_LEFT_MOTOR_ID = 3;
    public static final int FRONT_RIGHT_MOTOR_ID = 1;
    public static final int REAR_RIGHT_MOTOR_ID = 2;

    //Shooter constants
    public static final double AUTOSETPOINT = 0.75;
    public static final double TELEOPSETPOINT = 1.0;

    //Autnomous constants using preprogrammed names. CHANGE THEM!
    public static final double ksVolts = 0.22;
    public static final double kvVoltSecondsPerMeter = 1.98;
    public static final double kaVoltSecondsSquaredPerMeter = 0.2;
    public static final double kPDriveVel = 8.5;

    public static final double kMaxSpeedMetersPerSecond = 3;
    public static final double kMaxAccelerationMetersPerSecondSquared = 3;

    public static final float GEAR_RATIO = (float) 11/120; //1 falcon rotation = 11/120 wheel rotation
    public static final double WHEEL_RADIUS_METERS = 0.0762;
    public static final double DISTANCE_PER_WHEEL_ROTATION = 2 * WHEEL_RADIUS_METERS * Math.PI;
    public static final double DISTANCE_PER_MOTOR_ROTATION = GEAR_RATIO * DISTANCE_PER_WHEEL_ROTATION;


    public static final double kTrackwidthMeters = 0.69;
    public static final DifferentialDriveKinematics kDriveKinematics = new DifferentialDriveKinematics(kTrackwidthMeters);

    public static final double kRamseteB = 2;
    public static final double kRamseteZeta = 0.7;

    public static final int UNITS_PER_ROTATION = 2048;

}