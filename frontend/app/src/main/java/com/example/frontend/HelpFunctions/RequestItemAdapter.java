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

public class RequestItemAdapter extends RecyclerView.Adapter<RequestItemAdapter.ViewHolderA> {
    private Context mContext;
    private List<UserData.item> mList;
    public RequestItemAdapter(Context context, List<UserData.item> list) {
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
        holder.mTextView.setText("物品名称 "+mList.get(position).name);
        holder.mTextView2.setText("还需数量 "+mList.get(position).need);
        //holder.mTextView3.setText("已收数量 "+mList.get(position).get);
        holder.imageEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //定义一个自定义对话框
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

                builder.setTitle("输入信息");//设置标题

                View view2 = LayoutInflater.from(mContext).inflate(R.layout.my_dialog,null);//获得布局信息

                final EditText name = (EditText) view2.findViewById(R.id.secret);

                final EditText num= (EditText) view2.findViewById(R.id.confirmSecret);

                builder.setView(view2);//给对话框设置布局

                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override

                    public void onClick(DialogInterface dialogInterface, int i) {

                        //点击确定按钮的操作
                        UserData.item term= (UserData.item)UserData.itemList.get(position);
                        if(!name.getText().toString().isEmpty())
                        term.name=name.getText().toString();
                        if(!num.getText().toString().isEmpty())
                        term.need=Integer.valueOf(num.getText().toString());
                        else
                            term.need=0;
                        UserData.itemList.set(position,term);
                        mList.set(position,term);
                        //通知适配器item内容插入
                        notifyItemChanged(position);
                    }

                });

                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }

                });

                builder.show();

            }
        });

        holder.imageDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("删除将是不可逆的,确认?").setIcon(android.R.drawable.ic_dialog_info)
                        .setNegativeButton("取消", null);
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        HttpHandler.deleteReq(mContext,((UserData.item)UserData.itemList.get(position)).id);
                        UserData.itemList.remove(position);

                        //mList.remove(position);
                        notifyItemRemoved(position);

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
    public void addData(String content){
        UserData.item term=new UserData.item() ;
        term.name="未设置";
        term.need=0;
        term.get=0;
        term.id=-1;
        UserData.itemList.add(term);
        //mList.add( term);
        //通知适配器item内容插入
        notifyItemInserted(mList.size());
    }
    public void notifyChange(){

        notifyDataSetChanged();
    }
    //Item的ViewHolder以及item内部布局控件的id绑定
    class ViewHolderA extends RecyclerView.ViewHolder{
        TextView mTextView;
        TextView mTextView2;

        ImageView imageEdit;
        ImageView imageDelete;

        public ViewHolderA(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.item_name);
            mTextView2 = (TextView) itemView.findViewById(R.id.item_need_num);

            imageEdit=(ImageView) itemView.findViewById(R.id.imageView);
            imageDelete=(ImageView) itemView.findViewById(R.id.imageView2);
        }
    }
}

