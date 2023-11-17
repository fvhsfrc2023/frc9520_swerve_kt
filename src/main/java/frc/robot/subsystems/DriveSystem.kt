package frc.robot.subsystems

import com.ctre.phoenix.motorcontrol.TalonFXControlMode
import com.ctre.phoenix.motorcontrol.can.TalonFX
import frc.robot.Constants.DriveSystemConst
import frc.robot.Utils.MotorUtils

object DriveSystem {
    private val powerMotorFL = TalonFX(2)
    private val powerMotorFR = TalonFX(7)
    private val powerMotorRL = TalonFX(3)
    private val powerMotorRR = TalonFX(5)

    private val thetaMotorFL = TalonFX(1)
    private val thetaMotorFR = TalonFX(6)
    private val thetaMotorRL = TalonFX(4)
    private val thetaMotorRR = TalonFX(8)


    init {
        MotorUtils.configureMotor(
            powerMotorFL,
            DriveSystemConst.POWER_P,
            DriveSystemConst.POWER_I,
            DriveSystemConst.POWER_D,
//            DriveSystemConst.POWER_F,
//            DriveSystemConst.POWER_I_ZONE
        )
        MotorUtils.configureMotor(
            powerMotorFR,
            DriveSystemConst.POWER_P,
            DriveSystemConst.POWER_I,
            DriveSystemConst.POWER_D,
//            DriveSystemConst.POWER_F,
//            DriveSystemConst.POWER_I_ZONE
        )
        MotorUtils.configureMotor(
            powerMotorRL,
            DriveSystemConst.POWER_P,
            DriveSystemConst.POWER_I,
            DriveSystemConst.POWER_D,
//            DriveSystemConst.POWER_F,
//            DriveSystemConst.POWER_I_ZONE
        )
        MotorUtils.configureMotor(
            powerMotorRR,
            DriveSystemConst.POWER_P,
            DriveSystemConst.POWER_I,
            DriveSystemConst.POWER_D,
//            DriveSystemConst.POWER_F,
//            DriveSystemConst.POWER_I_ZONE
        )

        MotorUtils.configureMotor(
            thetaMotorFL,
            DriveSystemConst.THETA_P,
            DriveSystemConst.THETA_I,
            DriveSystemConst.THETA_D
        )
        MotorUtils.configureMotor(
            thetaMotorFR,
            DriveSystemConst.THETA_P,
            DriveSystemConst.THETA_I,
            DriveSystemConst.THETA_D
        )
        MotorUtils.configureMotor(
            thetaMotorRL,
            DriveSystemConst.THETA_P,
            DriveSystemConst.THETA_I,
            DriveSystemConst.THETA_D
        )
        MotorUtils.configureMotor(
            thetaMotorRR,
            DriveSystemConst.THETA_P,
            DriveSystemConst.THETA_I,
            DriveSystemConst.THETA_D
        )
    }


    enum class Motor {
        FRONTLEFT,
        FRONTRIGHT,
        REARLEFT,
        REARRIGHT
    }


    fun setPower(motor: Motor, power: Double) = setPower(motor, TalonFXControlMode.Velocity, power)
    fun setPower(motor: Motor, mode: TalonFXControlMode, power: Double) = when (motor) {
        Motor.FRONTLEFT -> powerMotorFL.set(mode, power)
        Motor.FRONTRIGHT -> powerMotorFR.set(mode, power)
        Motor.REARLEFT -> powerMotorRL.set(mode, power)
        Motor.REARRIGHT -> powerMotorRR.set(mode, power)
    }

    fun setTheta(motor: Motor, theta: Double) = setTheta(motor, TalonFXControlMode.Position,
        theta * DriveSystemConst.DEGREES2TICKS)
    fun setTheta(motor: Motor, mode: TalonFXControlMode, theta: Double) = when (motor) {
        Motor.FRONTLEFT -> thetaMotorFL.set(mode, theta)
        Motor.FRONTRIGHT -> thetaMotorFR.set(mode, theta)
        Motor.REARLEFT -> thetaMotorRL.set(mode, theta)
        Motor.REARRIGHT -> thetaMotorRR.set(mode, theta)
    }
}