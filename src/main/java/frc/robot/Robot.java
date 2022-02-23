// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import org.opencv.core.Mat;

import edu.wpi.first.cscore.CvSink;
import edu.wpi.first.cscore.CvSource;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.math.geometry.Pose2d;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {
  private RobotContainer container = new RobotContainer();
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    
    //I think it's just gonna give an error unless we select a camera from SmartDashboard
    new Thread(() -> {
      UsbCamera camera = CameraServer.startAutomaticCapture();
      camera.setResolution(640, 480);

      CvSink cvSink = CameraServer.getVideo();
      CvSource outputStream = CameraServer.putVideo("DriverCam", 640, 480);

      Mat source = new Mat();
      Mat output = new Mat();

      while(!Thread.interrupted()) {
        if (cvSink.grabFrame(source) == 0) {
          continue;
        }
        outputStream.putFrame(output);
      }
    }).start();
 
    System.out.println("robot init");
    if (container.table.getEntry("pipeline").getDouble(0) == 0) {
      System.out.println("looking for red balls");
    } else {
      System.out.println("looking for blue balls");
    }
  
  }

  /** This function is run once each time the robot enters autonomous mode. */
  @Override
  public void autonomousInit() {
    //container.drive.setDefaultGear();
    
    System.out.println("Autonomous init started");
    container.drive.resetOdometry(new Pose2d());

    // schedule the autonomous command (example)
    if (container.getAutonomousCommand() != null) {
      container.autonomousCommand.schedule();
    }
    
    }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    CommandScheduler.getInstance().run();
 
  }

  /** This function is called once each time the robot enters teleoperated mode. */
  @Override
  public void teleopInit() {
    container.setTeleop();
  }

  /** This function is called periodically during teleoperated mode. */
  @Override
  public void teleopPeriodic() {
    CommandScheduler.getInstance().run();

  }

  /** This function is called once each time the robot enters test mode. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}
