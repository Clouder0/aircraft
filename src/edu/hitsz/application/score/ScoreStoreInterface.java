package edu.hitsz.application.score;

import java.util.List;

public interface ScoreStoreInterface {
    List<ScoreRecord> getScores();

    void writeScore(ScoreRecord record);
}
