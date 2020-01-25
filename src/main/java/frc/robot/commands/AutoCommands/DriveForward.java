/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class DriveForward extends PIDCommand {
  DriveTrain drivetrain;
  double distance;
  double output;
  double angle;
  /**
   * Creates a new DriveForward.
   */

  PIDController gyroPID;

  public DriveForward(double distance, DriveTrain drivetrain, double angle) {

    super(

        // The controller that the command will use
        new PIDController(Constants.PID_Constants.kForward.kP, Constants.PID_Constants.kForward.kI,
            Constants.PID_Constants.kForward.kD),
        // This should return the measurement
        drivetrain::getEncoderValue,
        // This should return the setpoint (can also be a constant)
        distance,
        // This uses the output
        output -> drivetrain.tankDrive((-Math.pow(output, 1 / 2)), -Math.pow(output, 1 / 2)));
    this.drivetrain = drivetrain;
    this.distance = distance;
    this.angle = angle;
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
    gyroPID = new PIDController(Constants.PID_Constants.kTurn.kP, Constants.PID_Constants.kTurn.kI,
        Constants.PID_Constants.kTurn.kD);
    gyroPID.setTolerance(2);
    gyroPID.setSetpoint(angle);
  }

  public void initialize() {

  }

  public void execute() {
    GyroPID();
  }

  public double GyroPID() {
    return gyroPID.calculate(drivetrain.gyro.getAngle());
  }

  // public void execute() {
  // SmartDashboard.putNumber("Left Motor Input",
  // -getController().calculate(drivetrain.getEncoderValue(), distance) * 0.2);
  // SmartDashboard.putNumber("Right Motor Input",
  // -getController().calculate(drivetrain.getEncoderValue(), distance) * 0.2);
  // }

  public void end(boolean interrupted) {
    drivetrain.tankDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (Math.abs(distance - drivetrain.getEncoderValue()) <= 0.05) {
      return true;
    } else {
      return false;
    }
  }
}