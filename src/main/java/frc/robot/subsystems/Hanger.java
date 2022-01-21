package frc.robot.subsystems;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

public class Hanger extends SubsystemBase {
  public WPI_TalonFX hangerMotorTalon = new WPI_TalonFX(9);
  public MotorController hangerMotor = hangerMotorTalon; 
  // public double speed = 1;

  public Hanger() {
    hangerMotorTalon.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setPower(double s){
    hangerMotor.set(s);
  }

  public double getPower(){
    return hangerMotor.get();
  }

  public void resetSensorValues(){
    hangerMotorTalon.setSelectedSensorPosition(0);
  }

  public void getSensorValues(){
    hangerMotorTalon.getSelectedSensorPosition();
  }
}
