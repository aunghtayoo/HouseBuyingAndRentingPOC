package com.padc.homework.housebuyingandrentingpoc.fragments;

import com.google.android.material.snackbar.Snackbar;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {

    public void showSnackBar(String message){
        Snackbar snackbar = Snackbar.make(getView(), message, Snackbar.LENGTH_INDEFINITE);
        snackbar.show();
    }
}
