// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package Subsystems;

import javax.swing.TransferHandler.TransferSupport;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrainSubsystem extends SubsystemBase {
  /** Creates a new DriveTrainSubsystem. */

  //declaring the drive motors
  private CANSparkMax frontLeft;
  private CANSparkMax frontRight;
  private CANSparkMax backLeft;
  private CANSparkMax backRight;

  private DifferentialDrive drive;

  public DriveTrainSubsystem() {
    //setting the motors with the motor id and the motor type
    frontLeft = new CANSparkMax(Constants.DriveConstants.FRONT_LEFT_ID, MotorType.kBrushless);
    frontRight = new CANSparkMax(Constants.DriveConstants.FRONT_RIGHT_ID, MotorType.kBrushless);
    backLeft = new CANSparkMax(Constants.DriveConstants.BACK_LEFT_ID, MotorType.kBrushless);
    backRight = new CANSparkMax(Constants.DriveConstants.BACK_RIGHT_ID, MotorType.kBrushless);

    //instantiate
       drive = new DifferentialDrive(frontLeft, frontRight);

    //making sure that the back motors follow the corresponding front motors
    backLeft.follow(frontLeft);
    backRight.follow(frontRight);

    //inverts the left side motors so the robot motrs work correctly
    frontLeft.setInverted(true);
    backLeft.setInverted(true);

    //setting the current limits with configueMotors
    configuremotors(backLeft);
    configuremotors(backRight);
    configuremotors(frontLeft);
    configuremotors(frontRight);




  }

  public void arcadeDrive(double speed, double theta) {
    drive.arcadeDrive(speed,theta);

  }

  private void configuremotors(CANSparkMax motor){
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
