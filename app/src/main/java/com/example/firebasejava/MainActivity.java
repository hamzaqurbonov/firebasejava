package com.example.firebasejava;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

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

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final String[] VideoIds3 = {"Sy5WFjCQFwo"};
    public static String getNextVideoId() {
        return VideoIds3[0];
    }

    TextView VideoIds;

    private YouTubePlayerView youTubePlayerView;
//    private static final String[] myRef1 = {""};

    
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        youTubePlayerView = findViewById(R.id.youtube_player_view);

        initYouTubePlayerView();
//
        VideoIds = findViewById(R.id.myRef1);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
       DatabaseReference childrefrence = myRef.child("url");

//        myRef.setValue("YJ_haVTuHUM");
//        myRef.get();




        childrefrence.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                String m = datasnapshot.getValue(String.class);

                VideoIds.setText(m);

//                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
//                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });


    }




//    private VideoIdsProvider2  {
//        private static final String[] VideoIds2 = {"iz8DQnO1ab4"};

//        private static final String[] liveVideoIds = {""};
//        private static final Random random = new Random();

//        public static String getNextVideoId() {
//            return VideoIds[0];
//        }
//
//        public static String getNextLiveVideoId() {
//            return liveVideoIds[random.nextInt(liveVideoIds.length)];
//        }
//    }


    private void initYouTubePlayerView() {
        getLifecycle().addObserver(youTubePlayerView);


        YouTubePlayerListener listener = new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {

                // using pre-made custom ui
//                    DefaultPlayerUiController defaultPlayerUiController = new DefaultPlayerUiController(youTubePlayerView, youTubePlayer);
//                    youTubePlayerView.setCustomPlayerUi(defaultPlayerUiController.getRootView());

                setPlayNextVideoButtonClickListener(youTubePlayer);

                YouTubePlayerUtils.loadOrCueVideo(
                        youTubePlayer,
                        getLifecycle(),
                        getNextVideoId(),
                        0f
                );
            }
        };

        // disable web ui
        IFramePlayerOptions options = new IFramePlayerOptions.Builder().controls(0).build();

        youTubePlayerView.initialize(listener, options);
    }
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            youTubePlayerView.matchParent();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            youTubePlayerView.wrapContent();
        }
    }

    /**
     * Set a click listener on the "Play next video" button
     */
    private void setPlayNextVideoButtonClickListener(final YouTubePlayer youTubePlayer) {
        Button playNextVideoButton = findViewById(R.id.next_video_button);

        playNextVideoButton.setOnClickListener(view ->
                YouTubePlayerUtils.loadOrCueVideo(
                        youTubePlayer,
                        getLifecycle(),
                        "HXrETVPKWh0",

                        0f
                )
        );
    }
}