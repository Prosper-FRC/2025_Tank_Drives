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

And remember... Coding is epic. :sunglasses:
