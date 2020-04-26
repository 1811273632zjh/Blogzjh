package com.zjh.blog.domain;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.SplittableRandom;

/**
 * @author zjh
 * 
 * @date 2019-10-30
 */
public class Blog {
    private Integer id;         //id
    private String title;       //标题
    private String summary;     //摘要
    private Date releasedate;   //发布日期，最近修改日期
    private Integer clickhit;   //阅读次数
    private Integer replyhit;   //回复次数
    private String content;     //内容
    private String keyword;     //关键字，空格隔开
    private Integer typeId;     //博客类型id



    private String contentNoTag;  //博客内容

    private BlogType blogType; //博客类型
    private Integer blogCount; //博客数量，非博客实际属性，用于根据发布日期归档查询
    private String releaseDateStr;  //发布日期的字符串，只取年月

    private List<String> imageList = new LinkedList<String>();// 博客里存的图片，主要用于展示缩略图

    public BlogType getBlogType() {
        return blogType;
    }

    public void setBlogType(BlogType blogType) {
        this.blogType = blogType;
    }

    public Integer getBlogCount() {
        return blogCount;
    }

    public void setBlogCount(Integer blogCount) {
        this.blogCount = blogCount;
    }

    public String getReleaseDateStr() {
        return releaseDateStr;
    }

    public void setReleaseDateStr(String releaseDateStr) {
        this.releaseDateStr = releaseDateStr;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    public Date getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(Date releasedate) {
        this.releasedate = releasedate;
    }

    public Integer getClickhit() {
        return clickhit;
    }

    public void setClickhit(Integer clickhit) {
        this.clickhit = clickhit;
    }

    public Integer getReplyhit() {
        return replyhit;
    }

    public void setReplyhit(Integer replyhit) {
        this.replyhit = replyhit;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getContentNoTag() {
        return contentNoTag;
    }

    public void setContentNoTag(String contentNoTag) {
        this.contentNoTag = contentNoTag;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", releasedate=" + releasedate +
                ", clickhit=" + clickhit +
                ", replyhit=" + replyhit +
                ", content='" + content + '\'' +
                ", keyword='" + keyword + '\'' +
                ", typeId=" + typeId +
                ", contentNoTag='" + contentNoTag + '\'' +
                ", blogType=" + blogType +
                ", blogCount=" + blogCount +
                ", releaseDateStr='" + releaseDateStr + '\'' +
                ", imageList=" + imageList +
                '}';
    }

    //实现自然排序接口 并且默认以回复量排序 当前元素相等时强制返回前面大

    public int compareTo(Blog blogTwo) {
        if (this.replyhit > blogTwo.replyhit)
            return - 1;
        if (this.replyhit < blogTwo.replyhit)
            return 1;
        return 0;
    }

}