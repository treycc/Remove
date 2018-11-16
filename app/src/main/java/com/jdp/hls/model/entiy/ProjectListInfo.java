package com.jdp.hls.model.entiy;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/11/16 0016 上午 11:27
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ProjectListInfo {

    /**
     * IsAllowView : true
     * IsAllowEdit : true
     * LstProject : [{"ProjectId":"e10adc39-49ba-59ab-be56-e057f20f873a","ProjectNum":3,
     * "ProjectName":"浙江省温州市龙湾区三垟湿地，大龙街道200号-3","ProjectSysCode":"2010-abc-P3","Year":"2010","ProvinceId":330000,
     * "CityId":330300,"AreaId":330303,"StreetId":33030302,"Address":"浙江省温州市龙湾区三垟湿地，大龙街道200号-3","AreaRange":"132",
     * "Remark":"123","RealName":"杰森2,2号","ProjectEmployeeIDs":"1#2","ProjectEmployeeName":"杰森2,2号",
     * "ProvinceName":"浙江省","CityName":"温州市","AreaName":"龙湾区","StreetName":"沙城","IsAllowView":true,"IsAllowEdit":true}]
     */

    private boolean IsAllowView;
    private boolean IsAllowEdit;
    private List<ProjectItem> LstProject;

    public boolean isIsAllowView() {
        return IsAllowView;
    }

    public void setIsAllowView(boolean IsAllowView) {
        this.IsAllowView = IsAllowView;
    }

    public boolean isIsAllowEdit() {
        return IsAllowEdit;
    }

    public void setIsAllowEdit(boolean IsAllowEdit) {
        this.IsAllowEdit = IsAllowEdit;
    }

    public List<ProjectItem> getLstProject() {
        return LstProject;
    }

    public void setLstProject(List<ProjectItem> LstProject) {
        this.LstProject = LstProject;
    }

}
