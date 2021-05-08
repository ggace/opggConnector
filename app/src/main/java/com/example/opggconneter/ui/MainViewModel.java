package com.example.opggconneter.ui;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.opggconneter.adapter.OpggAdapter;

public class MainViewModel {
    public OpggAdapter opggAdapter;
    public View.OnClickListener onClickListenerForAdd;


    public MainViewModel(OpggAdapter adapter, View.OnClickListener onClickListenerForAdd) {
        this.opggAdapter = adapter;
        this.onClickListenerForAdd = onClickListenerForAdd;
    }
}
