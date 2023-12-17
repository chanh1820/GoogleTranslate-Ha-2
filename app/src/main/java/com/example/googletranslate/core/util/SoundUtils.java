package com.example.googletranslate.core.util;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import com.example.googletranslate.core.constant.DBConstant;

public class SoundUtils {

    public static void playAssetSound(Context context, String soundFileName) {
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            AssetFileDescriptor descriptor = context.getAssets().openFd(soundFileName + DBConstant.FILE_MP3);
            mediaPlayer.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
            descriptor.close();
            mediaPlayer.prepare();
            mediaPlayer.setLooping(false);
            mediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
