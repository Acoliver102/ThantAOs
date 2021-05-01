
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.commands.chassisDrive;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static frc.robot.Constants.autoFile;

public class Chassis extends SubsystemBase {
    // Initializes the motor variables
    public static WPI_TalonFX rMotor = null;
    public static WPI_TalonFX lMotor = null;

    public boolean runningAuton = false;

    Scanner chassisScanner;

    FileWriter chassisWriter;



    DifferentialDrive diffDrive = null;

    /** Creates a new Chassis. */
    public Chassis() throws IOException {

        // Names the motors
        rMotor = new WPI_TalonFX(0);
        rMotor.setNeutralMode(NeutralMode.Brake);
        lMotor = new WPI_TalonFX(1);
        lMotor.setNeutralMode(NeutralMode.Brake);

        diffDrive = new DifferentialDrive(lMotor, rMotor);

        chassisWriter = new FileWriter(new File(autoFile));
        chassisScanner = new Scanner(new File(autoFile));

        runningAuton = false;

        setDefaultCommand(new chassisDrive(this));

    }

    public void driveChassis(double fwdSpeed, double rotAmt) {
        // Uses the "arcadeDrive" function to move the robot
        diffDrive.curvatureDrive(fwdSpeed, rotAmt, true);
    }

    public void recordValues() throws IOException {
        chassisWriter.append(lMotor.get() + "," + rMotor.get() + "\n");
    }

    public void driveTank(double lSpeed, double rSpeed) {
        diffDrive.tankDrive(lSpeed, rSpeed);
    }

    @Override
    public void periodic() {
        diffDrive.feed();
        diffDrive.feedWatchdog();
        lMotor.feed();
        rMotor.feed();
    }



}
