package org.usfirst.frc.team5314.robot.commands;

import org.usfirst.frc.team5314.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TurnToAngleCommand extends Command {
	
	private double desiredAngle;
	private double twist;

    public TurnToAngleCommand(double angle) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.Chassis);
    	desiredAngle = angle;
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	SmartDashboard.putNumber("desired angle", desiredAngle);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	twist = ((desiredAngle) - Robot.ahrs.getAngle())*.05;
    	twist = (twist > .5) ? .5 : twist;
    	twist = (twist < -.5) ? -.5 : twist;
    	
    	Robot.Chassis.mecaDrive(0, 0, twist,0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Math.abs(Robot.ahrs.getAngle() - (desiredAngle))<=4);
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
