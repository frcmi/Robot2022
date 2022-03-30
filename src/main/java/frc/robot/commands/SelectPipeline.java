// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

//NOT USED
package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.networktables.NetworkTable;


// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class SelectPipeline extends InstantCommand {
  private NetworkTable table;

  public SelectPipeline(NetworkTable table) {
    this.table = table;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    table.getEntry("pipeline").setNumber((table.getEntry("pipeline").getDouble(0) + 1) % 2);
    System.out.println("pipeline set to: " + table.getEntry("pipeline").getDouble(0));
    System.out.println("O is red, 1 is blue");
  }

}
