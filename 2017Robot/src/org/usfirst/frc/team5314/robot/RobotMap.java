package org.usfirst.frc.team5314.robot;

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
	public static final int frontLeftMotor = 4;
	public static final int rearLeftMotor = 1;
	public static final int frontRightMotor = 2;
	public static final int rearRightMotor = 3;
	public static final int GearTilt = 5;
	
	//usb joystics
	public static final int driveStick = 0;
	public static final int mecanismStick = 1;
	
	//mecanisms joystick buttons
	public static final int climbUp = 6;
	public static final int climbDown = 5;
	public static final int gearTiltUp =4;
	public static final int gearTiltDown =1;
	public static final int Gearopen = 3;
	public static final int GearClose =2;
	
	//drive joystick buttons
	public static final int MecaDrive =5;
	public static final int ArcadeDrive =6;
	public static final int allignLeftGoal = 3;
	public static final int allignRightGoal = 2;
	public static final int allignCenterGoal = 1;
	public static final int allignGearDrop = 4;
	public static final int allignToVision = 10;
	
	
	//pcm
	public static final int wheelsdown = 1;
	public static final int	wheelsup = 0;
	public static final int gearRelease = 2;
	public static final int gearGrab= 3;
	public static final int red = 4;
	
	
	//static values for programing
	public static final double tiltDown = 5000;
	public static final double tiltUp = 4100;

	
	
	
	

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}
