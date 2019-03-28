package com.utils.web;/**
 * Created by lag on 2017/10/25.
 */

import java.io.Serializable;
import java.util.List;

/**
 * @author lag
 * @desc
 * @create 2017-10-25 10:55
 **/
public class Pagination<T> implements Serializable {

    /*查询参数*/
    private int pageNo;//当前页
    private int pageSize;//每页查询条数

    /*总条数*/
    private int count;
    /*记录data*/
    private List<T> data;

    /*自动计算生成*/
    private int preIndex;/*上一页*/
    private int nextIndex;/*下一页*/
    private int pagesCount;/*总页数*/


    public Pagination() {
        this.updateInfo(0, 0, 0);
    }

    public Pagination(int pageNo, int pageSize) {
        this.updateInfo(pageNo, pageSize, null);
    }

    public Pagination(int pageNo, int pageSize, int rowsCount) {
        this.updateInfo(pageNo, pageSize, rowsCount);
    }

    public void setPreIndex(int preIndex) {
        this.preIndex = preIndex;
    }

    public void setNextIndex(int nextIndex) {
        this.nextIndex = nextIndex;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }


    public List<T> getData() {
        return this.data;
    }

    public void setData(List<T> items) {
        this.data = items;
    }

    public int getNextIndex() {
        return this.nextIndex;
    }

    public int getPagesCount() {
        return this.pagesCount;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public int getPreIndex() {
        return this.preIndex;
    }

    public int getCount() {
        return this.count;
    }

    public int getFirstIndex() {
        return 1;
    }

    public int getLastIndex() {
        return this.pagesCount;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    private void updateInfo(int pageNo, int pageSize, Integer rowsCount) {
        if (pageSize > 0) {
            if (rowsCount==null){
                this.pageNo = pageNo;
                this.pageSize = pageSize;
                return;
            }
            this.pageNo = pageNo;
            this.count = rowsCount;
            this.pageSize = pageSize;
            this.pagesCount = (rowsCount + pageSize - 1) / pageSize;
            if (this.pageNo <= 0) {
                this.pageNo = 1;
            }

            if (this.pageNo > this.pagesCount) {
                this.pageNo = this.pagesCount;
            }

            this.nextIndex = this.pageNo + 1;
            if (this.nextIndex > this.pagesCount) {
                this.nextIndex = this.pagesCount;
            }

            this.preIndex = this.pageNo - 1;
            if (this.preIndex <= 0) {
                this.preIndex = 1;
            }
        } else {
            this.preIndex = 1;
            this.pageNo = 1;
            this.nextIndex = 1;
            this.pageSize = 0;
            this.pagesCount = 1;
        }

    }

    public void setCount(int rowsCount) {
        this.updateInfo(this.pageNo, this.pageSize, rowsCount);
    }

    public void setPagesCount(int pagesCount) {
        this.pagesCount = pagesCount;
    }


    /**
     * limit  startNum  , pageSize
     * 分页的第一参数
     *
     * @param pageNo   当前页
     * @param pageSize 每页查询大小
     * @return
     */
    public static int getStartNum(int pageNo, int pageSize) {
        int startNum = 0;
        /*小于1默认第一页*/
        if (pageNo > 1) {
            startNum = (pageNo - 1) * pageSize;
        } else {
            startNum = 0;
        }
        return startNum;
    }
}
