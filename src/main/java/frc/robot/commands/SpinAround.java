// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class SpinAround extends CommandBase {
  Timer timer = new Timer();
  boolean done = false;
  /** Creates a new SpinAround. */
  public SpinAround() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.container.drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    timer.start();
    while (timer.get() > 5) { //in seconds, change this value
      Robot.container.drive.drive(-1.0, 1.0);
    }
    done = true;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.container.drive.drive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return done;
  }
}
