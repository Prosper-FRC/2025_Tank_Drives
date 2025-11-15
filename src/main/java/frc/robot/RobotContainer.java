// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.ControllerConstants;
import frc.robot.commands.DriveCommand;
import frc.robot.subsystems.DriveTrainSubsystem;

public class RobotContainer {

  private DriveTrainSubsystem drive;
  
  // Initialize controller
  private CommandXboxController driverController;

  public RobotContainer() {

    driverController = new CommandXboxController(ControllerConstants.kDriverControllerPort);
    configureBindings();

    drive = new DriveTrainSubsystem();

    // every 20 milliseconds
    drive.setDefaultCommand(new DriveCommand(
        () -> driverController.getLeftY(), 
        () -> driverController.getRightX(), 
        drive)
    );
  }

  
  private void configureBindings() {
    
  }
}