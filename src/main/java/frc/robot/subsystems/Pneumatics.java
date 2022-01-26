package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;

public class Pneumatics extends SubsystemBase {
  public DoubleSolenoid doubleSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 1, 2); //dont know module type???
  // public double speed = 1;

  public Pneumatics() {
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setSolenoid(Value v){
    doubleSolenoid.set(v);
  }

  public Value getSolenoid(){
    return doubleSolenoid.get();
  }
  
  public void stop(){
    
  }
}
