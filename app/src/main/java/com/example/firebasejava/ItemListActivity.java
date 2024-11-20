package com.example.firebasejava;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Toast;

//import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoListResponse;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executors;




public class ItemListActivity extends AppCompatActivity {


    private static final String API_KEY = "AIzaSyDYs89G6hBgp4oBpgAwJ7Bqht8ifAS6dQ0"; // YouTube Data API калити
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
//    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
//    private static final JsonFactory JSON_FACTORY = new GsonFactory();

    private RecyclerView itemRecyclerView;
    private DatabaseHelper databaseHelper;
    private ItemAdapter itemAdapter;
    private List<ItemModel> modalArrayList;
    YouTubePlayer youTubePlayer; // Бу ерда YouTubePlayer объектини сақлаймиз
    YouTubePlayerView youTubePlayerView;
    String subcategoryId;
    private SeekBar customSeekBar;
    ImageView play, stop, fullscreen;
    RelativeLayout relativeLayout;
    ImageView add_plus, nextPlay, prevPlay;
    private int post = 0;
    private float videoDuration;  // Видео умумий давомийлиги
    private boolean isFullscreen = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        youTubePlayerView = findViewById(R.id.youtube_player_view);
        itemRecyclerView = findViewById(R.id.item_recycler_view);
        customSeekBar = findViewById(R.id.customSeekBar);
        play = findViewById(R.id.play);
        stop = findViewById(R.id.stop);
        relativeLayout = findViewById(R.id.relativeLayout);
        fullscreen = findViewById(R.id.fullscreen);
        nextPlay = findViewById(R.id.nextPlay);
        prevPlay = findViewById(R.id.prevPlay);
        getLifecycle().addObserver(youTubePlayerView);

        databaseHelper = new DatabaseHelper(this);

        // Intent орқали `subcategoryId` олиш
        int subcategoryId = getIntent().getIntExtra("subcategory", -1);
        modalArrayList = databaseHelper.getItemsBySubcategory(subcategoryId);






        itemAdapter = new ItemAdapter(modalArrayList);
        itemRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        itemRecyclerView.setAdapter(itemAdapter);

