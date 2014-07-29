
This is an Android app derived from the [Torque Plugin Sample](http://torque-bhp.com/), combined with a Bluetooth server that can send OBD data to a client (e.g. Google Glass).

Currently, the only parameters sent are (RPM, SPEED, THROTTLE, and GEAR).  Gear is computed based on speed/rpm ratio and is specific to a BMW F800ST.   A companion project [Glass OBD HUD](https://github.com/mpicco/glass-obd-hud) is a Google Glass app that displays it on Glass.

Note that bluetooth connection lifecycle is not yet handled well.  The app needs to be restarted before each new connection is made from Glass.  After Glass disconnects the server continues to try to send on a closed socket.



