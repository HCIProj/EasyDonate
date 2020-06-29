package com.example.frontend;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.frontend.HelpFunctions.HttpHandler;
import com.example.frontend.HelpFunctions.RequestItemAdapter;
import com.example.frontend.HelpFunctions.UserData;
import com.example.frontend.HelpFunctions.donationAdapter;

public class DonationInfoActivity extends AppCompatActivity {
    //private Switch switchModel;
    RecyclerView mRecyclerView;
    Switch aSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donationinfo);
        mRecyclerView = (RecyclerView) findViewById(R.id.donationinfo_recycle_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(DonationInfoActivity.this, 1));
        aSwitch=findViewById(R.id.switch1);
        // initData();
        //实例化并传输数据给adapter
        final donationAdapter adapter = new donationAdapter(DonationInfoActivity.this, UserData.donationList);

            aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked){
                        //Todo
                        UserData.donationLock=true;
                        HttpHandler.getAllDonation(DonationInfoActivity.this,UserData.username,false);
                        while(UserData.donationLock)
                        {
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                        adapter.notifyDataSetChanged();
                    }else {
                        //Todo
                        UserData.donationLock=true;
                        HttpHandler.getAllDonation(DonationInfoActivity.this,UserData.username,true);
                        while(UserData.donationLock)
                        {
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }
                }
            });


        mRecyclerView.setAdapter(adapter);
    }
}
