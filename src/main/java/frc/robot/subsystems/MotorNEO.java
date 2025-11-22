package frc.robot.subsystems;

import java.util.function.DoubleSupplier;

import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkMaxConfig;

import frc.robot.subsystems.DriveConstants.motorConfiguration;

public class MotorNEO implements MotorIO {
    // Instantiate our motor
    private final SparkMax kMotor;
    private final SparkBaseConfig kConfiguration;
    
    // Create double suppliers for our logged values
    private final DoubleSupplier kTemperature;
    private final DoubleSupplier kPositionMeters;
    private final DoubleSupplier kSpeedMPS;

    // Name for logging purposes
    public final String kName;

    // Define our control types
    private final ControlType kControlVelocity = ControlType.kVelocity;

    public MotorNEO(motorConfiguration kMotorConfiguration, String motorName) {
        kMotor = new SparkMax(kMotorConfiguration.ID(), kMotorConfiguration.type());
        kName = motorName;

        // Configuration of motor
        kConfiguration = new SparkMaxConfig();

        kConfiguration.inverted(kMotorConfiguration.shouldInvert());
        kConfiguration.follow(kMotorConfiguration.followerID());
        kConfiguration.smartCurrentLimit(DriveConstants.kStallCurrentAmps);
        kConfiguration.idleMode(DriveConstants.kSparkIdleMode);
        
        // Apply PID Constants
        kConfiguration.closedLoop.p(kMotorConfiguration.p(), ClosedLoopSlot.kSlot0);
        kConfiguration.closedLoop.i(kMotorConfiguration.i(), ClosedLoopSlot.kSlot0);
        kConfiguration.closedLoop.d(kMotorConfiguration.d(), ClosedLoopSlot.kSlot0);

        // TODO: implement feedforward x sysid for some genuine practice

        kMotor.configure(kConfiguration, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        // We define our rules to setting kTemperature to take advantage of lazy updating and also make our lives a LOT easier when updating the inputs.
        kTemperature = () -> kMotor.getMotorTemperature();
        kPositionMeters = () -> kMotor.getAbsoluteEncoder().getPosition() * 2 * Math.PI * DriveConstants.kWheelRadius;
        kSpeedMPS = () -> kMotor.getAbsoluteEncoder().getVelocity() * 2 * Math.PI * DriveConstants.kWheelRadius;
    }

    @Override
    public void updateInputs(driveInputs inputs) {
        inputs.temperature = kTemperature.getAsDouble();
        inputs.positionMeters = kPositionMeters.getAsDouble();
        inputs.speedMPS = kSpeedMPS.getAsDouble();
    }

    @Override
    public void setMotorSpeedMPS(double metersPerSecond) {
        kMotor.getClosedLoopController().setReference((metersPerSecond / DriveConstants.kWheelRadius) / (2 * Math.PI), kControlVelocity);
    }

    @Override
    public void stop() {
        kMotor.stopMotor();
    }

    // NEO Specific method(s)
    public SparkMax getMotor() {
        return this.kMotor;
    }
}
