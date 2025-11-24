// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.MotorIO;
import frc.robot.subsystems.MotorNEO;
import frc.robot.subsystems.MotorSim;
import frc.robot.subsystems.Drive.DriveState;
import static frc.robot.subsystems.DriveConstants.*;

// Why is there only two spaces in an indentation??? :(
public class RobotContainer {  
  private CommandXboxController driveController;

  // Instantiate subsystem(s)
  public final Drive robotDrive;

  public RobotContainer() {
    driveController = new CommandXboxController(Constants.kDriveControllerPort);
    if(RobotBase.isReal()) {
      robotDrive = new Drive(new MotorNEO[] {
        new MotorNEO(frontLeft),
        new MotorNEO(frontRight),
        new MotorNEO(backLeft),
        new MotorNEO(backRight)
      });
    }
    else if(RobotBase.isSimulation()) {
      robotDrive = new Drive(new MotorSim[] {
        new MotorSim(),
        new MotorSim(),
        new MotorSim(),
        new MotorSim()
      });
    }
    else {
      robotDrive = new Drive(new MotorIO[] {
        null, null, null, null
      });
    }

    configureBindings();
  }
  
  private void configureBindings() {
    // This will prevent the DS from being overflowed with excessive warnings so we can actually see what it is logging.
    DriverStation.silenceJoystickConnectionWarning(true);

    // Send controller inputs
    robotDrive.supplyJoytickInputs(() -> driveController.getLeftY(), () -> driveController.getRightX());

    // COMMAND BINDINGS //

    // Set the default command
    robotDrive.setDefaultCommand(robotDrive.setDriveStateCommandContinuous(DriveState.ArcadeDrive));

    // Button to hard stop the motors
    driveController.x()
      .onTrue(robotDrive.setDriveStateCommandContinuous(DriveState.Stop))
      .onFalse(robotDrive.setDriveStateCommand(DriveState.ArcadeDrive));

    // Button to enter test mode
    driveController.rightBumper().and(driveController.leftBumper())
      .onTrue(robotDrive.setDriveStateCommandContinuous(DriveState.Test))
      .onFalse(robotDrive.setDriveStateCommand(DriveState.ArcadeDrive));
  }


  
}
