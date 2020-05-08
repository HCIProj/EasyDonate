package com.example.frontend.Fragment;

import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.example.frontend.R;

public class MapFragment extends Fragment {
    private MapView mapView;
    private AMap aMap;
    private View mapLayout;
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

        //设置初始点位
       /* LatLng latLng = new LatLng(31.02228,121.442316);//构造一个位置
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,16));

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

}
