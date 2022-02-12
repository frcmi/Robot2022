// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class SelectPipeline extends InstantCommand {
  int pipeline = 0;
  private NetworkTable m_table;

  public SelectPipeline(NetworkTable p_table) {
    m_table = p_table;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() { // 0 is red, 1 is blue??
    if (pipeline % 2 == 0) {
      m_table.getEntry("pipeline").setNumber(pipeline); // red
      System.out.println("red");
      pipeline++;
    } else {
      m_table.getEntry("pipeline").setNumber(pipeline); // blue
      System.out.println("blue");
      pipeline++;
    }

  }

}
