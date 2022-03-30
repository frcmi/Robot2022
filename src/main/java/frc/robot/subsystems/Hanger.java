// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import static frc.robot.Constants.*;

public class Hanger extends SubsystemBase {
  public WPI_TalonFX hanger = new WPI_TalonFX(HANGER_MOTOR_ID);
  public MotorController hangerMotor = hanger; 
  /** Creates a new Hanger. */
  public Hanger() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setPower(double power) {
    hangerMotor.set(power);
  }

  public void stop() {
    hangerMotor.set(0);
  }
}
