package com.zjh.blog.domain;

/**
 * @author zjh
 * @Description：友链实体类
 * @date 2019-10-30
 */
public class Link {

    private Integer id;         //id
    private String linkName;    //友链名称
    private String linkUrl;     //友链地址
    private Integer orderNum;   //订单数量

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName == null ? null : linkName.trim();
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl == null ? null : linkUrl.trim();
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }
}