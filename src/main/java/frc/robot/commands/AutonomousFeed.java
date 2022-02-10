// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.Timer;

public class AutonomousFeed extends CommandBase {
  Timer timer = new Timer();
  boolean done = false;
  /** Creates a new AutonomousFeed. */
  public AutonomousFeed() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.container.feed);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    timer.start();
    while (timer.get() < 2) { //in seconds, change this value
      Robot.container.feed.setPower(1.0);
    }
    done = true;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.container.feed.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return done;
  }
}
