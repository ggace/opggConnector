package com.example.opggconneter.ui;

import android.view.View;

public class AddViewModel {

    public View.OnClickListener onClickListenerForSave;
    public View.OnClickListener onClickListenerForCancel;

    public AddViewModel(View.OnClickListener onClickListenerForSave, View.OnClickListener onClickListenerForCancel) {
        this.onClickListenerForSave = onClickListenerForSave;
        this.onClickListenerForCancel = onClickListenerForCancel;
    }
}
