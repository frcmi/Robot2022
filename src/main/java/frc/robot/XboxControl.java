package frc.robot;

import edu.wpi.first.wpilibj.XboxController;

public class XboxControl implements Controller {
    XboxController xboxController;
    public XboxControl(XboxController xboxController) {
        this.xboxController = xboxController;
    }
    public double getThrottleFwd() {
        return xboxController.getLeftY();
    }
    public double getRotation() {
        return xboxController.getLeftX();
    }
    public boolean getButton(int ButtonId) {
        return xboxController.getRawButton(ButtonId);
    }
}