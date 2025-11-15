package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrainSubsystem extends SubsystemBase 
{
    // Declare the four drive motors
    private CANSparkMax frontLeft;
    private CANSparkMax frontRight;
    private CANSparkMax backLeft;
    private CANSparkMax backRight;

    private DifferentialDrive drive;

    public DriveTrainSubsystem() 
    {
        // create instances of the motors
        frontLeft = new CANSparkMax(Constants.DriveConstants.FRONT_LEFT_ID, MotorType.kBrushless);
        frontRight = new CANSparkMax(Constants.DriveConstants.FRONT_RIGHT_ID, MotorType.kBrushless);
        backLeft = new CANSparkMax(Constants.DriveConstants.BACK_LEFT_ID, MotorType.kBrushless);
        backRight = new CANSparkMax(Constants.DriveConstants.BACK_RIGHT_ID, MotorType.kBrushless);

        drive  = new DifferentialDrive(frontRight, frontLeft);

        // make sure the back motors follow the front
        backLeft.follow(frontLeft);
        backRight.follow(frontRight);

        // invert the left motors to right
        frontLeft.setInverted(true);
        backLeft.setInverted(false);

        configureMotors(frontLeft);
        configureMotors(frontRight);
        configureMotors(backLeft);
        configureMotors(backRight);
    }
  
  private void configureMotors(CANSparkMax motor)
  {
    motor.restoreFactoryDefaults();
    motor.setSmartCurrentLimit(Constants.DriveConstants.DRIVE_CURRENT_LIMIT);
    motor.setIdleMode(IdleMode.kBrake);
    motor.burnFlash();
  }

  public void arcadeDrive(double speed, double theta)
  {
    drive.arcadeDrive(speed, theta);
  }

  @Override
  public void periodic()
  {
    
  }
}
  
