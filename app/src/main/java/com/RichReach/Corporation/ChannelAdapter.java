package com.RichReach.Corporation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class ChannelAdapter extends RecyclerView.Adapter<ChannelAdapter.MyView> {
    private List<String> mExampleList = new ArrayList<>();
    private Context context;
    List<String> lists = new ArrayList<>();

    Added_interface added_interface;


    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.example_item1, parent, false);
        return new MyView(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final MyView holder, final int position) {

        String currentItem = mExampleList.get(position);

        holder.textView.setText(currentItem);



        for(int i=0;i<lists.size();i++){

            if(lists.get(i).equals(currentItem)){
                holder.checkBox.setChecked(true);

            }
        }

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v;


                if(cb.isChecked()){
                    added_interface.AddedItem(currentItem,1);

                }else{
                    added_interface.AddedItem(currentItem,0);
                }



            }
        });
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public static class MyView extends RecyclerView.ViewHolder {

        public TextView textView;
        CheckBox checkBox;

        public MyView(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.TextView);
            checkBox = itemView.findViewById(R.id.checkbox);


        }
    }

    public ChannelAdapter(Context context, List<String> exampleList,Added_interface added_interface) {


        this.context = context;
        this.mExampleList = exampleList;
        this.added_interface = added_interface;
        TinyDB tinydb = new TinyDB(context);


        lists = tinydb.getListString("addedchannellist");


    }



}
