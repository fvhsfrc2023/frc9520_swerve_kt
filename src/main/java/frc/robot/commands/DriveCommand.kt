package frc.robot.commands

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import edu.wpi.first.wpilibj2.command.CommandBase
import frc.robot.Constants.DriveSystemConst
import frc.robot.Controller
import frc.robot.subsystems.DriveSystem
import frc.robot.subsystems.DriveSystem.Motor
import kotlin.math.min

object DriveCommand: CommandBase() {
    init {
        addRequirements(DriveSystem)
    }

    override fun execute() {
        DriveSystem.drive(Controller.radius, Controller.theta * DriveSystemConst.RADIANS2TICKS, Controller.z)

//        if (Controller.buttonX)
//            DriveSystem.incTheta(Motor.FRONTLEFT, 1.0)

        SmartDashboard.putNumber("radius", Controller.radius)
        SmartDashboard.putNumber("theta from DriveCommand", Controller.theta * DriveSystemConst.RADIANS2TICKS)
    }
}