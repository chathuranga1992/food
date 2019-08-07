package com.uwu.ans.foodsafty;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.uwu.ans.foodsafty.facade.ISystemFacade;
import com.uwu.ans.foodsafty.facade.SystemFacade;


/**
 * Created by Rukshan on 11/12/18.
 */

public abstract class GenericFragment extends Fragment {

    public ISystemFacade mFacade;
    public ProgressDialog dialog;

//    private LoadingDialog mAlertDialog;

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputManager = (InputMethodManager) activity
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        // check if no view has focus:
        View currentFocusedView = activity.getCurrentFocus();
        if (currentFocusedView != null) {
            inputManager.hideSoftInputFromWindow(currentFocusedView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFacade = SystemFacade.getFacade();
//        mAlertDialog = new LoadingDialog(getActivity(), android.R.style.Theme_Black_NoTitleBar_Fullscreen);
    }

    public void startNewActivity(Class<?> clazz) {
        Intent intent = new Intent(getActivity(), clazz);
        startActivity(intent);
    }

    public void startNewActivity(Intent intent) {
        startActivity(intent);
    }


}
