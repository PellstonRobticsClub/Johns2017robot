
package org.usfirst.frc.team5314.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.VisionThread;
import javafx.scene.chart.LineChart.SortingPolicy;

import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team5314.robot.GripPipeline;

import java.net.StandardProtocolFamily;

import org.usfirst.frc.team5314.robot.commands.AllignToTargetXaxisCommand;
import org.usfirst.frc.team5314.robot.commands.DriveSetDistanceCommand;
import org.usfirst.frc.team5314.robot.commands.DriveToDistFromWallCommand;
import org.usfirst.frc.team5314.robot.commands.TeleDriveCommand;

import org.usfirst.frc.team5314.robot.commands.TurnToAngleCommand;
import org.usfirst.frc.team5314.robot.commands.autoDriveForwardTurn90CommandGroup;
import org.usfirst.frc.team5314.robot.subsystems.ChassisSubsystem;
import org.usfirst.frc.team5314.robot.subsystems.GearHolderSubsystem;

import com.kauailabs.navx.frc.AHRS;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static final ChassisSubsystem Chassis = new ChassisSubsystem();
	public static final GearHolderSubsystem gearHolder = new GearHolderSubsystem();
	public static OI oi;
	public static Compressor compressor = new Compressor();
	public static AHRS ahrs = new AHRS(SPI.Port.kMXP);
	//public static ADXRS450_Gyro gyro = new ADXRS450_Gyro();
	public static AnalogInput dist = new AnalogInput(0);
	
	public static final int IMG_WIDTH = 320;
	private static final int IMG_HEIGHT = 240;
	public VisionThread visionThread;
	public static final Object imgLock = new Object();
	public static double centerX = 0.0;
	public static int CountContours = 0;
	public static boolean FoundContours = false;
	//public static Encoder enc = new Encoder(0, 1);
	
	
	

	Command autonomousCommand;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
	    camera.setResolution(IMG_WIDTH, IMG_HEIGHT);
	   
	    
	    visionThread = new VisionThread(camera, new GripPipeline(), pipeline -> {
	        
	        	synchronized (imgLock) {
                	CountContours = pipeline.filterContoursOutput().size();
            	}
	        	switch (pipeline.filterContoursOutput().size()) {
	        	case 3:
	        		Rect r13 = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
	        		Rect r23 = Imgproc.boundingRect(pipeline.filterContoursOutput().get(1));
	        		Rect r33 = Imgproc.boundingRect(pipeline.filterContoursOutput().get(2));
	        		
	        		if (Math.abs((r13.x - r23.x)) < 4){
	        			r23 = r33;
	        		} else if (Math.abs((r13.x - r33.x)) < 4){
	        			
	        		} else if (Math.abs((r23.x - r33.x)) < 4){
	        			
	        		}else {
	        			synchronized (imgLock) {
			        		FoundContours = false;
			        		centerX = 0;
			        	}
		            	break;
	        		}
	        		
	        		Rect rLeft3 = (r13.x < r23.x) ? r13 : r23;
	        		Rect rRight3 = (r13.x > r23.x) ? r13 : r23;
	        		
	        		double width3 = (rRight3.x+rRight3.width)-rLeft3.x;
	        		synchronized (imgLock) {
	                	centerX = rLeft3.x + (width3 / 2);
	                	FoundContours = true;
	            	}
	        		break;
	        	case 2:
	        		Rect r1 = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
	        		Rect r2 = Imgproc.boundingRect(pipeline.filterContoursOutput().get(1));
	        		
	        		Rect rLeft = (r1.x < r2.x) ? r1 : r2;
	        		Rect rRight = (r1.x > r2.x) ? r1 : r2;
	        		
	        		double width = (rRight.x+rRight.width)-rLeft.x;
	        		synchronized (imgLock) {
	                	centerX = rLeft.x + (width / 2);
	                	FoundContours = true;
	            	}
	        		break;
	        	case 1:
	        		//Rect r2 = Imgproc.boundingRect(pipeline.filterContoursOutput().get(1));
	        		Rect r = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
	            	synchronized (imgLock) {
	                	centerX = r.x + (r.width / 2);
	                	FoundContours = true;
	            	}
	            	break;
	            default:
	            	synchronized (imgLock) {
		        		FoundContours = false;
		        		centerX = 0;
		        	}
	            	break;
	        	}
	        
	    });
	    visionThread.start();
		
		oi = new OI();
		// chooser.addObject("My Auto", new MyAutoCommand());
		
		SmartDashboard.putData("Allign To Target", new AllignToTargetXaxisCommand(0));
		SmartDashboard.putData("drive forward", new DriveSetDistanceCommand(1000));
		SmartDashboard.putData("turnToAngle", new TurnToAngleCommand(90));
		SmartDashboard.putData("runAuto", new autoDriveForwardTurn90CommandGroup());
		SmartDashboard.putData("drive to wall", new DriveToDistFromWallCommand());
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		compressor.setClosedLoopControl(true);
		Scheduler.getInstance().run();
		Chassis.updateStatus();
		gearHolder.updateStatus();
		
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		//autonomousCommand = chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		compressor.setClosedLoopControl(true);
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putData(Chassis);
		SmartDashboard.putData(Scheduler.getInstance());
		Chassis.updateStatus();
		
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
