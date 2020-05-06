package com.example.frontend.HelpFunctions;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.frontend.R;

import java.util.List;

public class RequestItemAdapter extends RecyclerView.Adapter<RequestItemAdapter.ViewHolderA> {
    private Context mContext;
    private List<String> mList;
    public RequestItemAdapter(Context context, List<String> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public ViewHolderA onCreateViewHolder(ViewGroup parent, int viewType) {
        //此处动态加载ViewHolder的布局文件并返回holder
        View view = LayoutInflater.from(mContext).inflate(R.layout.request_item, parent, false);
        ViewHolderA holderA = new ViewHolderA(view);
        return holderA;
    }

    @Override
    public void onBindViewHolder(ViewHolderA holder, final int position) {
        //此处设置Item中view的数据
        holder.mTextView.setText("物品名称 "+mList.get(position));
        holder.mTextView2.setText("需要数量 "+mList.get(position));
        holder.mTextView3.setText("已收数量 "+mList.get(position));
       /* holder.mTextView.setBackgroundResource(ElfSourceController.getBackground(Integer.valueOf(mList.get(position))));
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(UserData.getOnlyHave()) {
                    Intent intent = new Intent(mContext, ElfDetailsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("variety", Integer.valueOf(UserData.getElfDetails().get(position).get("typeID").toString()));
                    intent.putExtras(bundle);
                    mContext.startActivity(intent);
                }
                else{
                    Intent intent = new Intent(mContext, ElfDetailsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("variety", position+1);
                    intent.putExtras(bundle);
                    mContext.startActivity(intent);
                }
            }
        });*/
    }

    @Override
    public int getItemCount() {
        //生成的item的数量
        return mList.size();
    }
    public void addData(String content){
        mList.add(1, content);
        //通知适配器item内容插入
        notifyItemInserted(1);
    }
    //Item的ViewHolder以及item内部布局控件的id绑定
    class ViewHolderA extends RecyclerView.ViewHolder{
        TextView mTextView;
        TextView mTextView2;
        TextView mTextView3;
        public ViewHolderA(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.item_name);
            mTextView2 = (TextView) itemView.findViewById(R.id.item_need_num);
            mTextView3 = (TextView) itemView.findViewById(R.id.item_get_num);
        }
    }
}

