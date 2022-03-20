// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;

import static frc.robot.Constants.*;

public class AutoShooter extends PIDSubsystem {
  private final SimpleMotorFeedforward m_shooterFeedforward = new SimpleMotorFeedforward(kSVolts, kVVoltSecondsPerRotation);
  /** Creates a new AutoShooter. */
  public AutoShooter() {
    super(
        // The PIDController used by the subsystem
        new PIDController(SHOOTER_PID_AUTO_kP, SHOOTER_PID_AUTO_kI, SHOOTER_PID_AUTO_kD));
    setSetpoint(AUTOSETPOINT);

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

}
