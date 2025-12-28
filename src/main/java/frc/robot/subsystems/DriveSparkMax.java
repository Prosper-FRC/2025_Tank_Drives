// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/** Add your docs here. */
public class DriveSparkMax implements DriveIO {
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

    private final double kleftPositionMeters = 0.0;
    private final double kleftSpeedMPS = 0.0;
    private final double kleftAppliedVolts = 0.0;
            
    private final double krightPositionMeters = 0.0;
    private final double krightSpeedMPS = 0.0;
    private final double krightAppliedVolts = 0.0;

    public DriveSparkMax() {
        // Instantiate motors
        frontLeft = new SparkMax(DriveConstants.kfrontLeftID, SparkLowLevel.MotorType.kBrushless);
        frontRight = new SparkMax(DriveConstants.kfrontRightID, SparkLowLevel.MotorType.kBrushless);
        backLeft = new SparkMax(DriveConstants.kBackLeftID, SparkLowLevel.MotorType.kBrushless);
        backRight = new SparkMax(DriveConstants.kBackRightID, SparkLowLevel.MotorType.kBrushless);

        fLConfig = new SparkMaxConfig();
        fRConfig = new SparkMaxConfig();
        bLConfig = new SparkMaxConfig();
        bRConfig = new SparkMaxConfig();
        
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


}