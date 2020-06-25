package com.example.frontend.HelpFunctions;

import android.util.Log;

import com.example.frontend.R;

import java.util.ArrayList;
import java.util.List;

public class UserData {
    public static class item{
        public String name;
        public int need;
        public int get;
        public int id;
        public int donate=0;
    }

    public static class donation{
        public String name;
       // public String itemname;
        public String expressnumber;
        //public int num;
        public int id;
        public String detail;
    }
    public static boolean donationLock;
    public static List donationList=new ArrayList<donation>();
    public static class org{
        public String orgName;
        public String addr;
        public double longitude;
        public double latitude;
        public String phone;
        public int id;
    }

    public static int checknum;
    public static int userLevel;
    public static long lastGetTime=0;
    public static String username;
    public static String phone;
    public static String orgName;
    public static String orgAddr;
    public static int orgId;
    public static double longitude;
    public static double latitude;
    public static void setLongAndLati(double a,double b){
        longitude=a;
        latitude=b;
    }
    public static List itemList=new ArrayList<item>();

    public static List orgList=new ArrayList<org>();
    public static List usualItem=new ArrayList<String>();
    public static void init(){
        usualItem.clear();
        usualItem.add("kn95 口罩");
        usualItem.add("医护口罩");
        usualItem.add("防毒面具");
        usualItem.add("防护服");
    }

    public static int getBackground(int id){
        switch (id){
            case 1:
                return R.drawable.item1;
            case 2:
                return R.drawable.item2;
            case 3:
                return R.drawable.item3;
            case 4:
                return R.drawable.item4;
            case 5:
                return R.drawable.item1;
            default:
                return R.drawable.item1;
        }
    }
    public static org targetOrg;
    public static boolean targetOrgLock;
    public static List targetItemList=new ArrayList<item>();
    public static int getOrgId(double longi,double lati){
        Log.d("orgid", String.valueOf(longi));
        for(int i=0;i< UserData.orgList.size();i++) {

            if( (((UserData.org)UserData.orgList.get(i)).latitude)==lati && (((org)UserData.orgList.get(i)).longitude)==longi) {
                targetOrg=(UserData.org)UserData.orgList.get(i);
                return ((org) UserData.orgList.get(i)).id;
            }
        }
        return -1;
    }

}
