// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.ControllerConstants;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  // Replace with CommandPS4Controller or CommandJoystick if needed

  // Initialize DriveTrainSubsystem
  
  // Initialize controller
  private CommandXboxController driverController;

  

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings

    // Instantiate controller and drive
    driverController = new CommandXboxController(ControllerConstants.kDriverControllerPort);
    configureBindings();

    // Set a default command for the DriveTrainSubsystem. This is where you supply your joystick
    // inputs as the speed and rotation for arcadeDrive. Keep in mind that you MUST use a lambda expression, as
    // this ensures the program is checking for the joystick to have moved periodically (every 20 milliseconds).

  }

  
  private void configureBindings() {
    
  }


  
}
