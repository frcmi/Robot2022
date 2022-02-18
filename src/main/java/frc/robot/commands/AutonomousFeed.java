// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.subsystems.*;

public class AutonomousFeed extends CommandBase {
  private Feed feed;
  Timer timer = new Timer();
  double seconds;
  boolean done = false;

  /** Creates a new AutonomousFeed. */
  public AutonomousFeed(double seconds, Feed feed) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.feed = feed;
    this.seconds = seconds;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    timer.start();
    while (timer.get() < seconds) { // in seconds, change this value
      feed.setPower(1.0);
    }
    done = true;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    feed.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return done;
  }
}
