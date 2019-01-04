package cn.xiami.module;

import java.util.List;

/**
 * 用户类
 */
public class User {

    //用户的电话号码，主键唯一
    private String phoneNumber;
    private String password;
    //用户的昵称
    private String nickName;
    //用户自身的描述信息
    private String description;
    //用户创建的歌单
    List<Category> categories;

    public User() {

    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
