package org.usfirst.frc.team5314.robot.subsystems;

import org.usfirst.frc.team5314.robot.Robot;
import org.usfirst.frc.team5314.robot.RobotMap;
import org.usfirst.frc.team5314.robot.commands.TeleDriveCommand;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ChassisSubsystem extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	
	//AnalogGyro gyro = new AnalogGyro(RobotMap.gyro);
	
	CANTalon frontLeftMotor = new CANTalon(RobotMap.frontLeftMotor);
	CANTalon rearLeftMotor = new CANTalon(RobotMap.rearLeftMotor);
	CANTalon frontRightMotor = new CANTalon(RobotMap.frontRightMotor);
	CANTalon rearRightMotor = new CANTalon(RobotMap.rearRightMotor);
	DoubleSolenoid dropWheels = new DoubleSolenoid(RobotMap.gearRelease, RobotMap.gearGrab);
	RobotDrive drivetrain = new RobotDrive(frontLeftMotor, 
										   rearLeftMotor, 
										   frontRightMotor, 
										   rearRightMotor);
	
	PowerDistributionPanel pdp = new PowerDistributionPanel();
	
	public ChassisSubsystem(){
		frontRightMotor.setInverted(true);
		rearRightMotor.setInverted(true);
		pdp.clearStickyFaults();
		liftWheels();
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new TeleDriveCommand());
		
	}
	
	public void TeleDrive(double x, double y, double twist, double angle){
		drivetrain.mecanumDrive_Cartesian(x, y, twist, angle);
	}
	
	
	public void updateStatus(){
		int size;
		double centerX;
		synchronized (Robot.imgLock) {
			size = Robot.CountContours;
			centerX = Robot.centerX;
			
		}
		double turn = centerX - (Robot.IMG_WIDTH / 2);
		SmartDashboard.putNumber("Box center", turn);
		SmartDashboard.putNumber("contours", size);
		SmartDashboard.putBoolean("target Seen", Robot.FoundContours);
		SmartDashboard.putNumber("gyro", Robot.ahrs.getAngle());
		SmartDashboard.putNumber("dist", Robot.dist.getVoltage());
		SmartDashboard.putNumber("encoder", GetEnc());
		
		
	}
	
	public void liftWheels(){
		dropWheels.set(DoubleSolenoid.Value.kReverse);
	}
	
	public double GetEnc(){
		return frontLeftMotor.getEncPosition();
	}
	public void resetEnc(){
		frontLeftMotor.setEncPosition(0);
	}
}
