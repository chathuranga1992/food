package com.uwu.ans.foodsafty;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.uwu.ans.foodsafty.facade.ISystemFacade;
import com.uwu.ans.foodsafty.facade.SystemFacade;
import com.uwu.ans.foodsafty.util.L;
import com.uwu.ans.foodsafty.util.LoadingDialog;
import com.uwu.ans.foodsafty.util.NetworkUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Rukshan on 11/12/18.
 */

public abstract class GenericActivity extends AppCompatActivity {

    public static final String mBroadcastStringAction = "com.influencehamptons.broadcast.string";
    public ISystemFacade mFacade;
    public ProgressDialog dialog;
    private LoadingDialog mAlertDialog;
    private NetworkConnectionReceiver mConnectionReceiver;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFacade = SystemFacade.getFacade();
        mAlertDialog = new LoadingDialog(this, android.R.style.Theme_Black_NoTitleBar_Fullscreen);


    }

    private void registerReceiver() {
        mConnectionReceiver = new NetworkConnectionReceiver();

        registerReceiver(mConnectionReceiver, new IntentFilter(mBroadcastStringAction));
    }

    public void startNewActivity(Intent intent) {
        startActivity(intent);
    }

    public void startNewActivity(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    public void hideKeyboardFrom(View view) {
        try {
            InputMethodManager imm = (InputMethodManager) getApplicationContext()
                    .getSystemService(Activity.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputManager = (InputMethodManager) activity
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        // check if no view has focus:
        View currentFocusedView = activity.getCurrentFocus();
        if (currentFocusedView != null) {
            inputManager.hideSoftInputFromWindow(currentFocusedView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Subscribe
    public void registerEventBus(Context context) {
        if (!EventBus.getDefault().isRegistered(context)) {
            EventBus.getDefault().register(context);
        }
    }

    @Subscribe
    public void unregisterEventBus(Context context) {
        if (EventBus.getDefault().isRegistered(context)) {
            EventBus.getDefault().unregister(context);
        }
    }

    public void showNetworkErrorMsg() {
        if (NetworkUtil.isConnected(this)) {
            showInfoMsg(getString(R.string.server_error));
        } else {
            showNetworkErrorInfoMsg(getString(R.string.no_connection_msg));
        }
    }

    public void showNetworkErrorInfoMsg(String msg) {
        new SweetAlertDialog(this, SweetAlertDialog.NORMAL_TYPE)
                .setContentText(msg)
                .setTitleText("Info")
                .setConfirmText("Ok")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismiss();
                        startActivity(getIntent());
                    }
                }).show();
    }

    public void showLoadingIndicator() {

        if (mAlertDialog != null) {
            mAlertDialog.dismiss();
        }

       /* mAlertDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        mAlertDialog.setTitleText("Loading");
        mAlertDialog.setCancelable(false);
        mAlertDialog.show();*/
        if (mAlertDialog != null && !mAlertDialog.isShowing()) {

            mAlertDialog.show();
        }
    }

    public void dismissLoadingIndicator() {
        if (mAlertDialog != null) {
            mAlertDialog.dismiss();
        }
        if (mAlertDialog.isShowing()) {
            mAlertDialog.dismiss();
        }
    }

    public void showInfoMsg(String msg) {
        new SweetAlertDialog(this, SweetAlertDialog.NORMAL_TYPE)
                .setContentText(msg)
                .setTitleText("Info")
                .setConfirmText("Ok")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismiss();
                    }
                }).show();
    }


    public void showInternetInfoMsg(String msg) {
        new SweetAlertDialog(this, SweetAlertDialog.NORMAL_TYPE)
                .setContentText(msg)
                .setTitleText("Network Error")
                .setConfirmText("Ok")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        dismissLoadingIndicator();
                        sweetAlertDialog.dismiss();
                        startActivity(getIntent());
                    }
                }).show();
    }

//
//    private void scheduleJob() {
//        JobInfo myJob = new JobInfo.Builder(0, new ComponentName(this, NetworkSchedulerService.class))
//                .setRequiresCharging(true)
//                .setMinimumLatency(1000)
//                .setOverrideDeadline(2000)
//                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
//                .setPersisted(true)
//                .build();
//
//        JobScheduler jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
//        jobScheduler.schedule(myJob);
//    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver();
//        scheduleJob();
    }

    @Override
    protected void onStop() {
        L.d("--------->>>", "onStop");
//        stopService(new Intent(this, NetworkSchedulerService.class));
        if (mConnectionReceiver != null) {
            unregisterReceiver(mConnectionReceiver);
        }
        super.onStop();
    }

    class NetworkConnectionReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            L.d("-------->>>", "onReceive");
            if (intent.hasExtra("data")) {
                boolean isConnected = intent.getBooleanExtra("data", false);
                String message = isConnected ? "Good! Connected to Internet" : "Sorry! Not connected to internet";
                //Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
//                EventBus.getDefault().
//                        post(new ConnectionStatusEvent(isConnected ? ConnectionStatusEvent.Status.CONNECTED : ConnectionStatusEvent.Status.DISCONNECTED));
               /* if (!isConnected) {
                    startActivityForResult(new Intent(GenericActivity.this, ConnectionActivity.class), 22);
                }*/
            }
        }
    }

}
