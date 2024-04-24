package edu.hitsz.application.score;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ScoreManager {
    protected List<ScoreStoreInterface> stores;

    public ScoreManager() {
        this.stores = new ArrayList<>();
    }

    public void addStore(ScoreStoreInterface s) {
        this.stores.add(s);
    }

    public List<ScoreRecord> getScores() {
        List<ScoreRecord> scores = stores.stream().map(ScoreStoreInterface::getScores).reduce(new ArrayList<>(), (now_list, records) -> {
            records.stream().filter((x) -> now_list.stream().noneMatch((y) -> y.time == x.time)).forEach(now_list::add);
            return now_list;
        });
        return scores.stream().sorted(Comparator.comparingInt((x) -> -x.score)).toList();
    }

    public void writeScore(ScoreRecord record) {
        for (var s : this.stores) s.writeScore(record);
    }
}
