package com.jdp.hls.page.crash;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.jdp.hls.R;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.util.ToastUtil;
import com.jdp.hls.util.VersionUtil;
import com.kingja.supershapeview.view.SuperShapeTextView;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import cat.ereza.customactivityoncrash.CustomActivityOnCrash;

/**
 * Description:崩溃界面
 * Create Time:2018/8/29 0029 下午 4:46
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class CrashActivity extends BaseTitleActivity implements CrashContract.View {
    @BindView(R.id.set_restartApp)
    SuperShapeTextView setRestartApp;
    @BindView(R.id.tv_errorLog)
    TextView tvErrorLog;
    private String errorDetail;
    @Inject
    CrashPresenter crashPresenter;


    @OnClick({R.id.set_restartApp, R.id.tv_errorLog})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.set_restartApp:
                CustomActivityOnCrash.restartApplication(this, CustomActivityOnCrash.getConfigFromIntent(getIntent()));
                break;
            case R.id.tv_errorLog:
                new MaterialDialog.Builder(this)
                        .title("异常信息")
                        .content(getErrorInfo())
                        .positiveText("确认")
                        .neutralText("复制到剪贴板")
                        .neutralColor(ContextCompat.getColor(this, R.color.main))
                        .onNeutral(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                copyErrorToClipboard();
                            }
                        })
                        .show();
                break;
        }
    }

    @Override
    public void initVariable() {
        errorDetail = CustomActivityOnCrash.getAllErrorDetailsFromIntent(this, getIntent());

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_crash;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerBaseCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    protected String getContentTitle() {
        return "";
    }

    @Override
    protected void initView() {
        crashPresenter.attachView(this);//上传服务器
        sava2Local();//保存在本地
    }

    @Override
    protected void initData() {

    }

    @Override
    protected boolean ifHideTitle() {
        return true;
    }

    @Override
    protected boolean ifHideBack() {
        return true;
    }

    @Override
    protected void initNet() {
//        crashPresenter.uploadError("", "1", VersionUtil.getVerName(this), "2", getErrorInfo(), "1");
    }

    private void copyErrorToClipboard() {
        String errorInformation = CustomActivityOnCrash.getAllErrorDetailsFromIntent(this, getIntent());

        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

        //Are there any devices without clipboard...?
        if (clipboard != null) {
            ClipData clip = ClipData.newPlainText(getString(cat.ereza.customactivityoncrash.R.string
                    .customactivityoncrash_error_activity_error_details_clipboard_label), errorInformation);
            clipboard.setPrimaryClip(clip);
            ToastUtil.showText("已复制到剪贴板");
        }
    }

    @Override
    public void onUploadErrorSuccess() {

    }

    private void sava2Local() {
        File mLogDir;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            mLogDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator +
                    Constants.DIR_HIL);
        } else {
            mLogDir = new File(getFilesDir().getAbsolutePath() + File.separator + Constants.DIR_HIL);
        }
        if (!mLogDir.exists()) {
            mLogDir.mkdirs();
        }
        File logFile = new File(mLogDir, Constants.LOG_FILENAME);
        PrintWriter pw;
        try {
            pw = new PrintWriter(new BufferedWriter(new FileWriter(logFile, true)));
            pw.println(getErrorInfo());
            pw.close();
        } catch (IOException e) {
            Log.e("CrashHandler", "save file failed...  ", e.getCause());
        }
    }

    public String getFormaTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date());
    }

    private String getErrorInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append(getFormaTime())
                .append("\n")
                .append("=================")
                .append("\n")
                .append("【Version】" + VersionUtil.getVerName(this) + " | " + VersionUtil.getVersionCode(this))
                .append("\n")
                .append("【Crash Thread】" + Thread.currentThread().getName())
                .append("\n")
                .append("【Phone Info】" + "MODEL:" + android.os.Build.MODEL + ","
                        + "SDK_INT:" + android.os.Build.VERSION.SDK_INT + ","
                        + "RELEASE:" + android.os.Build.VERSION.RELEASE + ","
                        + "CPU_ABI:" + android.os.Build.CPU_ABI)
                .append("\n")
                .append(errorDetail);
        return sb.toString();

    }
}
