package com.example.firebasejava;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;

public class InitYouTubePlayer  {
//
//    GetNextVideo getNextVideo = new GetNextVideo();
//    YouTubePlayerView youTubePlayerView;
//
//
//    @SuppressLint("MissingInflatedId")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        youTubePlayerView = findViewById(R.id.youtube_player_view);
//
//        initYouTubePlayerView();
//
////        final ArrayAdapter<String> myArrayAdaptrer = new ArrayAdapter<String>(InitYouTubePlayer.this, android.R.layout.simple_list_item_1, MainActivity.nextArrayList);
//    }
//
//    public void initYouTubePlayerView() {
//
//        getLifecycle().addObserver(youTubePlayerView);
//        View customPlayerUi = youTubePlayerView.inflateCustomPlayerUi(R.layout.custom_player_ui);
//        YouTubePlayerListener listener = new AbstractYouTubePlayerListener() {
//            @Override
//            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
//                CustomPlayerUiController customPlayerUiController = new CustomPlayerUiController(InitYouTubePlayer.this, customPlayerUi, youTubePlayer, youTubePlayerView);
//                youTubePlayer.addListener(customPlayerUiController);
//                setPlayNextVideoButtonClickListener(youTubePlayer);
//                YouTubePlayerUtils.loadOrCueVideo(
//                        youTubePlayer, getLifecycle(),
//                        "pL3vbhg8Qdo", 0f
//                );
//
////                Log.d("demo22", String.valueOf(nextArrayList));
////                Log.d("demo22", String.valueOf(nextArrayList.size()));
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
//                        "pL3vbhg8Qdo",
//                        0f
//                )
//
//        );
//        playNextVideoButton.setOnClickListener(view ->
//
//                YouTubePlayerUtils.loadOrCueVideo(
//                        youTubePlayer,
//                        getLifecycle(),
//                        "pL3vbhg8Qdo",
//                        0f
//                )
//
//        );
//
//    }
}
