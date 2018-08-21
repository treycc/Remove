package com.jdp.hls.page.suggest;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.jdp.hls.R;
import com.jdp.hls.activity.BigImgActivity;
import com.jdp.hls.adapter.BaseRvPositionAdapter;
import com.jdp.hls.adapter.ImgUriAdapter;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.util.CheckUtil;
import com.jdp.hls.util.DialogUtil;
import com.jdp.hls.util.FileUtil;
import com.jdp.hls.util.MatisseUtil;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.util.SpSir;
import com.jdp.hls.util.ToastUtil;
import com.jdp.hls.view.RvItemDecoration;
import com.kingja.supershapeview.view.SuperShapeEditText;
import com.zhihu.matisse.Matisse;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Description:意见或建议
 * Create Time:2018/7/30 0030 上午 10:42
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class SuggestActivity extends BaseTitleActivity implements SuggestContract.View {
    @BindView(R.id.sp_suggestType)
    Spinner spSuggestType;
    @BindView(R.id.et_content)
    SuperShapeEditText etContent;
    @BindView(R.id.rv_suggest_img)
    RecyclerView rvSuggestImg;
    @Inject
    SuggestPresenter suggestPresenter;
    private List<Uri> photoUris = new ArrayList<>();
    List<Uri> mSelectedUris;
    private ImgUriAdapter imgUriAdapter;
    private int suggestType;

    @Override
    public void initVariable() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_suggest;
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
        return "意见和建议";
    }

    @Override
    protected void initView() {
        suggestPresenter.attachView(this);
        imgUriAdapter = new ImgUriAdapter(this, photoUris);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        rvSuggestImg.setLayoutManager(layoutManager);
        rvSuggestImg.setAdapter(imgUriAdapter);
        rvSuggestImg.setItemAnimator(new DefaultItemAnimator());
        rvSuggestImg.addItemDecoration(new RvItemDecoration(this, RvItemDecoration.LayoutStyle.HORIZONTAL_LIST,
                12, 0x00ffffff));
        imgUriAdapter.setOnItemClickListener(new BaseRvPositionAdapter.OnItemClickListener<Uri>() {
            @Override
            public void onItemClick(List<Uri> list, int position) {
                if (imgUriAdapter.isLastItem(position)) {
                    MatisseUtil.openCamera(SuggestActivity.this, 3);
                } else {
                    BigImgActivity.goActivity(SuggestActivity.this, MatisseUtil.getDTOImgInfoFromUri(imgUriAdapter
                            .getDate()), position);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            switch (requestCode) {
                case MatisseUtil.REQUEST_CODE_CHOOSE:
                    mSelectedUris = Matisse.obtainResult(data);
                    imgUriAdapter.addData(mSelectedUris);
                    break;
            }
        }
    }

    @Override
    protected void initData() {
        setRightClick("发送", new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                checkData();
            }
        });
        spSuggestType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                suggestType = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void checkData() {
        String content = etContent.getText().toString().trim();
        if (CheckUtil.checkEmpty(content, "请输入内容详情")) {
            MultipartBody.Builder bodyBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("suggestType", String.valueOf(suggestType))
                    .addFormDataPart("suggestContent", content)
                    .addFormDataPart("authorId", String.valueOf(SpSir.getInstance().getEmployeeId()))
                    .addFormDataPart("clientId", "1");//终端类型，0:其它;1:Android;2:IOS;3:web
            List<Uri> uris = imgUriAdapter.getDate();
            for (int i = 0; i < uris.size(); i++) {
                File photoFile = FileUtil.getFileByUri(uris.get(i), this);
                bodyBuilder.addFormDataPart("rosterFile" + i, photoFile.getName(), RequestBody.create(MediaType.parse
                        ("image/*"), photoFile));
            }
            RequestBody requestBody = bodyBuilder.build();
            suggestPresenter.suggest(requestBody);
        }
    }

    @Override
    protected void initNet() {

    }

    @Override
    public void onSuggestSuccess() {
        showSuccessDialog();
    }

    @Override
    public void showLoading() {
        setProgressShow(true);
    }

    @Override
    public void hideLoading() {
        setProgressShow(false);
    }

    private void showSuccessDialog() {
        DialogUtil.showConfirmDialog(this, "感谢您提供的意见和建议", new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                finish();
            }
        });
    }
}
