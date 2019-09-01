package com.padc.homework.housebuyingandrentingpoc.fragments;

import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;

public abstract class BaseFragment extends Fragment {

    public void showSnackBar(String message){
        Snackbar snackbar = Snackbar.make(getView(), message, Snackbar.LENGTH_INDEFINITE);
        snackbar.show();
    }
}
