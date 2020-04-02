package com.zjh.blog.domain;

import java.util.Date;

/**
 * @Auther：zjh
 * @Description：
 * @Data：2020/2/27 10:59
 * Version 1.0
 */
public class Picture {

    private Integer id; // 图片id
    private String url; // 图片地址
    private String name; // 图片名称
    private String publisher; // 图片发布人
    private Date date; // 图片更新日期
    private String dateStr;
    private Integer click; // 点赞数

    public Picture() {

    }

    public Picture(Integer id, String url, String name, String publisher, Date date, String dateStr, Integer click) {
        super();
        this.id = id;
        this.url = url;
        this.name = name;
        this.publisher = publisher;
        this.date = date;
        this.dateStr = dateStr;
        this.click = click;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public Integer getClick() {
        return click;
    }

    public void setClick(Integer click) {
        this.click = click;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", publisher='" + publisher + '\'' +
                ", dateStr='" + dateStr + '\'' +
                ", click=" + click +
                '}';
    }
}
