// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.subsystems.*;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutonomousPlanA extends SequentialCommandGroup {
  /** Creates a new AutonomousPlanA. */
  public AutonomousPlanA(DriveTrain drive, Intake intake, Shooter teleopShooter, Double delay) {
    // Add your commands in the addCommands() call, e.g.
    System.out.println("running autonomous");
    addRequirements(drive, intake, teleopShooter);
    addCommands(
      new SequentialCommandGroup(
        teleopShooter.setShooter().withTimeout(Constants.SPINUP_DELAY), 
        new ParallelCommandGroup(
          intake.intake().withTimeout(.25),
          teleopShooter.setShooter().withTimeout(.25)
        ),
        new WaitCommand(delay), 
        drive.taxi().withTimeout(1.75)
      )
    );
   }
}
