package com.example.frontend.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.example.frontend.DonateActivity;
import com.example.frontend.HelpFunctions.HttpHandler;
import com.example.frontend.HelpFunctions.UserData;
import com.example.frontend.HelpFunctions.WeiboDialogUtils;
import com.example.frontend.LoginActivity;
import com.example.frontend.R;
import com.example.frontend.RecommendActivity;

public class MapFragment extends Fragment {
    private MapView mapView;
    private AMap aMap;
    private View mapLayout;
    private Dialog mWeiboDialog;
    private Button recommend;
    //初始化地图控制器对象

    private Vibrator vibrator;

    //定位需要的数据
    LocationSource.OnLocationChangedListener mListener;
    AMapLocationClient mlocationClient;
    AMapLocationClientOption mLocationOption;

    //定位蓝点
    MyLocationStyle myLocationStyle;

    //是否第一次定位
    boolean isFirstLocate=true;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,

        Bundle savedInstanceState) {
             if (mapLayout == null) {
                   //Log.i("sys", "MF onCreateView() null");
                   mapLayout = inflater.inflate(R.layout.activity_map, null);
                   mapView = (MapView) mapLayout.findViewById(R.id.map);
                   mapView.onCreate(savedInstanceState);
                   if (aMap == null) {
                         aMap = mapView.getMap();
                       }
                 }else {
                   if (mapLayout.getParent() != null) {
                         ((ViewGroup) mapLayout.getParent()).removeView(mapLayout);
                       }
                 }
        recommend=mapLayout.findViewById(R.id.recommend);
        recommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //定义一个自定义对话框
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                builder.setTitle("输入你要捐赠的物资的信息");//设置标题

                View view2 = LayoutInflater.from(getActivity()).inflate(R.layout.my_dialog3,null);//获得布局信息

                final EditText rec1 = (EditText) view2.findViewById(R.id.rec1);
                final EditText rec2 = (EditText) view2.findViewById(R.id.rec1);
                final EditText rec3 = (EditText) view2.findViewById(R.id.rec1);
                final EditText rec4 = (EditText) view2.findViewById(R.id.rec1);
                final EditText rec5 = (EditText) view2.findViewById(R.id.rec1);



                builder.setView(view2);//给对话框设置布局

                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        int a=0,b=0,c=0,d=0;
                        if(rec1.getText()!=null&&!rec1.getText().toString().isEmpty())
                            a=Integer.valueOf(rec1.getText().toString());
                        if(rec2.getText()!=null&&!rec2.getText().toString().isEmpty())
                            b=Integer.valueOf(rec2.getText().toString());
                        if(rec3.getText()!=null&&!rec3.getText().toString().isEmpty())
                            c=Integer.valueOf(rec3.getText().toString());
                        if(rec4.getText()!=null&&!rec4.getText().toString().isEmpty())
                            d=Integer.valueOf(rec4.getText().toString());
                        UserData.recommendOrgLock=true;
                        HttpHandler.getRecommendOrg(getActivity(),a,b,c,d);
                        while(UserData.recommendOrgLock)
                        {
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        Intent intent=new Intent(getActivity(), RecommendActivity.class);

                        getActivity().startActivity(intent);
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
        //设置初始点位
        LatLng latLng = new LatLng(31.02228,121.442316);//构造一个位置
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,16));
        Log.d("siez",UserData.orgList.size()+"");
        for(int i=0;i< UserData.orgList.size();i++) {
            View markerView = ViewGroup.inflate(getActivity(), R.layout.marker, null);
            TextView tv_endphone = markerView.findViewById(R.id.tv_endphone);
            ImageView img_marker = markerView.findViewById(R.id.img_marker);
            tv_endphone.setText( ((UserData.org)UserData.orgList.get(i)).orgName);
            MarkerOptions icon = new MarkerOptions().icon(BitmapDescriptorFactory.fromView(markerView));
            icon.position(new LatLng(((UserData.org)UserData.orgList.get(i)).latitude,
                    ((UserData.org)UserData.orgList.get(i)).longitude));//经纬度（左边纬度，右边经度）
           icon.draggable(true);//是否平铺，这里设置为平铺

            icon.snippet(String.valueOf(((UserData.org)UserData.orgList.get(i)).id));
            //icon.
            aMap.addMarker(icon);

            //参数依次是：视角调整区域的中心点坐标、希望调整到的缩放级别、俯仰角0°~45°（垂直与地图时为0）、偏航角 0~360° (正北方为0)




            Log.d("siessz",i+"");
            /*MarkerOptions markerOption = new MarkerOptions();
            markerOption.position(new LatLng(((UserData.request)UserData.requestList.get(i)).latitude,
                    ((UserData.request)UserData.requestList.get(i)).longitude));//经纬度（左边纬度，右边经度）
            markerOption.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_add_location_red_36dp));//自定义标点的图片
            markerOption.title(((UserData.request)UserData.requestList.get(i)).orgName).snippet("点击查看详情");
            markerOption.draggable(true);//是否平铺，这里设置为平铺
            aMap.addMarker(markerOption);*/
            //marker.setObject("121");
            //aMap.addMarker(markerOptions);
            //aMap.moveCamera(CameraUpdateFactory.changeLatLng(latLng));
        }
        aMap.setOnCameraChangeListener(new AMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {
                    if(cameraPosition.zoom<16.0f){
                        //Toast.makeText(getActivity(), "当前比例尺不再显示机构", Toast.LENGTH_SHORT).show();
                        aMap.clear();
                    }
               else{
                        //Toast.makeText(getActivity(), "开始显示机构", Toast.LENGTH_SHORT).show();
                    for(int i=0;i< UserData.orgList.size();i++) {
                        View markerView = ViewGroup.inflate(getActivity(), R.layout.marker, null);
                        TextView tv_endphone = markerView.findViewById(R.id.tv_endphone);
                        ImageView img_marker = markerView.findViewById(R.id.img_marker);
                        tv_endphone.setText( ((UserData.org)UserData.orgList.get(i)).orgName);
                        MarkerOptions icon = new MarkerOptions().icon(BitmapDescriptorFactory.fromView(markerView));
                        icon.position(new LatLng(((UserData.org)UserData.orgList.get(i)).latitude,
                                ((UserData.org)UserData.orgList.get(i)).longitude));//经纬度（左边纬度，右边经度）
                        icon.draggable(true);//是否平铺，这里设置为平铺

                        icon.snippet(String.valueOf(((UserData.org)UserData.orgList.get(i)).id));
                        //icon.
                        aMap.addMarker(icon);
                    }
                    aMap.notify();
                }
            }
            @Override
            public void onCameraChangeFinish(CameraPosition cameraPosition) {
                Log.v("test","当前地图缩放级别: " + cameraPosition.zoom);
            }
        });
        aMap.setOnMarkerClickListener(new AMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
               // aMap.
                //marker.getTitle()
                UserData.targetOrgLock=true;
                Log.d("allreq","ss");
                int orgid=UserData.getOrgId(marker.getPosition().longitude,marker.getPosition().latitude);
                if(orgid==UserData.orgId) {
                    Toast.makeText(getActivity(), "不能给自己捐赠", Toast.LENGTH_SHORT).show();
                    return false;
                }
                Log.d("orgid", String.valueOf(orgid));
                //HttpHandler.getReq(getActivity(),UserData.username);
                //marker.setSnippet();
                HttpHandler.getTargetReq(getActivity(),orgid);
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
                    return false;
                Intent intent=new Intent(getActivity(), DonateActivity.class);

                getActivity().startActivity(intent);
                return true;
            }
        });
        /*aMap.setLocationSource(getActivity());
        // 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        aMap.setMyLocationEnabled(true);
        //设置地图的放缩级别
        aMap.moveCamera(CameraUpdateFactory.zoomTo(19));
        // 设置定位监听
        aMap.setLocationSource(this);
        // 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        aMap.setMyLocationEnabled(true);
        // 设置定位的类型为定位模式，有定位、跟随或地图根据面向方向旋转几种
        aMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);*/
             return mapLayout;
           }
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

}
