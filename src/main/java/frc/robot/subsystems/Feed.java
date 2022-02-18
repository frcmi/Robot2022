package frc.robot.subsystems;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

public class Feed extends SubsystemBase {
  public WPI_TalonFX feedMotorTalon = new WPI_TalonFX(6);
  public MotorController feedMotor = feedMotorTalon; 

  public Feed() {
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setPower(double s){
    feedMotor.set(s);
  }

  public double getPower(){
    return feedMotor.get();
  }

  public void stop(){
    feedMotor.set(0);
  }
}
