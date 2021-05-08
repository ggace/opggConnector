package com.example.opggconneter.adapter;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.opggconneter.R;
import com.example.opggconneter.dto.LolUserInfo;
import com.example.opggconneter.ui.DetailActivity;
import com.example.opggconneter.util.Util;
import com.fasterxml.jackson.core.type.TypeReference;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class OpggAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    public List<LolUserInfo> lolUserInfoList;
    private View.OnLongClickListener onLongItemClickListener;
    private View.OnClickListener moveToDetail;

    public void setMoveToDetail(View.OnClickListener moveToDetail){
        this.moveToDetail = moveToDetail;
    }

    public OpggAdapter(List<LolUserInfo> lolUserInfoList){

        this.lolUserInfoList = lolUserInfoList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_loluserinfo, parent, false);

        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;

        LolUserInfo lolUserInfo = lolUserInfoList.get(position);

        itemViewHolder.textViewNumber.setText((position + 1) + "");
        itemViewHolder.textViewName.setText(lolUserInfo.getName());

        itemViewHolder.textViewNumber.setTag(position);
        itemViewHolder.textViewName.setTag(position);
    }

    @Override
    public int getItemCount() {
        return lolUserInfoList.size();
    }

    public void addLolUserInfo(LolUserInfo lolUserInfo){


        lolUserInfoList.add(lolUserInfo);
        notifyDataSetChanged();



        Util.spPut("lolUserInfoList", lolUserInfoList);
        Util.spCommit();
    }

    public void removeLolUserInfo(int index){
        lolUserInfoList.remove(index);
        notifyDataSetChanged();
        Util.spPut("lolUserInfoList", lolUserInfoList);
        Util.spCommit();
    }

    public void setOnLongItemClickListener(View.OnLongClickListener onLongItemClickListener){
        this.onLongItemClickListener = onLongItemClickListener;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewNumber;
        public TextView textViewName;




        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);



            textViewNumber = itemView.findViewById(R.id.item_loluserinfo__number);
            textViewName = itemView.findViewById(R.id.item_loluserinfo__name);

            textViewNumber.setOnLongClickListener(onLongItemClickListener);
            textViewName.setOnLongClickListener(onLongItemClickListener);

            textViewNumber.setOnClickListener(moveToDetail);
            textViewName.setOnClickListener(moveToDetail);
        }
    }
}
