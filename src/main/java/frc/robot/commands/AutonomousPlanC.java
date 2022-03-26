// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.*;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutonomousPlanC extends SequentialCommandGroup {
  /** Creates a new AutonomousPlanC. */
  public AutonomousPlanC(DriveTrain drive, Intake intake, Feed feed, Shooter shooter) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addRequirements(drive, intake, feed, shooter);
    addCommands(new ParallelRaceGroup(new SetShooter(shooter), new SequentialCommandGroup(new WaitCommand(1), new AutonomousFeed(2, feed),new SpinAround(drive, 1), new AutoGetBall(drive, 1), new SpinAround(drive, -1), new AutoGetBall(drive, -1), new AutonomousFeed(2, feed))));
  }
}
