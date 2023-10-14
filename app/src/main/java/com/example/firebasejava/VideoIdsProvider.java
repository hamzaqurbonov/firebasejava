package com.example.firebasejava;



import android.os.Bundle;
import android.widget.TextView;

import androidx.core.app.ComponentActivity;

import java.io.ObjectInputStream;
import java.text.BreakIterator;
import java.util.Random;


public class VideoIdsProvider  {



    private static final String[] VideoIds = {"pcbGX4JcILA"};

    private static final String[] liveVideoIds = {""};
    private static final Random random = new Random();

    public static String getNextVideoId() {
        return VideoIds[0];
    }

    public static String getNextLiveVideoId() {
        return liveVideoIds[random.nextInt(liveVideoIds.length)];
    }
}
