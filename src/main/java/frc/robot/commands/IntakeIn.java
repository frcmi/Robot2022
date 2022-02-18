// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.CommandBase;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class IntakeIn extends CommandBase {
  private Intake intake;

  public IntakeIn(Intake intake) {
    this.intake = intake;
    addRequirements(intake);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // System.out.println("intake in ran");
  }

  @Override
  public void execute() {
    intake.setPower(.10);
  }

  @Override
  public void end(boolean interrupted) {
    intake.stop();
    // System.out.println("intake in stopped");

  }

  // Returns true when the command should end. FIX THIS!!!
  @Override
  public boolean isFinished() {
    return false;
  }

}
