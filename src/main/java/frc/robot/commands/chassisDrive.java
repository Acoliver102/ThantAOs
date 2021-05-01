package frc.robot.commands;

import edu.wpi.first.wpilibj.SlewRateLimiter;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Chassis;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static frc.robot.Constants.autoFile;

public class chassisDrive extends CommandBase {

    Chassis cChassis;

    FileWriter cWriter;

    SlewRateLimiter driveLimit;


    /** Creates a new chassisDrive. */
    public chassisDrive(Chassis chassis) {
        cChassis = chassis;
        // adds a subsystem dependency
        addRequirements(cChassis);
        driveLimit = new SlewRateLimiter(10.0);

        try {
            cWriter = new FileWriter(new File(autoFile));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        try {
            cWriter = new FileWriter(new File(autoFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {

        // Sets the controller inputs to variables so they can be used easier



        double moveSpeed = -RobotContainer.driverController.getRawAxis(1);
        double rotateSpeed = 0.80 * RobotContainer.driverController.getRawAxis(4);

        // Calls the "driveChassis" command with the controller inputs as the commands

        if (!cChassis.runningAuton) {

            cChassis.driveChassis(moveSpeed, rotateSpeed);
            cChassis.lMotor.feed();
            cChassis.rMotor.feed();

            try {
                cWriter.append(cChassis.lMotor.get() + "," + cChassis.rMotor.get() + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void end(boolean isFinished) {
        try {
            cWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Writer closed");
    }

}
