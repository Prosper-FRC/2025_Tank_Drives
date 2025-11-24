package frc.robot.subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;

import static frc.robot.subsystems.DriveConstants.*;

public class MotorSim implements MotorIO {
    DCMotorSim simMotor = new DCMotorSim(
        LinearSystemId.createDCMotorSystem(DCMotor.getNEO(1), 0.004, kGearRatio),
        DCMotor.getNEO(1),
        0.0, 0.0
    );

    private static final PIDController motorPID = new PIDController(0.5, 0, 0.0025);

    private double voltageIn;

    @Override
    public void updateInputs(driveInputs inputs) {
        inputs.temperature = -1.0;
        inputs.positionMeters = simMotor.getAngularPositionRad() * kWheelRadius;
        inputs.speedMPS = simMotor.getAngularVelocityRadPerSec() * kWheelRadius;

        simMotor.setInputVoltage(voltageIn);
        simMotor.update(0.02);
    }

    @Override
    public void setMotorSpeedMPS(double MPS) {
        voltageIn = motorPID.calculate(simMotor.getAngularVelocityRadPerSec() * kWheelRadius, MPS);
    }

    @Override
    public void stop() {
        voltageIn = 0.0;
    }
}
