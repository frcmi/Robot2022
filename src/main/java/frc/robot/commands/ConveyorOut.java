// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.ConveyorBelt;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ConveyorOut extends CommandBase {
  private ConveyorBelt cBelt;

  /** Creates a new Blank. */
  public ConveyorOut(ConveyorBelt cBelt) {
    this.cBelt = cBelt;
    addRequirements(this.cBelt);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    cBelt.setPower(-0.50);
    System.out.println("conveyor out working");
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    cBelt.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
