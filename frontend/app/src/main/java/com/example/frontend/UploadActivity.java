package com.example.frontend;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.frontend.HelpFunctions.AddPictureAdapter;
import com.example.frontend.HelpFunctions.BitmapUtils;


public class UploadActivity extends Activity {
	private List<Bitmap> data = new ArrayList<Bitmap>();
	private List<String> jsonData=new ArrayList<>();
	private GridView mGridView;
	private String photoPath;
	private AddPictureAdapter addPictureAdapter;
    private TextView content;
    private Button send;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_share);
       // 设置默认图片为加号
		Bitmap bp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_addpic);
		data.add(bp);
		//找到内容控件
		content=findViewById(R.id.act_share_content_et);
		// 找到控件ID
		mGridView = (GridView) findViewById(R.id.act_share_gridView1);
		// 绑定Adapter
		addPictureAdapter = new AddPictureAdapter(getApplicationContext(), data, mGridView);
		mGridView.setAdapter(addPictureAdapter);
		// 设置点击监听事件
		mGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if (data.size() ==10) {
					Toast.makeText(UploadActivity.this, "只能添加九张照片", Toast.LENGTH_SHORT).show();
				} else {
					if (position == data.size() - 1) {
						Toast.makeText(UploadActivity.this, "添加图片", Toast.LENGTH_SHORT).show();
						// 选择图片
						Intent intent = new Intent(Intent.ACTION_PICK, null);
						intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
						startActivityForResult(intent, 0x1);
					} else {
						Toast.makeText(UploadActivity.this, "点击第" + (position + 1) + " 号图片", Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
		// 设置长按事件
		mGridView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				dialog(position);
				return true;
			}
		});
		send=findViewById(R.id.act_share_send_btn);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	if(content.getText().toString()== null ||content.getText().toString().replace(" ", "").equals("")&&jsonData.isEmpty()){
					Toast.makeText(getApplicationContext(), "不能上传空的信息!", Toast.LENGTH_SHORT).show();
            		return;
				}
				Toast.makeText(getApplicationContext(), "资料已经上传进行审核", Toast.LENGTH_SHORT).show();
                //long time =System.currentTimeMillis();
                //HttpHandler.postPic(jsonData,time, UserData.getUserName(),content.getText().toString());
                finish();
            }
        });
	}

	/*
	 * Dialog对话框提示用户删除操作 position为删除图片位置
	 */
	protected void dialog(final int position) {
		if(position!=data.size()-1) {
			Builder builder = new Builder(UploadActivity.this);
			builder.setMessage("确认移除已添加图片吗？");
			builder.setTitle("提示");
			builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
					jsonData.remove(position);
					data.remove(position);
					addPictureAdapter.notifyDataSetChanged();
				}
			});
			builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});
			builder.create().show();
		}
	}

	// 响应startActivityForResult，获取图片路径
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 0x1 && resultCode == RESULT_OK) {
			if (data != null) {

				ContentResolver resolver = getContentResolver();
				try {
					Uri uri = data.getData();
					// 这里开始的第二部分，获取图片的路径：
					String[] proj = { MediaStore.Images.Media.DATA };
					Cursor cursor = managedQuery(uri, proj, null, null, null);
					// 按我个人理解 这个是获得用户选择的图片的索引值
					int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
					cursor.moveToFirst();
					// 最后根据索引值获取图片路径
					photoPath = cursor.getString(column_index);
					Log.d("pic1",photoPath);
					Bitmap term=null;
					//Bitmap term=BitmapFactory.decodeFile(photoPath);
					//Bitmap bp= BitmapUtils.decodeSampledBitmapFromFd(photoPath,width,height);
					Log.d("pic2","sss");
					int width=term.getWidth()/8;
					int height=term.getHeight()/8;

					//Bitmap bp= BitmapUtils.decodeSampledBitmapFromFd(photoPath,width,height);
					Log.d("pic2",term.toString());
					jsonData.add(BitmapUtils.bitmapToBase64(term));
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}
	}

	//显示缩略图
	@Override
	protected void onResume() {
		super.onResume();
		if (!TextUtils.isEmpty(photoPath)) {
			Bitmap newBp = BitmapUtils.decodeSampledBitmapFromFd(photoPath, 300, 300);
			data.remove(data.size() - 1);
			Bitmap bp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_addpic);
			data.add(newBp);
			data.add(bp);
			//将路径设置为空，防止在手机休眠后返回Activity调用此方法时添加照片
			photoPath = null;
			addPictureAdapter.notifyDataSetChanged();
		}
	}

}
