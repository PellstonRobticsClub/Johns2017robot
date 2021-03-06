package org.usfirst.frc.team5314.robot.commands;

import org.usfirst.frc.team5314.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveToDistFromWallCommand extends Command {
	private double speed;
	private double angle;
	private double twist;
	private double distance;

    public DriveToDistFromWallCommand(double dist) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.Chassis);
    	distance = dist;
    	
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	angle = Robot.ahrs.getAngle();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	speed = (distance-Robot.dist.getVoltage())*10;
    	speed = (speed < -.5) ? -.5 : speed;
    	twist = (angle - Robot.ahrs.getAngle())*.1;
    	SmartDashboard.putNumber("twist", twist);
    	Robot.Chassis.mecaDrive(0, speed, twist,0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Robot.dist.getVoltage()< distance);
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
