// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.math.controller.PIDController;
import static frc.robot.Constants.*;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

public class TeleopShooter extends PIDSubsystem {
  public WPI_TalonFX shooterMotorTalon = new WPI_TalonFX(SHOOTER_MOTOR_ID);
  public MotorController shooterMotor = shooterMotorTalon; 
  private final SimpleMotorFeedforward m_shooterFeedforward =
  new SimpleMotorFeedforward(
      kSVolts, kVVoltSecondsPerRotation);
  /** Creates a new Shooter. */
  public TeleopShooter() {
    super(
        // The PIDController used by the subsystem
        new PIDController(SHOOTER_PID_TELEOP_kP, SHOOTER_PID_TELEOP_kI, SHOOTER_PID_TELEOP_kD));

      shooterMotorTalon.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
      setSetpoint(TELEOPSETPOINT);
  }

  @Override
  public void useOutput(double output, double setpoint) {
    // Use the output here
    shooterMotorTalon.setVoltage(output + m_shooterFeedforward.calculate(setpoint));
    
  }

  @Override
  public double getMeasurement() {
    // Return the process variable measurement here
    return shooterMotorTalon.getSelectedSensorVelocity();
  }

  public void stop() {
    shooterMotor.set(0.0);
  }

  public boolean atSetpoint() {
    return m_controller.atSetpoint();
  }

  public void changeSetpoint(double setpoint) {
    m_controller.setSetpoint(setpoint);
  }
}
