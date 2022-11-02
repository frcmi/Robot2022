// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Button;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

import edu.wpi.first.wpilibj.XboxController;



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

  public static XboxController xbox = new XboxController(0);
  JoystickButton spitButton = new JoystickButton(xbox, 5);
  Button feedButton = new Button(() -> xbox.getLeftTriggerAxis() >= 0.5);
  Button feedAndShootButton = new Button(() -> xbox.getRightTriggerAxis() >= 0.3);

  // Subsystems
  public static Intake intake = new Intake();
  public static DriveTrain drive = new DriveTrain();
  public static Shooter shooter = new Shooter();

  //Commands
  public static Autonomous autonomous = new Autonomous(drive, intake, shooter);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
   autonomous.addAutonomousShuffleboardTab();
   //intake.setDefaultCommand(defaultCommand);
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
    feedButton
      .whileHeld(intake::intake)
      .whenReleased(intake.outtake().withTimeout(.1));
    //circumvent lack of indexer to outtake ball to allow spinup of flywheel

    spitButton.whileActiveContinuous(intake.outtake());  

    feedAndShootButton.whenHeld(
      new SequentialCommandGroup(
        shooter.setShooter().withTimeout(SPINUP_DELAY),
        new ParallelCommandGroup(
          intake.intake().withTimeout(.25),
          shooter.setShooter().withTimeout(.25)
        )
      )
    );
  }


  public void setTeleop() {
    configureButtonBindings();
    System.out.println(xbox.getLeftTriggerAxis());
  }

  public void teleopPeriodic() {
    drive.cheesyDrive(SPEED_MULTIPLIER * xbox.getRawAxis(1), ROTATION_MULTIPLIER * xbox.getRawAxis(4));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return autonomous.getAutonomousCommand();
  }
}