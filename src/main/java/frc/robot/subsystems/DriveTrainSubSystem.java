package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrainSubSystem extends SubsystemBase {
    // Declare the four motors
    private CANSparkMax frontLeft;
    private CANSparkMax frontRight;
    private CANSparkMax backRight;
    private CANSparkMax backLeft;

    private DifferentialDrive drive;
    
    // Constructor for class
    public DriveTrainSubSystem() {
        frontLeft = new CANSparkMax(Constants.DriveConstants.FRONT_LEFT_ID, MotorType.kBrushless);
        frontRight = new CANSparkMax(Constants.DriveConstants.FRONT_RIGHT_ID, MotorType.kBrushless);
        backLeft = new CANSparkMax(Constants.DriveConstants.BACK_LEFT_ID, MotorType.kBrushless);
        backRight = new CANSparkMax(Constants.DriveConstants.BACK_RIGHT_ID, MotorType.kBrushless);

        drive = new DifferentialDrive(frontLeft, frontRight);

        backLeft.follow(frontLeft);
        backRight.follow(frontRight);

        frontLeft.setInverted(true);
        backLeft.setInverted(true);

        configureMotors(backLeft);
        configureMotors(backRight);
        configureMotors(frontLeft);
        configureMotors(frontRight);

    }

    public void arcadeDrive(double speed, double theta) {
        drive.arcadeDrive(speed, theta);
    }

    private void configureMotors(CANSparkMax motor) {
        motor.restoreFactoryDefaults();
        motor.setSmartCurrentLimit(Constants.DriveConstants.DRIVE_CURRENT_LIMIT);
        motor.setIdleMode(IdleMode.kBrake);
        motor.burnFlash();

    }

    @Override
    public void periodic() {
        
    }
}
