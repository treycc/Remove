package com.jdp.hls.model.entiy;

/**
 * Description:TODO
 * Create Time:2018/11/15 0015 下午 2:11
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class PageInfo {
    private int PageIndex;
    private int PageSize;
    private int NumRecord;
    private int PageCount;

    public int getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(int pageIndex) {
        PageIndex = pageIndex;
    }

    public int getPageSize() {
        return PageSize;
    }

    public void setPageSize(int pageSize) {
        PageSize = pageSize;
    }

    public int getNumRecord() {
        return NumRecord;
    }

    public void setNumRecord(int numRecord) {
        NumRecord = numRecord;
    }

    public int getPageCount() {
        return PageCount;
    }

    public void setPageCount(int pageCount) {
        PageCount = pageCount;
    }
}
