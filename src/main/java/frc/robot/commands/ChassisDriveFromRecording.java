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

    }

    @Override
    public void execute() {
        if ((scanner != null) && (scanner.hasNextDouble())) {
            if (onTime) {
                nextDouble = scanner.nextDouble();
            }

            tDelta = nextDouble - (System.currentTimeMillis() - startTime);

            if (tDelta <= 0.0) {
                cChassis.lMotor.set(scanner.nextDouble());
                cChassis.rMotor.set(scanner.nextDouble());

                onTime = true;
            } else {
                onTime = false;
            }

        }
    }
}
