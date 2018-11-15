package com.jdp.hls.model.entiy;

/**
 * Description:TODO
 * Create Time:2018/11/15 0015 下午 1:58
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class Module {

    /**
     * ModuleId : 5
     * ModuleName : 征收业务系统
     * ModuleDesc : 征收业务系统描述
     * IconUrl : Content/SysIcon/ic_levy_system.png
     * Sort : 4
     */

    private int ModuleId;
    private String ModuleName;
    private String ModuleDesc;
    private String IconUrl;
    private int Sort;

    public int getModuleId() {
        return ModuleId;
    }

    public void setModuleId(int ModuleId) {
        this.ModuleId = ModuleId;
    }

    public String getModuleName() {
        return ModuleName;
    }

    public void setModuleName(String ModuleName) {
        this.ModuleName = ModuleName;
    }

    public String getModuleDesc() {
        return ModuleDesc;
    }

    public void setModuleDesc(String ModuleDesc) {
        this.ModuleDesc = ModuleDesc;
    }

    public String getIconUrl() {
        return IconUrl;
    }

    public void setIconUrl(String IconUrl) {
        this.IconUrl = IconUrl;
    }

    public int getSort() {
        return Sort;
    }

    public void setSort(int Sort) {
        this.Sort = Sort;
    }
}
