package frc.robot;

public interface Controller {
    public double getThrottleFwd();
    public double getRotation();
    public boolean getButton(int ButtonId);
}