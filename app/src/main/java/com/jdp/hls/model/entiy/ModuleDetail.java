package com.jdp.hls.model.entiy;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/11/15 0015 下午 2:07
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ModuleDetail {
    private String Title;
    private String ImageUrl;
    private boolean IsVisible;
    private List<Module>Modules;

    public boolean isVisible() {
        return IsVisible;
    }

    public void setVisible(boolean visible) {
        IsVisible = visible;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public List<Module> getModules() {
        return Modules;
    }

    public void setModules(List<Module> modules) {
        Modules = modules;
    }
}
