// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrainSubsystem extends SubsystemBase {
  /** Creates a new DriveTrainSubsystem. */
  //Declaring the four drive motors (declaration)
  private CANSparkMax frontLeft;
  private CANSparkMax frontRight;
  private CANSparkMax backLeft;
  private CANSparkMax backRight;
  

  public DriveTrainSubsystem() {
      frontLeft =  new CANSparkMax(Constants.DriveConstants.FRONT_LEFT_ID, MotorType.kBrushless);
      frontRight =  new CANSparkMax(Constants.DriveConstants.FRONT_RIGHT_ID , MotorType.kBrushless);
      backLeft =  new CANSparkMax(Constants.DriveConstants.FRONT_LEFT_ID , MotorType.kBrushless);
      backRight =  new CANSparkMax(Constants.DriveConstants.FRONT_RIGHT_ID , MotorType.kBrushless);
  
      backLeft.follow(frontLeft);
      backRight.follow(frontRight);
  
      frontLeft.setInverted(true);
      backRight.setInverted(true);
}

private void configureMotors(CANSparkMax motor) {
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
