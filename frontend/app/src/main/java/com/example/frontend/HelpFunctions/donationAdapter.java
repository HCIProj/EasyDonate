package com.example.frontend.HelpFunctions;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.frontend.R;

import java.util.List;

public class donationAdapter extends RecyclerView.Adapter<donationAdapter.ViewHolderA> {
    private Context mContext;
    private List<String> mList;
    public donationAdapter(Context context, List<String> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public ViewHolderA onCreateViewHolder(ViewGroup parent, int viewType) {
        //此处动态加载ViewHolder的布局文件并返回holder
        View view = LayoutInflater.from(mContext).inflate(R.layout.donation_item, parent, false);
        ViewHolderA holderA = new ViewHolderA(view);
        return holderA;
    }



    @Override
    public void onBindViewHolder(final donationAdapter .ViewHolderA holder, final int position) {
        //此处设置Item中view的数据
        /*holder. itemName.setText("物品名称 "+mList.get(position));
        holder.itemImage.setBackgroundResource(UserData.getBackground(position+1));*/
        //holder.mTextView3.setText("已收数量 "+mList.get(position).get);
        final UserData.donation term= (UserData.donation)UserData.donationList.get(position);
        holder.expressnum.setText("快递单号:  "+term.expressnumber);
        holder.donator.setText("捐赠者:  "+term.name);
        holder.detailinfo.setText(term.detail);
        holder.showmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.detail.getVisibility()==View.GONE)
                     holder.detail.setVisibility(View.VISIBLE);
                else
                    holder.detail.setVisibility(View.GONE);
            }
        });

        holder.report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

                builder.setTitle("输入异常信息");//设置标题

                View view2 = LayoutInflater.from(mContext).inflate(R.layout.my_dialog_2,null);//获得布局信息

                final EditText name = (EditText) view2.findViewById(R.id.secret);

               // final EditText num= (EditText) view2.findViewById(R.id.confirmSecret);

                builder.setView(view2);//给对话框设置布局

                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override

                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(mContext, "信息已经汇报", Toast.LENGTH_SHORT).show();

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

        holder.confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("是否收到货物并确认无误?").setIcon(android.R.drawable.ic_dialog_info);
                builder.setNegativeButton("取消",  new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(mContext, "如果有误,点击报告异常以汇报情况", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(mContext, "确认收到", Toast.LENGTH_SHORT).show();
                        HttpHandler.confirmlDonation(mContext,term.expressnumber);
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

        TextView showmore;
        TextView confirm;
        TextView report;
        TextView donator;
        TextView expressnum;
        LinearLayout detail;
        TextView detailinfo;
        //ImageView showmoreImg;

        public ViewHolderA(View itemView) {
            super(itemView);

            donator=(TextView) itemView.findViewById(R.id.donator);
            confirm=(TextView) itemView.findViewById(R.id.confirm);
            report=(TextView) itemView.findViewById(R.id.report);
            expressnum=(TextView) itemView.findViewById(R.id.expressnum);
            showmore = (TextView) itemView.findViewById(R.id.fg_showmore);
            detail=itemView.findViewById(R.id.detail);
            detailinfo=(TextView) itemView.findViewById(R.id.detailinfo);
            //showmore.setText();
            //showmoreImg=(ImageView) itemView.findViewById(R.id.fg_showmore_img);
        }
    }
}
