package frc.robot.subsystems;

import org.littletonrobotics.junction.AutoLog;

public interface MotorIO {
    @AutoLog
    public static class driveInputs {
        public double temperature = 0.0;
        public double positionMeters = 0.0;
        public double speedMPS = 0.0;
    };

    default public void updateInputs(driveInputs inputs) {};

    default public void setMotorSpeedMPS(double dutyCycle) {};
    
    default public void stop() {};
};
