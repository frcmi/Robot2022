// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutonomousPlanA extends SequentialCommandGroup {
  private DriveTrain m_drive;
  private Feed m_feed;

  /** Creates a new AutonomousPlanA. */
  public AutonomousPlanA(DriveTrain p_drive, Feed p_feed) {
    m_feed = p_feed;
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    m_drive = p_drive;
    addRequirements(m_drive);
    addCommands(new AutonomousFeed(2, m_feed), new DriveOutOfTarmac(m_drive));
  }
}
