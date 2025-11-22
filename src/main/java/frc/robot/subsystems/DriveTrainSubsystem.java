// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.DriveConstants;

public class DriveTrainSubsystem extends SubsystemBase {
  
  // Declare motors
  private SparkMax frontLeft;
  private SparkMax frontRight;
  private SparkMax backLeft;
  private SparkMax backRight;

  private SparkMaxConfig fLConfig;
  private SparkMaxConfig fRConfig;
  private SparkMaxConfig bLConfig;
  private SparkMaxConfig bRConfig;

  private DifferentialDrive drive;

  /** Creates a new DriveTrainSusbsystem. 
     * @return */
    public void DriveTrainSusbsystem() {
    // Instantiate motors
    frontLeft = new SparkMax(Constants.DriveConstants.FRONT_LEFT_ID, SparkLowLevel.MotorType.kBrushless);
    frontRight = new SparkMax(Constants.DriveConstants.FRONT_RIGHT_ID, SparkLowLevel.MotorType.kBrushless);
    backLeft = new SparkMax(Constants.DriveConstants.BACK_LEFT_ID, SparkLowLevel.MotorType.kBrushless);
    backRight = new SparkMax(Constants.DriveConstants.BACK_RIGHT_ID, SparkLowLevel.MotorType.kBrushless);

    fLConfig = new SparkMaxConfig();
    fRConfig = new SparkMaxConfig();
    bLConfig = new SparkMaxConfig();
    bRConfig = new SparkMaxConfig();

    // Dont need to reset factory defaults anymore
    fLConfig
      .inverted(true)
      .idleMode(IdleMode.kBrake)
      .smartCurrentLimit(DriveConstants.DRIVE_CURRENT_LIMIT);
    fRConfig
      .inverted(false)
      .idleMode(IdleMode.kBrake)
      .smartCurrentLimit(DriveConstants.DRIVE_CURRENT_LIMIT);
    bLConfig
      .inverted(true)
      .follow(frontLeft)
      .idleMode(IdleMode.kBrake)
      .smartCurrentLimit(DriveConstants.DRIVE_CURRENT_LIMIT);
    bRConfig
      .inverted(false)
      .follow(frontRight)
      .idleMode(IdleMode.kBrake)
      .smartCurrentLimit(DriveConstants.DRIVE_CURRENT_LIMIT);

    frontLeft.configure(fLConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    frontRight.configure(fRConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    backLeft.configure(bLConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    backRight.configure(bRConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    drive = new DifferentialDrive(frontLeft, frontRight);
  }

  public void arcadeDrive(double speed, double theta) {
    drive.arcadeDrive(speed, theta);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
