package org.usfirst.frc.team5314.robot.commands;

import org.usfirst.frc.team5314.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */


public class TurnTo0Command extends Command implements PIDOutput {
	
	PIDController turnController;
	double rotateToAngleRate;
	
	static final double kP = 0.05;
	static final double kI = 0.00;
	static final double kD = 0.00;
	static final double kF = 0.00;
	
	static final double kToleranceDegrees = 2.0f;

    public TurnTo0Command() {
        // Use requires() here to declare subsystem dependencies
        // eg. requir`es(chassis);
    	requires(Robot.Chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	turnController = new PIDController(kP, kI, kD, kF, Robot.gyro, this);
        turnController.setInputRange(-180.0f,  180.0f);
        turnController.setOutputRange(-1.0, 1.0);
        turnController.setAbsoluteTolerance(kToleranceDegrees);
        turnController.setContinuous(true);
        turnController.setSetpoint(0.0f);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	turnController.enable();
    	SmartDashboard.putNumber("rotate rate", rotateToAngleRate);
    	Robot.Chassis.TeleDrive(Robot.oi.getDriveX(), 
    					        Robot.oi.getDriveY(), 
    					        rotateToAngleRate);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Math.abs(Robot.gyro.getAngle()-179.9) < 3);
    }

    // Called once after isFinished returns true
    protected void end() {
    	turnController.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
   
	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		rotateToAngleRate = output;
		
	}
}
