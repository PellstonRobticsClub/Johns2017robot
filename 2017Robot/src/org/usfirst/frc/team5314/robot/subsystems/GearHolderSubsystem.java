package org.usfirst.frc.team5314.robot.subsystems;

import org.usfirst.frc.team5314.robot.commands.GearMoveManual;
import org.usfirst.frc.team5314.robot.commands.GearTiltDownCommand;
import org.usfirst.frc.team5314.robot.commands.GearTiltStopCommand;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GearHolderSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private CANTalon tilt = new CANTalon(5);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new GearTiltStopCommand());
    }
    
    public void move(double speed){
    	tilt.set(speed);
    }
    
    public void updateStatus(){
    	SmartDashboard.putNumber("gear Angle", tilt.getPulseWidthPosition());
    }
    
    public double getEnc(){
    	return tilt.getPulseWidthPosition();
    }
}

