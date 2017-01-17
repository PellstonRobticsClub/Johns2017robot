package org.usfirst.frc.team5314.robot;

import org.usfirst.frc.team5314.robot.commands.TurnTo0Command;
import org.usfirst.frc.team5314.robot.commands.TurnTo180Command;

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
	Joystick drivestick = new Joystick(RobotMap.driveStick);
	Button gyroResetbutton = new JoystickButton(drivestick, RobotMap.gyroResetButton);
	Button turnTo0 = new JoystickButton(drivestick, RobotMap.button0);
	Button turnTo180 = new JoystickButton(drivestick, RobotMap.button180);
	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:
	public void oi(){
		turnTo0.whenPressed(new TurnTo0Command());
		turnTo180.whenPressed(new TurnTo180Command());
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

	public boolean gyroResetTrue(){
		return gyroResetbutton.get();
	}
	public double getDriveX(){
		return drivestick.getRawAxis(0);
	}
	public double getDriveY(){
		return drivestick.getRawAxis(1);
	}
	public double getDriveTwist(){
		return drivestick.getRawAxis(4);
	}
}
