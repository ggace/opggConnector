package com.example.opggconneter.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.opggconneter.R;
import com.example.opggconneter.adapter.OpggAdapter;
import com.example.opggconneter.databinding.ActivityMainBinding;
import com.example.opggconneter.dto.LolUserInfo;
import com.example.opggconneter.util.Util;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static OpggAdapter opggAdapter;
    public List<LolUserInfo> lolUserInfoList;
    public boolean is_longclicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        ActivityMainBinding mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setTitle("친구 리스트");
        mainBinding.setLifecycleOwner(this);

        setContentView(mainBinding.getRoot());





        if(Util.spGetString("lolUserInfoList", "") == "" || Util.spGetObj("lolUserInfoList", new TypeReference<List<LolUserInfo>>() {}) == null){
            lolUserInfoList = new ArrayList<>();
        }
        else{
            lolUserInfoList = Util.spGetObj("lolUserInfoList", new TypeReference<List<LolUserInfo>>() {});
        }

        opggAdapter = new OpggAdapter(lolUserInfoList);

        opggAdapter.setMoveToDetail(view -> {
            if(!(boolean)is_longclicked){
                Intent intent = new Intent(this, DetailActivity.class);
                String detailId = opggAdapter.lolUserInfoList.get((int)view.getTag()).getId();
                String detailName = opggAdapter.lolUserInfoList.get((int)view.getTag()).getName();

                intent.putExtra("detailId", detailId);
                intent.putExtra("detailName", detailName);
                startActivity(intent);
            }
        });

        opggAdapter.setOnLongItemClickListener(view -> {
            is_longclicked = true;
            int indexToDelete = (int) ((TextView) view).getTag();

            DialogInterface.OnClickListener onClickListener = (dialog, which) -> {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        opggAdapter.removeLolUserInfo(indexToDelete);
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            };

            new AlertDialog.Builder(this)
                    .setMessage("정말 " + opggAdapter.lolUserInfoList.get(indexToDelete).getName() + "을(를) 삭제하시겠습니까?")
                    .setPositiveButton("예", onClickListener)
                    .setNegativeButton("아니오", onClickListener)
                    .show();

            is_longclicked = false;

            return false;
        });



        MainViewModel viewModel = new MainViewModel(opggAdapter, view -> {
            Intent intent = new Intent(this, AddActivity.class);
            startActivity(intent);
        });
        mainBinding.setVm(viewModel);





    }
    

}