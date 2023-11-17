package frc.robot

import edu.wpi.first.wpilibj.Joystick

import frc.robot.Constants.ControllerConst
import kotlin.math.*

object Controller {
    private val m_Joystick = Joystick(ControllerConst.DRIVE_JOYSTICK_PORT)

    init {
        m_Joystick.xChannel = ControllerConst.X_CHANNEL
        m_Joystick.yChannel = ControllerConst.Y_CHANNEL
        m_Joystick.zChannel = ControllerConst.Z_CHANNEL
    }


    // 0 <= startPoint <= currentPoint <= 1
    private fun smooth(startPoint: Double, currentPoint: Double): Double {
        val slope = 1 / (1 - startPoint)

        return currentPoint * slope + -startPoint * slope
    }

    val v
        get() =
            if (abs(m_Joystick.y) > ControllerConst.Y_DEAD_ZONE)
                smooth(ControllerConst.Y_DEAD_ZONE, abs(m_Joystick.y)) * sign(m_Joystick.y)
            else 0.0

    val h
        get() =
            if (abs(m_Joystick.x) > ControllerConst.X_DEAD_ZONE)
                smooth(ControllerConst.X_DEAD_ZONE, abs(m_Joystick.x)) * sign(m_Joystick.x)
            else 0.0

    val z
        get() =
            if (abs(m_Joystick.z) > ControllerConst.Z_DEAD_ZONE)
                smooth(ControllerConst.Z_DEAD_ZONE, abs(m_Joystick.z)) * sign(m_Joystick.z)
            else 0.0

    // range: [0, 360)
    val theta
        get() =
            (when {
                h > 0 -> -atan(v / h) + PI / 2
                h < 0 -> atan(v / h) + PI / 2
                else -> if (v > 0.0) { 0.0 } else { PI }
            }) / PI * 180 + 180

    val radius
        get() = sqrt(v*v + h*h)
}