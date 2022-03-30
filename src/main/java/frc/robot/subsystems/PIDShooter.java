// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

//NOT USED. NO PID
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.math.controller.PIDController;
import static frc.robot.Constants.*;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;


public class PIDShooter extends PIDSubsystem {
  
  private final SimpleMotorFeedforward m_shooterFeedforward = new SimpleMotorFeedforward(kSVolts, kVVoltSecondsPerRotation);
  /** Creates a new Shooter. */
  public PIDShooter(double p, double i, double d) {
    super(
        // The PIDController used by the subsystem
        //new PIDController(SHOOTER_PID_TELEOP_kP, SHOOTER_PID_TELEOP_kI, SHOOTER_PID_TELEOP_kD));
        new PIDController(p, i, d)
        );


  shooterMotorTalon.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor); //does this in auto and teleop shooter. Might be issue?
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

}
