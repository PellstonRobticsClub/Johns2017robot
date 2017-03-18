package org.usfirst.frc.team5314.robot.subsystems;

import org.usfirst.frc.team5314.robot.commands.ClimberStopCommand;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ClimberSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private CANTalon climb = new CANTalon(6);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ClimberStopCommand());
    }
    
    public void move(double speed){
    	climb.set(speed);
    }
}

