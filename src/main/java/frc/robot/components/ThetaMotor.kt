package frc.robot.components

import com.ctre.phoenix.motorcontrol.TalonFXControlMode
import com.ctre.phoenix.motorcontrol.can.TalonFX
import kotlin.math.abs
import kotlin.math.sign

class ThetaMotor(private val motor: TalonFX, private val myPeriodTicks: Double, private val rawPeriodTicks: Double) {

    var theta: Double = 0.0
        private set(value) {
            field = value % myPeriodTicks
        }

    /*
     * should not be modified internally
     */
    var target: Double = 0.0
        set(value) {
            field = value % myPeriodTicks
        }

    /*
     * from target to theta
     */
    private val offset: Double
        get() = run {
            val o1: Double
            val o2: Double

            if (target >= theta) {
                o1 = target - theta
                o2 = target - (theta + myPeriodTicks)
            }
            else {
                o1 = target - theta
                o2 = target - (theta - myPeriodTicks)
            }

            if (abs(o1) <= abs(o2))
                o1
            else
                o2
        }


    fun turn(ticks: Double) {
        motor.set(TalonFXControlMode.Position,
            (motor.getSelectedSensorPosition(0) + ticks + rawPeriodTicks) % rawPeriodTicks)
    }

    fun turnHalf(positive: Boolean) {
        if (positive) {
            turn(rawPeriodTicks / 2 - 1)
        }
        else {
            turn(-rawPeriodTicks / 2 + 1)
        }
    }


    fun update() {
        val offset = offset
        if (abs(offset) < rawPeriodTicks / 2) {
            turn(offset)
            theta += offset
        }
        else {
            turnHalf(sign(offset) == 1.0)
        }
    }
}