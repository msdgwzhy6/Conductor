package com.bluelinelabs.conductor.demo.controllers;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bluelinelabs.conductor.demo.util.BundleBuilder;
import com.bluelinelabs.conductor.demo.R;
import com.bluelinelabs.conductor.demo.controllers.base.RefWatchingController;

import butterknife.Bind;

public class ChildController extends RefWatchingController {

    private static final String KEY_TITLE = "ChildController.title";
    private static final String KEY_BG_COLOR = "ChildController.bgColor";
    private static final String KEY_COLOR_IS_RES = "ChildController.colorIsResId";

    @Bind(R.id.tv_title) TextView mTvTitle;

    public ChildController(String title, int backgroundColor, boolean colorIsResId) {
        this(new BundleBuilder(new Bundle())
                .putString(KEY_TITLE, title)
                .putInt(KEY_BG_COLOR, backgroundColor)
                .putBoolean(KEY_COLOR_IS_RES, colorIsResId)
                .build());
    }

    public ChildController(Bundle args) {
        super(args);
    }

    @NonNull
    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_child, container, false);
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);

        mTvTitle.setText(getArgs().getString(KEY_TITLE));

        int bgColor = getArgs().getInt(KEY_BG_COLOR);
        if (getArgs().getBoolean(KEY_COLOR_IS_RES)) {
            bgColor = ContextCompat.getColor(getActivity(), bgColor);
        }
        view.setBackgroundColor(bgColor);
    }
}
