package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Chassis;

public class AutonGroup extends SequentialCommandGroup {

    Chassis cChassis;

    public AutonGroup(Chassis chassis) {
        cChassis = chassis;
        addCommands(new ChassisDriveFromRecording(cChassis));
    }

}
