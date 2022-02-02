// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.*;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.CommandBase;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class IntakeIn extends CommandBase {
  private Intake intake;
  public IntakeIn() {
    addRequirements(Robot.container.intake);
    // Use addRequirements() here to declare subsystem dependencies.
    intake = Robot.container.intake;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  @Override
  public void execute() {
    Robot.container.intake.setPower(1.0);
  }
  
  @Override
  public void end(boolean interrupted) {
    Robot.container.intake.stop();
  }
// Returns true when the command should end. FIX THIS!!!
@Override
public boolean isFinished() {
  return false;
}

}

