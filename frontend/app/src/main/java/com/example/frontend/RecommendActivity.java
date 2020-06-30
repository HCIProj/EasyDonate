package com.example.frontend;

import android.os.Bundle;
import android.widget.CompoundButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.frontend.HelpFunctions.HttpHandler;
import com.example.frontend.HelpFunctions.UserData;
import com.example.frontend.HelpFunctions.donationAdapter;
import com.example.frontend.HelpFunctions.recommendAdapter;

public class RecommendActivity extends AppCompatActivity {
    //private Switch switchModel;
    RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_dialog4);
        mRecyclerView = (RecyclerView) findViewById(R.id.recommend_recycle_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(RecommendActivity.this, 1));

        // initData();
        //实例化并传输数据给adapter
        final recommendAdapter adapter = new recommendAdapter(RecommendActivity.this, UserData.recommendOrgList);


        mRecyclerView.setAdapter(adapter);
    }
}
