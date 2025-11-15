// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveTrainSubsystem;

public class DriveCommand extends Command {

  // Runs 20 millisenconds
  private DoubleSupplier speed;
  private DoubleSupplier theta;
  private DriveTrainSubsystem drive;

  public DriveCommand(DoubleSupplier speed, DoubleSupplier theta, DriveTrainSubsystem drive) {

    this.speed = speed;
    this.theta = theta;
    this.drive = drive;

    addRequirements(drive);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    drive.arcadeDrive(speed.getAsDouble(), theta.getAsDouble());
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }

}