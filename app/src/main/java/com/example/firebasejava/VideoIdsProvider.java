package com.example.firebasejava;



import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ComponentActivity;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.YouTubePlayerUtils;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.io.ObjectInputStream;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Random;


public class VideoIdsProvider {

    ArrayList<MainActivity> ArrayList = new ArrayList<MainActivity>();

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
