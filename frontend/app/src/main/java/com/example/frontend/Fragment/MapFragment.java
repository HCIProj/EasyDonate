package com.example.frontend.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.MapView;
import com.example.frontend.R;

public class MapFragment extends Fragment {
    private MapView mapView;
    private AMap aMap;
    private View mapLayout;

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
             return mapLayout;
           }
}
