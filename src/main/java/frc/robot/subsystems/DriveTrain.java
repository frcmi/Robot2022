package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.kauailabs.navx.frc.AHRS;
import com.ctre.phoenix.motorcontrol.*; //change this import to be specific to feedback device
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import static frc.robot.Constants.*;

public class DriveTrain extends SubsystemBase {
  /** Creates a new DriveTrain. */
  
 /*public Solenoid solenoidLeft1 = new Solenoid(PneumaticsModuleType.CTREPCM, 0); //maybe need to change module type
 public Solenoid solenoidLeft2 = new Solenoid(PneumaticsModuleType.CTREPCM, 1);
 public Solenoid solenoidRight1 = new Solenoid(PneumaticsModuleType.CTREPCM, 2);
 public Solenoid solenoidRight2 = new Solenoid(PneumaticsModuleType.CTREPCM, 3);*/


  //Drivetrain and motors
  WPI_TalonFX rearLeft = new WPI_TalonFX(REAR_LEFT_MOTOR_ID);
  WPI_TalonFX frontLeft = new WPI_TalonFX(FRONT_LEFT_MOTOR_ID);
  WPI_TalonFX frontRight = new WPI_TalonFX(FRONT_RIGHT_MOTOR_ID);
  WPI_TalonFX rearRight = new WPI_TalonFX(REAR_RIGHT_MOTOR_ID);
  MotorControllerGroup left = new MotorControllerGroup(frontLeft, rearLeft);
  MotorControllerGroup right = new MotorControllerGroup(frontRight, rearRight);
  private DifferentialDrive difDrive = new DifferentialDrive(left,right);

  //autonomous and encoders
  public AHRS ahrs = new AHRS();
  private final DifferentialDriveOdometry odometry = new DifferentialDriveOdometry(ahrs.getRotation2d());

  
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    odometry.update(ahrs.getRotation2d(), (rearLeft.getSelectedSensorPosition() + frontLeft.getSelectedSensorPosition())/2, (rearRight.getSelectedSensorPosition() + frontRight.getSelectedSensorPosition())/2);
    //maybe change left and right encoder positions to navx?? doubt it though... since it reads only aboslute distance
  }

  public void drive(double l, double r) {
    difDrive.tankDrive(l, r);
    //System.out.println(l+r);
  }
  public double getLeftMotors() {
    return left.get();
  }
  public double getRightMotors() {
      return right.get();
  }

  public boolean isMoving() {
    return ahrs.isMoving();
    /*if (ahrs.getWorldLinearAccelX() > 0.1 && ahrs.getWorldLinearAccelY() > 0.1 && ahrs.getWorldLinearAccelZ() > 0.1) {
      return true;
    } else {
      return false;
    }*/
  }

  public void setEncoders() { //need to set distance per pulse in constants and in here
    rearLeft.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    frontLeft.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    rearRight.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    frontRight.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
  }

  public Pose2d getPose() {
    return odometry.getPoseMeters();
  }

  public void resetOdometry(Pose2d pose) {
    resetEncoders();
    odometry.resetPosition(pose, ahrs.getRotation2d());
  }

  public void resetEncoders() {
    rearLeft.setSelectedSensorPosition(0);
    frontLeft.setSelectedSensorPosition(0);
    rearRight.setSelectedSensorPosition(0);
    frontRight.setSelectedSensorPosition(0);
  }

  public void zeroHeading() {
    ahrs.reset();
  }

  public double getHeading() {
    return ahrs.getRotation2d().getDegrees();
  }

  public double getTurnRate() {
    return -ahrs.getRate();
  }

  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds((rearLeft.getSelectedSensorVelocity() + frontLeft.getSelectedSensorVelocity())/2, 
    (rearRight.getSelectedSensorVelocity() + frontRight.getSelectedSensorVelocity())/2);
  }

  public void tankDriveVolts(double leftVolts, double rightVolts) {
    left.setVoltage(leftVolts);
    right.setVoltage(rightVolts);
    difDrive.feed();
  }

  /*public void shift(){ //toggles pistons for gear shifting
    solenoidLeft1.toggle();
    solenoidLeft2.toggle();
    solenoidRight1.toggle();
    solenoidRight2.toggle();
  }
  public boolean getValue() {
    if (solenoidLeft1.get() == true) {
      System.out.println("Speedy");
    } else {
      System.out.println("Tanky");
    }
    return solenoidLeft1.get();
  }

  public void setDefaultGear() { //sets all solenoids to 
    solenoidLeft1.set(false);
    solenoidLeft2.set(false);
    solenoidRight1.set(false);
    solenoidRight2.set(false);
  }*/
}