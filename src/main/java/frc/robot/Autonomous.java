 // Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.List;
import java.lang.Thread;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.*;


import frc.robot.commands.AutonomousFeed;
import frc.robot.commands.AutonomousPlanA;
import frc.robot.commands.DriveOutOfTarmac;
import frc.robot.commands.IntakeIn;
import frc.robot.commands.FeedAndShootLow;
import edu.wpi.first.wpilibj.smartdashboard.*;

import java.nio.file.Path;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.math.trajectory.TrajectoryUtil;
import edu.wpi.first.math.trajectory.Trajectory;
import java.io.*;

/** Add your docs here. */
public class Autonomous {

    public static final double escargotWaitTime = 5.0;
    public static double waitTimeChoose = 0.0;


    private static SendableChooser<ChooseAutoPath> chooseAutoPath = new SendableChooser<>();
    private static SendableChooser<ChooseAutoDelay> chooseAutoDelay = new SendableChooser<>();

    Trajectory trajectory = new Trajectory();
    String trajectoryJSON = "paths/2ball.wpilib.json";

    private static enum ChooseAutoPath {
        ESCARGOT, ONLYTAXI, ONLYSHOOT, ONEBALLAUTO, TWOBALLAUTO
    }

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
    public Autonomous() {
        try {
                Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON);
                trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
             } catch (IOException ex) {
                System.out.println("Unable to open trajectory");
             }
    }
    
    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        Command autoCommand = getSelectedAutonomousCommand();
        Command delayCommand = getSelectedDelayCommand();

        if (delayCommand == null) {
            return autoCommand;
        } else if (autoCommand == null) {
            return delayCommand;
        } else {
            return delayCommand.andThen(autoCommand);
        }
    }

    /** Returns the autonmous command selected in the Shuffleboard tab. */
    private Command getSelectedAutonomousCommand() {
        switch (chooseAutoPath.getSelected()) {
            case ESCARGOT:
                // change to wait for x seconds
                new WaitCommand(escargotWaitTime);
                return new DriveOutOfTarmac(RobotContainer.drive);
            case ONLYTAXI:
                return new DriveOutOfTarmac(RobotContainer.drive);
            case ONLYSHOOT:
                return new FeedAndShootLow(RobotContainer.feed, RobotContainer.teleopShooter);
            case ONEBALLAUTO:
                return new AutonomousPlanA(RobotContainer.drive, RobotContainer.feed, RobotContainer.teleopShooter, waitTimeChoose);

            default:
                //return new TrajectoryMaker(trajectory, RobotContainer.drive);
                return new AutonomousPlanA(RobotContainer.drive, RobotContainer.feed, RobotContainer.teleopShooter, waitTimeChoose);

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
        chooseAutoPath.setDefaultOption("One Ball Autonomous", ChooseAutoPath.ONEBALLAUTO);
        chooseAutoPath.addOption("Escargot", ChooseAutoPath.ESCARGOT);
        chooseAutoPath.addOption("Taxi Only", ChooseAutoPath.ONLYTAXI);
        chooseAutoPath.addOption("Two Ball Autonomous", ChooseAutoPath.TWOBALLAUTO);
        chooseAutoPath.addOption("Only Shoot One", ChooseAutoPath.ONLYSHOOT);

        
        autoLayout.add("AutoPath", chooseAutoPath).withWidget(BuiltInWidgets.kComboBoxChooser);

        chooseAutoDelay.setDefaultOption("0 sec", ChooseAutoDelay.NO_DELAY);
        chooseAutoDelay.addOption("2 sec", ChooseAutoDelay.DELAY_2_SECONDS);
        chooseAutoDelay.addOption("5 sec", ChooseAutoDelay.DELAY_5_SECONDS);
        autoLayout.add("Delay", chooseAutoDelay).withWidget(BuiltInWidgets.kComboBoxChooser);
        SmartDashboard.putData(chooseAutoPath);
        SmartDashboard.putData(chooseAutoDelay);
    } 
}