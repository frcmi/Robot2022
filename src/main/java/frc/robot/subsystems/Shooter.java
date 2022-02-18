// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;


import edu.wpi.first.math.controller.PIDController;


public class Shooter extends SubsystemBase {
  /** Creates a new Shooter. */
  public WPI_TalonFX shootMotorTalon = new WPI_TalonFX(7);
  public MotorController shootMotor = shootMotorTalon; 
  PIDController shootMotorPID = new PIDController(1, 1, 0); //adjust

  public Shooter(){
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void set() {
    shootMotor.set(shootMotorPID.calculate(shootMotor.get()));
  }

  public void stop() {
    shootMotor.set(0.0);
  }

  public void changeSetpoint(double setpoint) {
    shootMotorPID.setSetpoint(setpoint);
  }

}
