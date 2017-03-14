package org.usfirst.frc.team5314.robot.subsystems;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class GearHolderSubsystem extends PIDSubsystem {
	static double p = 2.0;
	static double i = 0.0;
	static double d = 0.0;
	static double absoluteTolerance = 0.05;
	private CANTalon tilt = new CANTalon(5);

    public GearHolderSubsystem() {
    	super(p, i, d);// The constructor passes a name for the subsystem and the P, I and D constants that are sueed when computing the motor output
		setAbsoluteTolerance(0.05);
		getPIDController().setContinuous(false);
		// TODO Auto-generated constructor stub
	}

	// Initialize your subsystem here


    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        return tilt.getPulseWidthPosition();
    }

    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	tilt.set(output);
    }
}
