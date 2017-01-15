package org.usfirst.frc.team5314.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.SpeedController;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	public static final int gyro = 0;
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;
	//can motor controllers
	public static final int frontLeftMotor = 0;
	public static final int rearLeftMotor = 1;
	public static final int frontRightMotor = 2;
	public static final int rearRightMotor = 3;
	
	//usb joystics
	public static final int driveStick = 0;
	
	//drive joystick buttons
	public static final int gyroResetButton = 1;
	

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}
