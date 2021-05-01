package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Chassis;


import java.io.*;
import java.sql.Array;
import java.util.List;
import java.util.Scanner;

import static frc.robot.Constants.append;
import static frc.robot.Constants.autoFile;

public class ChassisDriveFromRecording extends CommandBase {

    Chassis cChassis;
    BufferedReader cReader;
    int row;
    String currentLine;
    String[] split;


    public ChassisDriveFromRecording(Chassis chassis) {

        split = new String[]{"0.0", "0.0"};
        try {
            cReader = new BufferedReader(new FileReader(new File(autoFile)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        row = 0;
        cChassis = chassis;
    }

    @Override
    public void initialize() {
        System.out.println("trying");
        try {
            cReader.close();
            cReader = new BufferedReader(new FileReader(new File(autoFile)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (cChassis != null) {
            cChassis.runningAuton = true;
        }
    }

    @Override
    public void execute() {


        try {
            if ((currentLine = cReader.readLine()) != null) {
                split = currentLine.split(",");
                System.out.println(split[0]);
                cChassis.lMotor.set(ControlMode.PercentOutput, Double.parseDouble(split[0]));
                cChassis.rMotor.set(ControlMode.PercentOutput, Double.parseDouble(split[1]));

            }
        } catch (IOException e) {
            System.out.println("System IOException");
        }
        cChassis.lMotor.set(ControlMode.PercentOutput, Double.parseDouble(split[0]));

    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean isFinished) {
        System.out.println("done");
        if (cChassis != null) {
            cChassis.driveChassis(0.0, 0.0);
            cChassis.runningAuton = false;
        }
        if (cReader != null) {
            try {
                cReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }

}
