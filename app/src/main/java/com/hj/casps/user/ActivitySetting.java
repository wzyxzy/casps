package com.hj.casps.user;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hj.casps.R;
import com.hj.casps.base.ActivityBaseHeader2;
import com.hj.casps.ui.MyDialog;
import com.hj.casps.util.DataCleanManager;

import cn.bmob.v3.listener.BmobUpdateListener;
import cn.bmob.v3.update.UpdateResponse;
import cn.bmob.v3.update.UpdateStatus;
import cn.common.updata.AppUpdateUtils;

/**
 * Created by YaoChen on 2017/4/14.
 */

public class ActivitySetting extends ActivityBaseHeader2 implements View.OnClickListener {
    private MyDialog selfDialog;
    private TextView updataTv;
    private TextView cacheTv;
    private TextView version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        setTitleRight(null,null);
        setTitle(getString(R.string.settings));
        initView();
        isUpdate();
        checkCache();
    }

    private void initView() {
        findViewById(R.id.setting_notice_Re).setOnClickListener(this);
        findViewById(R.id.setting_clean_Re).setOnClickListener(this);
        findViewById(R.id.setting_new_version_Re).setOnClickListener(this);
        findViewById(R.id.setting_about_version_Re).setOnClickListener(this);
        findViewById(R.id.setting_about_us_Re).setOnClickListener(this);

        updataTv = (TextView) findViewById(R.id.setting_new_version_tv);
        cacheTv = (TextView) findViewById(R.id.setting_cache_size);
        version = (TextView) findViewById(R.id.setting_about_version_tv);
        try {
            version.setText("v:" + getVersionName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.setting_notice_Re:
                break;
            case R.id.setting_clean_Re:
                selfDialog = new MyDialog(ActivitySetting.this);
                selfDialog.setMessage(getString(R.string.cleanask));
                selfDialog.setYesOnclickListener(getString(R.string.cleanfast), new MyDialog.onYesOnclickListener() {
                    @Override
                    public void onYesClick() {
                        selfDialog.dismiss();
                        DataCleanManager.cleanApplicationData(ActivitySetting.this);
                        checkCache();
                        toast(getString(R.string.cleancomplete));
                    }
                });
                selfDialog.setNoOnclickListener(getString(R.string.cancel), new MyDialog.onNoOnclickListener() {
                    @Override
                    public void onNoClick() {
                        selfDialog.dismiss();
                    }
                });
                selfDialog.show();
                break;
            case R.id.setting_new_version_Re:
                AppUpdateUtils.update(this, null);
                break;
            case R.id.setting_about_version_Re:
                startActivity(new Intent(ActivitySetting.this,ActivityAboutVersion.class));
                break;
            case R.id.setting_about_us_Re:
                startActivity(new Intent(ActivitySetting.this,ActivityAboutUs.class));
                break;
        }
    }

    /*
  * 检查缓存的大小
  */
    private void checkCache() {
        try {
            cacheTv.setText(DataCleanManager.getTotalCacheSize(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    * 检查是否有更新
    */
    private void isUpdate() {
        AppUpdateUtils.updatePatch(this, new BmobUpdateListener() {
            @Override
            public void onUpdateReturned(int i, UpdateResponse updateResponse) {
                if (i != UpdateStatus.Yes) {
                    updataTv.setText(getString(R.string.isnew_version));
                } else {
                    updataTv.setText(getString(R.string.have_new_version));
                }
            }
        }, false);
    }


    private String getVersionName() throws Exception {
        PackageManager packageManager = getPackageManager();
        PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(), 0);
        String version = packInfo.versionName;
        return version;
    }
}
