package com.zjh.blog.domain;

/**
 * 
 * 
 * @author wcyong
 * 
 * @date 2019-10-30
 */
public class Great {

    private Integer id; //点赞id
    private String userIp;//点赞的用户ip
    private Integer imageId;//点赞的图片id

    public Great(Integer id, String userIp, Integer imageId) {
        super();
        this.id = id;
        this.userIp = userIp;
        this.imageId = imageId;
    }

    public Great() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp == null ? null : userIp.trim();
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    @Override
    public String toString() {
        return "Great{" +
                "id=" + id +
                ", userIp='" + userIp + '\'' +
                ", imageId=" + imageId +
                '}';
    }
}