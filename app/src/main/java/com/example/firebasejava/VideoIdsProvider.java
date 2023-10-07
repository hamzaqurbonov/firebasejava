package com.example.firebasejava;



import android.os.Bundle;
import android.widget.TextView;

import java.io.ObjectInputStream;
import java.text.BreakIterator;
import java.util.Random;


public class VideoIdsProvider {
    TextView textView;



    private static final String[] myRef1 =  {"YJ_haVTuHUM"};

    private static final String[] liveVideoIds = {""};
    private static final Random random = new Random();

    public static String getNextVideoId() {
        return myRef1[random.nextInt(myRef1.length)];
    }

    public static String getNextLiveVideoId() {
        return liveVideoIds[random.nextInt(liveVideoIds.length)];
    }
}
