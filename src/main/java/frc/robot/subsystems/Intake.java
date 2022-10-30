// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import static frc.robot.Constants.*;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

public class Intake extends SubsystemBase {
  /** Creates a new Shooter. */
  private final WPI_TalonFX m_intakeMotor = new WPI_TalonFX(INTAKE_MOTOR_ID);
  
  public Intake(){

  }

  //toggles intake power to a set value
  public CommandBase intake() {
    return new RunCommand(() -> m_intakeMotor.set(Constants.FEED_SPEED));
  }

  public CommandBase outtake() {
    return new RunCommand(() -> m_intakeMotor.set(-Constants.FEED_SPEED));
  }
  
  public void stop(){
    m_intakeMotor.set(0);
  }
}
