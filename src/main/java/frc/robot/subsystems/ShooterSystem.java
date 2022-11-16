package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSystem extends SubsystemBase {
    
    public MotorController shooterMotor = new WPI_TalonFX(Constants.shooterMotorID); 

    public void setShooterMotorPower(double power) {

        shooterMotor.set(power); 

    }

    public void shooterMotorPowerSetZero() {

        shooterMotor.set(0.0); 

    }

}
