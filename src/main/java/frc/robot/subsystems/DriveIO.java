// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import org.littletonrobotics.junction.AutoLog;

// Mostly copied over from Advantage Kit DiffDrive Template
public interface DriveIO {
    @AutoLog
    public static class DriveInputs {
        public double leftPositionMeters = 0.0;
        public double leftSpeedMPS = 0.0;
            
        public double rightPositionMeters = 0.0;
        public double rightSpeedMPS = 0.0;
    }

    // Updates the set of loggable inputs
    public default void updateInputs(DriveInputs inputs) {}

    // Drives the robot using arcade drive
    public default void arcadeDriver(double speed, double theta) {}
    
    // Stops everything
    public default void stop() {}
}
