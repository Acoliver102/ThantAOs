package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Chassis;

public class chassisDrive extends CommandBase {

    Chassis cChassis;

    /** Creates a new chassisDrive. */
    public chassisDrive(Chassis chassis) {
        cChassis = chassis;
        // adds a subsystem dependency
        addRequirements(cChassis);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {}

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {

        // Sets the controller inputs to variables so they can be used easier

        double moveSpeed = -RobotContainer.driverController.getRawAxis(1);
        double rotateSpeed =  RobotContainer.driverController.getRawAxis(4);

        // Calls the "driveChassis" command with the controller inputs as the commands
        cChassis.driveChassis(moveSpeed, rotateSpeed);

        System.out.println(cChassis.getGyroHeading());

    }

}
