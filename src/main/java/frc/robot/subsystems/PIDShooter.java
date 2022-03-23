// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.math.controller.PIDController;
import static frc.robot.Constants.*;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.button.NetworkButton;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;

public class PIDShooter extends PIDSubsystem {
  ShuffleboardTab tab = Shuffleboard.getTab("Shooter");
  NetworkTableEntry P = tab.add("P value", 0.90).getEntry();
  NetworkTableEntry I = tab.add("I value", 0).getEntry();
  NetworkTableEntry D = tab.add("D value", 0).getEntry();


  private final SimpleMotorFeedforward m_shooterFeedforward = new SimpleMotorFeedforward(kSVolts, kVVoltSecondsPerRotation);
  /** Creates a new Shooter. */
  public PIDShooter() {
    super(
        // The PIDController used by the subsystem
        new PIDController(SHOOTER_PID_TELEOP_kP, SHOOTER_PID_TELEOP_kI, SHOOTER_PID_TELEOP_kD));

  shooterMotorTalon.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor); //does this in auto and teleop shooter. Might be issue?
      setSetpoint(TELEOPSETPOINT);
  }

  @Override
  public void useOutput(double output, double setpoint) {
    // Use the output here
    shooterMotorTalon.setVoltage(output + m_shooterFeedforward.calculate(setpoint));
  }

  @Override
  public double getMeasurement() {
    // Return the process variable measurement here
    return shooterMotorTalon.getSelectedSensorVelocity();
  }

  public void stop() {
    shooterMotor.set(0.0);
  }

  public boolean atSetpoint() {
    return m_controller.atSetpoint();
  }

}
