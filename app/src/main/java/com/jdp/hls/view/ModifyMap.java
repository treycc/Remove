package com.jdp.hls.view;

import com.jdp.hls.model.entiy.RosterDetail;

import java.util.HashMap;

/**
 * Description:TODO
 * Create Time:2018/8/20 0020 上午 11:31
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ModifyMap extends HashMap<Integer, Boolean> {
    private final Integer KEY_ADDRESS = 1;
    private final Integer KEY_COMPANY_NAME = 2;
    private final Integer KEY_REALNAME = 3;
    private final Integer KEY_MOBILE = 4;
    private final Integer KEY_IDCARD = 5;
    private final Integer KEY_GENDER = 6;
    private final Integer KEY_MEASURED = 7;
    private final Integer KEY_EVALUATED = 8;
    private final Integer KEY_IMG_COUNT = 9;
    private final Integer KEY_LOCATION = 10;
    private final Integer KEY_REMARK = 11;
    private final Integer KEY_ASSET_EVALUATED = 12;

    private RosterDetail rosterDetail;

    public void setData(RosterDetail rosterDetail) {
        this.rosterDetail = rosterDetail;
    }

    public boolean hasModified() {
        for (Boolean hasModified : values()) {
            if ( hasModified) {
                return true;
            }
        }
        return false;
    }

    public void setAddress(String address) {
        put(KEY_ADDRESS,!address.equals(rosterDetail.getHouseAddress()));
    }
    public void setCompanyName(String companyName) {
        put(KEY_COMPANY_NAME,!companyName.equals(rosterDetail.getEnterpriseName()));
    }
    public void setRealName(String realName) {
        put(KEY_REALNAME,!realName.equals(rosterDetail.getRealName()));
    }
    public void setMobile(String mobile) {
        put(KEY_MOBILE,!mobile.equals(rosterDetail.getMobilePhone()));
    }
    public void setIdcard(String idcard) {
        put(KEY_IDCARD,!idcard.equals(rosterDetail.getIdcard()));
    }
    public void setGender(boolean gender) {
        put(KEY_GENDER,gender!=rosterDetail.isGender());
    }
    public void setMeasured(boolean measured) {
        put(KEY_MEASURED,measured!=rosterDetail.isMeasured());
    }
    public void setEvaluated(boolean evaluated) {
        put(KEY_EVALUATED,evaluated!=rosterDetail.isEvaluated());
    }

    public void setAssetEvaluated(boolean assetEvaluated) {
        put(KEY_EVALUATED,assetEvaluated!=rosterDetail.isAssetEvaluator());
    }

    public void setImgs() {
        put(KEY_IMG_COUNT,true);
    }
    public void setLocation(double  lng,double lat) {
        put(KEY_LOCATION,!(lng==rosterDetail.getLongitude()&&lat==rosterDetail.getLatitude()));
    }
    public void setRemark(String remark) {
        put(KEY_REMARK,!remark.equals(rosterDetail.getRemark()));
    }
}
