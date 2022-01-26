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

   //Buttons and Joystick
   Joystick leftJoystick = new Joystick(0);
   Joystick rightJoystick = new Joystick(1);
   JoystickButton conveyorInButton = new JoystickButton(leftJoystick, 2);
   JoystickButton conveyorOutButton = new JoystickButton(leftJoystick, 3);
   public JoystickButton shiftGearButton = new JoystickButton(rightJoystick, 1); //go fast

  // Subsystems
  public Intake intake = new Intake();
  public DriveTrain drive = new DriveTrain();
  public Shooter shooter = new Shooter(1.0); //change value
  public ConveyorBelt conveyorBelt = new ConveyorBelt();
  public Hanger hanger = new Hanger();
  public Feed feed = new Feed();
  public Pneumatics pneumatics = new Pneumatics();

  //Commands
  
  public IntakeIn intakeIn = new IntakeIn();
  public IntakeOut intakeOut = new IntakeOut();
  public SetConveyorIn conveyorIn = new SetConveyorIn();
  public SetConveyorOut conveyorOut = new SetConveyorOut();
  public StartFeed startFeed = new StartFeed();
  public SolenoidReverse solenoidReverse = new SolenoidReverse();
  public SolenoidForward solenoidForward = new SolenoidForward();
  InstantCommand toShift = new InstantCommand(drive::shift, drive);
  RunCommand toDrive = new RunCommand(() -> drive.drive(-leftJoystick.getRawAxis(1), rightJoystick.getRawAxis(1)), drive);
  RunCommand runFlywheel = new RunCommand(() -> shooter.set(), drive);
  ParallelCommandGroup conveyerIntakeIn = new ParallelCommandGroup(intakeIn, conveyorIn);
  ParallelCommandGroup conveyerIntakeOut = new ParallelCommandGroup(intakeOut, conveyorOut);







  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    
  }

  public void initialize() {
      // Configure the button bindings
    configureButtonBindings();
    drive.setDefaultCommand(toDrive);
    shooter.setDefaultCommand(runFlywheel);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    conveyorInButton.whenPressed(conveyerIntakeIn);
    conveyorOutButton.whenPressed(conveyerIntakeOut);
    shiftGearButton.whenPressed(new InstantCommand(drive::shift, drive));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}