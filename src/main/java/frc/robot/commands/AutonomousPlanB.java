// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutonomousPlanB extends SequentialCommandGroup {
  private DriveTrain drive;
  private NetworkTable table;
  private Intake intake;
  private ConveyorBelt cBelt;
  private Feed feed;

  /** Creates a new AutonomousCommand. */
  public AutonomousPlanB(DriveTrain drive, NetworkTable table, Intake intake, ConveyorBelt cBelt, Feed feed) {
    this.drive = drive;
    this.table = table;
    this.intake = intake;
    this.cBelt = cBelt;
    this.feed = feed;
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    // NEEDS A LOT OF WORK
    addCommands(new ParallelCommandGroup(new SeekBall(drive, table)),
        new ParallelCommandGroup(new IntakeIn(intake), new ConveyorIn(cBelt)),
        new SpinAround(drive), new PathfindToStartingPosition(drive), new AutonomousFeed(5, feed)); // driveoutoftarmac
    // not actualy
    // driveoutoftarmac, pathfindtohub
    // Not sure if spinaround is needed. Probably would be factored into gyro angle
  }
}
