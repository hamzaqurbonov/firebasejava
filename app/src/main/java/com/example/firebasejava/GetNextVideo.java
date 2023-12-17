package com.example.firebasejava;

import static com.example.firebasejava.MainActivity.nextArrayList;
import java.util.ArrayList;
import java.util.Random;

public class GetNextVideo {
    public static ArrayList<String> previousArrayList = new ArrayList<>();
    private static final Random random = new Random();

    public static String getPreviousVideoId() {
        int size = previousArrayList.size();
        int randomNext = random.nextInt(size);
        String clon =  previousArrayList.get(randomNext);
//        Log.d("demo21", String.valueOf(previousArrayList));
//        Log.d("demo21", String.valueOf(previousArrayList.size()));
        return clon;
    }
    public static String getNextVideoId() {
        int size = MainActivity.nextArrayList.size();
        int randomNext = random.nextInt(size);
        String clon =  nextArrayList.get(randomNext);
        previousArrayList.add(clon);
//        Log.d("demo21", String.valueOf(size));
//        Log.d("demo21", String.valueOf(nextArrayList));
        return clon;
    }
}
