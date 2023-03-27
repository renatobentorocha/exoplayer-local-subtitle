package com.example.exoplayertest;

import static com.google.android.exoplayer2.C.SELECTION_FLAG_DEFAULT;
import androidx.appcompat.app.AppCompatActivity;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.ui.StyledPlayerView;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.common.collect.ImmutableList;

public class MainActivity extends AppCompatActivity {
    StyledPlayerView exoPlayerView;
    String videoURL = "https://media.geeksforgeeks.org/wp-content/uploads/20201217163353/Screenrecorder-2020-12-17-16-32-03-350.mp4";
    String videoSubtitle = "https://cdmdemo.contentdm.oclc.org/utils/getfile/collection/p15700coll2/id/18/filename/video2.vtt";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ExoPlayer player = new ExoPlayer.Builder(getApplicationContext()).build();

        exoPlayerView = findViewById(R.id.idExoPlayerVIew);
        exoPlayerView.setPlayer(player);

        Uri resourceURI = Uri.parse("file:///android_asset/video2.vtt");

        MediaItem.SubtitleConfiguration subtitle =
                new MediaItem.SubtitleConfiguration.Builder(resourceURI)
                        .setMimeType(MimeTypes.TEXT_VTT)
                        .setSelectionFlags(SELECTION_FLAG_DEFAULT)
                        .build();

        MediaItem mediaItem =
                new MediaItem.Builder()
                        .setUri(videoURL)
                        .setMimeType(MimeTypes.APPLICATION_MP4)
                        .setSubtitleConfigurations(ImmutableList.of(subtitle))
                        .build();

        // Set the media item to be played.
        player.setMediaItem(mediaItem);
        // Prepare the player.
        player.prepare();
        // Start the playback.
        player.play();

    }
}