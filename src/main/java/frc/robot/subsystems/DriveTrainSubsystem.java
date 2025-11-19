// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants;

public class DriveTrainSubsystem extends SubsystemBase {
  /** Creates a new DriveTrainSubsystem. */

  // Declaring the four drive motors
  private CANSparkMax frontLeft;
  private CANSparkMax frontRight;
  private CANSparkMax backLeft;
  private CANSparkMax backRight;
  
  private DifferentialDrive drive;

  public DriveTrainSubsystem() {
    // Instantiates the four drive motors
    frontLeft = new CANSparkMax(Constants.DriveConstants.FRONT_LEFT_ID, MotorType.kBrushless);
    frontRight = new CANSparkMax(Constants.DriveConstants.FRONT_RIGHT_ID, MotorType.kBrushless);
    backLeft = new CANSparkMax(Constants.DriveConstants.BACK_LEFT_ID, MotorType.kBrushless);
    backRight = new CANSparkMax(Constants.DriveConstants.BACK_RIGHT_ID, MotorType.kBrushless);

    drive = new DifferentialDrive(frontLeft, frontRight);

    backLeft.follow(frontLeft);
    backRight.follow(frontRight);

    frontLeft.setInverted(true);
    backLeft.setInverted(true);

    configureMotor(frontLeft);
    configureMotor(frontRight);
    configureMotor(backLeft);
    configureMotor(backRight);
   }

   public void arcadeDrive(double speed, double theta) {
    drive.arcadeDrive(speed, theta);
   }

   private void configureMotor(CANSparkMax motor) {
    motor.restoreFactoryDefaults();
    motor.setSmartCurrentLimit(Constants.DriveConstants.DRIVE_CURRENT_LIMIT);
    motor.setIdleMode(IdleMode.kBrake);
    motor.burnFlash();
   }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
