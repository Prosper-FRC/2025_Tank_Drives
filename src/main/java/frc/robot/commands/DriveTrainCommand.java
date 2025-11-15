package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveTrainSubSystem;

public class DriveTrainCommand extends Command {
    private DoubleSupplier speed;
    private DoubleSupplier theta;
    private DriveTrainSubSystem drive;

    public DriveTrainCommand(DoubleSupplier speed, DoubleSupplier theta, DriveTrainSubSystem drive) {
        this.speed = speed;
        this.theta = theta;
        this.drive = drive;
        
        addRequirements(drive);
    }

    @Override 
    public void initialize() {

    }

    @Override 
    public void execute() {
        drive.arcadeDrive(speed.getAsDouble(), theta.getAsDouble());
    }

    @Override 
    public void end(boolean interrupted) {
        
    }

    @Override 
    public boolean isFinished() {
        return false;
    }
}
