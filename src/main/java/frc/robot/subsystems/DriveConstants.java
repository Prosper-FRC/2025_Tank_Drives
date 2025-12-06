// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

/** Add your docs here. */
public class DriveConstants {

    public static final int FRONT_LEFT_ID = 20;
    public static final int FRONT_RIGHT_ID = 21;
    public static final int BACK_LEFT_ID = 22;
    public static final int BACK_RIGHT_ID = 23;
    
    public static final int DRIVE_CURRENT_LIMIT = 40;

    public static final record motorConfig(
        int ID,
        MotorType type,
        boolean inverted, 
        int followID 
    ) {}

    public static final motorConfig fLConfig = new motorConfig(
        FRONT_LEFT_ID, 
        MotorType.kBrushless, 
        true, 
        -1
    );

    public static final motorConfig fRConfig = new motorConfig(
        FRONT_RIGHT_ID, 
        MotorType.kBrushless, 
        false, 
        -1
    );

    public static final motorConfig bLConfig = new motorConfig(
        BACK_LEFT_ID, 
        MotorType.kBrushless, 
        true, 
        FRONT_LEFT_ID
    );

    public static final motorConfig bRConfig = new motorConfig(
        BACK_RIGHT_ID, 
        MotorType.kBrushless, 
        false, 
        FRONT_RIGHT_ID
    );

}
