package com.example.firebasejava;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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




    ListView MylistView1;
    static ArrayList<String> nextArrayList = new ArrayList<>();
    static ArrayList<String> previousArrayList = new ArrayList<>();
    DatabaseReference myRef;


   YouTubePlayerView youTubePlayerView;



    private static final Random random = new Random();


    public static String getPreviousVideoId() {
        int size = previousArrayList.size();
        int randomNext = random.nextInt(size);
        String clon =  previousArrayList.get(randomNext);

        Log.d("demo21", String.valueOf(previousArrayList));
        Log.d("demo21", String.valueOf(previousArrayList.size()));
        return clon;
    }

    public static String getNextVideoId() {
        int size = nextArrayList.size();
        int randomNext = random.nextInt(size);
        String clon =  nextArrayList.get(randomNext);
        previousArrayList.add(clon);
        Log.d("demo21", String.valueOf(nextArrayList));
        Log.d("demo21", String.valueOf(nextArrayList.size()));

        return clon;
    }



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        youTubePlayerView = findViewById(R.id.youtube_player_view);

//        getLifecycle().addObserver(youTubePlayerView);
//        View customPlayerUi = youTubePlayerView.inflateCustomPlayerUi(R.layout.custom_player_ui);
        initYouTubePlayerView();

//        YouTubePlayerListener listener = new AbstractYouTubePlayerListener() {
//            @Override
//            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
//                CustomPlayerUiController customPlayerUiController = new CustomPlayerUiController(MainActivity.this, customPlayerUi, youTubePlayer, youTubePlayerView);
//                youTubePlayer.addListener(customPlayerUiController);
//
//                YouTubePlayerUtils.loadOrCueVideo(
//                        youTubePlayer, getLifecycle(),
//                        "pL3vbhg8Qdo", 0f
//                );
//            }
//        };
//        IFramePlayerOptions options = new IFramePlayerOptions.Builder().controls(0).build();
//
//        youTubePlayerView.initialize(listener, options);



        final ArrayAdapter<String> myArrayAdaptrer = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,nextArrayList);
//        MylistView1 = (ListView) findViewById(R.id.listView1);
//        MylistView1.setAdapter(myArrayAdaptrer);
        myRef = FirebaseDatabase.getInstance().getReference();

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                String value =  snapshot.getValue(String.class);
                nextArrayList.add(value);
                myArrayAdaptrer.notifyDataSetChanged();

//                Log.d("demo20", String.valueOf(myArrayList));

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


    public void initYouTubePlayerView() {

        getLifecycle().addObserver(youTubePlayerView);
        View customPlayerUi = youTubePlayerView.inflateCustomPlayerUi(R.layout.custom_player_ui);
        YouTubePlayerListener listener = new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                CustomPlayerUiController customPlayerUiController = new CustomPlayerUiController(MainActivity.this, customPlayerUi, youTubePlayer, youTubePlayerView);
                youTubePlayer.addListener(customPlayerUiController);

                YouTubePlayerUtils.loadOrCueVideo(
                        youTubePlayer, getLifecycle(),
                        getNextVideoId(), 0f
                );
            }
        };
        // disable web ui
        IFramePlayerOptions options = new IFramePlayerOptions.Builder().controls(0).build();
        youTubePlayerView.initialize(listener, options);
    }
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
//                        getPreviousVideoId(),
//                        0f
//                )
//
//        );
//        playNextVideoButton.setOnClickListener(view ->
//
//                YouTubePlayerUtils.loadOrCueVideo(
//                        youTubePlayer,
//                        getLifecycle(),
//                        getNextVideoId(),
//                        0f
//                )
//
//        );
//
//    }




}