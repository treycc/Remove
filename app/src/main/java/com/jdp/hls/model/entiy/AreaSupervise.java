package com.jdp.hls.model.entiy;

import java.io.Serializable;
import java.util.List;

/**
 * Description:TODO
 * Create Time:2019/1/21 0021 下午 3:14
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class AreaSupervise implements Serializable{
    private List<AreaSupervise> children;
    private boolean isSelected;
    private boolean isExpand;
    private AreaSupervise parent;

    private int Level;
    private int RegionId;
    private String RegionName;
    private int ParentId;

    public AreaSupervise() {
    }

    public AreaSupervise(int level, int regionId, String regionName, int parentId) {
        Level = level;
        RegionId = regionId;
        RegionName = regionName;
        ParentId = parentId;
    }

    public int getLevel() {
        return Level;
    }

    public void setLevel(int level) {
        Level = level;
    }

    public int getRegionId() {
        return RegionId;
    }

    public void setRegionId(int regionId) {
        RegionId = regionId;
    }

    public int getParentId() {
        return ParentId;
    }

    public void setParentId(int parentId) {
        ParentId = parentId;
    }

    public String getRegionName() {
        return null == RegionName ? "" : RegionName;
    }

    public void setRegionName(String regionName) {
        RegionName = regionName;
    }

    public List<AreaSupervise> getChildren() {
        return children;
    }

    public void setChildren(List<AreaSupervise> children) {
        this.children = children;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public AreaSupervise getParent() {
        return parent;
    }

    public void setParent(AreaSupervise parent) {
        this.parent = parent;
    }

    public boolean isRootNode() {
        return parent == null;
    }
    public boolean isParentExpand() {
        if (parent == null)
            return false;
        return parent.isExpand();
    }

    public boolean isLeaf() {
        return children.size() == 0;
    }

    public boolean isExpand() {
        return isExpand;
    }

    public void setExpand(boolean isExpand) {
        this.isExpand = isExpand;
        if (!isExpand) {
            if (children != null) {
                for (AreaSupervise node : children) {
                    node.setExpand(false);
                    node.setSelected(false);
                }
            }
        }
    }

    public boolean hasAdded() {
        return isSelected|| checkChildrenAdded(children);
    }

    public boolean checkChildrenAdded(List<AreaSupervise> children) {
        if (children != null) {
            for (AreaSupervise child : children) {
                if (child.isSelected) {
                    return true;
                }
                if (child.children != null) {
                    return  checkChildrenAdded(children);
                }


            }
        }
        return false;


    }
}
