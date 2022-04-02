// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer;

public class DriveOutOfTarmac extends CommandBase {
  Timer timer = new Timer();
  boolean done = false;
  private DriveTrain drive;

  /** Creates a new DriveToHub. */
  public DriveOutOfTarmac(DriveTrain drive) {
    this.drive = drive;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(this.drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (timer.get() < 1.75) { // in seconds, change this value
      drive.drive(-.6, -.6);
    } else {
      done = true;
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drive.stop();
  }

  // Returns true when the command should end. MAKE IT STOP
  @Override
  public boolean isFinished() {
    return done;
  }
}
