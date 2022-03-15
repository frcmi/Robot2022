// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

import frc.robot.subsystems.*;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutonomousPlanB extends SequentialCommandGroup {


  /** Creates a new AutonomousCommand. */
  public AutonomousPlanB(DriveTrain drive, NetworkTable table, Intake intake, Feed feed, Shooter shooter) {
 
    addRequirements(drive, intake, feed, shooter);
    // Add your commands in the addCommands() call, e.g.
    addCommands(new ParallelCommandGroup(new AutonomousShooter(shooter), new SeekBall(drive, table),
       new IntakeIn(intake)),
        new SpinAround(drive), new PathfindToStartingPosition(drive), new AutonomousFeed(5, feed));
    // Not sure if spinaround is needed. Probably would be factored into gyro angle
  }
}
