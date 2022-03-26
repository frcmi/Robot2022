// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.*;

public class SpinAround extends CommandBase {
  private DriveTrain drive;
  int multiplier;
  Timer timer = new Timer();
  boolean done = false;

  /** Creates a new SpinAround. */
  public SpinAround(DriveTrain drive, int multiplier) {
    this.drive = drive;
    this.multiplier = multiplier;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(this.drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    timer.start();
    if (drive.getLeftDisplacement() < 1) { // in m, change this value
      drive.drive((multiplier * -0.5), (multiplier * 0.5));
    } else {
      done = true;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drive.drive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return done;
  }
}
