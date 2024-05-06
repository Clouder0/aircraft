package edu.hitsz.application;

import java.util.ArrayList;

public class MusicManager {

    public static boolean enabled = true;
    public static void play(String filename) {
        if(!enabled) return;
        new MusicHelper(filename).threadExecute();
    }
    private static ArrayList<MusicHelper> helpers = new ArrayList<>();
    public static MusicHelper playLoop(String filename) {
        System.out.println(enabled);
        System.out.println(filename);
        if(!enabled) return null;
        var helper =  new MusicHelper(filename);
        helpers.add(helper);
        helper.threadExecute();
        return helper;
    }

    public static void stopall() {
        for(var s : helpers) s.stop();
    }
}
