// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import edu.wpi.first.math.controller.PIDController;

public class SeekBall extends CommandBase {
  double steeringAdjust = 0.0;
  double headingError = 0.0;
  final double Kp = 1.0; //CHANGE
  private Boolean done;
  //PIDController shootMotorPID = new PIDController(1, 1, 0); //adjust
  /** Creates a new SeekBall. */
  public SeekBall() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.container.drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (Robot.container.table.getEntry("tv").getDouble(0) == 0.0) {
      steeringAdjust = 0.3;
    } else {
      headingError = Robot.container.table.getEntry("tx").getDouble(0);
      steeringAdjust = Kp * headingError;
    }
    Robot.container.drive.drive(Robot.container.drive.getLeftMotors() +steeringAdjust, Robot.container.drive.getRightMotors() -steeringAdjust);
    
    done = true;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end. FIX THIS!!!
  @Override
  public boolean isFinished() {
    return done ? true : false;
  }
}
