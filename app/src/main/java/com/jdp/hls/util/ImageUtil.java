package com.jdp.hls.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.widget.ImageView;

import com.jdp.hls.constant.Constants;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Description:TODO
 * Create Time:2018/8/30 0030 下午 3:39
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ImageUtil {
    private static File getDirFile(Context context) {
        File imgDir;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            imgDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator +
                    Constants.DIR_HIL);
        } else {
            imgDir = new File(context.getFilesDir().getAbsolutePath() + File.separator + Constants.DIR_HIL);
        }
        if (!imgDir.exists()) {
            imgDir.mkdirs();
        }
        return imgDir;
    }

    private static Bitmap drawable2Bitmap(Drawable d) {
        int width = d.getIntrinsicWidth();
        int height = d.getIntrinsicHeight();
        Bitmap.Config config = d.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                : Bitmap.Config.RGB_565;
        Bitmap bitmap = Bitmap.createBitmap(width, height, config);
        Canvas canvas = new Canvas(bitmap);
        d.setBounds(0, 0, width, height);
        d.draw(canvas);
        return bitmap;
    }

    public static boolean saveImgView(Context context, ImageView imageView) {
        Bitmap bmp = drawable2Bitmap(imageView.getDrawable());
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(getDirFile(context), fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            //通过io流的方式来压缩保存图片
            boolean isSuccess = bmp.compress(Bitmap.CompressFormat.JPEG, 60, fos);
            fos.flush();
            fos.close();
            //把文件插入到系统图库
            //MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), fileName, null);
            //保存图片后发送广播通知更新数据库
            Uri uri = Uri.fromFile(file);
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
            if (isSuccess) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
