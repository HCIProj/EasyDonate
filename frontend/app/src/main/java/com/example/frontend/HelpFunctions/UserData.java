package com.example.frontend.HelpFunctions;

import com.example.frontend.R;

import java.util.ArrayList;
import java.util.List;

public class UserData {
    public static class item{
        public String name;
        public int need;
        public int get;
        public int id;
    }
    public static int checknum;
    public static int userLevel;
    public static long lastGetTime=0;
    public static String username;
    public static String phone;
    public static String orgName;
    public static String orgAddr;
    public static List itemList=new ArrayList<item>();
    public static List usualItem=new ArrayList<String>();
    public static void init(){
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
}
