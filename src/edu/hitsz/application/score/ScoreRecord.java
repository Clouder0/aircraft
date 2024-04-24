package edu.hitsz.application.score;

import java.time.LocalDateTime;

public class ScoreRecord {
    public final String name;
    public final LocalDateTime time;
    public final int score;

    public ScoreRecord(String name, LocalDateTime time, int score) {
        this.name = name;
        this.time = time;
        this.score = score;
    }
}