        ItemClickVideo();
        play();
        stop();
        Fullscreen();
    }


    private YouTube getYouTubeService() throws Exception {
        return new YouTube.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JSON_FACTORY,
                null
        ).setApplicationName("YourApplicationName").build();
    }

    private void fetchVideoData(String videoId) {
        try {
            YouTube youtubeService = getYouTubeService();
            YouTube.Videos.List request = youtubeService.videos()
                    .list("snippet,contentDetails,statistics");
            request.setId(videoId);
            request.setKey(API_KEY);

            new Thread(() -> {
                try {
                    VideoListResponse response = request.execute();
                    runOnUiThread(() -> {
                        if (response.getItems().size() > 0) {
                            String title = response.getItems().get(0).getSnippet().getTitle();
                            Log.d("YouTubeAPI", "Видео номи: " + title);
                        } else {
                            Log.d("YouTubeAPI", "Видео топилмади!");
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }






    private void ItemClickVideo() {
        itemAdapter.setOnItemClickListener(new ItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                ItemModel model = modalArrayList.get(position);
                String getVieoId = model.getId();
                String getVideoName = model.getUrl();

                Log.d("demo61", "getVieoId: " + getVieoId);
//                post = position; // Позицияни ўрнатиш



                // YouTube видеони юклаш
                if (youTubePlayer != null) {
                    // YouTubePlayer аллақачон инициализация қилинган, фақат видеони юклаймиз
                    youTubePlayer.loadVideo(getVieoId, 0f);
                } else {
                    // YouTubePlayer'ни инициализация қилиш
                    View customPlayerUi = youTubePlayerView.inflateCustomPlayerUi(R.layout.layout_panel);
                    YouTubePlayerListener listener = new AbstractYouTubePlayerListener() {
                        @Override
                        public void onReady(@NonNull YouTubePlayer initializedYouTubePlayer) {
                            youTubePlayer = initializedYouTubePlayer; // YouTubePlayer объектини сақлаб қўямиз

                            // YouTube Data API орқали видеонинг номини олиш
//                            try {

//                                Toast.makeText(MainActivity.this, "Видео номи: " + videoTitle, Toast.LENGTH_SHORT).show();
//                            } catch (IOException es) {
//                                es.printStackTrace();
//                            }


                            nextPlay.setOnClickListener(v -> {
                                if (post < modalArrayList.size() - 1) { // Навбатдаги видео мавжудлигини текшириш
                                    post++;
                                    ItemModel model = modalArrayList.get(post);
                                    String nextVideoId = model.getId();
                                    youTubePlayer.loadVideo(nextVideoId, 0f);
                                    Toast.makeText(ItemListActivity.this, "Кейинги видео: " + model.getName(), Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(ItemListActivity.this, "Бошқа видео мавжуд эмас", Toast.LENGTH_SHORT).show();
                                }
                            });

                            // Орқага ўтиш тугмаси логикаси
                            prevPlay.setOnClickListener(v -> {
                                if (post > 0) {
                                    post--;
                                    ItemModel prevModel = modalArrayList.get(post);
                                    youTubePlayer.loadVideo(prevModel.getId(), 0f);
                                    Toast.makeText(ItemListActivity.this, "Аввалги видео: " + prevModel.getName(), Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(ItemListActivity.this, "Аввалги видео мавжуд эмас", Toast.LENGTH_SHORT).show();
                                }
                            });


                            CustomPlayerUiController customPlayerUiController = new CustomPlayerUiController(
                                    ItemListActivity.this, customPlayerUi, youTubePlayer, youTubePlayerView
                            );
                            youTubePlayer.addListener(customPlayerUiController);

                            youTubePlayer.loadVideo(getVieoId, 0f);

                            String testVideoId = getVieoId;
                            fetchVideoData(testVideoId);


                            customSeekBar.setMax(100);
                            // Видео умумий давомийлигини оламиз
                            youTubePlayer.addListener(new AbstractYouTubePlayerListener() {
                                @Override
                                public void onVideoDuration(YouTubePlayer youTubePlayer, float duration) {
                                    videoDuration = duration;
                                }

                                @Override
                                public void onCurrentSecond(YouTubePlayer youTubePlayer, float second) {
                                    // Видео пайтига қараб SeekBar'ни янгилаймиз
                                    if (videoDuration > 0) {
                                        customSeekBar.setProgress((int) (second / videoDuration * 100));
                                    }
                                }
                            });

//                             SeekBar'га ўзгартириш киритилганда видеонинг вақтини ўзгартириш
                            customSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                                @Override
                                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                    if (fromUser) {
                                        // Фойдаланувчи қўлда видеонинг вақтига ўтишда SeekBar'ни янгилайди
                                        float seekTo = (progress / 100f) * videoDuration;
                                        youTubePlayer.seekTo(seekTo);
                                    }
                                }

                                @Override
                                public void onStartTrackingTouch(SeekBar seekBar) {
                                    // Ҳеч нарса қўшилмайди
                                }

                                @Override
                                public void onStopTrackingTouch(SeekBar seekBar) {
                                    // Ҳеч нарса қўшилмайди
                                }
                            });


                        }
                    };

                    IFramePlayerOptions options = new IFramePlayerOptions.Builder().controls(0).build();
                    youTubePlayerView.initialize(listener, options);
                }
                relativeLayout.setVisibility(View.VISIBLE);
                youTubePlayerView.setVisibility(View.VISIBLE);
//                Toast.makeText(getActivity(), "Видео2 " + getVieoId, Toast.LENGTH_SHORT).show();
            }
        });
    }

//    public void getVideoTitle(String videoId, VideoTitleCallback callback) {
//        Executors.newSingleThreadExecutor().execute(() -> {
//            try {
//                // YouTube API хизмати билан боғланиш
//                YouTube youtubeService = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, null)
//                        .setApplicationName("YouTube Player Example")
//                        .build();
//
//                YouTube.Videos.List request = youtubeService.videos()
//                        .list(Collections.singletonList("snippet").toString());
//                VideoListResponse response = request.setId(Collections.singletonList(videoId).toString())
//                        .setKey(API_KEY)
//                        .execute();
//
//                if (!response.getItems().isEmpty()) {
//                    String videoTitle = response.getItems().get(0).getSnippet().getTitle();
//
//                    // Асосий оқимда натижани қайтариш
//                    runOnUiThread(() -> callback.onTitleReceived(videoTitle));
//                } else {
//                    runOnUiThread(() -> callback.onTitleReceived("Видео топилмади!"));
//                }
//
//            } catch (IOException e) {
//                e.printStackTrace();
//                runOnUiThread(() -> callback.onTitleReceived("Хато юз берди!"));
//            }
//        });
//    }
//    public interface VideoTitleCallback {
//        void onTitleReceived(String title);
//    }


    private void play() {
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop.setVisibility(View.VISIBLE);
                play.setVisibility(View.GONE);
                youTubePlayer.play();
            }
        });
    }
    private void stop() {
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop.setVisibility(View.GONE);
                play.setVisibility(View.VISIBLE);
                youTubePlayer.pause(); // Пауза қўйилади
            }
        });
    }

    private void Fullscreen() {
        fullscreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isFullscreen = !isFullscreen;
                toggleFullscreen(isFullscreen);
                // add_plus ни кўриниш ҳаракати
//                if (isFullscreen){
//                    add_plus.setVisibility(View.GONE);
//                }else {
//                    add_plus.setVisibility(View.VISIBLE);
//                }

            }
        });
    }


    private void toggleFullscreen(boolean fullscreen) {
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) relativeLayout.getLayoutParams();

        if (fullscreen) {
            // Тўлиқ экран ҳолатида пастга жойлаштириш
            params.topToBottom = ConstraintLayout.LayoutParams.UNSET;
            params.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID;
        } else {
            // Нормал ҳолатига қайтиш
            params.bottomToBottom = ConstraintLayout.LayoutParams.UNSET;
            params.topToBottom = R.id.youtube_player_view;
        }

        relativeLayout.setLayoutParams(params);

        // YouTubePlayerView ни тўлиқ экран қилиш
        ViewGroup.LayoutParams youtubeParams = youTubePlayerView.getLayoutParams();
        youtubeParams.width = fullscreen ? ViewGroup.LayoutParams.MATCH_PARENT : ViewGroup.LayoutParams.MATCH_PARENT;
        youtubeParams.height = fullscreen ? ViewGroup.LayoutParams.MATCH_PARENT : ViewGroup.LayoutParams.WRAP_CONTENT;
        youTubePlayerView.setLayoutParams(youtubeParams);

        // Статус ва навигация панелларини йўқ қилиш
        ItemListActivity.this.getWindow().getDecorView().setSystemUiVisibility(
                fullscreen
                        ? View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        : View.SYSTEM_UI_FLAG_VISIBLE
        );
    }

}