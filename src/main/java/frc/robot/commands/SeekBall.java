// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.networktables.NetworkTable;

public class SeekBall extends CommandBase {
  private DriveTrain m_drive;
  private NetworkTable m_table;

  double steeringAdjust = 0.0;
  double headingError = 0.0;
  final double Kp = 1.0; // CHANGE
  // PIDController shootMotorPID = new PIDController(1, 1, 0); //adjust

  /** Creates a new SeekBall. */
  public SeekBall(DriveTrain p_DriveTrain, NetworkTable p_table) {
    m_drive = p_DriveTrain;
    m_table = p_table;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() { // Find way for this to run repeatedly
    if (m_table.getEntry("tv").getDouble(0) == 0.0) {
      steeringAdjust = 0.3;
    } else {
      headingError = m_table.getEntry("tx").getDouble(0);
      steeringAdjust = Kp * headingError;
    }
    m_drive.drive(m_drive.getLeftMotors() + steeringAdjust,
        m_drive.getRightMotors() - steeringAdjust);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end. FIX THIS!!!
  @Override
  public boolean isFinished() {
    return false;
  }
}
