// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

/** Add your docs here. */
public class DriveConstants {

    public static final int kfrontLeftID = 20;
    public static final int kfrontRightID = 21;
    public static final int kBackLeftID = 22;
    public static final int kBackRightID = 23;
    
    public static final int DRIVE_CURRENT_LIMIT = 40;

    public static final record motorConfig(
        int ID,
        MotorType type,
        boolean inverted, 
        int followID 
    ) {}

    public static final motorConfig fLConfig = new motorConfig(
        kfrontLeftID, 
        MotorType.kBrushless, 
        true, 
        -1
    );

    public static final motorConfig fRConfig = new motorConfig(
        kfrontRightID, 
        MotorType.kBrushless, 
        false, 
        -1
    );

    public static final motorConfig bLConfig = new motorConfig(
        kBackLeftID, 
        MotorType.kBrushless, 
        true, 
        kfrontLeftID
    );

    public static final motorConfig bRConfig = new motorConfig(
        kBackRightID, 
        MotorType.kBrushless, 
        false, 
        kfrontRightID
    );


    // CONSTANTS ACROSS ALL MOTORS
    public static final int kStallCurrentAmps = 40; 
    public static final IdleMode kSparkIdleMode = IdleMode.kBrake; 
    public static final double kWheelRadius = 0.69; // TODO: Fix value
    public static final double kGearRatio = 0.25; // TODO: Fix value
    public static final double kTrackWidthMeters = 1.0; // TODO: Fix value
    
    // Only configures per wheel speed because separating linear speed and theta speed is a little strange when not using swerve
    public static final double kMaxWheelSpeedMPS = 4.0;
}
