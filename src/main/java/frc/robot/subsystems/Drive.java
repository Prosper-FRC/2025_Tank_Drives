package frc.robot.subsystems;

import static frc.robot.subsystems.DriveConstants.*;

import java.util.function.DoubleSupplier;

import org.littletonrobotics.junction.AutoLogOutput;
import org.littletonrobotics.junction.Logger;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelPositions;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.DifferentialDrive.WheelSpeeds;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drive extends SubsystemBase {
    
    // Improves the expandability of the code later on
    public enum DriveState {
        Stop,
        ArcadeDrive,
        Test // A test command, do whatever you want with it.
    }

    public final MotorIO[] kMotors;

    @AutoLogOutput(key = "Drive/OdometryPose")
    public static Pose2d odometryPose = new Pose2d(); 

    public static final driveInputsAutoLogged[] kMotorAutologgers = {
        new driveInputsAutoLogged(),
        new driveInputsAutoLogged(),
        new driveInputsAutoLogged(),
        new driveInputsAutoLogged()
    };

    public static DriveState robotDriveState = DriveState.ArcadeDrive;
    
    @AutoLogOutput(key = "Drive/RotationEstimation")
    private double rotationEstimation = 0.0;
    private DifferentialDriveOdometry odometryEstimator = new DifferentialDriveOdometry(new Rotation2d(rotationEstimation), 0, 0);

    // Joystick inputs
    private DoubleSupplier speedStick = () -> 0.0;
    private DoubleSupplier thetaStick = () -> 0.0;

    public Drive(MotorIO[] motors) {
        kMotors = motors;
    }

    public void supplyJoytickInputs(DoubleSupplier speed, DoubleSupplier theta) {
        speedStick = speed;
        thetaStick = theta;
    }

    public void arcadeDrive() {
        // We square the inputs to give a more responive feel (but its all done under the hood which is nice).
        WheelSpeeds speeds = DifferentialDrive.arcadeDriveIK(speedStick.getAsDouble(), thetaStick.getAsDouble(), true);
        // Set the target motor speeds
        kMotors[0].setMotorSpeedMPS(speeds.left * kMaxWheelSpeedMPS);
        kMotors[1].setMotorSpeedMPS(speeds.right * kMaxWheelSpeedMPS);
    }

    // Main update loop logic
    @Override
    public void periodic() {
        // Log inputs to AdvantageKit
        kMotors[0].updateInputs(kMotorAutologgers[0]);
        kMotors[1].updateInputs(kMotorAutologgers[1]);
        kMotors[2].updateInputs(kMotorAutologgers[2]);
        kMotors[3].updateInputs(kMotorAutologgers[3]);
        Logger.processInputs("Drive/Motors/FrontLeft", kMotorAutologgers[0]);
        Logger.processInputs("Drive/Motors/FrontRight", kMotorAutologgers[1]);
        Logger.processInputs("Drive/Motors/BackLeft", kMotorAutologgers[2]);
        Logger.processInputs("Drive/Motors/BackRight", kMotorAutologgers[3]);

        // Estimates how much the robot has rotated without a gyro
        /*
         * We are going to use the forward kinematic equations of a differential drive to find the
         * estimated theta value of the differential drive which should go as follows:
         * omega = (r/d) * (VR - VL) 
         * where omega is the angular velocity, r = radius, d = track width, V = velocity and R and L are right and left.
         * We can then let omega accumulate over intervals to get our estimated rotation.
         * 
         * We also need to integrate theta to get the change over a timestep (0.02 seconds).
         */
        rotationEstimation += ((kWheelRadius / kTrackWidthMeters) * (kMotorAutologgers[0].speedMPS - kMotorAutologgers[1].speedMPS)) * 0.02;

        switch(robotDriveState) {
            case Stop:
                for(int i = 0; i < kMotors.length; ++i) {
                    kMotors[i].stop();
                }
                break;
            case ArcadeDrive:
                // Set the motors according to the differential drive class
                arcadeDrive();
                break;
            case Test:
                // Do nothing
                break;
        }

        // Update odometry
        odometryPose = odometryEstimator.update(new Rotation2d(rotationEstimation), new DifferentialDriveWheelPositions(kMotorAutologgers[0].positionMeters, kMotorAutologgers[1].positionMeters));
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
