package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Solenoid;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.*;
public class DriveTrain extends SubsystemBase {
  /** Creates a new DriveTrain. */
  
 public void ShiftingGearBox() {}
 public Solenoid solenoid = new Solenoid(0);

  WPI_TalonFX rearLeft = new WPI_TalonFX(3);
  WPI_TalonFX middleLeft = new WPI_TalonFX(2); // new motor
  WPI_TalonFX frontLeft = new WPI_TalonFX(1);
  WPI_TalonFX frontRight = new WPI_TalonFX(4);
  WPI_TalonFX middleRight = new WPI_TalonFX(5); // new motor
  WPI_TalonFX rearRight = new WPI_TalonFX(6);
  SpeedControllerGroup left = new SpeedControllerGroup(frontLeft, middleLeft, rearLeft);
  SpeedControllerGroup right = new SpeedControllerGroup(frontRight, middleRight, rearRight);
  private DifferentialDrive myRobot = new DifferentialDrive(left,right);

  SpeedController rL = rearLeft;
  SpeedController mL = middleLeft;
  SpeedController fL = frontLeft;
  SpeedController rR = rearRight;
  SpeedController mR = middleRight;
  SpeedController fR = frontRight;
  
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void drive(double l, double r) {
    myRobot.tankDrive(l, r);
  }
  public double getLeftMotors() {
      return left.get();
  }
  public double getRightMotors() {
      return right.get();
  }
  public void shift(){
    solenoid.set(!solenoid.get());
  }
  public boolean getValue() {
    if (solenoid.get() == true) {
      System.out.println("Speedy");
    } else {
      System.out.println("Tanky");
    }
    return solenoid.get();
  }
}