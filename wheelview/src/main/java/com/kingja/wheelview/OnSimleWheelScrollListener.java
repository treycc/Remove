package com.kingja.wheelview;

/**
 * Description:TODO
 * Create Time:2018/11/20 0020 下午 2:13
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public abstract class OnSimleWheelScrollListener implements OnWheelScrollListener {
    @Override
    public void onScrollingStarted(WheelView wheel) {

    }

    @Override
    public abstract void onScrollingFinished(WheelView wheel);
}
