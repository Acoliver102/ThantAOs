package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Chassis;

import java.io.FileNotFoundException;
import java.util.Scanner;

import static frc.robot.Constants.autoFile;

public class ChassisDriveFromRecording extends CommandBase {

    Chassis cChassis;

    Scanner scanner;
    double startTime;
    double tDelta;
    double nextDouble;
    double timePassed;

    double lastLInput;
    double lastRInput;

    boolean onTime;

    public ChassisDriveFromRecording(Chassis chassis) {

        cChassis = chassis;

        try {
            scanner = new Scanner(autoFile);
            scanner.useDelimiter("[,\\n]");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        startTime = System.currentTimeMillis();
        tDelta = 0.0;
        nextDouble = 0.0;

        onTime = true;

        lastLInput = 0.0;
        lastRInput = 0.0;

        System.out.println("trying");

    }

    @Override
    public void initialize() {
        cChassis.runningAuton = true;
    }

    @Override
    public void execute() {
        if ((scanner != null) && (scanner.hasNextDouble())) {
            if (onTime) {
                nextDouble = scanner.nextDouble();
            }


            timePassed = System.currentTimeMillis() - startTime;
            tDelta = nextDouble - (System.currentTimeMillis() - startTime);

            System.out.println(tDelta);

            if (tDelta <= 0.0) {
                cChassis.lMotor.set(scanner.nextDouble());
                cChassis.rMotor.set(scanner.nextDouble());

                System.out.println("updated");

                onTime = true;
            } else {
                onTime = false;
            }

        }
        System.out.println("executing");


    }

    @Override
    public boolean isFinished() {
        return !scanner.hasNextDouble();
    }

    @Override
    public void end(boolean isFinished) {
        System.out.println("done");
        cChassis.runningAuton = false;
        cChassis.driveChassis(0.0, 0.0);
    }

}
