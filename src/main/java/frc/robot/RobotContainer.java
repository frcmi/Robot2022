// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.PneumaticsModuleType;
//import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.XboxController;

import frc.robot.commands.*;
import frc.robot.subsystems.*;
import static frc.robot.Constants.*;

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
    //Xbox control scheme
    XboxController xbox = new XboxController(0);
    JoystickButton conveyorOutButton = new JoystickButton(xbox, 2); //b button
    JoystickButton conveyorInButton = new JoystickButton(xbox, 1); //a button
    JoystickButton feedButton = new JoystickButton(xbox, 6); //right bumper
    JoystickButton toggleShooterButton = new JoystickButton(xbox, 3); //x button
    JoystickButton driveBack = new JoystickButton(xbox, 4);

    //Joystick control scheme
    // Joystick leftJoystick = new Joystick(1);
    // Joystick rightJoystick = new Joystick(0);
    // JoystickButton conveyorInButton = new JoystickButton(leftJoystick, 4);
    // JoystickButton conveyorOutButton = new JoystickButton(leftJoystick, 3);
    // JoystickButton feedButton = new JoystickButton(leftJoystick, 1);
    // JoystickButton selectPipelineButton = new JoystickButton(rightJoystick, 2);
    // JoystickButton toggleShooterButton = new JoystickButton(leftJoystick, 2);
  
  // Subsystems
  private static Intake intake = new Intake();
  public static DriveTrain drive = new DriveTrain();
  private static AutoShooter autoShooter = new AutoShooter();
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
  public ParallelCommandGroup spitOut = new ParallelCommandGroup(new IntakeOut(intake), new FeederOut(feed));

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
    conveyorInButton.whileHeld(new IntakeIn(intake));
    conveyorOutButton.whileHeld(spitOut);
    // conveyorOutButton.whileHeld(new IntakeOut(intake));
    // conveyorOutButton.whileHeld(new FeederOut(feed));
    //selectPipelineButton.whenPressed(new SelectPipeline(table));
    feedButton.whenHeld(new FeederIn(feed)); 
    toggleShooterButton.toggleWhenPressed(setShooter);
    driveBack.whenPressed(new DriveFromFender(drive));
  }


  public void setTeleop() { //set to take joystick inputs 
  //shooter.setDefaultCommand(runFlywheel);
  //drive.setDefaultCommand(joystickDrive);
  configureButtonBindings();
  setShooter.schedule();
  }
  public void teleopPeriodic() {
      while(xbox.getRawButton(5)){
        //System.out.println("slide");
        if (xbox.getRawAxis(0) > 0){
          drive.drive(xbox.getRightTriggerAxis(), 
                              (-(xbox.getRightTriggerAxis())));
          
        } else if (xbox.getRawAxis(0) < 0) {
          drive.drive((-(xbox.getRightTriggerAxis())), 
                              xbox.getRightTriggerAxis()); 
        }
      }

      if(xbox.getRightTriggerAxis() > 0 || xbox.getLeftTriggerAxis() > 0){
        if (xbox.getRawAxis(0) > 0){
          drive.drive(0.65 * (xbox.getRightTriggerAxis() - xbox.getLeftTriggerAxis() + (0.5 * xbox.getRawAxis(0))), 
                              0.65 * (xbox.getRightTriggerAxis() - xbox.getLeftTriggerAxis()));
        } else {
          drive.drive(.65 * (xbox.getRightTriggerAxis() - xbox.getLeftTriggerAxis()), 
                              .65 * (xbox.getRightTriggerAxis() - xbox.getLeftTriggerAxis() + (-0.5 * xbox.getRawAxis(0))));
        }
      }
      else {
        //drive.drive(-xbox.getRawAxis(0) * 0.5,-xbox.getRawAxis(0) * 0.5);
        drive.drive(0,0);
      }
  }
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return new AutonomousPlanA(drive, feed, autoShooter, teleopShooter);
    //return backupAutonomousCommand;
  }
}