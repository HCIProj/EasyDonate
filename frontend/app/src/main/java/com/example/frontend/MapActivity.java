package com.example.frontend;

import android.location.Location;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.example.frontend.HelpFunctions.UserData;
import com.example.frontend.R;

public class MapActivity extends AppCompatActivity implements LocationSource, AMapLocationListener,AMap.OnMapClickListener {

    private MapView mapView= null;
    private AMap aMap;
    private View mapLayout;
    //初始化地图控制器对象
    private Button endButton;
   // private Vibrator vibrator;

    //定位需要的数据
    LocationSource.OnLocationChangedListener mListener;
    AMapLocationClient mlocationClient;
    AMapLocationClientOption mLocationOption;

    //定位蓝点
    MyLocationStyle myLocationStyle;

    //是否第一次定位
    boolean isFirstLocate=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_map);
        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        if (aMap == null) {
            aMap = mapView.getMap();
        }
        endButton=(Button)findViewById(R.id.act_map_endButton);

        //设置初始点位
        LatLng latLng = new LatLng(31.02228,121.442316);//构造一个位置
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,16));

        //设置地图的放缩级别
        aMap.moveCamera(CameraUpdateFactory.zoomTo(19));
        // 设置定位监听
        aMap.setLocationSource(MapActivity.this);
        // 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        aMap.setMyLocationEnabled(true);
        // 设置定位的类型为定位模式，有定位、跟随或地图根据面向方向旋转几种
        aMap.setMyLocationType(AMap.MAP_TYPE_SATELLITE);
        aMap.setOnMapClickListener(this);

        //蓝点初始化
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        //aMap.getUiSettings().setMyLocationButtonEnabled(true);设置默认定位按钮是否显示，非必需设置。
        aMap.getUiSettings().setZoomControlsEnabled(false);
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）默认执行此种模式。


        myLocationStyle.showMyLocation(true);

        aMap.setOnMyLocationChangeListener(new AMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                //从location对象中获取经纬度信息，地址描述信息，建议拿到位置之后调用逆地理编码接口获取
                UserData.setLongAndLati(location.getLongitude(),location.getLatitude());
                Log.d("location",location.getLongitude()+"");
                MarkerOptions markerOption = new MarkerOptions();
                markerOption.position(new LatLng(location.getLongitude(), location.getLatitude()));//经纬度（左边纬度，右边经度）
                markerOption.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_add_location_red_36dp));//自定义标点的图片
                markerOption.title("选择地点").snippet("1");
                markerOption.draggable(true);//是否平铺，这里设置为平铺
                Marker marker = aMap.addMarker(markerOption);
                marker.setObject("121");

            }
        });
        endButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                MapActivity.this.finish();
            }
        });
    }

    @Override
    public void onMapClick(LatLng latLng) {
        aMap.clear();

        UserData.setLongAndLati(latLng.longitude,latLng.latitude);

        Log.d("2location",latLng.longitude+"");
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_add_location_red_36dp));
        markerOptions.position(latLng);
        aMap.addMarker(markerOptions);
        aMap.moveCamera(CameraUpdateFactory.changeLatLng(latLng));

        /*
        UserData.setLongAndLati(location.getLongitude(),location.getLatitude());
        Log.d("location",location.getLongitude()+"");
        MarkerOptions markerOption = new MarkerOptions();
        markerOption.position(new LatLng(location.getLongitude(), location.getLatitude()));//经纬度（左边纬度，右边经度）
        markerOption.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_add_location_red_36dp));//自定义标点的图片
        markerOption.title("选择地点").snippet("1");
        markerOption.draggable(true);//是否平铺，这里设置为平铺
        Marker marker = aMap.addMarker(markerOption);
        marker.setObject("121");*/
    }
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {

    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {

    }

    @Override
    public void deactivate() {

    }
}