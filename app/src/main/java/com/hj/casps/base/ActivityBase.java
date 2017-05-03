package com.hj.casps.base;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hj.casps.R;
import com.hj.casps.util.ActivityUtils;
import com.hj.casps.util.LogToastUtils;
import com.hj.casps.util.NetUtil;

import java.util.Calendar;

import cn.appsdream.nestrefresh.base.AbsRefreshLayout;
import cn.common.base.ActivityCommBase;
import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by Administrator on 2016/12/29.
 * 常用提示
 */

public class ActivityBase extends ActivityCommBase implements NetBroadcastReceiver.NetEvevt {
    private AlertDialog dialog;
    public Bundle bundle;
    public AbsRefreshLayout mLoader;
    public Context context;
    public static NetBroadcastReceiver.NetEvevt evevt;
    /**
     * 网络类型
     */
    private int netMobile;

    public FancyButton layout_head_left_btn;
    public FancyButton layout_head_right_btn;
    public TextView layout_head_right_tv;
    private Calendar c = Calendar.getInstance();


    protected void log(String log) {
        LogToastUtils.log(getClass().getSimpleName(), log);
    }

    public void toast(String toast) {
        LogToastUtils.toast(this, toast);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityUtils.addActivity(this);

        initViewBaseView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        inspectNet();
    }

    private void initViewBaseView() {
        bundle = new Bundle();
        context = this;
        evevt = this;
        inspectNet();
    }

    /**
     * 初始化时判断有没有网络
     */

    public boolean inspectNet() {
        this.netMobile = NetUtil.getNetWorkState(ActivityBase.this);
        return isNetConnect();

        // if (netMobile == 1) {
        // System.out.println("inspectNet：连接wifi");
        // } else if (netMobile == 0) {
        // System.out.println("inspectNet:连接移动数据");
        // } else if (netMobile == -1) {
        // System.out.println("inspectNet:当前没有网络");
        //
        // }
    }


    /**
     * 网络变化之后的类型
     */
    @Override
    public void onNetChange(int netMobile) {
        // TODO Auto-generated method stub
        this.netMobile = netMobile;
        isNetConnect();

    }

    /**
     * 判断有无网络 。
     *
     * @return true 有网, false 没有网络.
     */
    public boolean isNetConnect() {
        if (netMobile == 1) {
            return true;
        } else if (netMobile == 0) {
            return true;
        } else if (netMobile == -1) {
            return false;

        }
        return false;
    }

    public void addContentView(View view) {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        if (findViewById(R.id.base_tool_bar) != null) {
            int marginTop = (int) getResources().getDimension(R.dimen.title_height);
            params.setMargins(0, marginTop, 0, 0);
        }
        super.addContentView(view, params);
    }

    public void removeContentView() {
        FrameLayout content = (FrameLayout) findViewById(android.R.id.content);
        if (content.getChildCount() > 1) {
            content.removeViews(1, content.getChildCount() - 1);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dialogDismiss();
    }


    public void toastDialog(String str) {
        dialogDismiss();
        dialog = new AlertDialog.Builder(this).setMessage(str).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).create();
        dialog.show();
    }

    private void dialogDismiss() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    /**
     * 长toast
     *
     * @param content
     */
    public void toastLONG(String content) {
        Toast.makeText(this, content, Toast.LENGTH_LONG).show();
    }

    /**
     * 短toast
     *
     * @param content
     */
    public void toastSHORT(String content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }

    public void intentActivity(Context from, Class to) {
        Intent intent = new Intent(from, to);
        startActivity(intent);
    }


    public void intentActivity(Class to, Bundle bundle) {
        Intent intent = new Intent(context, to);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void intentActivity(Class toClass, int ac_type) {
        Intent intent = new Intent(this, toClass);
        startActivityForResult(intent, ac_type);
    }

    public void intentActivity(Class toClass, int ac_type, Bundle bundle) {
        Intent intent = new Intent(this, toClass);
        intent.putExtras(bundle);
        startActivityForResult(intent, ac_type);
    }

    /**
     * bundle
     *
     * @param from
     * @param to
     */
    public void intentActivity(Context from, Class to, Bundle bundle) {
        Intent intent = new Intent(from, to);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    public void showCalendar(final EditText editText) {
//        c = Calendar.getInstance();
        new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        int month = monthOfYear + 1;
                        if (month < 10 && dayOfMonth < 10) {
                            editText.setText(year + "-0" + month
                                    + "-0" + dayOfMonth);
                        } else if (month < 10 && dayOfMonth >= 10) {
                            editText.setText(year + "-0" + month
                                    + "-" + dayOfMonth);
                        } else if (month >= 10 && dayOfMonth < 10) {
                            editText.setText(year + "-" + month
                                    + "-0" + dayOfMonth);
                        } else {
                            editText.setText(year + "-" + month
                                    + "-" + dayOfMonth);
                        }

                    }
                }
                , c.get(Calendar.YEAR), c.get(Calendar.MONTH), c
                .get(Calendar.DAY_OF_MONTH)).show();
        editText.setCompoundDrawables(null, null, null, null);
    }
}
