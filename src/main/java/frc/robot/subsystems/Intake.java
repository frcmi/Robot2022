// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import static frc.robot.Constants.*;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

public class Intake extends SubsystemBase {
  /** Creates a new Shooter. */
  public WPI_TalonFX intakeMotorTalon = new WPI_TalonFX(INTAKE_MOTOR_ID);
  public MotorController intakeMotor = intakeMotorTalon; 
  public Intake() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  //toggles intake power to a set value
  
  public void setPower(double power) {
    intakeMotor.set(power);
  }
  
  public void stop(){
    intakeMotor.set(0);
  }
}
