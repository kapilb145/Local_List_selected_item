package com.RichReach.Corporation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView AddMore;
    RecyclerView relate_list;

    RecyclerView.LayoutManager mLayoutManager;

    List<String> relatedlist = new ArrayList<>();

    Related_List_Adapter related_list_adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AddMore = (TextView) findViewById(R.id.AddMore);
        relate_list = findViewById(R.id.relate_list);



        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        relate_list.setLayoutManager(mLayoutManager);


                AddMore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, Second_Screen.class);
                        startActivity(intent);
                    }
                });

    }


    protected void onResume(){
        super.onResume();
        relatedlist.clear();
        TinyDB tinyDB = new TinyDB(this);
        relatedlist = tinyDB.getListString("addedchannellist");
        related_list_adapter = new Related_List_Adapter(this,relatedlist);
        relate_list.setAdapter(related_list_adapter);
        related_list_adapter.notifyDataSetChanged();


    }



}
