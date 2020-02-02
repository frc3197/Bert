/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climber extends SubsystemBase {
  /**
   * Creates a new Climber.
   */
  public static final WPI_TalonFX climberWinch = new WPI_TalonFX(Constants.TalonID.kclimberWinch.id);
  public static final WPI_TalonFX climberTelescope = new WPI_TalonFX(Constants.TalonID.kclimberTelescope.id);

  public Climber() {
    climberWinch.setSafetyEnabled(false);
    climberTelescope.setSafetyEnabled(false);
  }

  public void setWinchMotor(double val) {
    climberWinch.set(val);
  }

  public void setTelescopeMotor(double val) {
    climberTelescope.set(val);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
