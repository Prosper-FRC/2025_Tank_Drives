# EPIC Workshop Tank Drive
### By: Carter Mott

---

This tank drive is a little more complex than the other tank drives, but it serves more as example code
than to be something the rookies can fully code. The biggest change is that this code runs on the
2025 version of REVlib meaning that the functions have changed slightly.


Honestly the code looks a lot like CTRE's TalonFX motor
controllers which makes it really easy to work with as
someone with more experience with TalonFX motors.


Another thing to note is that to get differeential drive
to work with PID you'll need to supply double consumers
to the differential drive instead of the usual motor
controller class, as WPIlib doesn't know about the Spark
Max's abilities with closed-loop PID.

## Main Features

- Advantage Kit implementation.
- PID implementation. (No feedforward rn because I want to integrate it with AK SysID)
- 2025 REVlib.
- Tank drive subsystem with IO layer architecture.
- Fully integrated simulation an stuff.

## How to sim
1. Get an XBox controller (Recommended)
2. Open up AdvantageScope, it should be installed with WPIlib. Then go to File -> Connect to Simulator.
3. In the WPIlib project click on the "Open WPIlib Command Pallete" button in the top right of the screen and search "Simulate Robot Code" then click on it.
![WPIlib Logo](https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ2KZHul-Vyy-Zkde33c7aLOfkRT_AhikVmnA&s)
4. Check the box "sim GUI" then click the blue OK button.
5. Now a tab called Robot Simulation should be open.
6. To the left on a tab called "System Joysticks", drag and drop the Xbox Controller to the Joysticks[0] tab over where it says Unassigned.
7. Now in Advantage Scop Set the tap to Odometry (It should be a tap on the top of the screen)
8. In Advantage Scope, navigate through the network tables tab to the left
going to RealOutputs -> Drive.
9. Drag and drop OdometryPose to the Poses tab (Located at the bottom of
the odometry field view).
10. Now you should be able to move and test out the robot with the left X and right Y Joysticks!

## Extra notes to consider

A lot of the constant values in my code e.g the kWheelRadius and kTrackWidthMeters are not properly set because I
do not know the constants of the top of my head. Therefore the motors are not properly tuned to realistic values
meaning sim is not a truly accurate simulation of the robot's motors at the moment.

The rotation estimation is also not based off of a gyro, it instead takes advantage of the pre-derived forward
kinematics equations for a differential drive, based off of the Encoders of the motors.

Sim Also still does not take into consideration that there are two motors driving each side of the drive base,
and that the robot has mass, so for a truly accurate simulation I would need access to those constants.

And remember... Coding is epic!!! :sunglasses:
