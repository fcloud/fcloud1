package com.fcloud.weservice.rule.model;


import com.fcloud.core.model.Entity;
import com.fcloud.core.repository.TableOrder;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * 自定义菜单
 *
 * @author
 * @version 1.0 2013-11-12
 */
@DatabaseTable(tableName = "we_menu")
@TableOrder(1001)
public class WeMenu extends Entity {

    /**
     * 自定义菜单JSON
     */
    @DatabaseField(columnName = "fd_json")
    protected String fdJson;

    /**
     * @return 自定义菜单JSON
     */
    public String getFdJson() {
        return fdJson;
    }

    /**
     * @param fdJson 自定义菜单JSON
     */
    public void setFdJson(String fdJson) {
        this.fdJson = fdJson;
    }

    /**
     * 版本号
     */
    @DatabaseField(columnName = "fd_version")
    protected Integer fdVersion;

    /**
     * @return 版本号
     */
    public Integer getFdVersion() {
        return fdVersion;
    }

    /**
     * @param fdVersion 版本号
     */
    public void setFdVersion(Integer fdVersion) {
        this.fdVersion = fdVersion;
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

    /**
     * 相关公众号
     */
    @DatabaseField(foreign = true,columnName = "we_public_id")
    protected WePublic fdWePublic;

    /**
     * @return 相关公众号
     */
    public WePublic getFdWePublic() {
        return fdWePublic;
    }

    /**
     * @param fdWePublic 相关公众号
     */
    public void setFdWePublic(WePublic fdWePublic) {
        this.fdWePublic = fdWePublic;
    }
}
