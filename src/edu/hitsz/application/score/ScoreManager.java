package edu.hitsz.application.score;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ScoreManager {
    protected static List<ScoreStoreInterface> stores = new ArrayList<>();


    public static void addStore(ScoreStoreInterface s) {
        stores.add(s);
    }

    public static List<ScoreRecord> getScores() {
        List<ScoreRecord> scores = stores.stream().map(ScoreStoreInterface::getScores).reduce(new ArrayList<>(), (now_list, records) -> {
            records.stream().filter((x) -> now_list.stream().noneMatch((y) -> y.time == x.time)).forEach(now_list::add);
            return now_list;
        });
        return scores.stream().sorted(Comparator.comparingInt((x) -> -x.score)).toList();
    }

    public static void writeScore(ScoreRecord record) {
        for (var s : stores) s.writeScore(record);
    }

    public static void clear() {
        for(var s : stores) s.clear();
    }
}
