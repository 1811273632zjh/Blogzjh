package com.zjh.blog.domain;

/**
 * @Author zjh
 * @Description 博主信息实体类
 * @date 2019-10-30
 */
public class Blogger {

    private Integer id;
    private String username;    //用户名
    private String passwoed;    //密码
    private String profile;     //博主信息
    private String nickname;    //昵称
    private String sign;        //博主签名
    private String imagename;   //图片路径

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPasswoed() {
        return passwoed;
    }

    public void setPasswoed(String passwoed) {
        this.passwoed = passwoed == null ? null : passwoed.trim();
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile == null ? null : profile.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign == null ? null : sign.trim();
    }

    public String getImagename() {
        return imagename;
    }

    public void setImagename(String imagename) {
        this.imagename = imagename == null ? null : imagename.trim();
    }

    public Blogger() {
    }

    public Blogger(Integer id, String username, String passwoed, String profile, String nickname, String sign, String imagename) {
        this.id = id;
        this.username = username;
        this.passwoed = passwoed;
        this.profile = profile;
        this.nickname = nickname;
        this.sign = sign;
        this.imagename = imagename;
    }

    @Override
    public String toString() {
        return "Blogger{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", passwoed='" + passwoed + '\'' +
                ", profile='" + profile + '\'' +
                ", nickname='" + nickname + '\'' +
                ", sign='" + sign + '\'' +
                ", imagename='" + imagename + '\'' +
                '}';
    }
}