package com.example.frontend.HelpFunctions;

import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.example.frontend.DonateActivity;
import com.example.frontend.LoginActivity;
import com.example.frontend.MainActivity;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static com.example.frontend.HelpFunctions.UserData.targetItemList;
import static com.example.frontend.HelpFunctions.UserData.targetOrgLock;

public class HttpHandler {
    private static String UrlHead="http://yijuan.free.idcfengye.com";

    public static void getCheck(final Context context,final String phoneNum) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("haha","go1");
                HttpURLConnection conn=null;
                BufferedReader br=null;
                String registerUrl=UrlHead+"/User/verify?phonenum="+phoneNum;
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
                    if(sb.toString().equals("0")){
                        Looper.prepare();
                        Toast.makeText(context,"手机号已经注册",Toast.LENGTH_SHORT).show();

                        Looper.loop();

                    }
                    else{
                        Looper.prepare();
                        UserData.checknum=Integer.valueOf(sb.toString());
                        Toast.makeText(context,"请注意查收",Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }
                    //setContent(sb.toString());
                    Log.d("123","---"+sb.toString());
                } catch (Exception e) {
                    Looper.prepare();
                    Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                    e.printStackTrace();
                    Log.d("haha",e.getMessage());
                }finally {
                    Looper.prepare();
                    Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
                    Looper.loop();
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
                    if(sb.toString().equals("2")){
                        Looper.prepare();
                        Toast.makeText(context,"注册成功",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(context,LoginActivity.class);
                        context.startActivity(intent);
                        Looper.loop();

                    }
                    else{
                        Looper.prepare();
                        if(sb.toString().equals("1"))
                            Toast.makeText(context,"手机号已存在",Toast.LENGTH_SHORT).show();
                        if(sb.toString().equals("2"))
                            Toast.makeText(context,"用户名已存在",Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }
                    //setContent(sb.toString());
                    Log.d("123","---"+sb.toString());
                } catch (Exception e) {
                    Looper.prepare();
                    Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                    e.printStackTrace();
                    Log.d("haha",e.getMessage());
                }finally {
                    Looper.prepare();
                    Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
                    Looper.loop();
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

    public static void apply
            (final Context context, final String username, final String itemname, final int num) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("haha","go1");
                HttpURLConnection conn=null;
                BufferedReader br=null;
                String registerUrl=UrlHead+"/Required/apply?username="+username+"&itemname="+itemname+"&num="+num;
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
                    Log.d("apply_log",sb.toString());
                    if(sb.toString().equals("1")){
                        Looper.prepare();
                        Toast.makeText(context,"已经提交审核",Toast.LENGTH_SHORT).show();
                       /* Intent intent=new Intent(context,LoginActivity.class);
                        context.startActivity(intent);*/
                        Looper.loop();

                    }
                    else{
                        Looper.prepare();
                        Looper.loop();
                    }
                    //setContent(sb.toString());
                    Log.d("123","---"+sb.toString());
                } catch (Exception e) {
                    Looper.prepare();
                    Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                    e.printStackTrace();
                    Log.d("haha",e.getMessage());
                }finally {
                    Looper.prepare();
                    Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
                    Looper.loop();
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

    public static void update
            (final Context context,  final String itemname, final int num,final int id) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("haha","go1");
                HttpURLConnection conn=null;
                BufferedReader br=null;
                String registerUrl=UrlHead+"/Required/update?"+"itemname="+itemname+"&num="+num+"&requiredid="+id;
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
                    Log.d("update_log",sb.toString());
                    if(sb.toString().equals("1")){
                        Looper.prepare();
                        //Toast.makeText(context,"已经提交审核",Toast.LENGTH_SHORT).show();
                       /* Intent intent=new Intent(context,LoginActivity.class);
                        context.startActivity(intent);*/
                        Looper.loop();

                    }
                    else{
                        Looper.prepare();
                        Looper.loop();
                    }
                    //setContent(sb.toString());
                    Log.d("123","---"+sb.toString());
                } catch (Exception e) {
                    Looper.prepare();
                    Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                    e.printStackTrace();
                    Log.d("haha",e.getMessage());
                }finally {
                    Looper.prepare();
                    Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
                    Looper.loop();
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

    public static void donate
            (final Context context,  final String itemname,final String username,final String expressnumber,final String expresscode, final int num,final int id) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("haha","go1");
                HttpURLConnection conn=null;
                BufferedReader br=null;
                String registerUrl=UrlHead+"/Donation/donate?"+"itemname="+itemname+"&num="+num+"&requiredid="+id+"&username="+username+"&expressnumber="+expressnumber
                        +"&expresscode="+expresscode;
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
                    Log.d("update_log",sb.toString());
                    if(sb.toString().equals("1")){
                        Looper.prepare();
                        Toast.makeText(context,"已经提交审核",Toast.LENGTH_SHORT).show();
                       /* Intent intent=new Intent(context,LoginActivity.class);
                        context.startActivity(intent);*/
                        Looper.loop();

                    }
                    else{
                        if(sb.toString().equals("-1")){

                            Toast.makeText(context,"请正确选择快递公司并正确输入单号",Toast.LENGTH_SHORT).show();

                        }
                        Looper.prepare();
                        Looper.loop();
                    }
                    //setContent(sb.toString());
                    Log.d("123","---"+sb.toString());
                } catch (Exception e) {
                    Looper.prepare();
                    Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                    e.printStackTrace();
                    Log.d("haha",e.getMessage());
                }finally {
                    Looper.prepare();
                    Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
                    Looper.loop();
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

    public static void getReq
            (final Context context, final String username) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                UserData.itemList.clear();
                Log.d("haha","go1");
                HttpURLConnection conn=null;
                BufferedReader br=null;
                String registerUrl=UrlHead+"/Required/displayall?username="+username;
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
                    Log.d("allreq",sb.toString());

                        Looper.prepare();
                        //Toast.makeText(context,"已经提交审核",Toast.LENGTH_SHORT).show();

                        JSONArray jsonArray = new JSONArray(sb.toString());
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject item = jsonArray.getJSONObject(i); // 得到每个对象
                            String name=item.getString("itemname");
                            int num = Integer.valueOf(item.getString("num")); // 获取对象对应的值
                            int id = Integer.valueOf(item.getString("requiredid")); // 获取对象对应的值
                            UserData.item term=new UserData.item ();
                            term.name=name;
                            term.need=num;
                            term.id=id;
                            UserData.itemList.add(term);

                        }
                    UserData.lock2=false;
                       /* Intent intent=new Intent(context,LoginActivity.class);
                        context.startActivity(intent);*/
                        Looper.loop();



                    //setContent(sb.toString());
                    Log.d("123","---"+sb.toString());
                } catch (Exception e) {
                    Looper.prepare();
                    Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                    UserData.lock2=false;
                    e.printStackTrace();
                    Log.d("haha",e.getMessage());
                }finally {
                    Looper.prepare();
                    Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                    UserData.lock2=false;
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

    public static void deleteReq
            (final Context context, final int id) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //UserData.itemList.clear();
                Log.d("haha","go1");
                HttpURLConnection conn=null;
                BufferedReader br=null;
                String registerUrl=UrlHead+"/Required/delete?requiredid="+id;
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
                    Log.d("allreq",sb.toString());

                    Looper.prepare();
                    //Toast.makeText(context,"已经提交审核",Toast.LENGTH_SHORT).show();


                       /* Intent intent=new Intent(context,LoginActivity.class);
                        context.startActivity(intent);*/
                    Looper.loop();



                    //setContent(sb.toString());
                    Log.d("123","---"+sb.toString());
                } catch (Exception e) {
                    Looper.prepare();
                    Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                    e.printStackTrace();
                    Log.d("aaaaaas",e.getMessage());
                }finally {
                    Looper.prepare();
                    Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
                    Looper.loop();
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

    public static void getTargetReq
            (final Context context, final int id) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                UserData.targetItemList.clear();
                Log.d("haha","go1");
                HttpURLConnection conn=null;
                BufferedReader br=null;
                String registerUrl=UrlHead+"/Organization/detail?organizationid="+id;
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
                    Log.d("allreq",sb.toString());

                    Looper.prepare();
                    //Toast.makeText(context,"已经提交审核",Toast.LENGTH_SHORT).show();

                    JSONArray jsonArray = new JSONArray(sb.toString());
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject item = jsonArray.getJSONObject(i); // 得到每个对象
                        String name=item.getString("itemname");
                        int num = Integer.valueOf(item.getString("num")); // 获取对象对应的值
                        int id = Integer.valueOf(item.getString("requiredid")); // 获取对象对应的值
                        UserData.item term=new UserData.item ();
                        term.name=name;
                        term.need=num;
                        term.id=id;
                        UserData.targetItemList.add(term);

                    }
                    UserData.targetOrgLock=false;
                    Log.d("allreq2", String.valueOf(targetItemList.size()));
                       /* Intent intent=new Intent(context,LoginActivity.class);
                        context.startActivity(intent);*/
                    Looper.loop();



                    //setContent(sb.toString());
                    Log.d("123","---"+sb.toString());
                } catch (Exception e) {
                    Looper.prepare();
                    Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                    UserData.targetOrgLock=false;
                    e.printStackTrace();
                    Log.d("haha",e.getMessage());
                }finally {
                    Looper.prepare();
                    Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                    UserData.targetOrgLock=false;
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

    public static void getAllOrg
            (final Context context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                UserData.orgList.clear();
                Log.d("haha","go1");
                HttpURLConnection conn=null;
                BufferedReader br=null;
                String registerUrl=UrlHead+"/Organization/displayall";
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
                    Log.d("allusersreq",sb.toString());

                    Looper.prepare();
                    //Toast.makeText(context,"已经提交审核",Toast.LENGTH_SHORT).show();

                    JSONArray jsonArray = new JSONArray(sb.toString());
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject item = jsonArray.getJSONObject(i); // 得到每个对象
                        String name=item.getString("organizationname");
                        String addr= item.getString("addr"); // 获取对象对应的值
                        String phonenum = item.getString("phonenum");
                        double longitude=Double.valueOf(item.getString("longitude"));
                        double latitude=Double.valueOf(item.getString("latitude"));
                        int id =Integer.valueOf(item.getString("organizationid"));
                        UserData.org term=new UserData.org ();
                        term.orgName=name;
                        term.addr=addr;
                        term.phone=phonenum;
                        term.longitude=longitude;
                        term.latitude=latitude;
                        term.id=id;
                        UserData.orgList.add(term);

                    }
                    UserData.lock1=false;
                       /* Intent intent=new Intent(context,LoginActivity.class);
                        context.startActivity(intent);*/
                    Looper.loop();



                    //setContent(sb.toString());
                    Log.d("123","---"+sb.toString());
                } catch (Exception e) {
                    Looper.prepare();
                    Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                    UserData.lock1=false;
                    e.printStackTrace();
                    Log.d("haha",e.getMessage());
                }finally {
                    Looper.prepare();
                    Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                    UserData.lock1=false;
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

    public static void getRecommendOrg
            (final Context context,final int a,final int b,final int c,final int d) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                UserData.recommendOrgList.clear();
                Log.d("haha","go1");
                HttpURLConnection conn=null;
                BufferedReader br=null;
                String registerUrl=UrlHead+"/Organization/recommend?kn95="+a+"&protectiveclothing="+d+"&gasmask="+c+"&mask="+b;
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
                    Log.d("allrecommend",sb.toString());

                    Looper.prepare();
                    //Toast.makeText(context,"已经提交审核",Toast.LENGTH_SHORT).show();

                    JSONArray jsonArray = new JSONArray(sb.toString());
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject item = jsonArray.getJSONObject(i); // 得到每个对象
                        String name=item.getString("organizationname");
                        String addr= item.getString("addr"); // 获取对象对应的值
                        String phonenum = item.getString("phonenum");
                        double longitude=Double.valueOf(item.getString("longitude"));
                        double latitude=Double.valueOf(item.getString("latitude"));
                        int id =Integer.valueOf(item.getString("organizationid"));
                        UserData.org term=new UserData.org ();
                        term.orgName=name;
                        term.addr=addr;
                        term.phone=phonenum;
                        term.longitude=longitude;
                        term.latitude=latitude;
                        term.id=id;
                        UserData.recommendOrgList.add(term);

                    }
                    UserData.recommendOrgLock=false;
                       /* Intent intent=new Intent(context,LoginActivity.class);
                        context.startActivity(intent);*/
                    Looper.loop();



