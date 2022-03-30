// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.PneumaticsModuleType;
//import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

import frc.robot.commands.*;
import frc.robot.subsystems.*;
import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj2.command.button.Button;

import java.nio.file.Path;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.math.trajectory.TrajectoryUtil;
import edu.wpi.first.math.trajectory.Trajectory;
import java.io.*;


/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  // Limelight and values
  public static final NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  NetworkTableEntry tx = table.getEntry("tx");
  NetworkTableEntry ty = table.getEntry("ty");
  NetworkTableEntry ta = table.getEntry("ta");

  // Buttons and Joysticks
  XboxController xbox = new XboxController(0);
    /*Xbox control scheme Joey
    JoystickButton conveyorOutButton = new JoystickButton(xbox, 2); //b button
    JoystickButton conveyorInButton = new JoystickButton(xbox, 1); //a button
    JoystickButton feedButton = new JoystickButton(xbox, 3); //x
    JoystickButton toggleShooterButton = new JoystickButton(xbox, 4); //y
    //JoystickButton driveBack = new JoystickButton(xbox, 4);*/
    
    //Joystick control scheme
    // Joystick leftJoystick = new Joystick(1);
    // Joystick rightJoystick = new Joystick(0);
    // JoystickButton conveyorInButton = new JoystickButton(leftJoystick, 4);
    // JoystickButton conveyorOutButton = new JoystickButton(leftJoystick, 3);
    // JoystickButton feedButton = new JoystickButton(leftJoystick, 1);
    // JoystickButton selectPipelineButton = new JoystickButton(rightJoystick, 2);
    // JoystickButton toggleShooterButton = new JoystickButton(leftJoystick, 2);
  

    //Xbox control scheme VB
    JoystickButton feedShootButton = new JoystickButton(xbox, 6); //R1
    Trigger conveyorOutButton = new JoystickButton(xbox, XboxController.Button.kLeftBumper.value).and(new JoystickButton(xbox, XboxController.Button.kRightBumper.value)).whenActive(new IntakeIn(intake));
    JoystickButton conveyorInButton = new JoystickButton(xbox, 5); //L1

    //Xbox controls Tuck
    boolean getbool() {
      return true;
    }
    // Button spinup = new Button(() -> xbox.getLeftTriggerAxis() >= 0.5);
    // Button shoot = new Button(() -> xbox.getRightTriggerAxis() >= 0.5);

  Trajectory trajectory = new Trajectory();
  String trajectoryJSON = "paths/2ball.wpilib.json";

    
  // Subsystems
  private static Intake intake = new Intake();
  public static DriveTrain drive = new DriveTrain();
  private static Shooter teleopShooter = new Shooter();
  private static Feed feed = new Feed();
  
  //Commands
  //public final AutonomousPlanA autonomousCommand = new AutonomousPlanA(drive, feed, autoShooter, teleopShooter);
  //private AutonomousPlanB backupAutonomousCommand = new AutonomousPlanB(drive, table, intake, feed, autoShooter, teleopShooter);
  // public RunCommand joystickDrive = new RunCommand(() -> drive.drive(leftJoystick.getRawAxis(1), -rightJoystick.getRawAxis(1)), drive);
  public SetShooter setShooter = new SetShooter(teleopShooter);
  // public RunCommand joystickDrive = new RunCommand(() -> drive.drive(xbox.getRightTriggerAxis() * ((0.95 * 0.8 * xbox.getRawAxis(1)) + (0.95 * 0.8 * xbox.getRawAxis(2))),
  // -xbox.getRightTriggerAxis() * ((0.95 * 0.8 * xbox.getRawAxis(1)) - (0.95 * 0.8 * xbox.getRawAxis(2)))), drive);

  //public RunCommand runFlywheel = new RunCommand(() -> teleopShooter.enable(), teleopShooter);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
   * it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    //selectPipelineButton.whenPressed(new SelectPipeline(table));

    conveyorInButton.whileHeld(new IntakeIn(intake));
    conveyorOutButton.whileActiveContinuous(new SpitOut(intake, feed));
    feedShootButton.whenHeld(new FeedAndShoot(feed, teleopShooter)); 
    // spinup.whileHeld(new SetShooter(teleopShooter));
    // shoot.whileHeld(new FeederIn(feed));
  }


  public void setTeleop() { //set to take joystick inputs 
    //shooter.setDefaultCommand(runFlywheel);
    //drive.setDefaultCommand(joystickDrive);
    configureButtonBindings();
    setShooter.schedule();
  }
  public void teleopPeriodic() {

    // if(xbox.getRawButton(5)){
    //     System.out.println("slide");
    //     if (xbox.getRawAxis(0) > 0){
    //       drive.drive(xbox.getRightTriggerAxis(), 
    //                           (-(xbox.getRightTriggerAxis())));
          
    //     } else if (xbox.getRawAxis(0) < 0) {
    //       drive.drive((-(xbox.getRightTriggerAxis())), 
    //                           xbox.getRightTriggerAxis()); 
    //     }
    // } else {
    //   if(xbox.getRawButton(6)) {
    //     System.out.println("turbo");
    //     drive.drive(1,1);
    //   }
    //   else if(xbox.getRightTriggerAxis() > 0 || xbox.getLeftTriggerAxis() > 0){
        
    //     if (xbox.getRawAxis(0) > 0){
    //       drive.drive(0.65 * (xbox.getRightTriggerAxis() - xbox.getLeftTriggerAxis() + (0.5 * xbox.getRawAxis(0))), 
    //                           0.65 * (xbox.getRightTriggerAxis() - xbox.getLeftTriggerAxis()));
    //     } else {
    //       drive.drive(.65 * (xbox.getRightTriggerAxis() - xbox.getLeftTriggerAxis()), 
    //                           .65 * (xbox.getRightTriggerAxis() - xbox.getLeftTriggerAxis() + (-0.5 * xbox.getRawAxis(0))));
    //     }
    //   }
    //   else {
    //     drive.drive(-xbox.getRawAxis(0) * 0.5,-xbox.getRawAxis(0) * 0.5);
    //     drive.drive(0,0);
    //   } 
    // }
   /* if (Math.abs(xbox.getLeftY()) <= 0.2)  {
      drive.drive(0.8 * (-(xbox.getLeftY()) + xbox.getLeftX()),
          0.8 * (xbox.getLeftY() + xbox.getLeftX()));
    } else {
      drive.drive(0.8 * (-(xbox.getLeftY()) + 0.5 * xbox.getLeftX()),
          0.8 * ( + 0.5 * xbox.getLeftY()));
    }*/

    //VB's controls
    if(xbox.getRawButton(3)) { //turbo on x
      drive.drive(xbox.getLeftY(), xbox.getRightY());
    } else {
      drive.drive(0.6 * xbox.getLeftY(), 0.6 * xbox.getRightY());
    }
  }
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return new AutonomousPlanA(drive, feed, teleopShooter);
  }
}