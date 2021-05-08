package com.example.opggconneter.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.opggconneter.R;
import com.example.opggconneter.adapter.OpggAdapter;
import com.example.opggconneter.databinding.ActivityAddBinding;
import com.example.opggconneter.dto.LolUserInfo;
import com.example.opggconneter.util.Util;

import java.util.ArrayList;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityAddBinding addBinding = ActivityAddBinding.inflate(getLayoutInflater());
        addBinding.setLifecycleOwner(this);

        setTitle("추가");

        setContentView(addBinding.getRoot());


        View.OnClickListener onClickListenerForSave = view -> {
            String name = addBinding.activityAddEdittextName.getText().toString();
            String id = addBinding.activityAddEdittextId.getText().toString();



            if(name == null || id == null){
                Util.toast("이름과 아이디를 모두 입력해주세요");
                return;
            }
            MainActivity.opggAdapter.addLolUserInfo(new LolUserInfo(name, id));

            finish();
        };

        View.OnClickListener onClickListenerForCancel = view -> {

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        };

        AddViewModel addViewModel = new AddViewModel(onClickListenerForSave, onClickListenerForCancel);

        addBinding.setVm(addViewModel);


    }
}