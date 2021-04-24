package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Chassis;

public class ChassisDriveAuton extends CommandBase {

    Chassis cChassis;
    Timer timer;

    double fwd;
    double rot;
    double time;

    public ChassisDriveAuton(double f, double r, double t, Chassis chassis) {
        cChassis = chassis;
        timer = new Timer();

        addRequirements(cChassis);

        fwd = f;
        rot = r;
        time = t;
    }

    @Override
    public void initialize() {
        timer.reset();
        timer.start();
        System.out.println("Auton Starting");
    }

    @Override
    public void execute() {
        cChassis.driveChassis(fwd, rot);
    }

    @Override
    public boolean isFinished() {
        return (timer.hasPeriodPassed(time));
    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("ended");
    }



}
