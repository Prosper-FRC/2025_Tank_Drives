// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.DriveConstants;

public class DriveTrainSubsystem extends SubsystemBase {
  /** Creates a new test. */

  // Declares the motors
  private CANSparkMax frontLeft;
  private CANSparkMax frontRight;
  private CANSparkMax backLeft;
  private CANSparkMax backRight;

  private DifferentialDrive drive;

  public DriveTrainSubsystem() {

    // Instantiation of motor controllers.
    frontLeft = new CANSparkMax(Constants.DriveConstants.FRONT_LEFT_ID, MotorType.kBrushless);
    frontRight = new CANSparkMax(Constants.DriveConstants.FRONT_RIGHT_ID, MotorType.kBrushless);
    backLeft = new CANSparkMax(Constants.DriveConstants.BACK_LEFT_ID, MotorType.kBrushless);
    backRight = new CANSparkMax(Constants.DriveConstants.FRONT_RIGHT_ID, MotorType.kBrushless);

    drive = new DifferentialDrive(frontLeft, frontRight);

    // BACK FOLLOW FRONT
    backLeft.follow(frontLeft);
    backRight.follow(frontRight);

    // LEFT INVERTED
    frontLeft.setInverted(false);
    backLeft.setInverted(false);

    configureMotors(backLeft);
    configureMotors(backRight);
    configureMotors(frontLeft);
    configureMotors(frontRight);
  }

  public void arcadeDrive(double speed, double theta) {
    drive.arcadeDrive(speed, theta);
  }

  public void configureMotors(CANSparkMax motor) {

    motor.restoreFactoryDefaults();
    motor.setIdleMode(IdleMode.kBrake);
    motor.setSmartCurrentLimit(DriveConstants.DRIVE_CURRENT_LIMIT);

    // Burn all configurations into robot
    motor.burnFlash();

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
