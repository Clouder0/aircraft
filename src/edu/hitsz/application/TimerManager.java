package edu.hitsz.application;

import edu.hitsz.utils.TickTimer;

import java.util.ArrayList;

public class TimerManager {
    private static final ArrayList<TickTimer> timers = new ArrayList<>();

    public static void addTimer(TickTimer timer) {
        timers.add(timer);
    }

    public static void nextTick() {
        for (TickTimer t : timers) {
            t.next();
        }
    }

}
