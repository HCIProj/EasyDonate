package com.example.frontend.HelpFunctions;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.frontend.R;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolderA> {
    private Context mContext;
    private List<String> mList;
    public ItemAdapter(Context context, List<String> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public ViewHolderA onCreateViewHolder(ViewGroup parent, int viewType) {
        //此处动态加载ViewHolder的布局文件并返回holder
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycleview_item, parent, false);
        ViewHolderA holderA = new ViewHolderA(view);
        return holderA;
    }

    @Override
    public void onBindViewHolder(ViewHolderA holder, final int position) {
        //此处设置Item中view的数据
        holder. itemName.setText("物品名称 "+mList.get(position));
        holder.itemImage.setBackgroundResource(UserData.getBackground(position+1));
        //holder.mTextView3.setText("已收数量 "+mList.get(position).get);

        holder.itemImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserData.item term=new UserData.item();
                term.id=-1;
                term.name=mList.get(position);
                term.need=0;
                term.get=0;
                UserData.itemList.add(term);
                Toast.makeText(mContext, term.name+"添加成功", Toast.LENGTH_SHORT).show();


            }
        });
        holder.itemName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserData.item term=new UserData.item();
                term.id=-1;
                term.name=mList.get(position);
                term.need=0;
                term.get=0;
                Toast.makeText(mContext, term.name+"添加成功", Toast.LENGTH_SHORT).show();

            }
        });


    }

    @Override
    public int getItemCount() {
        //生成的item的数量
        return mList.size();
    }
   /* public void addData(String content){
        UserData.item term=new UserData.item() ;
        term.name="未设置";
        term.need=0;
        term.get=0;
        term.id=-1;
        UserData.itemList.add(term);
        //mList.add( term);
        //通知适配器item内容插入
        notifyItemInserted(mList.size());
    }*/
    //Item的ViewHolder以及item内部布局控件的id绑定
    class ViewHolderA extends RecyclerView.ViewHolder{

        TextView itemName;
        ImageView itemImage;

        public ViewHolderA(View itemView) {
            super(itemView);
            itemName = (TextView) itemView.findViewById(R.id.fg_name);
            itemImage=(ImageView) itemView.findViewById(R.id.fg_cover);
        }
    }
}

