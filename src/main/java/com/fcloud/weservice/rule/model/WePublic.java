package com.fcloud.weservice.rule.model;

import com.fcloud.core.model.Entity;
import com.fcloud.core.repository.TableOrder;
import com.fcloud.wechat.user.model.User;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * 公众号
 *
 * @author
 * @version 1.0 2013-11-12
 */
@TableOrder(1000)
@DatabaseTable(tableName = "we_public")
public class WePublic extends Entity {

    /**
     * 微信号
     */
    @DatabaseField(columnName = "fd_public")
    protected String fdPublic;

    /**
     * @return 微信号
     */
    public String getFdPublic() {
        return fdPublic;
    }

    /**
     * @param fdPublic 微信号
     */
    public void setFdPublic(String fdPublic) {
        this.fdPublic = fdPublic;
    }

    /**
     * 昵称
     */
    @DatabaseField(columnName = "fd_name")
    protected String fdName;

    /**
     * @return 昵称
     */
    public String getFdName() {
        return fdName;
    }

    /**
     * @param fdName 昵称
     */
    public void setFdName(String fdName) {
        this.fdName = fdName;
    }

    /**
     * 登录邮箱
     */
    @DatabaseField(columnName = "fd_email")
    protected String fdEmail;

    /**
     * @return 登录邮箱
     */
    public String getFdEmail() {
        return fdEmail;
    }

    /**
     * @param fdEmail 登录邮箱
     */
    public void setFdEmail(String fdEmail) {
        this.fdEmail = fdEmail;
    }

    /**
     * 类型
     */
    @DatabaseField(columnName = "fd_ptype")
    protected Integer fdPtype;

    /**
     * @return 类型
     */
    public Integer getFdPtype() {
        return fdPtype;
    }

    /**
     * @param fdPtype 类型
     */
    public void setFdPtype(Integer fdPtype) {
        this.fdPtype = fdPtype;
    }

    /**
     * 国家
     */
    @DatabaseField(columnName = "fd_country")
    protected String fdCountry;

    /**
     * @return 国家
     */
    public String getFdCountry() {
        return fdCountry;
    }

    /**
     * @param fdCountry 国家
     */
    public void setFdCountry(String fdCountry) {
        this.fdCountry = fdCountry;
    }

    /**
     * 省市区
     */
    @DatabaseField(columnName = "fd_area")
    protected String fdArea;

    /**
     * @return 省市区
     */
    public String getFdArea() {
        return fdArea;
    }

    /**
     * @param fdArea 省市区
     */
    public void setFdArea(String fdArea) {
        this.fdArea = fdArea;
    }

    /**
     * 功能介绍
     */
    @DatabaseField(columnName = "fd_info")
    protected String fdInfo;

    /**
     * @return 功能介绍
     */
    public String getFdInfo() {
        return fdInfo;
    }

    /**
     * @param fdInfo 功能介绍
     */
    public void setFdInfo(String fdInfo) {
        this.fdInfo = fdInfo;
    }

    /**
     * 头像
     */
    @DatabaseField(columnName = "fd_pic")
    protected String fdPic;

    /**
     * @return 头像
     */
    public String getFdPic() {
        return fdPic;
    }

    /**
     * @param fdPic 头像
     */
    public void setFdPic(String fdPic) {
        this.fdPic = fdPic;
    }

    /**
     * 二维码
     */
    @DatabaseField(columnName = "fd_code")
    protected String fdCode;

    /**
     * @return 二维码
     */
    public String getFdCode() {
        return fdCode;
    }

    /**
     * @param fdCode 二维码
     */
    public void setFdCode(String fdCode) {
        this.fdCode = fdCode;
    }

    /**
     * 接口URL
     */
    @DatabaseField(columnName = "fd_int_url")
    protected String fdIntUrl;

    /**
     * @return 接口URL
     */
    public String getFdIntUrl() {
        return fdIntUrl;
    }

    /**
     * @param fdIntUrl 接口URL
     */
    public void setFdIntUrl(String fdIntUrl) {
        this.fdIntUrl = fdIntUrl;
    }

    /**
     * 接口Token
     */
    @DatabaseField(columnName = "fd_int_token")
    protected String fdIntToken;

    /**
     * @return 接口Token
     */
    public String getFdIntToken() {
        return fdIntToken;
    }

    /**
     * @param fdIntToken 接口Token
     */
    public void setFdIntToken(String fdIntToken) {
        this.fdIntToken = fdIntToken;
    }

    /**
     * AppId
     */
    @DatabaseField(columnName = "fd_app_id")
    protected String fdAppId;

    /**
     * @return AppId
     */
    public String getFdAppId() {
        return fdAppId;
    }

    /**
     * @param fdAppId AppId
     */
    public void setFdAppId(String fdAppId) {
        this.fdAppId = fdAppId;
    }

    /**
     * AppSecret
     */
    @DatabaseField(columnName = "fd_app_secret")
    protected String fdAppSecret;

    /**
     * @return AppSecret
     */
    public String getFdAppSecret() {
        return fdAppSecret;
    }

    /**
     * @param fdAppSecret AppSecret
     */
    public void setFdAppSecret(String fdAppSecret) {
        this.fdAppSecret = fdAppSecret;
    }

    /**
     * 创建时间
     */
    @DatabaseField(columnName = "fd_createtime")
    protected Date fdCreatetime;

    /**
     * @return 创建时间
     */
    public Date getFdCreatetime() {
        return fdCreatetime;
    }

    /**
     * @param fdCreatetime 创建时间
     */
    public void setFdCreatetime(Date fdCreatetime) {
        this.fdCreatetime = fdCreatetime;
    }

    @DatabaseField(columnName = "user",foreign = true)
    protected User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
