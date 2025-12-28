// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;

import java.util.function.DoubleSupplier;

import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.subsystems.DriveConstants.motorConfig;

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

    private DoubleSupplier leftPositionMeters;
    private DoubleSupplier leftSpeedMPS;
            
    private DoubleSupplier rightPositionMeters;
    private DoubleSupplier rightSpeedMPS;

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
        .inverted(DriveConstants.fLConfig.inverted())
        .idleMode(DriveConstants.kIdleMode)
        .smartCurrentLimit(DriveConstants.kDriveCurrentLimit);
        fRConfig
        .inverted(DriveConstants.fRConfig.inverted())
        .idleMode(IdleMode.kBrake)
        .smartCurrentLimit(DriveConstants.kDriveCurrentLimit);
        bLConfig
        .inverted(DriveConstants.bLConfig.inverted())
        .follow(DriveConstants.bLConfig.followID())
        .idleMode(IdleMode.kBrake)
        .smartCurrentLimit(DriveConstants.kDriveCurrentLimit);
        bRConfig
        .inverted(DriveConstants.bRConfig.inverted())
        .follow(DriveConstants.bRConfig.followID())
        .idleMode(IdleMode.kBrake)
        .smartCurrentLimit(DriveConstants.kDriveCurrentLimit);

        frontLeft.configure(fLConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        frontRight.configure(fRConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        backLeft.configure(bLConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        backRight.configure(bRConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        drive = new DifferentialDrive(frontLeft, frontRight);

        leftPositionMeters = () -> frontLeft.getAbsoluteEncoder().getPosition() * 2 * Math.PI * DriveConstants.kWheelRadius;
        leftSpeedMPS = () -> frontLeft.getAbsoluteEncoder().getVelocity() * 2 * Math.PI * DriveConstants.kWheelRadius;

        rightPositionMeters = () -> frontRight.getAbsoluteEncoder().getPosition() * 2 * Math.PI * DriveConstants.kWheelRadius;
        rightSpeedMPS = () -> frontRight.getAbsoluteEncoder().getVelocity() * 2 * Math.PI * DriveConstants.kWheelRadius;
    }

    // Updates the set of loggable inputs
    public void updateInputs(DriveIOInputs inputs) {
        inputs.leftPositionMeters = leftPositionMeters.getAsDouble();
        inputs.leftSpeedMPS = leftSpeedMPS.getAsDouble();

        inputs.rightPositionMeters = rightPositionMeters.getAsDouble();
        inputs.rightSpeedMPS = rightSpeedMPS.getAsDouble();
    }

    // Drives the robot using arcade drive
    public void arcadeDriver(double speed, double theta) {
        drive.arcadeDrive(speed, theta);
    }
    
    // Stops everything
    public void stop() {
        frontLeft.stopMotor();
        frontRight.stopMotor();
        backLeft.stopMotor(); // These two are probably unessecary but better safe than sorry
        backRight.stopMotor();;
    }


}