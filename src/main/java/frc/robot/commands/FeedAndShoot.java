// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.*;


// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class FeedAndShoot extends ParallelCommandGroup {
  /** Creates a new FeedAndShoot. */
  Feed feed;
  Shooter shooter;
  /** Creates a new FeedAndShoot. */
  public FeedAndShoot(Feed feed, Shooter shoot) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
    new ParallelRaceGroup(
      new SetShooter(shoot), 
      new WaitCommand(4)), 
    new SequentialCommandGroup(
      new WaitCommand(2), 
      new AutonomousFeed(1.5, feed)));
  }
}
