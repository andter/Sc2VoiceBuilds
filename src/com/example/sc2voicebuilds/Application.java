package com.example.sc2voicebuilds;

        import com.parse.Parse;
        import com.parse.ParseUser;

public class Application extends android.app.Application {

    public void onCreate() {
        Parse.initialize(this, "v9D4hN8qNtXWTE4z4aNOHZsXkhBlVW29Iucw1Ll9", "bw5EcOR0neExSVWiMzrd8xdj1sqeSAmTSWlnZTdC");
    }

}