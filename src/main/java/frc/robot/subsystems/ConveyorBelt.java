package frc.robot.subsystems;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

public class ConveyorBelt extends SubsystemBase {
  public WPI_TalonFX conveyorMotorTalon = new WPI_TalonFX(7);
  public MotorController conveyorMotor = conveyorMotorTalon; 
  public double speed = 1;

  public ConveyorBelt() {
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void SetSpeed(double s){
    speed = s;
  }
}
