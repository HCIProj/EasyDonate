package com.example.frontend.HelpFunctions;

import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.example.frontend.LoginActivity;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;

public class HttpHandler {
    private static String UrlHead="http://yijuan.free.idcfengye.com";

    public static void register(final Context context, final String username, final String password, final String email) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("haha","go1");
                HttpURLConnection conn=null;
                BufferedReader br=null;
                String registerUrl=UrlHead+"/User/register?username="+username+"&password="+password+"&phonenum="+email;
                //https://6ed30734.ngrok.io/user/register/username/macoredroid/password/c7o2r1e4/email/coredroid0401@gmail.com
                try {
                    //URL url=new URL("https://5184c2d6.ngrok.io/user/login/username/macoredroid/password/c7o2r1e4");
                    URL url=new URL(registerUrl);
                    conn= (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(8000);
                    conn.setReadTimeout(8000);
                    InputStream in=conn.getInputStream();
                    br=new BufferedReader(new InputStreamReader(in));

                    StringBuilder sb=new StringBuilder();
                    String s;
                    while((s = br.readLine())!=null){
                        sb.append(s);
                    }
                    Log.d("6s112",sb.toString());
                    if(sb.toString().equals("3")){
                        Looper.prepare();
                        Toast.makeText(context,"注册成功",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(context,LoginActivity.class);
                        context.startActivity(intent);
                        Looper.loop();

                    }
                    else{
                        Looper.prepare();
                        Toast.makeText(context,"注册失败",Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }
                    //setContent(sb.toString());
                    Log.d("123","---"+sb.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("haha",e.getMessage());
                }finally {
                    if (conn!=null){
                        conn.disconnect();
                    }
                    if (br!=null){
                        try {
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();

    }

}
