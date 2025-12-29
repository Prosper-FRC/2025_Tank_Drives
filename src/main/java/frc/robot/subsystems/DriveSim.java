// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import static frc.robot.subsystems.DriveConstants.*;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.DifferentialDrive.WheelSpeeds;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim.KitbotGearing;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim.KitbotMotor;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim.KitbotWheelSize;

/** Add your docs here. */
public class DriveSim implements DriveIO {
  private DifferentialDrivetrainSim sim =
  DifferentialDrivetrainSim.createKitbotSim(
      KitbotMotor.kDualCIMPerSide, KitbotGearing.k10p71, KitbotWheelSize.kSixInch, null);

  private static final PIDController PID = new PIDController(0.5, 0, 0.0025);

  private double leftVoltsIn;
  private double rightVoltsIn;

  @Override
  public void updateInputs(DriveInputs inputs) {
    inputs.leftPositionMeters = sim.getLeftPositionMeters();
    inputs.leftSpeedMPS = sim.getLeftVelocityMetersPerSecond();

    inputs.rightPositionMeters = sim.getRightPositionMeters();
    inputs.rightSpeedMPS = sim.getRightVelocityMetersPerSecond();

    sim.update(0.02);
  }

  // Drives the robot using arcade drive
  public void arcadeDriver(double speed, double theta) {
    WheelSpeeds speeds = DifferentialDrive.arcadeDriveIK(speed, theta, false);
    leftVoltsIn = PID.calculate(sim.getLeftVelocityMetersPerSecond(), speeds.left * kMaxWheelSpeedMPS);
    rightVoltsIn = PID.calculate(sim.getRightVelocityMetersPerSecond(), speeds.right * kMaxWheelSpeedMPS);

    sim.setInputs(leftVoltsIn, rightVoltsIn);
  }
  
  // Stops everything
  public void stop() {
    leftVoltsIn = 0.0;
    rightVoltsIn = 0.0;
    sim.setInputs(leftVoltsIn, rightVoltsIn);
  }

  public double getLeftAppliedVolts() {
    return leftVoltsIn;
  }

  public double getRightAppliedVolts() {
    return rightVoltsIn;
  }
}
