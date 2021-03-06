package com.hj.casps.commodity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hj.casps.R;
import com.hj.casps.base.ActivityBaseHeader2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mehdi.sakout.fancybuttons.FancyButton;

public class SelectPicture03 extends ActivityBaseHeader2 {
    @BindView(R.id.iv)
    ImageView imageView;
    @BindView(R.id.fragment_mypic_empty_class_Et)
    EditText editText;
    @BindView(R.id.submit)
    FancyButton fancyButton;
    public static final String ExtraImagePath = "path";
    public static final String ExtraImageName = "name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_picture03);
        setTitle(R.string.Classs);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        Glide.with(this).load(getIntent().getStringExtra(ExtraImagePath)).into(imageView);
    }

    @OnClick(R.id.submit)
    void onSubmit(FancyButton v) {
        String resNameStr = editText.getText().toString().trim();
        if (resNameStr.length()!=0){
            // TODO: 2017/4/21   /*文件上传*/
            Intent data = new Intent();
            data.putExtra(ExtraImageName, resNameStr);
            data.putExtra(ExtraImagePath, getIntent().getStringExtra(ExtraImagePath));
            setResult(RESULT_OK, data);
            finish();
        }else {
            toast("文件名称不能为空");
        }

    }
}
