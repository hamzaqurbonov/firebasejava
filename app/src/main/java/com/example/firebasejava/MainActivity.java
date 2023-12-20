package com.example.firebasejava;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.YouTubePlayerUtils;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
//    InitYouTubePlayer InitPlayer = new InitYouTubePlayer();
    GetNextVideo getNextVideo = new GetNextVideo();
    Button button;
    public  static ArrayList<String> nextArrayList = new ArrayList<>();
    DatabaseReference myRef;
   YouTubePlayerView youTubePlayerView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aktivity);

//        youTubePlayerView = findViewById(R.id.youtube_player_view);
        button = findViewById(R.id.button);
//
//        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view);
//        getLifecycle().addObserver(youTubePlayerView);



//
        button.setOnClickListener(v -> {
            // we have declared an intent to open new activity.
            Intent i = new Intent(MainActivity.this, InitYouTubePlayer.class);
            startActivity(i);
        });



    final ArrayAdapter<String> myArrayAdaptrer = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,nextArrayList);


        myRef = FirebaseDatabase.getInstance().getReference();
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                String value =  snapshot.getValue(String.class);
                nextArrayList.add(value);
                myArrayAdaptrer.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                myArrayAdaptrer.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

//
//    public void initYouTubePlayerView() {
//
//        getLifecycle().addObserver(youTubePlayerView);
//        View customPlayerUi = youTubePlayerView.inflateCustomPlayerUi(R.layout.custom_player_ui);
//        YouTubePlayerListener listener = new AbstractYouTubePlayerListener() {
//            @Override
//            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
//                CustomPlayerUiController customPlayerUiController = new CustomPlayerUiController(MainActivity.this, customPlayerUi, youTubePlayer, youTubePlayerView);
//                youTubePlayer.addListener(customPlayerUiController);
//                setPlayNextVideoButtonClickListener(youTubePlayer);
//                YouTubePlayerUtils.loadOrCueVideo(
//                        youTubePlayer, getLifecycle(),
//                        getNextVideo.getNextVideoId(), 0f
//                );
//
//                Log.d("demo22", String.valueOf(nextArrayList));
//                Log.d("demo22", String.valueOf(nextArrayList.size()));
//            }
//        };
//        // disable web ui
//        IFramePlayerOptions options = new IFramePlayerOptions.Builder().controls(0).build();
//        youTubePlayerView.initialize(listener, options);
//    }
//
//
//    @Override
//    public void onConfigurationChanged(@NonNull Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        // Checks the orientation of the screen
//        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            youTubePlayerView.matchParent();
//        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
//            youTubePlayerView.wrapContent();
//        }
//    }
//
//    private void setPlayNextVideoButtonClickListener(final YouTubePlayer youTubePlayer) {
//        Button playPreviousVideoButton = findViewById(R.id.previous_video_button);
//        Button playNextVideoButton = findViewById(R.id.next_video_button);
//
//        playPreviousVideoButton.setOnClickListener(view ->
//
//                YouTubePlayerUtils.loadOrCueVideo(
//                        youTubePlayer,
//                        getLifecycle(),
//                        getNextVideo.getPreviousVideoId(),
//                        0f
//                )
//
//        );
//        playNextVideoButton.setOnClickListener(view ->
//
//                YouTubePlayerUtils.loadOrCueVideo(
//                        youTubePlayer,
//                        getLifecycle(),
//                        getNextVideo.getNextVideoId(),
//                        0f
//                )
//
//        );

//    }




}