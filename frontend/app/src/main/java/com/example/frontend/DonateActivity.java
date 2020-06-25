package com.example.frontend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.frontend.HelpFunctions.HttpHandler;
import com.example.frontend.HelpFunctions.RequestItem;
import com.example.frontend.HelpFunctions.RequestItemAdapter;
import com.example.frontend.HelpFunctions.SharedPreferencesUtil;
import com.example.frontend.HelpFunctions.UserData;

import java.util.Date;

public class DonateActivity extends AppCompatActivity {
    private TextView orgName;
    private TextView orgAddr;
    private Spinner mspinner;
    private ArrayAdapter adapter;
    //private TextView chargePerson;
    private TextView chargePersonPhone;
    private Button donate;
    private String company="STO";
    EditText  expressNum;

    RecyclerView mRecyclerView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);
        //新页面接收数据




        mspinner=(Spinner)findViewById(R.id.spinner) ;
        String[] ctype = new String[]{"申通快递", "百世快递", "中通快递", "圆通快递"};
        //创建一个数组适配器
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ctype);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);     //设置下拉列表框的下拉选项样式

        mspinner = findViewById(R.id.spinner);
        mspinner.setAdapter(adapter);
//条目点击事件
        mspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            private String positions;

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                positions = (String) adapter.getItem(position);
                if (positions.equals("申通快递")){
                    //zhye=3+"";
                    company="STO";
                    Toast.makeText(DonateActivity.this, "申通快递", Toast.LENGTH_SHORT).show();

                }else if (positions.equals("百世快递")){
                    //zhye=4+"";
                    company="HTKY";
                    Toast.makeText(DonateActivity.this, "百世快递", Toast.LENGTH_SHORT).show();

                }else if (positions.equals("中通快递")){
                    //zhye=5+"";
                    company="ZTO";
                    Toast.makeText(DonateActivity.this, "中通快递", Toast.LENGTH_SHORT).show();

                }else if (positions.equals("圆通快递")){
                    //zhye=6+"";
                    company="YTO";
                    Toast.makeText(DonateActivity.this, "圆通快递", Toast.LENGTH_SHORT).show();

                }
                parent.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                parent.setVisibility(View.VISIBLE);
            }
        });


        orgName = (TextView) findViewById(R.id.donate_org_name);
        //elfname.setText(ElfSourceController.getName(typeID,grade));
        orgAddr = (TextView) findViewById(R.id.donate_org_addr);
        //level.setText("lv."+(exp/100+1));
        //chargePerson = (TextView) findViewById(R.id.donate_org_person);
        chargePersonPhone = (TextView) findViewById(R.id.donate_org_phone);
        orgName.setText("机构名  " + UserData.targetOrg.orgName);
        orgAddr.setText("地址    " + UserData.targetOrg.addr);
        chargePersonPhone.setText("联系电话 " + UserData.targetOrg.phone);
        expressNum=(EditText)findViewById(R.id.expressnum);
        mRecyclerView = (RecyclerView) findViewById(R.id.donate_recycle_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(DonateActivity.this,1));
        // initData();
        //实例化并传输数据给adapter
        final RequestItem adapter = new  RequestItem(DonateActivity.this, UserData.targetItemList);
        mRecyclerView.setAdapter(adapter);
        donate = (Button)findViewById(R.id.act_donate );
        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DonateActivity.this, "已经提交审核", Toast.LENGTH_SHORT).show();
                for(int i=0;i<UserData.targetItemList.size();i++) {
                        if(((UserData.item) UserData.targetItemList.get(i)).donate!=0) {
                            HttpHandler.donate(DonateActivity.this, ((UserData.item) UserData.targetItemList.get(i)).name,UserData.username, expressNum.getText().toString(),company,
                                    /*((UserData.item) UserData.targetItemList.get(i)).need-*/((UserData.item) UserData.targetItemList.get(i)).donate, ((UserData.item) UserData.targetItemList.get(i)).id);
                        }

                }
               finish();
            }
        });
    }

}
