package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

public class ConveyorBelt extends SubsystemBase {
  
  private WPI_TalonFX shootMotorTalon = new WPI_TalonFX(4);
  public ConveyorBelt() {
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
