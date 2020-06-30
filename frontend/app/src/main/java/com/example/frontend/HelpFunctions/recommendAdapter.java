package com.example.frontend.HelpFunctions;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.frontend.DonateActivity;
import com.example.frontend.R;

import java.util.List;

public class recommendAdapter  extends RecyclerView.Adapter<recommendAdapter.ViewHolderA> {
        private Context mContext;
        private List<UserData.org> mList;
        public recommendAdapter (Context context, List<UserData.org> list) {
            mContext = context;
            mList = list;
        }

        @Override
        public recommendAdapter.ViewHolderA onCreateViewHolder(ViewGroup parent, int viewType) {
            //此处动态加载ViewHolder的布局文件并返回holder
            View view = LayoutInflater.from(mContext).inflate(R.layout.org_item, parent, false);
            recommendAdapter.ViewHolderA holderA = new recommendAdapter.ViewHolderA(view);
            return holderA;
        }



        @Override
        public void onBindViewHolder(final recommendAdapter .ViewHolderA holder, final int position) {
            //此处设置Item中view的数据
        /*holder. itemName.setText("物品名称 "+mList.get(position));
        holder.itemImage.setBackgroundResource(UserData.getBackground(position+1));*/
            //holder.mTextView3.setText("已收数量 "+mList.get(position).get);
            final UserData.org term= (UserData.org)mList.get(position);
            UserData.targetOrg=term;
            holder.orgname.setText("机构名:  "+term.orgName);
            holder.orgaddr.setText("机构地址:  "+term.addr);
            holder.orgname.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UserData.targetOrgLock=true;
                    Log.d("allreq","ss");
                    int orgid=term.id;
                    if(orgid==UserData.orgId) {
                        Toast.makeText(mContext, "不能给自己捐赠", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Log.d("orgid", String.valueOf(orgid));
                    //HttpHandler.getReq(getActivity(),UserData.username);
                    //marker.setSnippet();
                    HttpHandler.getTargetReq(mContext,orgid);
                    while(UserData.targetOrgLock)
                    {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
               /* mWeiboDialog = WeiboDialogUtils.createLoadingDialog(getActivity(), "加载中");
                mHandler.sendEmptyMessageDelayed(1, 2000);*/
                    if(orgid==-1)
                        return;
                    Intent intent=new Intent(mContext, DonateActivity.class);

                    mContext.startActivity(intent);
                    return ;
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

    TextView orgname;
    TextView orgaddr;


    public ViewHolderA(View itemView) {
        super(itemView);

        orgname=(TextView) itemView.findViewById(R.id.org);
        orgaddr=(TextView) itemView.findViewById(R.id.orgaddr);
    }
}
}
