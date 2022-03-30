// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.


//NOT USED
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.networktables.NetworkTable;

public class SeekBall extends CommandBase {
  private DriveTrain drive;
  private NetworkTable table;

  double steeringAdjust = 0.0;
  double headingError = 0.0;
  final double Kp = 1.0; // CHANGE
  boolean done = false;
  boolean foundBall = false;

  /** Creates a new SeekBall. */
  public SeekBall(DriveTrain drive, NetworkTable table) {
    this.drive = drive;
    this.table = table;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(this.drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() { 
    if (table.getEntry("tv").getDouble(0) == 0.0) {
      steeringAdjust = 0.3;
    } 
    if (table.getEntry("tv").getDouble(0) != 0.0) {
      headingError = table.getEntry("tx").getDouble(0); //test how much off by default from camera placement
      steeringAdjust = Kp * headingError;
      foundBall = true;
    }
    drive.drive(drive.getLeftMotors() +steeringAdjust, drive.getRightMotors() -steeringAdjust);
    //Maybe need a timer for it to move forward just for a bit more? after ball gets too close and cannot be detected Need to test
    if (foundBall && table.getEntry("tv").getDouble(0) == 0) {
      done = true;
    } //for when the ball gets too close to be seen by the limelight but we still need to go forward
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drive.stop();
  }

  // Returns true when the command should end. FIX THIS!!!
  @Override
  public boolean isFinished() {
    return done;
  }
}
