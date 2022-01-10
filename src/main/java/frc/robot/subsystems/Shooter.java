// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.PWMTalonFX;

public class Shooter extends SubsystemBase {
  /** Creates a new Shooter. */
  private PWMTalonFX shootMotorTalon = new PWMTalonFX(4);
  public SpeedController shootMotor = shootMotorTalon; 
  public Shooter() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void shoot() {
    shootMotor.set(1.0);
  }
}
