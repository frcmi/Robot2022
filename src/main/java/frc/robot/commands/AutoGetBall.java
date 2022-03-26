// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;

public class AutoGetBall extends CommandBase {
  DriveTrain drive;
  int multiplier;
  /** Creates a new AutoGetBall. */
  public AutoGetBall(DriveTrain drive, int multiplier) {
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
    //0.0765 m between center and edge of lower hub 7.77 m between center and where balls are-> 7.6935 m between front of hub and edge of ball circle
    //We start 0.61 m from tip of bot (w bumpers) from edge of hub -> 7.0835 m from front of bot to edge of ball circle
    //shoot ball, turn around towards ball, drive to get ball, turn back around, go forward to shoot position, shoot

    if(drive.getLeftDisplacement() < 7.0835 && drive.getLeftDisplacement() < 7.0835) {
      drive.drive((multiplier * 0.5), (multiplier * 0.5));
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
