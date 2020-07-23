package com.RichReach.Corporation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Related_List_Adapter extends RecyclerView.Adapter<Related_List_Adapter.MainHolder> {

    private List<String> realtedList = new ArrayList<>();
    private Context mContext;

    public  Related_List_Adapter(Context context, List<String> list){

        this.realtedList = list;
        this.mContext = context;

    }


    @NonNull
    @Override
    public MainHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.related_item, parent, false);
        MainHolder mv = new MainHolder(itemView);
        return mv;
    }

    @Override
    public void onBindViewHolder(@NonNull MainHolder holder, int position) {

        holder.textView.setText(realtedList.get(position));

        holder.close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realtedList.remove(realtedList.get(position));
                TinyDB tinyDB = new TinyDB(mContext);
                tinyDB.putListString("addedchannellist", (ArrayList<String>) realtedList);
                notifyDataSetChanged();
            }
        });


    }

    @Override
    public int getItemCount() {
        return realtedList.size();
    }

    static class MainHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView close;


        MainHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.Markou_Pharmacis);
            close = itemView.findViewById(R.id.close1);


        }
    }
}
