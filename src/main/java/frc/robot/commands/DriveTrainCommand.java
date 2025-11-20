
package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;

import edu.wpi.first.wpilibj2.command.Subsystem;

import frc.robot.subsystems.DriveTrainSubsystem;

public class DriveTrainCommand extends Command 
{
    private DoubleSupplier speed;
    private DoubleSupplier theta;
    private DriveTrainSubsystem drive;

    public DriveTrainCommand(DoubleSupplier speed, DoubleSupplier theta, DriveTrainSubsystem drive)
    {
        this.speed = speed;
        this.theta = theta;
        this.drive = drive;

        addRequirements(drive);

    }

@Override
public void initialize()
{

}

@Override
public void execute()
{
    drive.arcadeDrive(speed.getAsDouble(),theta.getAsDouble());
}






}
