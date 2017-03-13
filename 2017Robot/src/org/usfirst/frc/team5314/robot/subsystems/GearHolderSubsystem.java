package org.usfirst.frc.team5314.robot.subsystems;

import org.usfirst.frc.team5314.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GearHolderSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private CANTalon tilt = new CANTalon(RobotMap.GearTilt);
	private DoubleSolenoid clamp = new DoubleSolenoid(RobotMap.gearRelease, RobotMap.gearGrab);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void updateStatus(){
    	SmartDashboard.putNumber("gear Angle", tilt.getPulseWidthPosition());
    }
    
    public void grabGear(){
    	clamp.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void ReleaseGear(){
    	clamp.set(DoubleSolenoid.Value.kForward);
    }
}

