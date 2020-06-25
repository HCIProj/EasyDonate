package com.example.frontend.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.frontend.DonateActivity;
import com.example.frontend.DonationInfoActivity;
import com.example.frontend.HelpFunctions.HttpHandler;
import com.example.frontend.HelpFunctions.UserData;
import com.example.frontend.HelpFunctions.WeiboDialogUtils;
import com.example.frontend.LoginActivity;
import com.example.frontend.MapActivity;
import com.example.frontend.R;
import com.example.frontend.RegisterActivity;
import com.example.frontend.UploadActivity;

import java.text.DecimalFormat;



import static android.app.Activity.RESULT_OK;

/**
 * Created by Jay on 2015/8/28 0028.
 */
public class UserFragment extends Fragment {

    private LinearLayout checkfriend;
    private LinearLayout checkneighbour;
    private LinearLayout checkrecord;
    private LinearLayout checkBBS;
    private LinearLayout contactus;
    private LinearLayout loginset;
    private LinearLayout donateGet;

    private TextView isOrg;
    private TextView mileage;
    private TextView editInfo;
    private TextView upload;
    private ImageView elfImage;
    private ImageView myCover;
    private TextView username;
    private int elfId=-1;
    private TextView orgName;
    private TextView orgAddr;
    private TextView chargePerson;
    private TextView chargePersonPhone;
    private Dialog mWeiboDialog;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    WeiboDialogUtils.closeDialog(mWeiboDialog);
                    break;
            }
        }
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_content, container, false);
        /*checkfriend=(LinearLayout) view.findViewById(R.id.fg_layout_check_friend);
        checkneighbour=(LinearLayout) view.findViewById(R.id.fg_layout_check_neighbour);
        checkrecord=(LinearLayout) view.findViewById(R.id.fg_layout_check_record);
        checkBBS=(LinearLayout) view.findViewById(R.id.fg_layout_check_bbs);
*/
        myCover = view.findViewById(R.id.fg_cover);

        isOrg = (TextView) view.findViewById(R.id.fg_achieve);
        orgName = (TextView) view.findViewById(R.id.fg_elfname);
        //elfname.setText(ElfSourceController.getName(typeID,grade));
        orgAddr = (TextView) view.findViewById(R.id.fg_elflevel);
        //level.setText("lv."+(exp/100+1));
        chargePerson = (TextView) view.findViewById(R.id.fg_charge_person);
        chargePersonPhone = (TextView) view.findViewById(R.id.fg_fightpoint);
        orgName.setText("机构名  " + UserData.orgName);
        orgAddr.setText("地址    " + UserData.orgAddr);
        chargePerson.setText("负责人  " + UserData.username);
        chargePersonPhone.setText("电话    " + UserData.phone);
        if (UserData.userLevel == 2){
            isOrg.setText("已认证");
            isOrg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //定义一个自定义对话框
                    Toast.makeText(getActivity(), "你已认证过了,点击'点击修改'以修改信息", Toast.LENGTH_SHORT).show();

                }
            });

        }
        else {
            isOrg.setText("点击认证");
            isOrg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //定义一个自定义对话框
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                    builder.setTitle("输入信息");//设置标题

                    View view2 = LayoutInflater.from(getActivity()).inflate(R.layout.org_dialog,null);//获得布局信息

                    final EditText orgName2 = (EditText) view2.findViewById(R.id.org_name);

                    final EditText orgAddr2= (EditText) view2.findViewById(R.id.org_addr);

                    final ImageView getAddr2=(ImageView)view2.findViewById(R.id.org_get_addr);

                    final EditText chargePerson2= (EditText) view2.findViewById(R.id.charge_person);

                    final EditText phone= (EditText) view2.findViewById(R.id.charge_person_phone);
                    getAddr2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent=new Intent(getActivity(), MapActivity.class);
                            startActivity(intent);
                        }

                    });
                    builder.setView(view2);//给对话框设置布局

                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                        @Override

                        public void onClick(DialogInterface dialogInterface, int i) {


                            HttpHandler.applyOrg(getActivity(),UserData.username,orgName2.getText().toString(),UserData.longitude,UserData.latitude,
                                    orgAddr2.getText().toString(),"null");
                            orgName.setText("机构名  "+orgName2.getText().toString());
                            orgAddr.setText("地址    "+orgAddr2.getText().toString());
                            chargePerson.setText("负责人  "+chargePerson2.getText().toString());
                            chargePersonPhone.setText("电话    "+phone.getText().toString());
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
        }

        mileage=(TextView)view.findViewById(R.id.fg_score);
        editInfo=(TextView)view.findViewById(R.id.fg_goal);
        editInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //定义一个自定义对话框
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                builder.setTitle("输入信息");//设置标题

                View view2 = LayoutInflater.from(getActivity()).inflate(R.layout.org_dialog,null);//获得布局信息

                final EditText orgName2 = (EditText) view2.findViewById(R.id.org_name);

                final EditText orgAddr2= (EditText) view2.findViewById(R.id.org_addr);

                final ImageView getAddr2=(ImageView)view2.findViewById(R.id.org_get_addr);

                final EditText chargePerson2= (EditText) view2.findViewById(R.id.charge_person);

                final EditText phone= (EditText) view2.findViewById(R.id.charge_person_phone);
                getAddr2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(getActivity(), MapActivity.class);
                        startActivity(intent);
                    }

                });
                builder.setView(view2);//给对话框设置布局

                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override

                    public void onClick(DialogInterface dialogInterface, int i) {


                        HttpHandler.applyOrg(getActivity(),UserData.username,orgName2.getText().toString(),UserData.longitude,UserData.latitude,
                                orgAddr2.getText().toString(),"null");
                        orgName.setText("机构名  "+orgName2.getText().toString());
                        orgAddr.setText("地址    "+orgAddr2.getText().toString());
                        chargePerson.setText("负责人  "+chargePerson2.getText().toString());
                        chargePersonPhone.setText("电话    "+phone.getText().toString());
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
       upload=(TextView)view.findViewById(R.id.fg_upload);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //定义一个自定义对话框

                Intent intent=new Intent(getActivity(), UploadActivity.class);

                getActivity().startActivity(intent);
            }
        });

        DecimalFormat format=new DecimalFormat("#0.00");
        /*distance.setText(""+format.format(UserData.distance/1000)+"公里");
        mileage.setText(""+format.format(UserData.getMileage()/1000)+"公里");
        mileageGoal.setText(""+format.format(UserData.getMileageGoal()/1000)+"公里");*/


        loginset=view.findViewById(R.id.fg_my_login_setup);
        contactus=view.findViewById(R.id.fg_my_contact_us);
        donateGet=view.findViewById(R.id.fg_get_donate);
        username=(TextView)view.findViewById(R.id.fg_username);
        username.setText(UserData.username);

        //fightPoint.setText(""+ElfSourceController.getPower(typeID,exp/100+1,grade));
        //elfImage=view.findViewById(R.id.fg_elf);
        //elfImage.setBackgroundResource(ElfSourceController.getBackgroundWithLevel(typeID,grade));
        //myExp.setText(UserData.getExp()+"");
        /*if(UserData.getCoverImmediately(UserData.getUserName())==null) {
            myCover.setBackgroundResource(R.drawable.pikachu);
        }
        else{
            myCover.setImageBitmap(UserData.getCoverImmediately(UserData.getUserName()));
        }*/
        loginset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
        contactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog dialog;
                dialog=new AlertDialog.Builder(getActivity()).setTitle("github地址")
                        .setMessage("https://github.com/HCIProject/EasyDonate")
                        .setNegativeButton("返回",null).create();
                dialog.show();
            }
        });
        donateGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserData.targetOrgLock=true;
                HttpHandler.getAllDonation(getActivity(),UserData.username);
                while(UserData.targetOrgLock)
                    Log.d("donation", "111") ;
                Intent intent=new Intent(getActivity(), DonationInfoActivity.class);
                startActivity(intent);
            }
        });
        /*
        //用户点击头像设置头像
        myCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 选择图片
                Intent intent = new Intent(Intent.ACTION_PICK, null);
                intent.setType("image/*");
                startActivityForResult(intent, 0x1);
            }
        });
        checkfriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), FriendPageActivity.class);
                startActivity(intent);
            }
        });

        checkneighbour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getActivity(), CheckNeighbour.class);
                startActivity(intent);
            }
        });

        checkrecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), RecordActivity.class);
                startActivity(intent);
            }
        });

        checkBBS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mWeiboDialog = WeiboDialogUtils.createLoadingDialog(getActivity(), "加载动态中");
                mHandler.sendEmptyMessageDelayed(1, 1000);
                UserData.isNewTimeInit=false;
                UserData.isAllMomentsGet=false;
                UserData.setOldForumTime(-1);
                Intent intent=new Intent(getActivity(), SquareActivity.class);
                startActivity(intent);
            }
        });*/

        LinearLayout linearLayout=view.findViewById(R.id.fg_title);
        linearLayout.setPadding(0,getStatusBarHeight(),0,0);
        return view;
    }


    private int getStatusBarHeight() {
        Resources resources = getActivity().getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen","android");
        int height = resources.getDimensionPixelSize(resourceId);
        Log.v("dbw", "Status height:" + height);
        return height;
    }
