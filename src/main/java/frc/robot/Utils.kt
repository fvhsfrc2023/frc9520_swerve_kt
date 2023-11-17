package frc.robot

import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration
import com.ctre.phoenix.motorcontrol.can.TalonFX

object Utils {
    object MotorUtils {
        fun configureMotor(motor: TalonFX, P: Double, I: Double, D: Double, /*F: Double, Izone: Double*/) {
            motor.config_kP(0, P, Constants.CAN_TIMEOUT_MS)
            motor.config_kI(0, I, Constants.CAN_TIMEOUT_MS)
            motor.config_kD(0, D, Constants.CAN_TIMEOUT_MS)
//            motor.config_kF(0, F, Constants.CAN_TIMEOUT_MS)
//            motor.config_IntegralZone(0, I, Constants.CAN_TIMEOUT_MS)
//            motor.configStatorCurrentLimit(
//                StatorCurrentLimitConfiguration(
//                    true,
//                    Constants.DriveSystemConst.CURRENT_LIMIT_AMPS,
//                    Constants.DriveSystemConst.CURRENT_LIMIT_AMPS,
//                    Constants.DriveSystemConst.CURRENT_LIMIT_DELAY
//                )
//            )
//            motor.configSupplyCurrentLimit(
//                SupplyCurrentLimitConfiguration(
//                    true,
//                    Constants.DriveSystemConst.CURRENT_LIMIT_AMPS,
//                    Constants.DriveSystemConst.CURRENT_LIMIT_AMPS,
//                    Constants.DriveSystemConst.CURRENT_LIMIT_DELAY
//                )
//            )
        }
    }
}