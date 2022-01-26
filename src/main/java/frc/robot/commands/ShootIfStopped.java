// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ShootIfStopped extends CommandBase {
  /** Creates a new ShootIfStopped. This is a command specifically for autonomous*/ 
  public ShootIfStopped() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.container.shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (!Robot.container.navx.isMoving()) {
      Robot.container.feed.setPower(1.0); //change
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.container.shooter.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
