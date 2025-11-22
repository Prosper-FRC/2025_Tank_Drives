package frc.robot.subsystems;

import static frc.robot.subsystems.DriveConstants.*;

import java.util.function.DoubleSupplier;

import org.littletonrobotics.junction.Logger;

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

    public final MotorNEO[] kMotors = {
        new MotorNEO(frontLeft, "FrontLeft"),
        new MotorNEO(frontRight, "FrontRight"),
        new MotorNEO(backLeft, "BackLeft"),
        new MotorNEO(backRight, "BackRight")
    };

    public static final driveInputsAutoLogged kMotorAutologger = new driveInputsAutoLogged(); 

    public static DriveState robotDriveState = DriveState.ArcadeDrive;

    // Joystick inputs
    private DoubleSupplier speedStick = () -> 0.0;
    private DoubleSupplier thetaStick = () -> 0.0;

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
        for(int i = 0; i < kMotors.length; ++i) {
            // NOTE We're using the same AK auto logger for all four motors which maybe will cause issues? But probably not.
            kMotors[i].updateInputs(kMotorAutologger);
            Logger.processInputs("DriveInputs/" + kMotors[i].kName, kMotorAutologger);
        }

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
