package frc.robot.subsystems

import com.ctre.phoenix.motorcontrol.FeedbackDevice
import com.ctre.phoenix.motorcontrol.NeutralMode
import com.ctre.phoenix.motorcontrol.TalonFXControlMode
import com.ctre.phoenix.motorcontrol.can.TalonFX
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import edu.wpi.first.wpilibj2.command.SubsystemBase
import frc.robot.Constants.DriveSystemConst
import frc.robot.Utils.MotorUtils
import frc.robot.components.ThetaMotor
import kotlin.math.*

object DriveSystem: SubsystemBase() {
    private val powerMotorFL = TalonFX(2)
    private val powerMotorFR = TalonFX(7)
    private val powerMotorRL = TalonFX(3)
    private val powerMotorRR = TalonFX(5)

//    private val thetaMotorFL = TalonFX(1)
//    private val thetaMotorFR = TalonFX(6)
//    private val thetaMotorRL = TalonFX(4)
//    private val thetaMotorRR = TalonFX(8)

    private val thetaMotorFL = TalonFX(1)
    private val thetaMotorFR = TalonFX(6)
    private val thetaMotorRL = TalonFX(4)
    private val thetaMotorRR = TalonFX(8)

    private val thetaMotors: Array<ThetaMotor> = kotlin.run {
        var thetaMotors: Array<ThetaMotor> = arrayOf()
        for (motor in arrayOf(thetaMotorFL, thetaMotorFR, thetaMotorRL, thetaMotorRR)) {
            thetaMotors += ThetaMotor(
                motor,
                DriveSystemConst.THETAMOTOR_PERIOD_TICKS,
                DriveSystemConst.TALONFX_PERIOD_TICKS
            )
        }
        thetaMotors
    }


    init {
        for (motor in arrayOf(powerMotorFL, powerMotorFR, powerMotorRL, powerMotorRR)) {
            motor.configFactoryDefault()
            MotorUtils.configureMotor(motor,
                DriveSystemConst.POWER_P,
                DriveSystemConst.POWER_I,
                DriveSystemConst.POWER_D)
            motor.setNeutralMode(NeutralMode.Brake)
        }

        for (motor in arrayOf(thetaMotorFL, thetaMotorFR, thetaMotorRL, thetaMotorRR)) {
            motor.configFactoryDefault()
            motor.setSensorPhase(true)
            motor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor)
//            MotorUtils.configureMotor(motor,
//                DriveSystemConst.THETA_P,
//                DriveSystemConst.THETA_I,
//                DriveSystemConst.THETA_D)
            motor.config_kP(0, DriveSystemConst.THETA_P)
            motor.config_kI(0, DriveSystemConst.THETA_I)
            motor.config_kD(0, DriveSystemConst.THETA_D)
            motor.setNeutralMode(NeutralMode.Brake)
        }
    }


    enum class Motor {
        FRONTLEFT,
        FRONTRIGHT,
        REARLEFT,
        REARRIGHT
    }


    fun setPower(motor: Motor, power: Double, mode: TalonFXControlMode = TalonFXControlMode.PercentOutput) = when (motor) {
        Motor.FRONTLEFT -> powerMotorFL.set(mode, power)
        Motor.FRONTRIGHT -> powerMotorFR.set(mode, power)
        Motor.REARLEFT -> powerMotorRL.set(mode, power)
        Motor.REARRIGHT -> powerMotorRR.set(mode, power)
    }

//    fun setTheta(motor: Motor, theta: Double, mode: TalonFXControlMode = TalonFXControlMode.Position) = when (motor) {
//        Motor.FRONTLEFT -> thetaMotorFL.set(mode, theta)
//        Motor.FRONTRIGHT -> thetaMotorFR.set(mode, theta)
//        Motor.REARLEFT -> thetaMotorRL.set(mode, theta)
//        Motor.REARRIGHT -> thetaMotorRR.set(mode, theta)
//    }


//    var lastTheta = 0.0
    fun drive(power: Double, direction: Double, offset: Double) {
//        for (motor in Motor.values()) {
//            setTheta(motor, 100.0, TalonFXControlMode.Velocity)
//            val dir = direction % 2024 - 1024
////            incTheta(motor, dir - lastTheta)
//            SmartDashboard.putNumber("drive direction:N Double", dir)
//        }
//        lastTheta = direction - lastTheta

        for (motor in thetaMotors) {
            motor.target = direction / 2 / PI * DriveSystemConst.THETAMOTOR_PERIOD_TICKS
        }

        val leftPower = power * min(2 * offset + 1, 1.0)
        val rightPower = power * min(-2 * offset + 1, 1.0)

        SmartDashboard.putNumber("leftPower", leftPower)
        SmartDashboard.putNumber("rightPower", rightPower)

        val powers = arrayOf(leftPower, rightPower, leftPower, rightPower)

        for ((i, motor) in Motor.values().withIndex()) {
            setPower(motor, powers[i])
        }
    }
//
//
//    /*
//     *  0   fl
//     *  1   fr
//     *  2   rl
//     *  3   rr
//     *
//     *  Unit Radius
//     */
//    private var thetaOffsets = arrayOf(0.0, 0.0, 0.0, 0.0)

//    fun incTheta(motor: Motor, inc: Double) {
//        val select = when (motor) {
//            Motor.FRONTLEFT -> 0
//            Motor.FRONTRIGHT -> 1
//            Motor.REARLEFT -> 2
//            Motor.REARRIGHT -> 3
//        }
//
//        thetaOffsets[select] += inc * DriveSystemConst.THETA_GEAR_RATIO
//    }

    fun getMotor(motor: Motor): TalonFX = when(motor) {
        Motor.FRONTLEFT -> thetaMotorFL
        Motor.FRONTRIGHT -> thetaMotorFR
        Motor.REARLEFT -> thetaMotorRL
        Motor.REARRIGHT -> thetaMotorRR
    }

//    private fun updateTheta() {
//        for ((i, motor) in Motor.values().withIndex()) {
//            val cs = min(abs(thetaOffsets[i]), PI / 4) * sign(thetaOffsets[i])
//            thetaOffsets[i] -= cs
//            setTheta(motor, getMotor(motor).getSelectedSensorPosition(0) + cs)
//            SmartDashboard.putNumber("SP", getMotor(motor).getSelectedSensorPosition())
//        }
//    }

    private fun updateTheta() {
        for (motor in thetaMotors) {
            motor.update()
        }
    }

    override fun periodic() {
        updateTheta()

//        for ((i, each) in thetaOffsets.withIndex()) {
//            SmartDashboard.putNumber("thetaOffset $i", each)
//        }

        for ((i, each) in thetaMotors.withIndex()) {
            SmartDashboard.putNumber("thetaMotor $i theta", each.theta)
            SmartDashboard.putNumber("thetaMotor $i target", each.target)
        }
    }
}