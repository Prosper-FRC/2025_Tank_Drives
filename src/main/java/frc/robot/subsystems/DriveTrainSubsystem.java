// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrainSubsystem extends SubsystemBase {
  /** Creates a new DriveTrainSubsystem. */

   /*Declares four motors - scope: private */
   private CANSparkMax frontleft;
   private CANSparkMax frontright;
   private CANSparkMax backleft;
   private CANSparkMax backright;

   private DifferentialDrive drive;

  public DriveTrainSubsystem() {
    frontLeft = new CANSparkMax(Constants.DriveConstants.FRONT_LEFT_ID, MotorType.kBrushess);
    frontRight = new CANSparkMax(Constants.DriveConstants.FRONT_RIGHT_ID, MotorType.kBrushess);
    backLeft = new CANSparkMax(Constants.DriveConstants.BACK_LEFT_ID, MotorType.kBrushess);
    backRight = new CANSparkMax(Constants.DriveConstants.BACK_RIGHT_ID, MotorType.kBrushess);

    drive = new DifferentialDrive(frontLeft, frontRight);

    /* follow motots and set Inverted */


    backLeft.follow(frontLeft);
    backRight.follow(frontRight);

    frontLeft.setInverted(true);
    backLeft.setInverted(true);
    
    /* restore Factory Defaults, set SmartCurrentLimits, setIdleMode, and burn Flash*/
    configureMotors(backLeft);
    configureMotors(backRight);
    configureMotors(frontLeft);
    configureMotors(frontRight);

  }

  public void arcadeDrive(double speed, double theta) {
    /*parameters angle and speed for movement */
    drive.arcadeDrive(speed, theta);
  }

  private void configureMotors(CANSparkMax motor) {
    motor.restoreFactoryDefauls();
    motor.setSmartCurrentLimit(Constants.DriveConstants.DRIVE_CURRENT_LIMIT);
    motor.setIdleMode(IdleMode.kBrake);
    motor.burnFlash();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
