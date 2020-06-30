package com.example.frontend.HelpFunctions;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.frontend.R;

import java.util.List;

public class RequestItem extends RecyclerView.Adapter<RequestItem.ViewHolderA> {
    private Context mContext;
    private List<UserData.item> mList;
    public RequestItem(Context context, List<UserData.item> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public ViewHolderA onCreateViewHolder(ViewGroup parent, int viewType) {
        //此处动态加载ViewHolder的布局文件并返回holder
        View view = LayoutInflater.from(mContext).inflate(R.layout.request_item2, parent, false);
        ViewHolderA holderA = new ViewHolderA(view);
        return holderA;
    }

    @Override
    public void onBindViewHolder(final ViewHolderA holder, final int position) {
        //此处设置Item中view的数据
        holder.mTextView.setText("物品名称 "+mList.get(position).name);
        holder.mTextView2.setText("还需数量 "+mList.get(position).need);
        holder.mTextView3.setText("你要捐赠的数量"+0);
        holder.imageEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //定义一个自定义对话框
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

                builder.setTitle("输入要捐赠的数量");//设置标题

                View view2 = LayoutInflater.from(mContext).inflate(R.layout.donate_dialog,null);//获得布局信息



                final EditText num= (EditText) view2.findViewById(R.id.donatenum);

                builder.setView(view2);//给对话框设置布局

                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override

                    public void onClick(DialogInterface dialogInterface, int i) {

                        //点击确定按钮的操作
                        UserData.item term= (UserData.item)UserData.targetItemList.get(position);
                        if(num.getText()!=null&&!num.getText().toString().isEmpty())
                        term.donate=Integer.valueOf(num.getText().toString());
                        holder.mTextView3.setText("你要捐赠的数量"+num.getText());
                        //通知适配器item内容插入

                    }

                });


                builder.show();

            }
        });



    }

    @Override
    public int getItemCount() {
        //生成的item的数量
        return mList.size();
    }

    public void notifyChange(){

        notifyDataSetChanged();
    }
    //Item的ViewHolder以及item内部布局控件的id绑定
    class ViewHolderA extends RecyclerView.ViewHolder{
        TextView mTextView;
        TextView mTextView2;
        TextView mTextView3;
        ImageView imageEdit;

        public ViewHolderA(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.ri2_name);
            mTextView2 = (TextView) itemView.findViewById(R.id.ri2_need_num);
            mTextView3 = (TextView) itemView.findViewById(R.id.ri2_give_num);
            imageEdit=(ImageView) itemView.findViewById(R.id.ri2_edit);

        }
    }
}

