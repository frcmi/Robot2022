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


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  //Limelight and values
  public NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  NetworkTableEntry tx = table.getEntry("tx");
  NetworkTableEntry ty = table.getEntry("ty");
  NetworkTableEntry ta = table.getEntry("ta");

   //Buttons and Joystick
   
   Joystick leftJoystick = new Joystick(0);
   Joystick rightJoystick = new Joystick(1);
   public JoystickButton conveyorInButton = new JoystickButton(leftJoystick, 2);
   public JoystickButton conveyorOutButton = new JoystickButton(leftJoystick, 3);
   JoystickButton shiftGearButton = new JoystickButton(rightJoystick, 1); //go fast
   public JoystickButton extendHangerDownButton = new JoystickButton(rightJoystick, 2);
   public JoystickButton extendHangerUpButton = new JoystickButton(rightJoystick, 3);
   JoystickButton feedButton = new JoystickButton(rightJoystick, 4);
   JoystickButton selectPipelineButton = new JoystickButton(rightJoystick, 4);


  // Subsystems
  public static Intake intake = new Intake();
  public static DriveTrain drive = new DriveTrain();
  public static Shooter shooter = new Shooter(1.0); //change value
  public static ConveyorBelt conveyorBelt = new ConveyorBelt();
  public static Hanger hanger = new Hanger();
  public static Navx navx = new Navx();
  public static Feed feed = new Feed();
  

  //Commands
  
  // public IntakeIn intakeIn = new IntakeIn();
  // public IntakeOut intakeOut = new IntakeOut();
  // public SetConveyorIn conveyorIn = new SetConveyorIn();
  // public SetConveyorOut conveyorOut = new SetConveyorOut();
  // public ExtendHangerDown extendHangerDown = new ExtendHangerDown();
  // public ExtendHangerUp extendHangerUp = new ExtendHangerUp();
  // public StartFeed startFeed = new StartFeed();
  // public ShootIfStopped shootIfStopped = new ShootIfStopped();
  // public DriveToHub driveToHub = new DriveToHub();
  // public SeekBall seekBall = new SeekBall();
  // public SelectPipeline selectPipeline = new SelectPipeline();
  public AutonomousCommand autonomousCommand = new AutonomousCommand();
  // public SpinAround spinAround = new SpinAround();
  //InstantCommand toShift = new InstantCommand(drive::shift, drive);
  RunCommand toDrive = new RunCommand(() -> drive.drive(-leftJoystick.getRawAxis(1), rightJoystick.getRawAxis(1)), drive);
  RunCommand runFlywheel = new RunCommand(() -> shooter.set(), shooter);
  ParallelCommandGroup conveyorIntakeIn = new ParallelCommandGroup(new IntakeIn(), new ConveyorIn());
  ParallelCommandGroup conveyorIntakeOut = new ParallelCommandGroup(new IntakeOut(), new ConveyorOut());

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    configureButtonBindingsAndFlywheel();
  }


  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindingsAndFlywheel() {
    conveyorInButton.whenHeld(conveyorIntakeIn);
    conveyorOutButton.whenHeld(conveyorIntakeOut);
    extendHangerDownButton.whenHeld(new ExtendHangerDown());
    extendHangerUpButton.whenHeld(new ExtendHangerUp());
    selectPipelineButton.whenPressed(new SelectPipeline());
    feedButton.whenPressed(new StartFeed());
    //shiftGearButton.whenPressed(new InstantCommand(drive::shift, drive));
    shooter.setDefaultCommand(runFlywheel);
  }


  public void setTeleop() {
    drive.setDefaultCommand(toDrive);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return autonomousCommand;
  }
}