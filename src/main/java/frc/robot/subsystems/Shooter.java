// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import static frc.robot.Constants.*;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

public class Shooter extends SubsystemBase {
  /** Creates a new Shooter. */
  private final WPI_TalonFX m_shooterMotor = new WPI_TalonFX(SHOOTER_MOTOR_ID);

  public Shooter(){

  }

  public void initDefaultCommand(){
    m_shooterMotor.set(0.0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void set(double speed) {
    m_shooterMotor.set(speed);
  }

  public void stop() {
    m_shooterMotor.set(0.0);
  }

  public CommandBase setShooter() {
    return new InstantCommand(() -> m_shooterMotor.set(Constants.SHOOT_SPEED));
  }

  public CommandBase stopCommand() {
    return new InstantCommand(() -> m_shooterMotor.set(0));
  }
}