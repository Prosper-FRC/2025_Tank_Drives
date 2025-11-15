// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrainSubsystem extends SubsystemBase {
  // Declaring our four motors 
  private CANSparkMax frontleft;
  private CANSparkMax frontright;
  private CANSparkMax backleft;
  private CANSparkMax backright;


  /** Creates a new DriveTrainSubsystem. */
  private DifferentialDrive drive;
  public DriveTrainSubsystem() {
    frontleft = new CANSparkMax(Constants.DriveConstants.FRONT_LEFT_ID, MotorType.kBrushless);
    frontright = new CANSparkMax(Constants.DriveConstants.FRONT_RIGHT_ID, MotorType.kBrushless);
    backleft = new CANSparkMax(Constants.DriveConstants.BACK_LEFT_ID, MotorType.kBrushless);
    backright = new CANSparkMax(Constants.DriveConstants.BACK_RIGHT_ID, MotorType.kBrushless);

    drive = new DifferentialDrive(frontleft, frontright);

    backleft.follow(frontleft);
    backright.follow(frontright);

    frontleft.setInverted(true);
    backleft.setInverted(true);

    configureMotors(frontleft);
    configureMotors(frontright);
    configureMotors(backleft);
    configureMotors(backright);
  }

  public void arcadeDrive(double speed, double theta) {
    drive.arcadeDrive(speed, theta);
  }

  private void configureMotors(CANSparkMax motor){
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
