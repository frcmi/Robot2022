// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.PWMTalonFX;

public class Intake extends SubsystemBase {
  /** Creates a new Shooter. */
  private PWMTalonFX IntakeMotorTalon = new PWMTalonFX(4);
  public SpeedController IntakeMotor = IntakeMotorTalon; 
  public Intake() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  int power = 1;
  int currentpower = 0;
  //toggles intake power on and off
  public void toggle() {
    if(currentpower!=power) {
      currentpower = power;
    }
    else {
      currentpower = 0;
    }
  }
  public void setPower(int power) {
    this.power = power;
  }
}
