package frc.robot;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;


public class RobotContainer {

  //Joysticks and buttons
  public Joystick leftStick = new Joystick(0);
  public Joystick rightStick = new Joystick(1);
  public JoystickButton shiftGearButton = new JoystickButton(rightStick, 2); //go fast

  //Subsystems
  public DriveTrain drive = new DriveTrain();

  //Commands
  InstantCommand toShift = new InstantCommand(drive::shift, drive);
  RunCommand toDrive = new RunCommand(() -> drive.drive(-leftStick.getRawAxis(0), rightStick.getRawAxis(0)), drive);

  //creates sendablechooser
  public SendableChooser<Command> chooser = new SendableChooser<>();  


  //Constants
  public static final double kDistancePerRevolution = 18.84;  // guestimate from your code
  public static final double kPulsesPerRevolution = 1024;     // for an AS5145B Magnetic Encoder
  public static final double kDistancePerPulse = kDistancePerRevolution / kPulsesPerRevolution;

  public static final double kUnitsPerRevolution = 2048; //constant for falcons

  public RobotContainer() {
    // Use addRequirements() here to declare subsystem dependencies.
    configureButtonBindings();
    drive.setDefaultCommand(toDrive);
  }

  public void initialize() {
    configureButtonBindings();
  }

  private void configureButtonBindings() {
    shiftGearButton.whenPressed(new InstantCommand(drive::shift, drive));
  }


}