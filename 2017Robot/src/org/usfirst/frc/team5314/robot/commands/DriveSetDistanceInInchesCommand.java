package org.usfirst.frc.team5314.robot.commands;

import org.usfirst.frc.team5314.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveSetDistanceCommand extends Command {
	
	private double angle;
	private double speed;
	private double twist;
	private double distance;

	
	
	// pass in encoder count at 2730 per foot
    public DriveSetDistanceCommand(double distance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	// this.distance = distance * 227.5
    	this.distance = distance;
    	requires(Robot.Chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	angle = Robot.ahrs.getAngle();
    	Robot.Chassis.resetEnc();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//speed = Robot.Chassis.GetEnc() -(distance -100)*.005;
    	//speed = (speed < -.5) ? -.5 : speed;
    	speed = .5;
    	twist = 0;
    	//twist = (angle - Robot.ahrs.getAngle())*.01;
    	//SmartDashboard.putNumber("twist", twist);
    	Robot.Chassis.mecaDrive(0, -speed, twist,0);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Robot.Chassis.GetEnc() < distance);
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
