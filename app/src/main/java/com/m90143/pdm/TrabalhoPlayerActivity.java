package com.m90143.pdm;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.audiofx.Visualizer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 * Copy test.mp3 to /res/raw/ folder
 *
 * needed in AndroidManifest.xml
 * android:minSdkVersion="9"
 * uses-permission of "android.permission.RECORD_AUDIO"
 *
 * reference: Android demo example -
 * ApiDemos > Media > AudioTx
 */

public class TrabalhoPlayerActivity extends AppCompatActivity {

    VisualizerView mVisualizerView;

    private MediaPlayer mMediaPlayer;
    private Visualizer mVisualizer;
    private DatabaseTrabalho database;
    String sql = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mVisualizerView = (VisualizerView) findViewById(R.id.myvisualizerview);

        database = new DatabaseTrabalho(this);
        Bundle extras = getIntent().getExtras();
        sql = "SELECT audio FROM recorder WHERE id = " + extras.getString("idDados");

        initAudio();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isFinishing() && mMediaPlayer != null) {
            mVisualizer.release();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    private void initAudio() {
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        //mMediaPlayer = MediaPlayer.create(this, R.raw.test);

        File file = null;
        FileOutputStream fos;
        byte[] byteAudio = null;

        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        byteAudio = cursor.getBlob(cursor.getColumnIndex("audio"));

        try {

            file = File.createTempFile("sound", "sound");
            fos = new FileOutputStream(file);
            fos.write(byteAudio);
            fos.close();

            Log.d("File", file.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        mMediaPlayer = MediaPlayer.create(this, Uri.fromFile(file));

        //setupVisualizerFxAndUI();
        // Make sure the visualizer is enabled only when you actually want to
        // receive data, and
        // when it makes sense to receive data.

        //mVisualizer.setEnabled(true);
        // When the stream ends, we don't need to collect any more data. We
        // don't do this in
        // setupVisualizerFxAndUI because we likely want to have more,
        // non-Visualizer related code
        // in this callback.
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mVisualizer.setEnabled(false);
                    }
                });
        mMediaPlayer.start();

    }

    private void setupVisualizerFxAndUI() {

        // Create the Visualizer object and attach it to our media player.
        mVisualizer = new Visualizer(mMediaPlayer.getAudioSessionId());
        mVisualizer.setCaptureSize(Visualizer.getCaptureSizeRange()[1]);
        mVisualizer.setDataCaptureListener(
                new Visualizer.OnDataCaptureListener() {
                    public void onWaveFormDataCapture(Visualizer visualizer,
                                                      byte[] bytes, int samplingRate) {
                        mVisualizerView.updateVisualizer(bytes);
                    }

                    public void onFftDataCapture(Visualizer visualizer,
                                                 byte[] bytes, int samplingRate) {
                    }
                }, Visualizer.getMaxCaptureRate() / 2, true, false);
    }

}