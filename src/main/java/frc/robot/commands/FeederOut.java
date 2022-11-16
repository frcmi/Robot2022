package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.FeederSystem;

public class FeederOut extends CommandBase{
    private final FeederSystem feederSystem;

    public FeederOut(FeederSystem newFeederSystem) {
        feederSystem = newFeederSystem;
        addRequirements(feederSystem);
    }

    @Override
    public void execute() {
        feederSystem.setPower(0.5);
    }

    @Override
    public void end(boolean interrupted) {
        feederSystem.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
