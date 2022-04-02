// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;

public class ExtendMonkey extends CommandBase {
  Monkey hanger;
  /** Creates a new ExtendMonkey. */
  public ExtendMonkey(Monkey hanger) {
    this.hanger = hanger;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(this.hanger);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //hanger.resetEncoder();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    hanger.setPower(-0.9);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    hanger.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //return hanger.getEncoder() > 0.2;
    return false;
  }
}
