package com.example.frontend.HelpFunctions;

import java.util.ArrayList;
import java.util.List;

public class UserData {
    public static class item{
        public String name;
        public int need;
        public int get;
    }
    public static int checknum;
    public static int userLevel;
    public static long lastGetTime=0;
    public static String username;
    public static String phone;
    public static String orgName;
    public static String orgAddr;
    public static List itemList=new ArrayList<item>();
}
