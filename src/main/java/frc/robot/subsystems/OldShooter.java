// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.math.controller.PIDController;
import static frc.robot.Constants.*;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

public class OldShooter extends SubsystemBase {
  /** Creates a new Shooter. */
  public WPI_TalonFX shootMotorTalon = new WPI_TalonFX(SHOOTER_MOTOR_ID);
  public MotorController shootMotor = shootMotorTalon; 

  public OldShooter(){
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void set(PIDController PIDCont) {
    System.out.println(shootMotor.get());
    shootMotor.set(PIDCont.calculate(shootMotor.get()));
  }

  public void stop() {
    shootMotor.set(0.0);
  }

  public void changeSetpoint(double setpoint, PIDController PIDCont) {
    PIDCont.setSetpoint(setpoint);
  }

}
