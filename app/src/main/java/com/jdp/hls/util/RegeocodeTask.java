package com.jdp.hls.util;

import android.content.Context;
import android.util.Log;

import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;

/**
 * Description:TODO
 * Create Time:2018/7/25 0025 上午 11:40
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class RegeocodeTask implements GeocodeSearch.OnGeocodeSearchListener {
    private static final float SEARCH_RADIUS = 50;
    private static final String TAG = "RegeocodeTask";
    private OnLocationGetListener mOnLocationGetListener;

    private GeocodeSearch mGeocodeSearch;

    private double lat = 0.0;
    private double lng = 0.0;

    public RegeocodeTask(Context context) {
        mGeocodeSearch = new GeocodeSearch(context);
        mGeocodeSearch.setOnGeocodeSearchListener(this);
    }

    public void search(double latitude, double longitude) {
        Log.e(TAG, "search: " );
        lat = latitude;
        lng = longitude;
        RegeocodeQuery regecodeQuery = new RegeocodeQuery(new LatLonPoint(
                latitude, longitude), SEARCH_RADIUS, GeocodeSearch.AMAP);
        mGeocodeSearch.getFromLocationAsyn(regecodeQuery);
    }

    public void setOnLocationGetListener(
            OnLocationGetListener onLocationGetListener) {
        mOnLocationGetListener = onLocationGetListener;
    }

    @Override
    public void onGeocodeSearched(GeocodeResult arg0, int arg1) {

    }

    @Override
    public void onRegeocodeSearched(RegeocodeResult regeocodeReult,
                                    int resultCode) {
        Log.e(TAG, "resultCode: "+resultCode );
        if (resultCode == AMapException.CODE_AMAP_SUCCESS) {
            if (regeocodeReult != null
                    && regeocodeReult.getRegeocodeAddress() != null
                    && mOnLocationGetListener != null) {
                String address = regeocodeReult.getRegeocodeAddress()
                        .getFormatAddress();
                String city = regeocodeReult.getRegeocodeAddress().getCity();

                PositionEntity entity = new PositionEntity();
                entity.address = address;
                entity.city = city;
                entity.latitue = lat;
                entity.longitude = lng;
                mOnLocationGetListener.onRegecodeGet(entity);

            }
        }
        //TODO 可以根据app自身需求对查询错误情况进行相应的提示或者逻辑处理
    }

   public class PositionEntity {

        public double latitue;
        public double longitude;
        public String address;
        public String city;

        public PositionEntity() {
        }

        public PositionEntity(double latitude, double longtitude, String address, String city) {
            this.latitue = latitude;
            this.longitude = longtitude;
            this.address = address;
            this.city = city;
        }

    }

    public interface OnLocationGetListener {

        public void onLocationGet(PositionEntity entity);

        public void onRegecodeGet(PositionEntity entity);
    }
}
