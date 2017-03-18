package org.usfirst.frc.team5314.robot.subsystems;

import org.usfirst.frc.team5314.robot.RobotMap;
import org.usfirst.frc.team5314.robot.commands.ClawCloseCommand;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ClawSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private DoubleSolenoid clamp = new DoubleSolenoid(RobotMap.gearRelease, RobotMap.gearGrab);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ClawCloseCommand());
    }
    public void grabGear(){
    	clamp.set(DoubleSolenoid.Value.kForward);
    }
    
    public void ReleaseGear(){
    	clamp.set(DoubleSolenoid.Value.kReverse);
    }
}

