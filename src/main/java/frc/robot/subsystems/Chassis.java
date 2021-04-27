
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

public class Chassis extends SubsystemBase {
    // Initializes the motor variables
    public static WPI_TalonFX rMotor = null;
    public static WPI_TalonFX lMotor = null;

    public boolean runningAuton = false;



    DifferentialDrive diffDrive = null;

    /** Creates a new Chassis. */
    public Chassis() {

        // Names the motors
        rMotor = new WPI_TalonFX(0);
        rMotor.setNeutralMode(NeutralMode.Brake);
        lMotor = new WPI_TalonFX(1);
        lMotor.setNeutralMode(NeutralMode.Brake);

        diffDrive = new DifferentialDrive(lMotor, rMotor);


    }

    public void driveChassis(double fwdSpeed, double rotAmt) {
        // Uses the "arcadeDrive" function to move the robot
        diffDrive.curvatureDrive(fwdSpeed, rotAmt, true);
    }



}
