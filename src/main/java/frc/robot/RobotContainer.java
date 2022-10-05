// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Button;

//import edu.wpi.first.wpilibj.PneumaticsModuleType;
//import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.XboxController;


import frc.robot.commands.*;
import frc.robot.subsystems.*;

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
  public XboxController xbox = new XboxController(0);
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
              //     _=====_                               _=====_
          //      / _____ \                             / _____ \
          //    +.-'__5___'-.---------------------------.-'__6___'-.+
          //   /   |  0   |  '.pretend it's a 360 controller       .'  |  _  |   \
          //  / ___| /|\ |___ \                     / ___| /4\ |___ \
          // / |      |      | ;  __           _   ; | _         _ | ;                 
          // | | <---   ---> | | |__|         |_:> | ||3|       (2)| |          left stick <>=0
          // | |___   |   ___|       7          8 ; |___       ___| ;           left stick up down = 1
          // |\    | \|/ |    /  _     ___      _   \    | (1) |    /|          right stick <>=4
          // | \   |__180___|  .','" "', |___|  ,'" "', '.  |_____|  .' |       right stick up down = 5
          // |  '-.______.-' /       \ANALOG/       \  '-._____.-'   |          left trigger = 2
          // |               |   9    |------|  10    |                |        right trigger = 1
          // |              /\       /      \       /\               |
          // |             /  '.___.'        '.___.'  \              |
          // |            /                            \             |
          //  \          /                              \           /
          //   \________/                                \_________/
  

    //Xbox control scheme Hutton
    //JoystickButton feed = new JoystickButton(xbox, 6); //L1
    JoystickButton conveyorOutButton = new JoystickButton(xbox, 5);
    Button conveyorInButton = new Button(() -> xbox.getLeftTriggerAxis() >= 0.5);
    Button shooter = new Button(() -> xbox.getRightTriggerAxis() >= 0.3);
    //Button spinup = new Button(() -> xbox.getLeftTriggerAxis() >= 0.5);
    //Button extendMonkeyButton = new Button(() -> xbox.getPOV()==0 || xbox.getPOV()==45 || xbox.getPOV()==315);
    Button manualFeed = new Button(() -> xbox.getPOV()==135 || xbox.getPOV()==225 || xbox.getPOV()==180);
    JoystickButton extendMonkeyButton = new JoystickButton(xbox, 4);
    JoystickButton retractMonkeyButton = new JoystickButton(xbox, 1);
    JoystickButton feedAndShootLowButton = new JoystickButton(xbox,2);


  Trajectory trajectory = new Trajectory();
  String trajectoryJSON = "paths/2ball.wpilib.json";

    
  // Subsystems
  public static Intake intake = new Intake();
  public static DriveTrain drive = new DriveTrain();
  public static Shooter teleopShooter = new Shooter();
  public static Feed feed = new Feed();
  public static Monkey monkey = new Monkey();
  //Commands
  Autonomous autonomous = new Autonomous();
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
    try {
      Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON);
      trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
   } catch (IOException ex) {
      System.out.println("Unable to open trajectory");
   }
   autonomous.addAutonomousShuffleboardTab();
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
    conveyorOutButton.whileActiveContinuous(new ParallelCommandGroup(new IntakeOut(intake), new FeederOut(feed)));  
    shooter.whenHeld(new FeedAndShoot(feed, teleopShooter));
    //spinup.whileHeld(new ParallelCommandGroup(new SetShooter(teleopShooter),new SequentialCommandGroup(new WaitCommand(2), new StartEndCommand(() -> {xbox.setRumble(XboxController.RumbleType.kLeftRumble, 0.5);xbox.setRumble(XboxController.RumbleType.kRightRumble, 0.5);}, () -> {xbox.setRumble(XboxController.RumbleType.kLeftRumble, 0);xbox.setRumble(XboxController.RumbleType.kRightRumble, 0);}))));
    extendMonkeyButton.whileHeld(new ExtendMonkey(monkey));
    retractMonkeyButton.whileHeld(new RetractMonkey(monkey));
    manualFeed.whileHeld(new FeederIn(feed));
    feedAndShootLowButton.whenHeld(new FeedAndShootLow(feed, teleopShooter));
    // spinup.whileHeld(new SetShooter(teleopShooter));
    // shoot.whileHeld(new FeederIn(feed));
  }


  public void setTeleop() { //set to take joystick inputs 
    //drive.setDefaultCommand(joystickDrive);
    configureButtonBindings();
  }
  public void teleopPeriodic() {
    double xSpeed = xbox.getRawAxis(1);
    double zRotation = xbox.getRawAxis(4);
    
    // If in deadzone (±0.2), set to 0
    if (Math.abs(xSpeed) < 0.2)
      xSpeed = 0;
    if (Math.abs(zRotation) < 0.2)
      zRotation = 0;

    // 0 times something is still 0 and multipiers here simplifies deadzone
    xSpeed *= 0.45;
    zRotation *= 0.8;

    drive.cheesydrive(xSpeed, zRotation);
    // System.out.println(monkey.getEncoder());
  }
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return autonomous.getAutonomousCommand();
  }

  // public void joeyDrive() {
  //     if(xbox.getRawButton(5)){
  //        System.out.println("slide");
  //        if (xbox.getRawAxis(0) > 0){
  //          drive.drive(xbox.getRightTriggerAxis(), 
  //                              (-(xbox.getRightTriggerAxis())));
          
  //        } else if (xbox.getRawAxis(0) < 0) {
  //          drive.drive((-(xbox.getRightTriggerAxis())), 
  //                              xbox.getRightTriggerAxis()); 
  //        }
  //    } else {
  //      if(xbox.getRawButton(6)) {
  //        System.out.println("turbo");
  //        drive.drive(1,1);
  //      }
  //      else if(xbox.getRightTriggerAxis() > 0 || xbox.getLeftTriggerAxis() > 0){
        
  //        if (xbox.getRawAxis(0) > 0){
  //          drive.drive(0.65 * (xbox.getRightTriggerAxis() - xbox.getLeftTriggerAxis() + (0.5 * xbox.getRawAxis(0))), 
  //                              0.65 * (xbox.getRightTriggerAxis() - xbox.getLeftTriggerAxis()));
  //        } else {
  //          drive.drive(.65 * (xbox.getRightTriggerAxis() - xbox.getLeftTriggerAxis()), 
  //                              .65 * (xbox.getRightTriggerAxis() - xbox.getLeftTriggerAxis() + (-0.5 * xbox.getRawAxis(0))));
  //        }
  //      }
  //      else {
  //        drive.drive(-xbox.getRawAxis(0) * 0.5,-xbox.getRawAxis(0) * 0.5);
  //        drive.drive(0,0);
  //     } 
  //   }
  // }
}