/*
    @Override
    public void onHiddenChanged(boolean hidden){
        super.onHiddenChanged(hidden);
        if (!hidden) {
            DecimalFormat format=new DecimalFormat("#0.00");
            distance.setText(""+format.format(UserData.distance/1000)+"公里");
            mileage.setText(""+format.format(UserData.getMileage()/1000)+"公里");
            mileageGoal.setText(""+format.format(UserData.getMileageGoal()/1000)+"公里");
            int typeID = -1;
            int grade = -1;
            int exp = -1;
            if(!UserData.getElfWithId((int)UserData.getUserInfo().get("pet")).isEmpty()) {
                typeID = (int) (UserData.getElfWithId((int) UserData.getUserInfo().get("pet")).get("typeID"));
                grade = (int) UserData.getElfWithId(typeID).get("grade");
                exp = (int) UserData.getElfWithId(typeID).get("exp");
            }
            username.setText(UserData.getUserName());
            elfname.setText(ElfSourceController.getName(typeID,grade));
            level.setText("lv."+(exp/100+1));
            fightPoint.setText(""+ElfSourceController.getPower(typeID,exp/100+1,grade));
            elfImage.setBackgroundResource(ElfSourceController.getBackgroundWithLevel(typeID,grade));
            myExp.setText(UserData.getExp()+"");
            if(UserData.getCoverImmediately(UserData.getUserName())==null)
                myCover.setBackgroundResource(R.drawable.pikachu);
            else {
                myCover.setImageBitmap(UserData.getCoverImmediately(UserData.getUserName()));
            }
        }
    }*/

    // 响应startActivityForResult，获取图片路径
    /*@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == 0x1 && resultCode == RESULT_OK) {
            if (data != null) {
                Bitmap bm;
                ContentResolver resolver = getActivity().getContentResolver();
                try {
                    Uri uri = data.getData();
                    // 这里开始的第二部分，获取图片的路径：
                    String[] proj = { MediaStore.Images.Media.DATA };
                    Cursor cursor = getActivity().managedQuery(uri, proj, null, null, null);
                    // 按我个人理解 这个是获得用户选择的图片的索引值
                    int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    cursor.moveToFirst();
                    // 最后根据索引值获取图片路径
                    String photoPath = cursor.getString(column_index);
                    Bitmap term=BitmapFactory.decodeFile(photoPath);
                    Bitmap bp=BitmapUtils.BitmapCompress(term);
                    UserData.setCover(BitmapUtils.bitmapToBase64(bp));
                    HttpHandler.changeCover(UserData.getUserName(),BitmapUtils.bitmapToBase64(bp));
                    myCover.setImageBitmap(bp);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }*/


}
