package com.jdp.hls.map;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.model.Marker;
import com.jdp.hls.R;
import com.jdp.hls.model.entiy.Roster;

/**
 * Description:TODO
 * Create Time:2018/8/14 0014 下午 5:31
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class KMapInfoWindowAdapter implements AMap.InfoWindowAdapter {
    private final Context context;
    private final Roster roster;
    private View infoWindow = null;


    public KMapInfoWindowAdapter(Context context, Roster roster) {
        this.context = context;
        this.roster = roster;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        if (infoWindow == null) {
            infoWindow = LayoutInflater.from(context).inflate(
                    R.layout.map_info_window, null);
        }
        TextView tv_name = infoWindow.findViewById(R.id.tv_name);
        TextView tv_address = infoWindow.findViewById(R.id.tv_address);
        tv_name.setText(roster.getRealName());
        tv_address.setText(roster.getHouseAddress());
        return infoWindow;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }
}
