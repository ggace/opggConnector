package com.example.opggconneter;

import android.view.View;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.opggconneter.util.Util;

public class Binding {
    @BindingAdapter({"adapter"})
    public static void setAdater(RecyclerView view, RecyclerView.Adapter adapter){

        view.setAdapter(adapter);
    }

    @BindingAdapter({"onclick"})
    public static void onclick(View view, View.OnClickListener onClickListener){

        view.setOnClickListener(onClickListener);
    }
}
