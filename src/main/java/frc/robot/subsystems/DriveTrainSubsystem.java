// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.driveDifferentialDrive;
import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrainSubsystem extends SubsystemBase 
{

  //Declare the four drive motors
  private CANSparkMax frontLeft;
  private CANSparkMax frontRight;
  private CANSparkMax backLeft;
  private CANSparkMax backRight;

  private DifferentialDrive drive;



  /** Creates a new TankDriveSubsystem. */
  public DriveTrainSubsystem() 
  
  {
    //create instances of our motors
    frontLeft = new CANSparkMax(Constants.DriveConstants.FRONT_LEFT_ID, MotorType.kBrushless);
    frontRight = new CANSparkMax(Constants.DriveConstants.FRONT_RIGHT_ID, MotorType.kBrushless);
    backLeft = new CANSparkMax(Constants.DriveConstants.BACK_LEFT_ID, MotorType.kBrushless);
    backRight = new CANSparkMax(Constants.DriveConstants.BACK_RIGHT_ID, MotorType.kBrushless);

    drive = new DifferentialDrive(frontRight, frontLeft);

    //make sure the back motors follow front
    backLeft.follow(frontLeft);
    backRight.follow(frontRight);

    //invert the left motors to the right

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
    motor.getSmartCurrentLimit(Constants.DriveConstants.DRIVE_CURRENT_LIMIT);
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
    // This method will be called once per scheduler run
  }
}
