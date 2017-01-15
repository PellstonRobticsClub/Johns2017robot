package org.usfirst.frc.team5314.robot.subsystems;

import org.usfirst.frc.team5314.robot.RobotMap;
import org.usfirst.frc.team5314.robot.commands.TeleDriveCommand;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.hal.PDPJNI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ChassisSubsystem extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	ADXRS450_Gyro gyro = new ADXRS450_Gyro();
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
		gyro.calibrate();
		pdp.clearStickyFaults();
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new TeleDriveCommand());
	}
	
	public void TeleDrive(double x, double y, double twist){
		double angle = gyro.getAngle();
		drivetrain.mecanumDrive_Cartesian(x, y, twist, angle);
	}
	
	public void gyroReset(){
		gyro.reset();
	}
	public void updateStatus(){
		SmartDashboard.putNumber("gyro", gyro.getAngle());
	}
}
