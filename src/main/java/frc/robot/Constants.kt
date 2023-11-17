package frc.robot

object Constants {
    const val CAN_TIMEOUT_MS = 10

    object DriveSystemConst {
        const val DEGREES2TICKS = 2048.0 / 360.0

        const val CURRENT_LIMIT_AMPS = 80.0
        const val CURRENT_LIMIT_DELAY = 0.25

        const val POWER_P = 0.1
        const val POWER_I = 0.001
        const val POWER_D = 5.0
//        const val POWER_F = 1023.0 / 20660.0
//        const val POWER_I_ZONE = 300.0

        const val THETA_P = 0.1
        const val THETA_I = 0.00001
        const val THETA_D = 1.0
    }
    object ControllerConst {
        const val DRIVE_JOYSTICK_PORT = 0

        const val X_CHANNEL = 0
        const val Y_CHANNEL = 1
        const val Z_CHANNEL = 4

        const val X_DEAD_ZONE = 0.2
        const val Y_DEAD_ZONE = 0.2
        const val Z_DEAD_ZONE = 0.2
    }
}