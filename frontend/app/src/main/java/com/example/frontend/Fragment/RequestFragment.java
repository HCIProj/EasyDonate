package com.example.frontend.Fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.frontend.HelpFunctions.RequestItemAdapter;
import com.example.frontend.HelpFunctions.UserData;
import com.example.frontend.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.widget.Toast;

public class RequestFragment extends Fragment implements OnInitListener {
   // private Switch switchModel;
    private List<String> list;
    RecyclerView mRecyclerView;
    private TextToSpeech tts;
    ImageView add;
    public RequestFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.request_fg_content,container,false);
        tts = new TextToSpeech(getActivity(), this);
        list = new ArrayList<>();
        list.add("test");
        mRecyclerView = (RecyclerView) view.findViewById(R.id.elf_fg_recycle_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
       // initData();
        //实例化并传输数据给adapter
        final RequestItemAdapter adapter = new  RequestItemAdapter(getActivity(), UserData.itemList);
        mRecyclerView.setAdapter(adapter);
       // refresh( mRecyclerView);


        LinearLayout linearLayout=view.findViewById(R.id.fg_elf_topbar);
        linearLayout.setPadding(0,getStatusBarHeight(),0,0);

        add=view.findViewById(R.id.button_add);
        //用户点击头像设置头像
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 选择图片
                adapter.addData("test!!!!!!!!!");
                tts.speak("需要转化的文字", TextToSpeech.QUEUE_FLUSH, null);
            }
        });
        return view;
    }

   /* private void refresh( RecyclerView mRecyclerView){
        HttpHandler.getElfs(getActivity(),UserData.getUserName());
        if(!UserData.getOnlyHave())
            return;
        ElfRecycleViewAdapter adapter = new ElfRecycleViewAdapter(getActivity(),UserData.getElfList());
        mRecyclerView.setAdapter(adapter);
    }

    private void refresh(String userName){
        HttpHandler.getElfs(UserData.getUserName());
        ElfRecycleViewAdapter adapter = new ElfRecycleViewAdapter(getActivity(),UserData.getElfList());
        mRecyclerView.setAdapter(adapter);
    }*/


    @Override
    public void onHiddenChanged(boolean hidden){
        super.onHiddenChanged(hidden);
        if (!hidden) {
            //refresh( mRecyclerView);
        }
    }

    private int getStatusBarHeight() {
        Resources resources = getActivity().getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen","android");
        int height = resources.getDimensionPixelSize(resourceId);
        Log.v("dbw", "Status height:" + height);
        return height;
    }

    @Override
    public void onInit(int status){
        // 判断是否转化成功
        if (status == TextToSpeech.SUCCESS){
            //默认设定语言为中文，原生的android貌似不支持中文。
            int result = tts.setLanguage(Locale.CHINESE);
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Toast.makeText(getActivity(), "aaa", Toast.LENGTH_SHORT).show();
            }else{
                //不支持中文就将语言设置为英文
                tts.setLanguage(Locale.US);
            }
        }
    }
}
