// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.commands.*;
import frc.robot.Robot;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutonomousCommand extends SequentialCommandGroup {
  /** Creates a new AutonomousCommand. */
  public AutonomousCommand() {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand()); 
    //NEEDS A LOT OF WORK
    addCommands(new ParallelCommandGroup(new DriveToHub(), new ShootIfStopped()), new SpinAround(),
    new ParallelCommandGroup(new SeekBall(), new  ParallelCommandGroup(new IntakeIn(), new ConveyorIn())), 
    new SpinAround(), new ParallelCommandGroup(new DriveToHub(), new ShootIfStopped())); 
    //Can't be driveToHub in line 23, must navigate back to shooting position
  }
}
