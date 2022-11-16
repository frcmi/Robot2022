package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;

public class XboxControl implements Controller {
    XboxController xboxController;
    public XboxControl(XboxController xboxController) {
        this.xboxController = xboxController;
    }
    public double getThrottleFwd() {
        return XboxController;
    }
    public double getRotation() {
        return 0;
    }
    public double getButton(int ButtonId) {
        return 0;
    }
}