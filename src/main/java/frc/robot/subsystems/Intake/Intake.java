package frc.robot.subsystems.Intake;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
    public enum RollerGoals {
        kCoralInake(() -> 2.0),
        kAlgeaIntake(() -> -5.0),
        kScoreAlgea(() -> -5.0),
        kScoreCoral(() -> -4.0),

        custom(new, LoggedTunableNumber("Intake/Feedback/RollerSetPoint", 0,0));

        private DoubleSupplier goalVoltage;
        RollerGoal(DoubleSupplier, getVoltage) {
            this.goalVoltage = goalVoltage;
        }
        public double getGoalVoltage() {
            return this.getVoltage.getAsDouble();
        }
    }


    public enum PivotGoal
}
