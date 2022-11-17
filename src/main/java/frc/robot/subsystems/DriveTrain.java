// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

//import java.sql.Struct;
//import java.util.ResourceBundle.Control;
//import java.util.function.DoubleSupplier;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Controller;

public class DriveTrain extends SubsystemBase {
  Controller controller;

  private static WPI_TalonFX rightFrontTalonFX = new WPI_TalonFX(Constants.rightFrontPort);
  private static WPI_TalonFX rightBackTalonFX = new WPI_TalonFX(Constants.rightBackPort);
  private static WPI_TalonFX leftFrontTalonFX = new WPI_TalonFX(Constants.leftFrontPort);
  private static WPI_TalonFX leftBackTalonFX = new WPI_TalonFX(Constants.leftBackPort);
  private static MotorControllerGroup leftMotorGroup = new MotorControllerGroup(leftBackTalonFX, leftFrontTalonFX);
  private static MotorControllerGroup rightMotorGroup = new MotorControllerGroup(rightBackTalonFX, rightFrontTalonFX);

private static DifferentialDrive differentialDrive = new DifferentialDrive(leftMotorGroup, rightMotorGroup); 
  public DriveTrain(Controller controller) {
    this.controller = controller;


  }
  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    differentialDrive.arcadeDrive(controller.getThrottleFwd(), controller.getRotation());
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
