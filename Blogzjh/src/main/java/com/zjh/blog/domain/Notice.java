package com.zjh.blog.domain;

import java.util.Date;

/**
 * @author zjh
 * @date 2019-10-30
 */
public class Notice {

    private static final long serialVersionUID = 1L;
    private Integer id;             //公告id
    private String content;         //公告
    private String noticePublisher; //发布人
    private Date noticeDate;        //发布日期
    private Byte level;             //公告级别（一般：0，重要：1，特别重要：2）

    public Notice() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getNoticePublisher() {
        return noticePublisher;
    }

    public void setNoticePublisher(String noticePublisher) {
        this.noticePublisher = noticePublisher == null ? null : noticePublisher.trim();
    }

    public Date getNoticeDate() {
        return noticeDate;
    }

    public void setNoticeDate(Date noticeDate) {
        this.noticeDate = noticeDate;
    }

    public Byte getLevel() {
        return level;
    }

    public void setLevel(Byte level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", noticePublisher='" + noticePublisher + '\'' +
                ", noticeDate=" + noticeDate +
                ", level=" + level +
                '}';
    }
}