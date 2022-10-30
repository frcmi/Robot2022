package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.kauailabs.navx.frc.AHRS;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import static frc.robot.Constants.*;

public class DriveTrain extends SubsystemBase {
  // Drivetrain and motor initialization
  private final WPI_TalonFX rearLeft = new WPI_TalonFX(REAR_LEFT_MOTOR_ID);
  private final WPI_TalonFX frontLeft = new WPI_TalonFX(FRONT_LEFT_MOTOR_ID);
  private final WPI_TalonFX frontRight = new WPI_TalonFX(FRONT_RIGHT_MOTOR_ID);
  private final WPI_TalonFX rearRight = new WPI_TalonFX(REAR_RIGHT_MOTOR_ID);

  private final MotorControllerGroup left = new MotorControllerGroup(frontLeft, rearLeft);
  private final MotorControllerGroup right = new MotorControllerGroup(frontRight, rearRight);
  private final DifferentialDrive diffDrive = new DifferentialDrive(left, right);

  public DriveTrain() {
    rearLeft.configClosedloopRamp(.5);
    rearRight.configClosedloopRamp(.5);
    frontLeft.configClosedloopRamp(.5);
    frontRight.configClosedloopRamp(.5);
    left.setInverted(true);
  }

  @Override
  public void periodic() {
  }

  // motors and driving
  public void curvyDrive(double xSpeed, double zRotation) {
    diffDrive.curvatureDrive(xSpeed, zRotation, false);
  }

  public void cheesyDrive(double xSpeed, double zRotation) {
    diffDrive.curvatureDrive(xSpeed, zRotation, true);
  }

  public CommandBase taxi () {
    return new RunCommand(() -> diffDrive.curvatureDrive(-.5, 0, false)).withTimeout(1.25);
  }

  public void stop() {
    diffDrive.tankDrive(0, 0);
  }
}