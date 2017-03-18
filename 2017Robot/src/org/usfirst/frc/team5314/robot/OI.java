package org.usfirst.frc.team5314.robot;



import org.usfirst.frc.team5314.robot.commands.ArcadeDriveCommand;
import org.usfirst.frc.team5314.robot.commands.ClawCloseCommand;
import org.usfirst.frc.team5314.robot.commands.ClawOpenCommand;
import org.usfirst.frc.team5314.robot.commands.ClimberDownCommand;
import org.usfirst.frc.team5314.robot.commands.ClimberUpCommand;
import org.usfirst.frc.team5314.robot.commands.GearTiltDownCommand;
import org.usfirst.frc.team5314.robot.commands.GearTiltUpCommand;
import org.usfirst.frc.team5314.robot.commands.MecaDriveCommand;
import org.usfirst.frc.team5314.robot.commands.TurnToAngleCommand;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	static Joystick drivestick = new Joystick(RobotMap.driveStick);
	static Joystick mecanismStick = new Joystick(RobotMap.mecanismStick);
	
	static Button climb = new JoystickButton(mecanismStick, RobotMap.climbUp);
	static Button climbDown = new JoystickButton(mecanismStick, RobotMap.climbDown);
	static Button gearTiltUp = new JoystickButton(mecanismStick, RobotMap.gearTiltUp);
	static Button gearTiltDown = new JoystickButton(mecanismStick, RobotMap.gearTiltDown);
	static Button Gearopen = new JoystickButton(mecanismStick, RobotMap.Gearopen);
	static Button GearClose = new JoystickButton(mecanismStick, RobotMap.GearClose);
	
	static Button MecaDrive = new JoystickButton(drivestick, RobotMap.MecaDrive);
	static Button ArcadeDriv = new JoystickButton(drivestick, RobotMap.ArcadeDrive);
	static Button allignLeftGoal = new JoystickButton(drivestick, RobotMap.allignLeftGoal);
	static Button allignRightGoal = new JoystickButton(drivestick, RobotMap.allignRightGoal);
	static Button allignCenterGoal = new JoystickButton(drivestick, RobotMap.allignCenterGoal);
	static Button allignGearDrop = new JoystickButton(drivestick, RobotMap.allignGearDrop);
	static Button allignToVision = new JoystickButton(drivestick, RobotMap.allignToVision);
	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:
	public OI(){
		climb.whileHeld(new ClimberUpCommand());
		climbDown.whileHeld(new ClimberDownCommand());
		allignLeftGoal.whenPressed(new TurnToAngleCommand(60));
		allignRightGoal.whenPressed(new TurnToAngleCommand(-60));
		allignCenterGoal.whenPressed(new TurnToAngleCommand(0));
		gearTiltUp.whileHeld(new GearTiltUpCommand());
		gearTiltDown.whileHeld(new GearTiltDownCommand());
		Gearopen.whenPressed(new ClawOpenCommand());
		GearClose.whenPressed(new ClawCloseCommand());
		MecaDrive.whenPressed(new MecaDriveCommand());
		ArcadeDriv.whenPressed(new ArcadeDriveCommand());
	}
	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());


	public double getDriveX(){
		return drivestick.getRawAxis(0);
	}
	public double getDriveY(){
		return drivestick.getRawAxis(1);
	}
	public double getDriveTwist(){
		return drivestick.getRawAxis(4);
	}
	public boolean getClimbUpButton(){
		return climb.get();
	}
	public boolean getClimbDownrButton(){
		return climbDown.get();
	}
	public double getGearupAxis(){
		return mecanismStick.getRawAxis(5);
	}
}
