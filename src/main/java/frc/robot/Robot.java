// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;






/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {
    
  public static RobotContainer container = new RobotContainer();

  @Override
  public void robotInit() {
  }

  /** This function is run once each time the robot enters autonomous mode. */
  @Override
  public void autonomousInit() {
    //container.drive.setDefaultGear();
    CommandScheduler.getInstance().cancelAll();
    CommandScheduler.getInstance().clearButtons();
    System.out.println("Autonomous init started");
    // schedule the autonomous command (example)
    if (container.getAutonomousCommand() != null) {
      container.getAutonomousCommand().schedule();
    }
    
    }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    CommandScheduler.getInstance().run();
    SmartDashboard.putData(CommandScheduler.getInstance());
  }

  /** This function is called once each time the robot enters teleoperated mode. */
  @Override
  public void teleopInit() {
    CommandScheduler.getInstance().cancelAll();
    container.setTeleop();
  }

  /** This function is called periodically during teleoperated mode. */
  @Override
  public void teleopPeriodic() {
    container.teleopPeriodic();
    CommandScheduler.getInstance().run();
    SmartDashboard.putData(CommandScheduler.getInstance());
  }

  /** This function is called once each time the robot enters test mode. */
  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
  }
}
