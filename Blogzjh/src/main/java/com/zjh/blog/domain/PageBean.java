package com.zjh.blog.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther：zjh
 * @Description：分页bean封装实体类
 * @Data：2019/11/11 15:50
 * Version 1.0
 */
public class PageBean<T> {
    private int currPage; // 当前页数
    private int pageSize; // 每页显示的个数
    private long total; // 总记录数
    private int start; // limit（start,end）
    private int end;
    private List<T> result; // 分页查询的结果
    private long count ; //总页数
    private Map<String, Object> map = new HashMap<String, Object>(); // 查询条件

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public long getCount() {
        count = total % pageSize==0 ? total/pageSize : total/pageSize+1;
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public PageBean() {
    }

    public PageBean(int currPage, int pageSize) {
        this.currPage = currPage;
        this.pageSize = pageSize;
    }



}
