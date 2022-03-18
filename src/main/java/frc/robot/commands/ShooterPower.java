// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;
import static frc.robot.Constants.*;

public class ShooterPower extends CommandBase {
  private Shooter shooter;
  /** Creates a new ShooterPower. */
  public ShooterPower(Shooter shooter) {
    this.shooter = shooter;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  shooter.set(SHOOTER_PID_TELEOP);
  }

  public void changeSetpoint(double TELEOPSETPOINT, PIDController SHOOTER_PID_TELEOP) {
    shooter.changeSetpoint(TELEOPSETPOINT, SHOOTER_PID_TELEOP);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  shooter.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
