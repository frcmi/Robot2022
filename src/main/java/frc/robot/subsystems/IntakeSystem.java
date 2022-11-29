package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSystem extends SubsystemBase{

    public MotorController intakeMotor = new WPI_TalonFX(Constants.intakeMotorID); 

    
    //Sets the power of the intakeMotor
    public void intakeMotorSetPower(double power) { 
        intakeMotor.set(power); 
    }

    //gets intakeMotor power
    public double getIntakeMotorPower() {
        return intakeMotor.get();
    }

}
