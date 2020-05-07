package com.example.frontend;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import com.example.frontend.HelpFunctions.HttpHandler;
import com.example.frontend.HelpFunctions.UserData;


public class RegisterActivity extends AppCompatActivity {
    private Button register;
    private Button getCheckNum;
    private EditText usernameText;
    private EditText passwordText;
    private EditText rpasswordText;
    private EditText emailText;
    private EditText checkNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        usernameText = findViewById(R.id.act_register_username);
        passwordText = findViewById(R.id.act_register_password);
        rpasswordText = findViewById(R.id.act_register_password2);
        emailText = findViewById(R.id.act_register_emailedit);
        register = findViewById(R.id.act_register_button_register);
        checkNum=findViewById(R.id.act_register_checknum);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkNum.getText().toString().equals("")||UserData.checknum!=Integer.valueOf(checkNum.getText().toString())){
                    Toast.makeText(RegisterActivity.this, "验证码错误", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!passwordText.getText().toString().equals(rpasswordText.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "两次输入的密码必须相同", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    String username = usernameText.getText().toString();
                    String password = passwordText.getText().toString();
                    String email = emailText.getText().toString();
                    //register(username,password,email);
                    HttpHandler.register(RegisterActivity.this, username, password, email);
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }

            }
        });
        getCheckNum = findViewById(R.id.act_register_button_get);
        getCheckNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date = new Date();
                long timestamp = date.getTime()/1000;
                if(timestamp< UserData.lastGetTime+60){
                    Toast.makeText(RegisterActivity.this,(60-(timestamp-UserData.lastGetTime))+"秒后再尝试",Toast.LENGTH_SHORT).show();
                }else {
                    UserData.lastGetTime=timestamp;
                    String email = emailText.getText().toString();
                    HttpHandler.getCheck(RegisterActivity.this, email);
                }

            }
        });
    }
}
