package org.usfirst.frc.team5314.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team5314.robot.Robot;

/**
 *
 */
public class MecaDriveCommand extends Command {
	public MecaDriveCommand() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.Chassis);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.Chassis.liftWheels();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		double angle = Robot.ahrs.getAngle();
		Robot.Chassis.mecaDrive(Robot.oi.getDriveX(), Robot.oi.getDriveY(), Robot.oi.getDriveTwist(),angle);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
