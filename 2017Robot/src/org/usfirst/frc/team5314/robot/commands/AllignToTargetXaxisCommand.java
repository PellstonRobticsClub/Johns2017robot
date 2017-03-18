package org.usfirst.frc.team5314.robot.commands;

import org.usfirst.frc.team5314.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AllignToTargetXaxisCommand extends Command {
	
	private double angle;
	private double twist;
	private double drive;
	private double dist;

    public AllignToTargetXaxisCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	dist = Robot.dist.getVoltage();
    	requires(Robot.Chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	angle = Robot.ahrs.getAngle();
    	if (dist ==0){
    		dist = Robot.Chassis.GetEnc();
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double strafe;
    	
    	synchronized (Robot.imgLock) {
    		strafe = (Robot.centerX- (Robot.IMG_WIDTH / 2))*.03;
    	}
    	
    	SmartDashboard.putNumber("strafe", strafe);
    	twist = (angle-Robot.ahrs.getAngle())*.03;
    	strafe = (strafe > .4) ? .4 : strafe;
    	strafe = (strafe < -.4) ? -.4 : strafe;
    	drive = (dist-Robot.Chassis.GetEnc());
    	drive = (drive < -.5) ? -.5 : drive;
    	Robot.Chassis.mecaDrive(0, strafe, 0 ,0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (!Robot.FoundContours){
    		return true;
    	}
        return (Math.abs(Robot.centerX- (Robot.IMG_WIDTH / 2)) < 5);
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
