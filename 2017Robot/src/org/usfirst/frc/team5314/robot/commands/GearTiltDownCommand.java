package org.usfirst.frc.team5314.robot.commands;

import org.usfirst.frc.team5314.robot.Robot;
import org.usfirst.frc.team5314.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GearTiltDownCommand extends Command {
	private double speed;

    public GearTiltDownCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.gearHolder);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//speed = (-(RobotMap.gearTiltDown) - Robot.gearHolder.getEnc()*.005);
    	//speed = (speed < .5) ? .5 : speed;
    	speed = (-.25);
    	Robot.gearHolder.move(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
