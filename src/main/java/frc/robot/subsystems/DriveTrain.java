package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.Robot;
import frc.robot.subsystems.*;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
public class DriveTrain extends SubsystemBase {
  /** Creates a new DriveTrain. */
  
 public void ShiftingGearBox() {}
 public Solenoid solenoidLeft1 = new Solenoid(PneumaticsModuleType.CTREPCM, 0); //maybe need to change module type
 public Solenoid solenoidLeft2 = new Solenoid(PneumaticsModuleType.CTREPCM, 1);
 public Solenoid solenoidRight1 = new Solenoid(PneumaticsModuleType.CTREPCM, 2);
 public Solenoid solenoidRight2 = new Solenoid(PneumaticsModuleType.CTREPCM, 3);


  WPI_TalonFX rearLeft = new WPI_TalonFX(3);
  WPI_TalonFX middleLeft = new WPI_TalonFX(2); // new motor
  WPI_TalonFX frontLeft = new WPI_TalonFX(1);
  WPI_TalonFX frontRight = new WPI_TalonFX(4);
  WPI_TalonFX middleRight = new WPI_TalonFX(5); // new motor
  WPI_TalonFX rearRight = new WPI_TalonFX(6);
  MotorControllerGroup left = new MotorControllerGroup(frontLeft, middleLeft, rearLeft);
  MotorControllerGroup right = new MotorControllerGroup(frontRight, middleRight, rearRight);
  private DifferentialDrive myRobot = new DifferentialDrive(left,right);

  MotorController rL = rearLeft;
  MotorController mL = middleLeft;
  MotorController fL = frontLeft;
  MotorController rR = rearRight;
  MotorController mR = middleRight;
  MotorController fR = frontRight;
  
  
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
  public void shift(){ //toggles pistons for gear shifting
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

  public void setDefault() { //sets all solenoids to 
    solenoidLeft1.set(false);
    solenoidLeft2.set(false);
    solenoidRight1.set(false);
    solenoidRight2.set(false);
  }

  public void DriveToHub(){
    while (Robot.container.navx.isMoving()) {
      this.drive(1.0, 1.0);
    }
  }
}