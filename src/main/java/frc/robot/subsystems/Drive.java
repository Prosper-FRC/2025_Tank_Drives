
// Copyright 2021-2025 FRC 6328
// http://github.com/Mechanical-Advantage
//
// This program is free software; you can redistribute it and/or
// modify it under the terms of the GNU General Public License
// version 3 as published by the Free Software Foundation or
// available in the root directory of this project.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU General Public License for more details.

package frc.robot.subsystems;

import static frc.robot.subsystems.DriveConstants.*;

import java.util.function.DoubleSupplier;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelPositions;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.littletonrobotics.junction.AutoLogOutput;
import org.littletonrobotics.junction.Logger;

public class Drive extends SubsystemBase {

    // Improves the expandability of the code later on
    public enum DriveState {
        Stop,
        ArcadeDrive,
        Test // A test command, for testing purposes... shocker
    }
    public static DriveState robotDriveState = DriveState.ArcadeDrive;

    private final DriveIO kDriveIO;
    private final DriveInputsAutoLogged inputs = new DriveInputsAutoLogged();

    @AutoLogOutput(key = "Drive/RotationEstimation")
    private double rotationEstimation = 0.0;
    private DifferentialDriveOdometry odometryEstimator = new DifferentialDriveOdometry(new Rotation2d(rotationEstimation), 0, 0);

    // Joystick inputs
    private DoubleSupplier speed = () -> 0.0;
    private DoubleSupplier theta = () -> 0.0;

    @AutoLogOutput(key = "Drive/OdometryPose")
    public static Pose2d odometryPose = new Pose2d(); 

    public Drive(DriveIO io) {
        kDriveIO = io;
    }

    public void supplyJoytickInputs(DoubleSupplier speed, DoubleSupplier theta) {
        this.speed = speed;
        this.theta = theta;
    }

    @Override
    public void periodic() {
        kDriveIO.updateInputs(inputs);
        Logger.processInputs("Drive", inputs);


        rotationEstimation += ((DriveConstants.kWheelRadius / DriveConstants.kTrackWidthMeters) * (inputs.leftSpeedMPS - inputs.rightSpeedMPS)) * 0.02;
    
        switch(robotDriveState) {
            case Stop:
                kDriveIO.stop();
                break;
            case ArcadeDrive:
                kDriveIO.arcadeDriver(speed.getAsDouble(), theta.getAsDouble());
                break;
            case Test:
                // Do nothing, for now
                break;
        }

        // Update odometry
        odometryPose = odometryEstimator.update(new Rotation2d(rotationEstimation), new DifferentialDriveWheelPositions(inputs.leftPositionMeters, inputs.rightPositionMeters));
    }

    // Drive commands

    /** 
     * This method will set the drive state command once. Its generally recommended that you use 
     * setDriveStateCommandContinuous to ensure the state is not re-overriden by the default command
     * <p><b>Reqires this drive subsystem.</b></p>
     * @param targetState The DriveState you would like to set the drive subsystem to use.
     * @return A new InstantCommand setting robotDriveState to targetState.
    */
    public Command setDriveStateCommand(DriveState targetState) {
        // We use the this keyword to say this specific instance of Drive subsystem is required.
        return new InstantCommand(() -> robotDriveState = targetState, this);
    }

    /** 
     * This method will continuously set the drive state command until overridden by another command.
     * <p><b>Reqires this drive subsystem.</b></p>
     * @param targetState The DriveState you would like to set the drive subsystem to use.
     * @return A new RunCommand setting robotDriveState to targetState.
    */
    public Command setDriveStateCommandContinuous(DriveState targetState) {
        return new RunCommand(() -> robotDriveState = targetState, this);
    }
}

// this thing will explode if i push it to an actual robot
// frankenstien's monster bro