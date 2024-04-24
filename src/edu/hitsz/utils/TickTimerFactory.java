package edu.hitsz.utils;

public class TickTimerFactory {
    public static TickTimer setTimeout(int interval, Runnable callback) {
        return new TickTimer(interval, 0, callback, null, null, null, null);
    }

    public static TickTimer setLasting(int duration, Runnable enter, Runnable leave) {
        return setLasting(duration, enter, leave, enter, leave);
    }

    public static TickTimer setLasting(int duration, Runnable pre_start, Runnable after_finished, Runnable pre_resume, Runnable after_pause) {
        return new TickTimer(duration, 0, null, pre_start, after_finished, pre_resume, after_pause);
    }

    public static TickTimer setAlways(Runnable callback) {
        return new TickTimer(1, -1, callback, callback, null, callback, null);
    }
}
