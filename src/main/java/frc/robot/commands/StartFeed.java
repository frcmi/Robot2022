// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import frc.robot.subsystems.Feed;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class StartFeed extends CommandBase {
  private Feed feed;

  /** Creates a new Blank. */
  public StartFeed(Feed feed) {
    this.feed = feed;
    addRequirements(this.feed);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    feed.setPower(-0.2); //change this later during testing
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    feed.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
