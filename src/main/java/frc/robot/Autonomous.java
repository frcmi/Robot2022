 // Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.*;


import frc.robot.commands.AutonomousPlanA;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.smartdashboard.*;


/** Add your docs here. */
public class Autonomous {
    private final DriveTrain driveTrain;
    private final Intake intake;
    private final Shooter shooter;

    public static final double escargotWaitTime = 5.0;
    public static double waitTimeChoose = 0.0;

    private static SendableChooser<ChooseAutoDelay> chooseAutoDelay = new SendableChooser<>();

    private static enum ChooseAutoDelay {
        NO_DELAY(0),
        DELAY_2_SECONDS(2),
        DELAY_5_SECONDS(3);

        private double delay;

        ChooseAutoDelay(double delay) {
            this.delay = delay;
            waitTimeChoose = delay;
        }

        public double getDelay() {
            return delay;
        }
    }
    public Autonomous(DriveTrain dT, Intake i, Shooter s) {
        driveTrain = dT;
        intake = i;
        shooter = s;
    }
    
    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        Command autoCommand = new AutonomousPlanA(driveTrain, intake, shooter, waitTimeChoose);
        Command delayCommand = getSelectedDelayCommand();

        if (delayCommand == null) {
            return autoCommand;
        } else {
            return delayCommand.andThen(autoCommand);
        }
    }

    /**
     * Returns a command to wait the period of time selected in the Shuffleboard
     * tab, or null if no delay is selected.
     */
    private static Command getSelectedDelayCommand() {
        ChooseAutoDelay delayChoice = chooseAutoDelay.getSelected();

        if (delayChoice.getDelay() == 0) {
            return null;
        }

        return new WaitCommand(delayChoice.getDelay());
    }

    // this adds another tab which i don't want 
    public void addAutonomousShuffleboardTab() {
        ShuffleboardTab autoTab = Shuffleboard.getTab("Main");
        ShuffleboardLayout autoLayout = autoTab.getLayout("Main", BuiltInLayouts.kList)
                .withPosition(0, 0)
                .withSize(2, 2);

        chooseAutoDelay.setDefaultOption("0 sec", ChooseAutoDelay.NO_DELAY);
        chooseAutoDelay.addOption("2 sec", ChooseAutoDelay.DELAY_2_SECONDS);
        chooseAutoDelay.addOption("5 sec", ChooseAutoDelay.DELAY_5_SECONDS);
        autoLayout.add("Delay", chooseAutoDelay).withWidget(BuiltInWidgets.kComboBoxChooser);
        SmartDashboard.putData(chooseAutoDelay);
    } 
}