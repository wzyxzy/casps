package com.hj.casps.user;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.hj.casps.R;
import com.hj.casps.util.SubmitUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by YaoChen on 2017/4/10.
 * 登录页面
 */

public class ActivityLogin extends AppCompatActivity implements View.OnClickListener {
    @BindView((R.id.login_toast))
    TextView toast;
    @BindView(R.id.login_inputname_Et)
    EditText user_name_Et;
    @BindView(R.id.login_inputps_Et)
    EditText password_Et;
    @BindView(R.id.login_true_Btn)
    FancyButton submit;
    private int Uuid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        submit.setOnClickListener(this);
    }

    /**
     * 错误提示事件
     *
     * @param msg
     */
    private void toastShow(String msg) {
        toast.setVisibility(View.VISIBLE);
        if (toast.getVisibility() == View.VISIBLE) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    toast.setVisibility(View.GONE);
                }
            }, 1000);
        }
    }

    /**
     * 本地的格式判断
     *
     * @return
     */
    private boolean isCheck() {
        if (user_name_Et.getText().toString().length() == 0) {
            toastShow(getString(R.string.error_user_name));
            return false;
        }
        if (password_Et.getText().toString().length() == 0) {
            toastShow(getString(R.string.error_password));
        }
        return true;
    }

    /**
     * 登录事件的网络请求
     */
    private void loginNet() {
//        //网络地址
//        String url = ((HejiaApp) getApplicationContext()).getApiList().getItemUrl(ApiList.Login_index);
//        //请求参数
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("email", email.getText().toString());
//        jsonObject.put("password", password.getText().toString().trim());
//
//
//        HttpRxUtils.nextPostJson(this, url, jsonObject, LoginBean.class, null, true).
//                subscribe(new NextSuccessSubscriber<LoginBean>(this,false) {
//                    @Override
//                    protected void onSuccessNext(LoginBean baseBean) {
//
//                        LoginBean.DataBean data = baseBean.getData();
//                        data.setVaildtime(UserBeanPersistenceUtils.getLocalExptime(data.getVaildtime()));
//                        UserBeanPersistenceUtils.write(data, true);
//                        setResult(RESULT_OK, getIntent());
//                        finish();
//                    }
//                });
        startActivity(new Intent(ActivityLogin.this, ActivityUser.class));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_true_Btn:
//                Uuid=Uuid+1;
//                System.out.println(String.format("%05d", Uuid));
//                SubmitUtils submitUtils=new SubmitUtils(submit);
//                submitUtils.addTimeClock();
                loginNet();
                break;
        }
    }
}
