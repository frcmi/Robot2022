// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import static frc.robot.Constants.*;

public class Monkey extends SubsystemBase {
  public WPI_VictorSPX monkey = new WPI_VictorSPX(7);
  public MotorController monkeyMotor = monkey; 
  /** Creates a new Monkey. */
  public Monkey() {
   // monkey.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
   // monkey.setSelectedSensorPosition(0);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  // public double getEncoder() {
  //   //return monkey.getSelectedSensorPosition();
  // }

  // public void resetEncoder() {
  //   monkey.setSelectedSensorPosition(0);
  // }

  public void setPower(double power) {
    monkeyMotor.set(power);
  }

  public void stop() {
    monkeyMotor.set(0);
  }
}
