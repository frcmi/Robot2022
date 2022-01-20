// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


public class Intake extends SubsystemBase {
  /** Creates a new Shooter. */
  private CANSparkMax intakeMotorNEO = new CANSparkMax(4, MotorType.kBrushless);
  public MotorController intakeMotor = intakeMotorNEO; 
  public Intake() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  //toggles intake power on and off
  
  public void setPower(double power) {
    intakeMotor.set(power);
  }
}