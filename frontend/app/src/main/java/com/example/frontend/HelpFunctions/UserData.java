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
    public static List itemList=new ArrayList<item>();
}
