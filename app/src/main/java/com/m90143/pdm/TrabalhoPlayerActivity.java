package com.m90143.pdm;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.core.content.ContextCompat;
import com.chibde.visualizer.LineVisualizer;

public class TrabalhoPlayerActivity extends BaseActivity {

    private DatabaseTrabalho database;
    String sql = "";

    @Override
    protected void init() {
        LineVisualizer lineVisualizer = findViewById(R.id.visualizer);

        mediaPlayer = MediaPlayer.create(this, Uri.fromFile(file));

        // set custom color to the line.
        lineVisualizer.setColor(ContextCompat.getColor(this, R.color.custom));

        // set the line with for the visualizer between 1-10 default 1.
        lineVisualizer.setStrokeWidth(1);

        // Set you media player to the visualizer.
        lineVisualizer.setPlayer(mediaPlayer.getAudioSessionId());
    }


    public void replay(View view) {
        if (mediaPlayer != null) {
            mediaPlayer.seekTo(0);
        }
    }

    public void playPause(View view) {
        playPauseBtnClicked((ImageButton) view);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_line_visualizer;
    }
}