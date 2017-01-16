package org.usfirst.frc.team5314.robot.subsystems;

import org.usfirst.frc.team5314.robot.Robot;
import org.usfirst.frc.team5314.robot.RobotMap;
import org.usfirst.frc.team5314.robot.commands.TeleDriveCommand;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ChassisSubsystem extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	
	//AnalogGyro gyro = new AnalogGyro(RobotMap.gyro);
	Talon frontLeftMotor = new Talon(RobotMap.frontLeftMotor);
	Talon rearLeftMotor = new Talon(RobotMap.rearLeftMotor);
	Talon frontRightMotor = new Talon(RobotMap.frontRightMotor);
	Talon rearRightMotor = new Talon(RobotMap.rearRightMotor);
	
	RobotDrive drivetrain = new RobotDrive(frontLeftMotor, 
										   rearLeftMotor, 
										   frontRightMotor, 
										   rearRightMotor);
	
	PowerDistributionPanel pdp = new PowerDistributionPanel();
	
	public ChassisSubsystem(){
		
		pdp.clearStickyFaults();
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new TeleDriveCommand());
	}
	
	public void TeleDrive(double x, double y, double twist){
		double angle = Robot.gyro.getAngle();
		drivetrain.mecanumDrive_Cartesian(x, y, twist, angle);
	}
	
	public void gyroReset(){
		Robot.gyro.reset();
	}
	public void updateStatus(){
		SmartDashboard.putNumber("gyro", Robot.gyro.getAngle());
	}
}
