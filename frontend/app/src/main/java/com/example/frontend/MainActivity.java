package com.example.frontend;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.frontend.Fragment.MapFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
//UI Object

    private TextView txt_channel;

    private TextView txt_message;

    private TextView txt_better;

    private FrameLayout ly_content;

    private MapFragment mapFragment;

    private FragmentManager fManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main_2);
        fManager = getSupportFragmentManager();
        bindViews();
    }
    //隐藏所有Fragment

    private void hideAllFragment(FragmentTransaction fragmentTransaction){
        if(mapFragment != null)fragmentTransaction.hide(mapFragment);
      /*  if(runningFragment != null)fragmentTransaction.hide(runningFragment);
        if(fg3 != null)fragmentTransaction.hide(fg3);*/
    }

    //UI组件初始化与事件绑定
    private void bindViews() {
        txt_channel = (TextView) findViewById(R.id.txt_channel);
        txt_message = (TextView) findViewById(R.id.txt_message);
        txt_better = (TextView) findViewById(R.id.txt_better);
        ly_content = (FrameLayout) findViewById(R.id.ly_content);
        txt_channel.setOnClickListener(this);
       /* txt_message.setOnClickListener(this);
        txt_better.setOnClickListener(this);*/
    }

    //重置所有文本的选中状态
    private void setSelected(){
        txt_channel.setSelected(false);
        //txt_message.setSelected(false);
       // txt_better.setSelected(false);
    }
    @Override
    public void onClick(View v) {

        FragmentTransaction fTransaction = fManager.beginTransaction();

        hideAllFragment(fTransaction);

        switch (v.getId()){

            case R.id.txt_channel:
                Log.d("hahahaha","on00eatse");
                setSelected();

                txt_channel.setSelected(true);

                if(mapFragment == null){

                    mapFragment = new MapFragment();

                    fTransaction.add(R.id.ly_content,mapFragment);

                }else{

                    fTransaction.show(mapFragment);

                }

                break;

        }

        fTransaction.commit();

    }
}
