// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

/** Add your docs here. */
public class DriveConstants {

    public static final int kfrontLeftID = 20;
    public static final int kfrontRightID = 21;
    public static final int kBackLeftID = 22;
    public static final int kBackRightID = 23;

    public static final record motorConfig(
        boolean inverted, 
        int followID 
    ) {}

    public static final motorConfig fLConfig = new motorConfig(
        true, 
        -1
    );

    public static final motorConfig fRConfig = new motorConfig(
        false, 
        -1
    );

    public static final motorConfig bLConfig = new motorConfig(
        true, 
        kfrontLeftID
    );

    public static final motorConfig bRConfig = new motorConfig(
        false, 
        kfrontRightID
    );


    // CONSTANTS ACROSS ALL MOTORS
    public static final int kDriveCurrentLimit = 40;
    public static final IdleMode kIdleMode = IdleMode.kBrake; 
    public static final double kWheelRadius = 0.1524; // i guessed that this is in meters and just used 6 inch diameter wheels
    public static final double kGearRatio = 0.25; // TODO: Fix value
    public static final double kTrackWidthMeters = 1.0; // TODO: Fix value
    
    // Only configures per wheel speed because separating linear speed and theta speed is a little strange when not using swerve
    public static final double kMaxWheelSpeedMPS = 4.0;
}
