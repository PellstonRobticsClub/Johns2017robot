package org.usfirst.frc.team5314.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class autoDriveForwardTurn90CommandGroup extends CommandGroup {

    public autoDriveForwardTurn90CommandGroup() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
    	addSequential(new DriveSetDistanceCommand(1000));
    	addSequential(new ChassisStopCommand());
    	addSequential(new TurnToAngleCommand(90));
    	addSequential(new ChassisStopCommand());
    	addSequential(new AllignToTargetXaxisCommand(0));
    	addSequential(new ChassisStopCommand());
    	addSequential(new AllignToTargetXaxisCommand(0.07));
    	addSequential(new ChassisStopCommand());

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
