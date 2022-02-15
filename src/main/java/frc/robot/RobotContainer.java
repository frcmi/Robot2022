// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
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
  public NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  NetworkTableEntry tx = table.getEntry("tx");
  NetworkTableEntry ty = table.getEntry("ty");
  NetworkTableEntry ta = table.getEntry("ta");

  // Buttons and Joystick

  Joystick leftJoystick = new Joystick(0);
  Joystick rightJoystick = new Joystick(1);
  public JoystickButton conveyorInButton = new JoystickButton(leftJoystick, 2);
  public JoystickButton conveyorOutButton = new JoystickButton(leftJoystick, 3);
  // JoystickButton shiftGearButton = new JoystickButton(rightJoystick, 1); //go
  // fast
  JoystickButton feedButton = new JoystickButton(rightJoystick, 1);
  JoystickButton selectPipelineButton = new JoystickButton(rightJoystick, 2);

  // Subsystems
  private static Intake intake = new Intake();
  public static DriveTrain drive = new DriveTrain(); // if robot.java is head this should be private, if isnt head ublic
  private static Shooter shooter = new Shooter(); // change value
  private static ConveyorBelt cBelt = new ConveyorBelt();
  private static Feed feed = new Feed();

  // Commands

  public IntakeOut intakeOut = new IntakeOut(intake);
  public ConveyorIn conveyorIn = new ConveyorIn(cBelt);
  public IntakeIn intakeIn = new IntakeIn(intake);
  public ConveyorOut conveyorOut = new ConveyorOut(cBelt);
  public StartFeed startFeed = new StartFeed(feed);
  public DriveOutOfTarmac driveOutOfTarmac = new DriveOutOfTarmac(drive);
  public SeekBall seekBall = new SeekBall(drive, table);
  public SelectPipeline selectPipeline = new SelectPipeline(table);
  public AutonomousPlanA autonomousCommand = new AutonomousPlanA(drive, feed); // change??
  // public AutonomousPlanB autonomousCommand = new AutonomousPlanB();
  // public AutonomousFeed autonomousCommand = new AutonomousFeed();
  public SpinAround spinAround = new SpinAround(drive);
  // InstantCommand toShift = new InstantCommand(drive::shift, drive);
  RunCommand joystickDrive = new RunCommand(() -> drive.drive(-leftJoystick.getRawAxis(1), rightJoystick.getRawAxis(1)),
      drive);
  RunCommand runFlywheel = new RunCommand(() -> shooter.set(), shooter);
  ParallelCommandGroup conveyorIntakeIn = new ParallelCommandGroup(new IntakeIn(intake), new ConveyorIn(cBelt));
  ParallelCommandGroup conveyorIntakeOut = new ParallelCommandGroup(new IntakeOut(intake), new ConveyorOut(cBelt));

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    configureButtonBindings();
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
    conveyorInButton.whenHeld(conveyorIntakeIn);
    conveyorOutButton.whenHeld(conveyorIntakeOut);
    selectPipelineButton.whenPressed(new SelectPipeline(table));
    feedButton.whenHeld(new StartFeed(feed)); // probably want when held??
    // shiftGearButton.whenPressed(new InstantCommand(drive::shift, drive));
  }

  public void setAutonomous() {
    shooter.changeSetpoint(AUTOSETPOINT);
    shooter.setDefaultCommand(runFlywheel);
  }

  public void setTeleop() {
    drive.setDefaultCommand(joystickDrive);
    shooter.changeSetpoint(TELEOPSETPOINT);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    System.out.println("getAutonomousCommand returns autonomous");
    // An ExampleCommand will run in autonomous
    return autonomousCommand;
  }
}