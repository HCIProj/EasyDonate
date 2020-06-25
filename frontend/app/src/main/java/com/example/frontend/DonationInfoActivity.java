package com.example.frontend;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.frontend.HelpFunctions.RequestItemAdapter;
import com.example.frontend.HelpFunctions.UserData;
import com.example.frontend.HelpFunctions.donationAdapter;

public class DonationInfoActivity extends AppCompatActivity {
    //private Switch switchModel;
    RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donationinfo);
        mRecyclerView = (RecyclerView) findViewById(R.id.donationinfo_recycle_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(DonationInfoActivity.this, 1));
        // initData();
        //实例化并传输数据给adapter
        final donationAdapter adapter = new donationAdapter(DonationInfoActivity.this, UserData.donationList);
        mRecyclerView.setAdapter(adapter);
    }
}
