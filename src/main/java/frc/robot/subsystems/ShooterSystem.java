package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSystem extends SubsystemBase {
    
    public WPI_TalonFX shooterMotorTalonFX = new WPI_TalonFX(Constants.shooterMotorID); 
    public MotorController shooterMotor = shooterMotorTalonFX; 

    public void setShooterMotorPower(double power) {
        shooterMotor.set(power); 
    }
    
    public double getSensorVelocity() {
        return shooterMotorTalonFX.getSelectedSensorVelocity(); 
    }

    public double getPower() {
        return shooterMotorTalonFX.get();
    }

}