                    //setContent(sb.toString());
                    Log.d("123","---"+sb.toString());
                } catch (Exception e) {
                    Looper.prepare();
                    Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                    UserData.recommendOrgLock=false;
                    e.printStackTrace();
                    Log.d("haha",e.getMessage());
                }finally {
                    Looper.prepare();
                    Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                    UserData.recommendOrgLock=false;
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

    public static void getAllDonation
            (final Context context, final String username, final boolean isAll) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                UserData.donationList.clear();
                Log.d("haha","go1");
                HttpURLConnection conn=null;
                BufferedReader br=null;
                String registerUrl=UrlHead+"/Donation/displayorganization?username="+username;
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
                    Log.d("allgetdona",sb.toString());

                    Looper.prepare();
                    //Toast.makeText(context,"已经提交审核",Toast.LENGTH_SHORT).show();

                    JSONArray jsonArray = new JSONArray(sb.toString());
                    Map<String, Integer> map=new HashMap<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject item = jsonArray.getJSONObject(i); // 得到每个对象
                        UserData.donation term=new UserData.donation ();
                        String expressnumber=item.getString("expressnumber");
                        String donatedname= item.getString("donatedname"); // 获取对象对应的值
                        String itemname=item.getString("itemname");
                       // String donatedname= item.getString("donatedname"); // 获取对象对应的值
                        Integer id=Integer.valueOf(item.getString("donationid"));
                        Integer num=Integer.valueOf(item.getString("num"));
                        Integer isarrived=Integer.valueOf(item.getString("isarrived"));
                        //boolean isarrived=item.getBoolean("isarrived");
                        Log.d("213212", String.valueOf( map.containsKey(id)));
                        if(map.containsKey(expressnumber)){
                            if(isarrived==1&&!isAll)
                                continue;
                            Log.d("2312", String.valueOf(map.size()));
                            for(int j=0;j< UserData.donationList.size();j++){
                                if( ((UserData.donation)UserData.donationList.get(j)).expressnumber.equals(expressnumber) ){
                                    term=(UserData.donation)UserData.donationList.get(j);
                                    term.detail+="物品种类:"+itemname+"  数量:"+num+'\n';
                                    UserData.donationList.set(j,term);
                                   // ((UserData.donation)UserData.donationList.get(j)).detail+="物品种类:"+itemname+"  数量:"+num+'\r';
                                    break;
                                }
                            }
                        }else{
                            if(isarrived==1&&!isAll)
                                continue;
                            term.detail="物品种类:"+itemname+"  数量:"+num+'\n';
                            term.id=id;
                            term.expressnumber=expressnumber;
                            term.name=donatedname;
                            UserData.donationList.add(term);
                            map.put(expressnumber,1);


                        }

                        //UserData.donationList.add(term);
                        /*
                        String name=item.getString("organizationname");
                        String addr= item.getString("addr"); // 获取对象对应的值
                        String phonenum = item.getString("phonenum");
                        double longitude=Double.valueOf(item.getString("longitude"));
                        double latitude=Double.valueOf(item.getString("latitude"));
                        int id =Integer.valueOf(item.getString("organizationid"));
                        UserData.org term=new UserData.org ();
                        term.orgName=name;
                        term.addr=addr;
                        term.phone=phonenum;
                        term.longitude=longitude;
                        term.latitude=latitude;
                        term.id=id;
                        UserData.orgList.add(term);*/

                    }
                    UserData.donationLock=false;
                       /* Intent intent=new Intent(context,LoginActivity.class);
                        context.startActivity(intent);*/
                    Looper.loop();

                    //setContent(sb.toString());
                    Log.d("123","---"+sb.toString());
                } catch (Exception e) {
                    Looper.prepare();
                    Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                    UserData.donationLock=false;
                    e.printStackTrace();
                    Log.d("haha",e.getMessage());
                }finally {
                    Looper.prepare();
                    Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                    UserData.donationLock=false;
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


    public static void confirmlDonation
            (final Context context, final String expressnum) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                UserData.donationList.clear();
                Log.d("haha","go1");
                HttpURLConnection conn=null;
                BufferedReader br=null;
                String registerUrl=UrlHead+"/Donation/confirm?expressnumber="+expressnum;
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
                    Log.d("update_log",sb.toString());
                    if(sb.toString().equals("1")){
                        Looper.prepare();
                        //Toast.makeText(context,"已经提交审核",Toast.LENGTH_SHORT).show();
                       /* Intent intent=new Intent(context,LoginActivity.class);
                        context.startActivity(intent);*/
                        Looper.loop();

                    }
                    else{
                        Looper.prepare();
                        Looper.loop();
                    }
                    //setContent(sb.toString());
                    Log.d("123","---"+sb.toString());
                } catch (Exception e) {
                   Looper.prepare();
                   Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
                   Looper.loop();
                    e.printStackTrace();
                    Log.d("haha",e.getMessage());
                }finally {
                   Looper.prepare();
                   Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
                   Looper.loop();
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


    public static void applyOrg(final Context context, final String username, final String orgname, final double longitude,final double latitude,
                                final String addr,final String license) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("haha","go1");
                HttpURLConnection conn=null;
                BufferedReader br=null;
                String registerUrl=UrlHead+"/Organization/apply?username="+username+"&organizationname="+orgname+"&longitude="+longitude
                        +"&latitude="+latitude+"&addr="+addr+"&license="+license;
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
                    if(sb.toString().equals("1")){
                        Looper.prepare();
                        Toast.makeText(context,"认证成功",Toast.LENGTH_SHORT).show();
                        UserData.orgName=orgname;
                        UserData.orgAddr=addr;

                        Looper.loop();

                    }
                    else{
                        Looper.prepare();
                        Toast.makeText(context,"认证失败",Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }
                    //setContent(sb.toString());
                    Log.d("123","---"+sb.toString());
                } catch (Exception e) {
                    Looper.prepare();
                    Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                    e.printStackTrace();
                    Log.d("haha",e.getMessage());
                }finally {
                    Looper.prepare();
                    Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
                    Looper.loop();
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

    public static void getOrgInfo(final Context context, final String username) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("haha","go1");
                HttpURLConnection conn=null;
                BufferedReader br=null;
                String registerUrl=UrlHead+"/Organization/information?username="+username;
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

                    JSONObject item = new JSONObject(sb.toString());
                    String orgname=item.getString("organizationname");
                    String addr= item.getString("addr"); // 获取对象对应的值
                    String phonenum = item.getString("phonenum");
                    int orgid= Integer.parseInt(item.getString("organizationid"));
                    UserData.phone=phonenum;
                    UserData.orgName=orgname;
                    UserData.orgAddr=addr;
                    UserData.orgId=orgid;
                    Log.d("myinfo",sb.toString());
                    UserData.lock3=false;
                    //setContent(sb.toString());
                    Log.d("123","---"+sb.toString());
                } catch (Exception e) {
                    Looper.prepare();
                    Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                    UserData.lock3=false;
                    e.printStackTrace();
                    Log.d("haha",e.getMessage());
                }finally {
                    Looper.prepare();
                    Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                    UserData.lock3=false;
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

    public static void clearOrgReq(final Context context, final String username) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("haha","go1");
                HttpURLConnection conn=null;
                BufferedReader br=null;
                String registerUrl=UrlHead+"/Required/deleteall?username="+username;
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
                    while((s = br.readLine())!=null) {
                        sb.append(s);
                    }

                    //setContent(sb.toString());
                    Log.d("123","---"+sb.toString());
                } catch (Exception e) {
                    Looper.prepare();
                    Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                    e.printStackTrace();
                    Log.d("haha",e.getMessage());
                }finally {
                    Looper.prepare();
                    Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
                    Looper.loop();
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



    public static void login(final Context context, final String username, final String password, final boolean isauto) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                Log.d("haha","go1");
                HttpURLConnection conn=null;
                BufferedReader br=null;
                String registerUrl=UrlHead+"/User/login?username="+username+"&password="+password;
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
                    if(sb.toString().equals("2")||sb.toString().equals("3")){
                        Looper.prepare();
                        if (isauto) {
                            SharedPreferencesUtil.putBoolean(context.getApplicationContext(), "isauto", true);
                        }
                        Toast.makeText(context,"登陆成功",Toast.LENGTH_SHORT).show();
                        if(sb.toString().equals("2"))
                            UserData.userLevel=1;
                        if(sb.toString().equals("3"))
                            UserData.userLevel=2;
                        UserData.username=username;
                        UserData.lock1=true;
                        UserData.lock2=true;
                        UserData.lock3=true;
                        HttpHandler.getOrgInfo(context,UserData.username);
                        HttpHandler.getReq(context,UserData.username);
                        HttpHandler.getAllOrg(context);
                        Intent intent=new Intent(context, MainActivity.class);
                        context.startActivity(intent);
                        Looper.loop();
                        UserData.targetOrgLock=true;
                        UserData.donationLock=true;

                    }
                    else{
                        Looper.prepare();
                        Toast.makeText(context,"登陆失败",Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }
                    //setContent(sb.toString());
                    Log.d("123","---"+sb.toString());
                } catch (Exception e) {
                    Looper.prepare();
                    Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                    e.printStackTrace();
                    Log.d("haha",e.getMessage());
                }finally {
                    Looper.prepare();
                    Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
                    Looper.loop();
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
