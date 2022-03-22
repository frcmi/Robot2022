// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.OldShooter;
import frc.robot.subsystems.TeleopShooter;
import static frc.robot.Constants.*;

public class TeleopShootForButton extends CommandBase {
  private OldShooter shooter;
  /** Creates a new ShooterPower. */
  public TeleopShootForButton(OldShooter shooter) {
    this.shooter = shooter;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  shooter.set(90);
  }

 

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  shooter.set(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
