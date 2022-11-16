package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class FeederSystem extends SubsystemBase {
    public MotorController feederMotorController = new WPI_TalonFX(Constants.feederMotorID);;

    public void setPower(double power) {
        feederMotorController.set(power);
    }

    public double getPower() {
        return feederMotorController.get();
    }

    public void stop() {
        feederMotorController.set(0);
    }
}
