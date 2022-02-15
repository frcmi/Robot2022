// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.ConveyorBelt;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ConveyorOut extends CommandBase {
  // private MotorController conveyorMotor;
  private ConveyorBelt conveyorBelt;
  private Boolean done;
  // private double speed; 

  /** Creates a new Blank. */
  public ConveyorOut() {
     addRequirements(Robot.container.conveyorBelt);
      conveyorBelt = Robot.container.conveyorBelt;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    conveyorBelt.setPower(-1);
    done = true;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    conveyorBelt.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return done ? true : false;
  }
}
