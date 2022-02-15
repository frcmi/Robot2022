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
  private DriveTrain m_drive;
  private NetworkTable m_table;
  private Intake m_intake;
  private ConveyorBelt m_cBelt;
  private Feed m_feed;

  /** Creates a new AutonomousCommand. */
  public AutonomousPlanB(DriveTrain p_drive, NetworkTable p_table, Intake p_intake, ConveyorBelt p_cBelt, Feed p_feed) {
    m_drive = p_drive;
    m_table = p_table;
    m_intake = p_intake;
    m_cBelt = p_cBelt;
    m_feed = p_feed;
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    // NEEDS A LOT OF WORK
    addCommands(new ParallelCommandGroup(new SeekBall(m_drive, m_table)),
        new ParallelCommandGroup(new IntakeIn(m_intake), new ConveyorIn(m_cBelt)),
        new SpinAround(m_drive), new PathfindToStartingPosition(m_drive), new AutonomousFeed(5, m_feed)); // driveoutoftarmac
    // not actualy
    // driveoutoftarmac, pathfindtohub
    // Not sure if spinaround is needed. Probably would be factored into gyro angle
    // compared to start so figure that out
  }
}
