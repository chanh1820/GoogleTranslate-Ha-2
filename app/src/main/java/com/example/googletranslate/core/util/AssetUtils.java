package com.example.googletranslate.core.util;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

public class AssetUtils {

    static String pathTemplate = "{1}/{2}/{3}/{4}";

    public static final Drawable getImageFromAsset(int unitId, String fileName, Context context) {
        InputStream imageStream = null;
        Drawable drawable;
        try {
//            imageStream = context.getAssets().open(
//                    pathTemplate.replace("{1}", "unit")
//                            .replace("{2}", String.valueOf(unitId))
//                            .replace("{3}", "image")
//                            .replace("{4}", fileName)
//            );
            imageStream = context.getAssets().open("unit/all/image/"+fileName);
            drawable = Drawable.createFromStream(imageStream, null);
        } catch (IOException ex) {
            return null;
        }
        return drawable;
    }

    public static void playSoundFromAsset(int unitId, String fileName,Context context) {
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            AssetFileDescriptor descriptor = null;
//            descriptor = context.getAssets().openFd(
//                        pathTemplate.replace("{1}", "unit")
//                                .replace("{2}", String.valueOf(unitId))
//                                .replace("{3}", "sound")
//                                .replace("{4}", fileName)
//                );
            descriptor = context.getAssets().openFd("unit/all/sound/"+fileName);
            if (descriptor == null){
                Log.e("playSoundFromAsset", unitId + "/" + fileName);
                return;
            }
            mediaPlayer.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
            descriptor.close();
            mediaPlayer.prepare();
            mediaPlayer.setLooping(false);
            mediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String playSoundFromAsset2(int unitId, String fileName,Context context) {
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            AssetFileDescriptor descriptor = null;
            descriptor = context.getAssets().openFd("unit/all/sound/"+fileName);
            if (descriptor == null){
                return unitId + "/"+ fileName;
            }
        } catch (Exception e) {
            return unitId + "/ "+ fileName;
        }
        return "0";

    }
}
