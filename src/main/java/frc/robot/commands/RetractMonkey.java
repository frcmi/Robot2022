// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;

public class RetractMonkey extends CommandBase {
  Monkey monkey;
  /** Creates a new RetractMonkey. */
  public RetractMonkey(Monkey monkey) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.monkey = monkey;
    addRequirements(this.monkey);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //monkey.resetEncoder();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    monkey.setPower(0.9);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    monkey.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
    //return monkey.getEncoder()<-0.3;
  }
}
