package com.example.frontend.HelpFunctions;

import java.util.ArrayList;
import java.util.List;

public class UserData {
    public static class item{
        String name;
        int need;
        int get;
    }
    public static int checknum;
    public static int userLevel;
    public static long lastGetTime=0;
    public static List itemList=new ArrayList<item>();
}
