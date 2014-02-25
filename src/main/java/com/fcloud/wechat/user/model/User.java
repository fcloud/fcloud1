package com.fcloud.wechat.user.model;

import com.fcloud.core.model.NamedEntity;
import com.fcloud.core.repository.TableOrder;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author Ruben Fu
 */
@SuppressWarnings("serial")
@TableOrder(8)
@DatabaseTable(tableName = "we_users")
public class User extends NamedEntity {

    /**
     * 密码
     */
    @DatabaseField(columnName = "password", width = 200)
    protected String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 状态 0禁用 1活动
     */
    @DatabaseField(columnName = "status", width = 2)
    protected Integer status = 1;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * Email
     */
    @DatabaseField(columnName = "email", width = 200)
    protected String email;

    /**
     * 电话
     */
    @DatabaseField(columnName = "mobile", width = 20)
    protected String mobile;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @DatabaseField(columnName = "user_level", width = 2)
    protected Integer userLevel = 0; // 当前默认为0，现在暂时不区分用户

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    protected String repeatPwd;

    public String getRepeatPwd() {
        return repeatPwd;
    }

    public void setRepeatPwd(String repeatPwd) {
        this.repeatPwd = repeatPwd;
    }
    //    /**
//     * 用户级别
//     */
//    @DatabaseField(canBeNull = false, foreign = true)
//    protected UserLevel level;
//
//    public UserLevel getLevel() {
//        return level;
//    }
//
//    public void setLevel(UserLevel level) {
//        this.level = level;
//    }
}
