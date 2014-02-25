package com.fcloud.weservice.rule.model;


import com.fcloud.core.model.Entity;
import com.fcloud.core.repository.TableOrder;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * OpenID
 *
 * @author
 * @version 1.0 2013-11-12
 */
@TableOrder(1001)
@DatabaseTable(tableName = "we_openid")
public class WeOpenid extends Entity {

	/**
	 * OpenID
	 */
    @DatabaseField(columnName = "fd_openid")
	protected String fdOpenid;

	/**
	 * @return OpenID
	 */
	public String getFdOpenid() {
		return fdOpenid;
	}

	/**
	 * @param fdOpenid OpenID
	 */
	public void setFdOpenid(String fdOpenid) {
		this.fdOpenid = fdOpenid;
	}

	/**
	 * 相关公众号
	 */
    @DatabaseField(columnName = "fd_wepublic",foreign = true)
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
