// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutonomousPlanA extends SequentialCommandGroup {

  /** Creates a new AutonomousPlanA. */
  public AutonomousPlanA(DriveTrain drive, Feed feed, Shooter shooter) {
    // Add your commands in the addCommands() call, e.g.
    addRequirements(drive, feed, shooter);
    addCommands(new ParallelCommandGroup(new AutonomousShooter(shooter), new AutonomousFeed(2, feed)), new DriveOutOfTarmac(drive));
  }
}
