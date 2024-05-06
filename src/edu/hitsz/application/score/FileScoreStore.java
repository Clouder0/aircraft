package edu.hitsz.application.score;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FileScoreStore implements ScoreStoreInterface {
    private final Path filepath;

    public FileScoreStore(Path filepath) {
        this.filepath = filepath;
    }

    public List<ScoreRecord> getScores() {
        ArrayList<ScoreRecord> res = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(this.filepath);
            for (String line : lines) {
                var fields = line.split(" ");
                ScoreRecord scoreRecord = new ScoreRecord(fields[0], LocalDateTime.parse(fields[1]), Integer.parseInt(fields[2]));
                res.add(scoreRecord);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        res.sort(Comparator.comparingInt((ScoreRecord a) -> -a.score));
        return res;
    }

    public void writeScore(ScoreRecord record) {
        String s = record.name + " " + record.time.toString() + " " + record.score + "\n";
        try {
            Files.write(this.filepath, s.getBytes(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void clear() {
        try {
            Files.write(this.filepath, "".getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
