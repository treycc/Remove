package com.jdp.hls.model.entiy;

import java.io.Serializable;
import java.util.List;

/**
 * Description:TODO
 * Create Time:2019/1/21 0021 下午 3:14
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class AreaSupervise implements Serializable {
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

    public AreaSupervise(int level, int regionId, String regionName, int parentId, boolean isSelected) {
        Level = level;
        RegionId = regionId;
        RegionName = regionName;
        ParentId = parentId;
        this.isSelected = isSelected;
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

    public String getParentName() {
        StringBuilder sb = new StringBuilder();
        sb.insert(0, getRegionName());
        if (parent != null) {
            addName(sb, parent);
        }
        return sb.toString();
    }

    private void addName(StringBuilder stringBuilder, AreaSupervise areaSupervise) {
        stringBuilder.insert(0, areaSupervise.getRegionName() + " ");
        if (areaSupervise.getParent() != null) {
            addName(stringBuilder, areaSupervise.getParent());
        }
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
        setExpand(isExpand, false);
    }


    public boolean hasAdded() {
        return checkChildrenAdded(children);
    }

    public boolean checkChildrenAdded(List<AreaSupervise> children) {
        if (children != null) {
            for (AreaSupervise child : children) {
                if (child.isSelected) {
                    return true;
                }
                if (child.children != null) {
                    return checkChildrenAdded(child.children);
                }
            }
        }
        return false;
    }

    public void setExpand(boolean isExpand, boolean isClear) {
        this.isExpand = isExpand;
        if (!isExpand) {
            if (children != null) {
                for (AreaSupervise node : children) {
                    node.setExpand(false);
                    if (isClear) {
                        node.setSelected(false);
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        return "AreaSupervise{" +
                "Level=" + Level +
                ", RegionId=" + RegionId +
                ", RegionName=" + RegionName +
                ", getParentName='" + getParentName() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AreaSupervise)) {
            return false;
        }
        AreaSupervise item = (AreaSupervise) obj;
        return RegionId == item.getRegionId();
    }

    @Override
    public int hashCode() {
        return RegionId;
    }

    public boolean expandable() {
        return !isExpand;
    }
}
