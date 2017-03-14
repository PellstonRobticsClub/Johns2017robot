package org.usfirst.frc.team5314.robot.commands;

import org.usfirst.frc.team5314.robot.Robot;
import org.usfirst.frc.team5314.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GearTiltDownCommand extends Command {

    public GearTiltDownCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.gearHolder);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.gearHolder.setSetpoint(RobotMap.tiltDown);
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.gearHolder.enable();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Math.abs(Robot.gearHolder.getPosition() - RobotMap.tiltDown)< 0.05);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.gearHolder.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
