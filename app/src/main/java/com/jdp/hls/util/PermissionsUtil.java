package com.jdp.hls.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.page.rosteradd.RosterAddActivity;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.disposables.Disposable;

/**
 * Description:TODO
 * Create Time:2018/9/4 0004 下午 3:55
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class PermissionsUtil {
    public static void checkOpenPhoto(AppCompatActivity context) {
        RxPermissions rxPermission = new RxPermissions(context);
        Disposable disposable = rxPermission
                .requestEachCombined(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(permission -> {
                    if (permission.granted) {
                        // 用户已经同意该权限
                        MatisseUtil.openCamera(context, Constants.MAX_IMG_UPLOAD_COUNT);
                    } else if (permission.shouldShowRequestPermissionRationale) {
                        // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
                        DialogUtil.showDoubleDialog(context, "为保证您正常上传图片，需要获取打开相机、读写手机存储权限，请允许", new
                                MaterialDialog.SingleButtonCallback() {
                                    @Override
                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                        checkOpenPhoto(context);
                                    }
                                });
                    } else {
                        // 用户拒绝了该权限，并且选中『不再询问』
                        DialogUtil.showDoubleDialog(context,
                                "未取得打开相机、读写手机存储权限，将无法正常上传图片。请前往应用权限设置打开权限。", new MaterialDialog
                                        .SingleButtonCallback() {
                                    @Override
                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                        startAppSettings(context);
                                    }
                                });
                    }
                });
    }

    private static void startAppSettings(AppCompatActivity context) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.fromParts("package", context.getPackageName(), null));
        context.startActivity(intent);
    }
}
