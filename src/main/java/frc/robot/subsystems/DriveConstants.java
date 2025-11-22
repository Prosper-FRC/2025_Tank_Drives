package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

public class DriveConstants {
    // These are all of our drive constants if you couldn't tell by the name
    public static final record motorConfiguration(int ID, MotorType type, boolean shouldInvert, int followerID, double p, double i, double d) {}

    public static final motorConfiguration frontLeft = new motorConfiguration(
        20, 
        MotorType.kBrushless,
        true,
        -1,
        0.15,
        0.0,
        0.0025
    );
    public static final motorConfiguration frontRight = new motorConfiguration(
        21, 
        MotorType.kBrushless,
        false,
        -1,
        0.15,
        0.0,
        0.0025
    );
    public static final motorConfiguration backLeft = new motorConfiguration(
        22, 
        MotorType.kBrushless,
        frontLeft.shouldInvert(),
        frontLeft.ID(),
        0.15,
        0.0,
        0.0025
    );
    public static final motorConfiguration backRight = new motorConfiguration(
        23, 
        MotorType.kBrushless,
        frontRight.shouldInvert(),
        frontRight.ID(),
        0.15,
        0.0,
        0.0025
    );

    // CONSTANTS ACROSS ALL MOTORS
    public static final int kStallCurrentAmps = 40; // Apparently this is supposed to be 40
    public static final IdleMode kSparkIdleMode = IdleMode.kBrake; // This should be constant across all motors bruh 
    public static final double kWheelRadius = 0.67; // TODO: Set this value so the encoder doesn't tweak out
    
    // Only configures per wheel speed because separating linear speed and theta speed is a little strange when not using swerve
    public static final double kMaxWheelSpeedMPS = 4.0;
}
