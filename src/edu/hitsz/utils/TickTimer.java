package edu.hitsz.utils;

public class TickTimer {
    private final int interval;
    private final int repetition;
    private int passed_repetition;
    private final Runnable callback, pre_start, after_finished, pre_resume, after_pause;
    private boolean running;
    private int now_tick;

    // -1 for infinite loop
    public TickTimer(int interval, int repetition, Runnable callback, Runnable pre_start, Runnable after_finished, Runnable pre_resume, Runnable after_pause) {
        this.interval = interval;
        this.repetition = repetition;
        this.callback = callback;
        this.running = false;
        this.pre_start = pre_start;
        this.after_finished = after_finished;
        this.pre_resume = pre_resume;
        this.after_pause = after_pause;
    }

    // return true if runnning, false if run finished
    public boolean next() {
        if (!this.running) return false; // not started
        this.now_tick++;
        if (this.now_tick >= this.interval) {
            if (this.repetition == -1 || this.passed_repetition < this.repetition) {
                // trigger
                if (this.callback != null) this.callback.run();
                this.now_tick = 0;
                this.passed_repetition++;
            }
            if (this.passed_repetition >= this.repetition) {
                this.stop();
                return false;
            }
        }
        return true;
    }

    public void start() {
        this.now_tick = 0;
        this.passed_repetition = 0;
        if (this.pre_start != null) this.pre_start.run();
        this.running = true;
    }

    public void stop() {
        this.running = false;
        this.now_tick = 0;
        this.passed_repetition = 0;
        if (this.after_finished != null) this.after_finished.run();
    }

    public void pause() {
        if (this.after_pause != null) this.after_pause.run();
        this.running = false;
    }

    public void resume() {
        if (this.pre_resume != null) this.pre_resume.run();
        this.running = true;
    }

    public void delay(int ticks) {
        this.now_tick -= ticks;
    }
}
