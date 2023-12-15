package frc.robot

import edu.wpi.first.wpilibj.Joystick
import edu.wpi.first.wpilibj.TimedRobot
import com.ctre.phoenix.motorcontrol.can.TalonFX
import com.ctre.phoenix.motorcontrol.ControlMode
import com.ctre.phoenix.motorcontrol.TalonFXControlMode
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import edu.wpi.first.wpilibj2.command.CommandScheduler
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup
import frc.robot.commands.DriveCommand
import frc.robot.subsystems.DriveSystem

/**
 * The VM is configured to automatically run this object (which basically function as a singleton class),
 * and to call the [run] function when the robot is enabled. This is written as an object rather than a
 * class since there should only ever be a single instance, and it cannot take any constructor arguments.
 * This makes it a natural fit to be an object in Kotlin.
 *
 * If you change the name of this object or its package after creating this project, you must also update
 * the `Main.kt` file in the project. (If you use the IDE's Rename or Move refactorings when renaming the
 * object or package, it will get changed everywhere.)
 *
 * **This robot extends EducationalRobot. Do NOT use for competitions. This is a simple robot used for
 * teaching purposes.**
 */
object Robot : TimedRobot() {
    private val teleopCommands = ParallelCommandGroup(DriveCommand)

    override fun teleopInit() =
        CommandScheduler.getInstance().schedule(teleopCommands)

    override fun teleopExit() =
        CommandScheduler.getInstance().cancel(teleopCommands)


    override fun robotPeriodic() {
        SmartDashboard.putNumber("theta", Controller.theta)
        SmartDashboard.putNumber("v", Controller.v)
        SmartDashboard.putNumber("h", Controller.h)
        SmartDashboard.putNumber("z", Controller.z)

        CommandScheduler.getInstance().run()
    }
}