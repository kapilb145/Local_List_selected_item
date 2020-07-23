package com.RichReach.Corporation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Second_Screen extends AppCompatActivity implements Added_interface {
    private Button create_own;
    private Button Search, Connect;
    private String Addchannel;
    private EditText AddChannelList;
    private Button AddChannelButton;


    private RecyclerView recyclerView1;

    private RecyclerView.Adapter mAdapter;
    //    ArrayList<ExampleItem1> exampleList;
    List<String> channellist = new ArrayList<>();
    List<String> mainlist = new ArrayList<>();
    RecyclerView.LayoutManager mLayoutManager;
    LinearLayout search_list;


    Dialog dialog;


    LinearLayoutManager HorizontalLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second__screen);
        create_own = (Button) findViewById(R.id.create_own);
        Search = (Button) findViewById(R.id.search);
        Connect = (Button) findViewById(R.id.connectButton);
        recyclerView1 = (RecyclerView) findViewById(R.id.channelList);
        search_list = (LinearLayout) findViewById(R.id.search_list);
        recyclerView1.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());

        TinyDB tinyDB = new TinyDB(this);

        dialog = new Dialog(this);
        mainlist = tinyDB.getListString("addedchannellist");




        channellist.clear();
        channellist = tinyDB.getListString("searchList");


        mAdapter = new ChannelAdapter(Second_Screen.this, channellist, this);
        recyclerView1.setAdapter(mAdapter);

        HorizontalLayout = new LinearLayoutManager(Second_Screen.this, LinearLayoutManager.VERTICAL, false);
        recyclerView1.setLayoutManager(HorizontalLayout);


        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Search.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        search_list.setVisibility(View.VISIBLE);
                    }
                });
            }
        });

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                create_own.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialogBox();
                    }
                });
            }
        });


        Connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TinyDB tinydb = new TinyDB(Second_Screen.this);

                tinydb.putListString("addedchannellist", (ArrayList<String>) mainlist);

                finish();

            }
        });


    }

    private void showDialogBox() {


        dialog.setContentView(R.layout.dialogbox);
        AddChannelList = (EditText) dialog.findViewById(R.id.AddChannel);
        AddChannelButton = (Button) dialog.findViewById(R.id.ChannelConnect);
        ImageView close = (ImageView) dialog.findViewById(R.id.inform);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dialog.isShowing()) {
                    dialog.cancel();
                }
            }
        });


        AddChannelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkCondition()) {
                    channellist.add(Addchannel);


                    TinyDB tinyDB = new TinyDB(Second_Screen.this);

                    tinyDB.putListString("searchList", (ArrayList<String>) channellist);


                    dialog.dismiss();
                    mAdapter.notifyDataSetChanged();
                }
            }
        });


        dialog.show();
    }

    private boolean checkCondition() {
        Addchannel = AddChannelList.getText().toString();
        if (Addchannel.isEmpty()) {
            AddChannelList.setError("Please Enter Channel !");
            AddChannelList.requestFocus();
            return false;
        } else {
            return true;
        }
    }


    @Override
    public void AddedItem(String item, int flag) {

        if (flag == 1) {
            mainlist.add(item);
        } else if (flag == 0) {
            mainlist.remove(item);
        }


    }
}
